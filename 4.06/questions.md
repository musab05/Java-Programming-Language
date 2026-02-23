## 🎤 Frequently Asked Interview Questions

### 1. Why are `wait()`, `notify()`, and `notifyAll()` in the `Object` class instead of the `Thread` class?

**Answer:** Because the lock (the monitor) is attached to the **Object**, not the thread. Threads are the ones *seeking* the lock, but the object is the "gatekeeper." Since any object can serve as a lock, the methods to manage the waiting queue for that lock must be available on every object.

### 2. Why must `wait()` and `notify()` be called from a `synchronized` context?

**Answer:** Because you can't "give up" a lock (`wait`) or "alert" others about a lock (`notify`) if you don't **own** the lock in the first place. If you try to call them outside a synchronized block, the JVM will throw an `IllegalMonitorStateException`.

### 3. What happens to a thread after it is "notified"?

**Answer:** It doesn't immediately jump back to the `RUNNING` state. It moves from the **Waiting Pool** to the **Entry Set** (the blocked state). It must wait for the thread that called `notify()` to exit its synchronized block and release the lock. Once the lock is free, the notified thread competes with other threads to re-acquire the lock and continue from where it left off.

### 4. What is the "Lost Wakeup" problem?

**Answer:** This happens if `notify()` is called *before* a thread has actually started `wait()`-ing. Since `notify()` doesn't "store" a signal (it’s just a momentary shout), the signal is lost. This is why we check conditions in a `while` loop—to ensure that even if a signal was missed or a spurious wakeup occurred, the thread state remains consistent.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Infinite Waiter

**Task:** What is wrong with this code?

```java
public synchronized void task() {
    System.out.println("Starting...");
    this.wait(); 
    System.out.println("Finished!");
}

```

**Solution:** It won't compile unless you handle the **`InterruptedException`** (a checked exception). Also, if no other thread ever calls `notify()`, this thread will stay in the `WAITING` state forever (a "zombie" thread).

### Challenge 2: The Lock Thief

**Task:** Does `Thread.sleep(1000)` release the lock it currently holds?
**Solution:** **No.** This is a common bug. If a thread sleeps inside a synchronized block, every other thread trying to get into that block is stuck waiting for the sleeper to wake up and finish. Use `wait(1000)` if you want to let others use the lock while you pause.

---

## 📝 Practice Problems

### Problem 1: The Messaging App (Simple ITC)

Build a simple one-way radio. A "Sender" sends a message, and a "Receiver" prints it.

**Requirements:**

1. Create a `Message` class with a `String content` and `boolean empty = true`.
2. Write a `synchronized String take()` method: While `empty` is true, call `wait()`. Then set `empty = true`, call `notifyAll()`, and return the content.
3. Write a `synchronized void put(String message)` method: While `empty` is false, call `wait()`. Then set `empty = false`, this.`content` = message, and call `notifyAll()`.
4. Run two threads and see them handshake.

### Problem 2: The Busy Kitchen (`notifyAll`)

Simulate a kitchen where one **Chef** produces "Pizza" and three **Hungry Customers** are waiting for it.

**Requirements:**

1. Use the `SharedBuffer` logic from the lecture.
2. Create one Chef thread that produces 5 pizzas.
3. Create three Customer threads that all try to `consume()`.
4. **The Goal:** Use `notifyAll()` in the `produce` method. Observe how all three customers wake up, but only one "wins" the lock to eat the pizza, while the others safely go back to sleep because of the `while` loop.
