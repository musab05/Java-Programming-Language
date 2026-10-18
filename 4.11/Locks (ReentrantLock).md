We’ve used the `synchronized` keyword quite a bit, but as you grow as a Java developer, you’ll find scenarios where `synchronized` is just too "blunt" a tool. It’s like a light switch—it’s either on or off.

In 2004, Java 5 introduced the **`java.util.concurrent.locks`** package, specifically the **`ReentrantLock`**. This is a high-performance, flexible alternative to the internal locks used by `synchronized`.

---

## 1. Why `ReentrantLock`?

If `synchronized` works, why do we need `ReentrantLock`? In enterprise systems, we often need **advanced features** that the keyword doesn't offer:

1. **TryLock:** The ability to "try" to acquire a lock and give up if it's already held (prevents blocking forever).
2. **Timeouts:** The ability to wait for a lock for a specific duration.
3. **Interruptibility:** A thread waiting for a `ReentrantLock` can be interrupted, unlike a thread blocked by `synchronized`.
4. **Fairness:** You can ensure the longest-waiting thread gets the lock next (to prevent **Starvation**).

---

## 2. Basic Syntax: The "Try-Finally" Rule

The biggest difference in usage is that `ReentrantLock` is an object. You must manually call `lock()` and, most importantly, you **must** call `unlock()` in a `finally` block. If you forget, the lock stays held forever, and your app will eventually deadlock.

```java
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private final ReentrantLock lock = new ReentrantLock();

    public void safeMethod() {
        lock.lock(); // Acquire the lock
        try {
            // Critical Section: Only one thread here
            System.out.println(Thread.currentThread().getName() + " is working...");
        } finally {
            lock.unlock(); // ALWAYS unlock in finally
        }
    }
}

```

---

## 3. The Power of `tryLock()`

In a production environment, you don't always want a thread to sit and wait. Imagine a UI thread—if it waits for a lock, the whole app freezes. `tryLock()` allows you to check availability.

```java
public void performTask() {
    if (lock.tryLock()) { // Check if available immediately
        try {
            // Do task
        } finally {
            lock.unlock();
        }
    } else {
        System.out.println("Could not get lock. Doing something else instead...");
    }
}

```

You can even add a timeout: `lock.tryLock(5, TimeUnit.SECONDS)`. This is the single best way to **prevent deadlocks** in complex systems.

---

## 4. Reentrancy: Why the name?

A lock is called "Reentrant" if a thread that already holds the lock can acquire it again without getting stuck. Both `synchronized` and `ReentrantLock` are reentrant.

Internally, the lock keeps a **hold count**. Each time the owning thread calls `lock()`, the count goes up. Each time it calls `unlock()`, the count goes down. The lock is only truly released when the count hits zero.

---

## 5. Fair vs. Unfair Locks

By default, `ReentrantLock` is **Unfair**. This means if multiple threads are waiting, and the lock becomes free, a new "fast" thread might "barge in" and steal the lock before the long-waiting threads.

* **Unfair (Default):** Better performance (more throughput).
* **Fair:** Guarantees "First-In-First-Out" (FIFO). Use this only if you must prevent **Starvation**.

```java
// Setting 'true' enables the fairness policy
ReentrantLock fairLock = new ReentrantLock(true);

```

---

## 6. ReadWriteLock: Optimization for Scaling

If you have a resource that many threads **read** from but only occasionally **write** to (like a cache), using a standard `ReentrantLock` is slow because it stops readers from working together.

**`ReentrantReadWriteLock`** allows:

* **Multiple Readers** to work simultaneously.
* **Only One Writer** to work exclusively.

---

## Summary Table: Synchronized vs. ReentrantLock

| Feature | `synchronized` | `ReentrantLock` |
| --- | --- | --- |
| **Simplicity** | High (Built-in keyword) | Lower (Manual object) |
| **Deadlock Safety** | Low (No timeout) | High (`tryLock` with timeout) |
| **Fairness** | No | Yes (Optional) |
| **Flexibility** | Block-structured | Flexible (can lock in one method, unlock in another) |
| **Performance** | High (heavily optimized by JVM) | High (can be better under high contention) |

### Tip:

Don't use `ReentrantLock` everywhere just because it’s "fancier." Modern JVMs have optimized the `synchronized` keyword (using techniques like **Biased Locking**) to be incredibly fast for simple use cases. Only reach for `ReentrantLock` when you actually need one of the advanced features like `tryLock` or `Fairness`.
