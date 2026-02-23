## 🎤 Frequently Asked Interview Questions

### 1. What is a "Monitor" in the context of Java synchronization?

**Answer:** In Java, every single object has an associated "Monitor" or "Intrinsic Lock." When a thread enters a `synchronized` block, it "acquires the monitor" for that object. No other thread can acquire that same monitor until the first thread releases it (by finishing the code block). It’s essentially a "talking stick" for threads.

### 2. Can two threads execute two different `synchronized` methods of the same object simultaneously?

**Answer:** **No.** If both methods are synchronized on `this` (the instance), the first thread to enter either method grabs the object's one and only lock. The second thread will be **BLOCKED** even if it’s trying to call a completely different method, because the lock is held at the object level, not the method level.

### 3. What is "Lock Contention" and how do you reduce it?

**Answer:** Lock contention occurs when multiple threads are constantly waiting to acquire the same lock, causing the application to slow down to single-threaded speeds. You reduce it by:

* **Shrinking the scope:** Use `synchronized` blocks instead of methods.
* **Lock Stripping:** Breaking one big lock into multiple smaller ones (like `ConcurrentHashMap` does).
* **Using Atomic Classes:** Using `AtomicInteger` for simple math instead of heavy locks.

### 4. Why is `volatile` not enough to fix a `count++` operation?

**Answer:** Because `count++` is actually **three** operations: (1) Read the value, (2) Increment the value, and (3) Write it back. `volatile` only ensures that the *reading* and *writing* are done to main memory. If two threads read "5" at the same time, they both increment to "6" and write back "6"—we still lost an increment. You need `synchronized` or `AtomicInteger` to make all three steps happen as one "atomic" unit.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Static Trap

**Task:** Identify the bug in this code. Will the static variable be protected?

```java
public class Data {
    private static int total = 0;
    public synchronized void add() { total++; } 
}

```

**Solution:** No, it is **not protected**. The method is synchronized on the *instance* (`this`). If you create two `Data` objects, each has its own lock, so two threads can call `add()` at the same time on different instances, corrupting the static `total`. You must use `public static synchronized void add()`.

### Challenge 2: The Block Refactor

**Task:** Refactor this slow method into a faster synchronized block version.

```java
public synchronized void heavyTask() {
    doVeryExpensiveCalculation(); // 2 seconds
    count++;                       // 1 millisecond
}

```

**Solution:**

```java
public void heavyTask() {
    doVeryExpensiveCalculation(); // This runs in parallel
    synchronized(this) {
        count++; // This is the only part that needs to be serialized
    }
}

```

---

## 📝 Practice Problems

### Problem 1: The Inventory Manager (Race Condition Fix)

You are building an e-commerce backend. You have a `Warehouse` with a limited stock of a popular gaming console.

**Requirements:**

1. Create a class `Warehouse` with `private int stock = 10`.
2. Write a method `public void buy(int quantity)`.
3. Inside, check if `stock >= quantity`. If so, sleep for 100ms (to simulate processing), then subtract and print "Purchase successful! Remaining: X".
4. **The Test:** Create 5 threads that all try to buy 3 consoles each.
5. **The Goal:** Run it without synchronization first to see the stock go into negative numbers. Then, add the `synchronized` keyword to fix it.

### Problem 2: The Toggle Switch (`volatile`)

Create a "Kill Switch" for a background worker thread.

**Requirements:**

1. Create a class `Worker` that implements `Runnable`.
2. Define a `private volatile boolean running = true`.
3. The `run()` method should use a `while(running)` loop that prints "Worker processing..." and sleeps for 200ms.
4. Add a method `public void shutdown() { running = false; }`.
5. **The Test:** Start the worker thread from `main`. Let it run for 1 second, then call `shutdown()` from the `main` thread.
6. **Experiment:** Remove `volatile` and see if the thread sometimes keeps running forever on your machine because it cached the `running` value!
