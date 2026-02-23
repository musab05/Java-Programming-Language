Welcome back to the core of our multithreading series. We’ve learned how to create "workers" (threads), but now we have to face the biggest challenge in parallel programming: **Thread Interference.**

In a multithreaded environment, threads share the same memory (the Heap). When two or more threads try to access and modify the same variable at the exact same time, you get a **Race Condition**. The result is unpredictable data corruption.

**Synchronization** is the mechanism we use to ensure that only one thread can access a resource at a time.

---

## 1. The Problem: A Race Condition in Action

Imagine a bank account with $100. Two threads try to withdraw $50 at the same time.

1. **Thread A** reads the balance ($100).
2. **Thread B** reads the balance ($100).
3. **Thread A** subtracts $50 and updates the balance to $50.
4. **Thread B** subtracts $50 and updates the balance to $50.

We should have $0 left, but we have $50. This is a classic race condition.

---

## 2. The `synchronized` Keyword

In Java, every object has an internal "lock" (also called a **Monitor**). When a method is marked as `synchronized`, a thread must acquire that object's lock before it can execute the code.

### A. Synchronized Methods

If you mark a method as synchronized, the thread locks the **entire object** (`this`).

```java
public class Counter {
    private int count = 0;

    // Only one thread can execute this at a time for a specific Counter instance
    public synchronized void increment() {
        count++;
    }
}

```

### B. Synchronized Blocks

Sometimes, locking an entire method is overkill and slows down performance. You can lock just a specific block of code using a specific object.

```java
public void updateData() {
    // Non-critical code here (parallel)
    
    synchronized(this) {
        // Critical Section: Only one thread here (serialized)
        count++;
    }
}

```

---

## 3. Static Synchronization

What if you have a `static` variable? A normal synchronized method locks the *instance*, but static variables belong to the *class*. To protect static data, you need to lock the **Class Object**.

```java
public class GlobalCounter {
    private static int totalCount = 0;

    // This locks the GlobalCounter.class object
    public static synchronized void increment() {
        totalCount++;
    }
}

```

---

## 4. The "Lock" Concept: Intrinsic vs. Explicit

As a developer, you should know that `synchronized` uses **Intrinsic Locks**. These are "reentrant," meaning if a thread holds a lock, it can enter other synchronized methods of the same object without getting stuck.

However, `synchronized` has limitations:

* You can't "try" to get a lock; you just wait forever.
* You can't interrupt a thread that is waiting for a lock.

For more complex scenarios, we use **Explicit Locks** (like `ReentrantLock`), which allow for timeouts and better control.

---

## 5. The "Deadlock" Trap

Synchronization is powerful, but it's the #1 cause of **Deadlocks**. A deadlock happens when:

* **Thread 1** holds Lock A and waits for Lock B.
* **Thread 2** holds Lock B and waits for Lock A.

They will wait forever, and your application will hang.

---

## 6. Voluntary Visibility: The `volatile` Keyword

Sometimes you don't need full synchronization; you just need to make sure that when one thread changes a variable, all other threads see it immediately.

By default, threads might cache variables in the CPU register for performance. The `volatile` keyword tells the JVM: *"Don't cache this; always read it from the main RAM."*

> **Note:** `volatile` only guarantees **visibility**, not **atomicity**. Use it for flags (like `boolean stopRequested`), but not for increments (like `count++`).

---

## Summary Table: Sync Strategies

| Strategy | When to use? | Key Benefit |
| --- | --- | --- |
| **Synchronized Method** | Simple, whole-object protection. | Easy to read and implement. |
| **Synchronized Block** | High-performance, granular locking. | Reduces "lock contention" time. |
| **Volatile** | Simple flags/indicators. | Fast, no lock overhead. |
| **Atomic Classes** | Simple counters/math. | Extremely fast, lock-free thread safety. |

### Tip:

Avoid "Over-synchronization." Every time you use the `synchronized` keyword, you turn your multi-lane highway into a single-lane road. Only synchronize the **absolute minimum** code necessary to protect your data.
