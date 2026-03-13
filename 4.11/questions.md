## 🎤 Frequently Asked Interview Questions

### 1. What happens if you forget to call `unlock()`?

**Answer:** This is a catastrophic bug. Unlike the `synchronized` keyword, which automatically releases the lock when the block ends (even if an exception occurs), `ReentrantLock` stays locked until you manually call `unlock()`. If you miss it, every other thread that needs that lock will be **BLOCKED** forever, eventually leading to an application hang or crash.

### 2. Can you explain "Lock Barging"?

**Answer:** This occurs with **Unfair Locks** (the default). When a thread releases a lock, it takes time to "wake up" the next thread in the waiting queue. If a new thread arrives at that exact microsecond, it might "barge in" and grab the lock immediately because it's already in the `RUNNABLE` state. This actually **improves performance** (throughput) because the CPU doesn't stay idle while waiting for the queued thread to wake up.

### 3. When would you choose `ReentrantReadWriteLock` over `ReentrantLock`?

**Answer:** When you have **High Read Contention**. If you have a shared Map where 100 threads are constantly reading data and only 1 thread updates it once a minute, a standard lock would force those 100 readers to wait for each other. A `ReadWriteLock` allows all 100 readers to run in parallel, only stopping them when the writer needs to make an update.

### 4. What is the "Hold Count"?

**Answer:** Since the lock is **Reentrant**, a single thread can lock the same object multiple times (e.g., calling a locked method from within another locked method). The `ReentrantLock` maintains a counter. If the thread locks it three times, it must call `unlock()` exactly three times before the lock is actually released for other threads to use.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Non-Blocking Attempt

**Task:** Use `tryLock()` to attempt to acquire a lock. If you get it, print "Work done"; if not, print "Doing other work" and move on.
**Solution:**

```java
if (lock.tryLock()) {
    try {
        System.out.println("Work done");
    } finally {
        lock.unlock();
    }
} else {
    System.out.println("Doing other work");
}

```

### Challenge 2: The Fair Queue

**Task:** Create a lock that ensures threads are served in the exact order they requested the lock.
**Solution:**

```java
// Pass 'true' to the constructor to enable Fairness
ReentrantLock fairLock = new ReentrantLock(true);

```

---

## 📝 Practice Problems

### Problem 1: The "Patient" Printer (`tryLock` with Timeout)

Imagine a shared office printer. If it's busy, a worker should wait for up to 2 seconds. If it's still busy after that, they should give up and try again later.

**Requirements:**

1. Create a `Printer` class with a `ReentrantLock`.
2. Write a `print(String document)` method.
3. Use `lock.tryLock(2, TimeUnit.SECONDS)` to attempt to get the lock.
4. If successful, sleep for 1 second (simulating printing) and then `unlock()`.
5. If unsuccessful, print "Printer busy, I'll come back later."
6. Test it with 5 threads trying to print simultaneously.

### Problem 2: The High-Speed Cache (`ReadWriteLock`)

Build a simple thread-safe cache that allows many readers but only one writer.

**Requirements:**

1. Create a `SimpleCache` class with a `Map<String, String>` and a `ReentrantReadWriteLock`.
2. Write a `get(String key)` method that uses the **ReadLock**.
3. Write a `put(String key, String value)` method that uses the **WriteLock**.
4. In `main`, start 10 "Reader" threads and 2 "Writer" threads.
5. **Observation:** Notice how multiple "Reader" logs appear at the same time, while "Writer" logs always appear in isolation.
