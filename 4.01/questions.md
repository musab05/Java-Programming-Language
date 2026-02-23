## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between `start()` and `run()`?

**Answer:** This is a classic "trap" question.

* **`start()`**: Tells the JVM to create a brand-new call stack and allocate system resources for a new thread. Once the thread is ready, the JVM calls the `run()` method *on that new stack*.
* **`run()`**: Simply executes the code in the current thread. If you call `run()` directly, you aren't multithreading; you’re just making a normal, blocking method call on the main thread.

### 2. What is a "Race Condition" and how do you detect one?

**Answer:** A race condition occurs when the outcome of a program depends on the unpredictable timing of thread execution. For example, if two threads try to do `count++` at the exact same time, they might both read the value `10`, increment it, and write back `11`. The count should be `12`, but one increment was lost. You detect them by looking for shared mutable state that isn't protected by `synchronized` or `Lock` blocks.

### 3. What does the `volatile` keyword actually do?

**Answer:** In modern CPUs, each core has its own cache. A thread might update a variable in its CPU cache, but another thread on a different core won't see that update immediately. Marking a variable `volatile` forces the JVM to always read and write that variable directly from the **Main Memory**, ensuring all threads see the most up-to-date value. It solves **visibility** issues, but it does *not* solve atomicity issues (it won't fix `count++`).

### 4. What is a "Deadlock" and how can you prevent it?

**Answer:** A deadlock is a "deadly embrace" where Thread 1 holds Lock A and waits for Lock B, while Thread 2 holds Lock B and waits for Lock A. Neither can ever move.

* **Prevention:** Always acquire locks in a **consistent order**. If every thread always tries to get Lock A then Lock B, a circular wait is impossible.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The `join()` Maneuver

**Task:** You have a `Thread worker`. Write the line of code that ensures the `main` thread waits for `worker` to finish before printing "Task Complete".
**Solution:**

```java
worker.start();
worker.join(); // The main thread pauses right here until worker finishes
System.out.println("Task Complete");

```

### Challenge 2: The Atomic Upgrade

**Task:** Replace this thread-unsafe code with the high-performance `AtomicInteger` version.

```java
// Unsafe
int count = 0;
public void inc() { count++; }

```

**Solution:**

```java
// Safe and High-Performance
AtomicInteger count = new AtomicInteger(0);
public void inc() { count.incrementAndGet(); }

```

---

## 📝 Practice Problems

Multithreading is best learned through scenarios. These problems test your ability to prevent data corruption and coordinate timing.

### Problem 1: The Bank Account Crisis (Synchronization)

You are building a banking app. Two people (threads) share one account. They both try to withdraw money at the same time.

**Requirements:**

1. Create a `BankAccount` class with a `private int balance = 100`.
2. Create a method `public void withdraw(int amount)`.
3. Inside the method, first check `if (balance >= amount)`. If true, subtract the amount and print the remaining balance.
4. **The Catch:** Without synchronization, if two threads check the balance at the same time, they might both see $100 and both withdraw $75, putting the account at -$50!
5. Use `synchronized` to ensure only one withdrawal happens at a time.
6. Test it by creating two threads that both try to withdraw $75.

### Problem 2: The Producer-Consumer Handshake (`wait/notify`)

Imagine a bakery with one shelf that can hold only **one** loaf of bread. A `Producer` makes bread, and a `Consumer` eats it.

**Requirements:**

1. Create a class `Bakery` with a `boolean hasBread = false`.
2. Write a `synchronized void bake()` method: If `hasBread` is true, call `wait()`. Otherwise, set `hasBread = true`, print "Baked!", and call `notify()`.
3. Write a `synchronized void eat()` method: If `hasBread` is false, call `wait()`. Otherwise, set `hasBread = false`, print "Eaten!", and call `notify()`.
4. Run a Producer and Consumer thread. You should see a perfect alternating pattern: Baked, Eaten, Baked, Eaten...
