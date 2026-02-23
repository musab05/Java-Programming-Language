## 🎤 Frequently Asked Interview Questions

### 1. How do you detect a Deadlock in a running application?

**Answer:** You take a **Thread Dump**. Most modern JVMs have built-in deadlock detection. If you use the `jstack` utility or a profiler like VisualVM, the JVM will explicitly label the "Deadlock" section and show you: "Thread-1 is waiting for Lock A (held by Thread-2), and Thread-2 is waiting for Lock B (held by Thread-1)."

### 2. What is the "Lock Ordering" technique to prevent Deadlock?

**Answer:** It is the practice of ensuring that all threads acquire shared locks in the exact same sequence. For example, in a bank transfer, instead of locking `from` then `to`, you could compare their unique IDs and always lock the one with the **smaller ID first**. Since every thread follows the same rule, a "circular wait" becomes impossible.

### 3. How does `Livelock` differ from `Deadlock` in terms of CPU usage?

**Answer:** Deadlock is "quiet"—the threads are in a `BLOCKED` or `WAITING` state, so CPU usage is near zero. Livelock is "noisy"—the threads are technically `RUNNABLE` and constantly executing code (the "back-off" and "retry" logic), so you will see high CPU usage even though no actual progress is being made.

### 4. Can `synchronized` methods cause Starvation?

**Answer:** Yes. In older JVM versions or specific OS schedulers, the "wait set" for a `synchronized` block wasn't necessarily a fair queue. If "Greedy Threads" keep releasing and immediately re-acquiring a lock, a "patient" thread might be bypassed indefinitely. This is why `ReentrantLock` has an optional **"Fairness"** parameter.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Deadlock Blueprint

**Task:** Given two objects, `Resource A` and `Resource B`, describe the sequence of events that leads to a deadlock.
**Solution:** 1. Thread 1 locks Resource A.
2. Thread 2 locks Resource B.
3. Thread 1 tries to lock Resource B (and blocks).
4. Thread 2 tries to lock Resource A (and blocks).
*Result: Both threads wait forever.*

### Challenge 2: The Fair Lock

**Task:** Write the code to create a lock that guarantees the thread that has been waiting the longest gets the lock next (preventing Starvation).
**Solution:**

```java
// Pass 'true' to the constructor to enable the fairness policy
ReentrantLock fairLock = new ReentrantLock(true);

```

---

## 📝 Practice Problems

### Problem 1: The "Safe" Account Transfer (Deadlock Prevention)

Refactor the deadlocked `transfer` method from the lecture using **Lock Ordering**.

**Requirements:**

1. Create an `Account` class with an `int id` and `double balance`.
2. Write a method `public void safeTransfer(Account acc1, Account acc2, double amount)`.
3. Use an `if` statement to determine which account has the lower ID.
4. **Always** synchronize on the lower-ID account first, then the higher-ID account.
5. **The Test:** Start 100 threads where half send money from Account 1 to 2, and the other half send from 2 to 1. Without this fix, they would deadlock instantly.

### Problem 2: The Smart Retry (Livelock Prevention)

Simulate two threads trying to access a shared resource using `tryLock()`. Use "Random Jitter" to ensure they don't get stuck in a livelock.

**Requirements:**

1. Create a `ReentrantLock`.
2. Create a thread that tries to `lock.tryLock()`. If it fails, it should print "Collision! Backing off..." and sleep for a **random** amount of time (between 10ms and 50ms) before trying again.
3. Start two such threads.
4. **The Goal:** Notice how the randomness (jitter) prevents them from retrying in perfect sync and allows one thread to eventually "win" the lock.
