## 🎤 Frequently Asked Interview Questions

### 1. Is there a "RUNNING" state in Java's `Thread.State` enum?

**Answer:** Technically, **no**. The enum only defines `RUNNABLE`. This is a design choice: a thread in the `RUNNABLE` state might be executing, or it might be waiting for the OS to give it a turn on the CPU. The JVM leaves the actual "Running" distinction to the Operating System's scheduler.

### 2. What is the difference between `WAITING` and `TIMED_WAITING`?

**Answer:** It's all about the "wake-up call."

* **`WAITING`**: The thread is in a deep sleep. It will stay there forever unless another thread explicitly calls `notify()` or `notifyAll()` on the object the thread is waiting on.
* **`TIMED_WAITING`**: The thread has an internal alarm clock. It will wake up either when another thread notifies it **OR** when the specified time (like in `Thread.sleep(1000)`) has passed.

### 3. Can a thread go from `BLOCKED` directly to `WAITING`?

**Answer:** No. A thread must be `RUNNABLE` to execute the code that puts it into a `WAITING` state (like calling `object.wait()`). Similarly, a thread that is "notified" out of `WAITING` doesn't go straight to running; it moves back to `RUNNABLE` (or potentially `BLOCKED` if it needs to re-acquire a lock) to wait for its turn on the CPU.

### 4. How can you find the state of all threads in a running production app?

**Answer:** You use a **Thread Dump**. Tools like `jstack` (command line) or VisualVM allow you to see exactly which state every thread is in. If you see 50 threads in the `BLOCKED` state, you know you have a "Lock Contention" issue—too many workers fighting over the same door.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Instant Death

**Task:** What happens if you try to call `.start()` on a thread that is already in the `TERMINATED` state?
**Solution:**

```java
// It will throw an IllegalThreadStateException. 
// A thread's life is a one-way street; once it hits TERMINATED, it cannot be revived.

```

### Challenge 2: The Logic Check

**Task:** Identify the state of `Thread B` in this scenario: `Thread A` is currently inside a `synchronized` method. `Thread B` tries to call that same `synchronized` method on the same object.
**Solution:**

```java
// Thread B is in the BLOCKED state. 
// It is actively waiting for Thread A to release the monitor lock.

```

---

## 📝 Practice Problems

These problems help you connect the theoretical states to actual code behavior, which is essential for debugging.

### Problem 1: The "Waiting" Trap

You need to create a thread that starts, prints a message, and then sits in the `WAITING` state indefinitely until the user presses a key in the console.

**Requirements:**

1. Create a shared `Object lock = new Object();`.
2. Create a thread that enters a `synchronized(lock)` block and calls `lock.wait()`.
3. In the `main` thread, start the worker thread.
4. Print the worker thread's state after a short delay to verify it is `WAITING`.
5. Use `Scanner` to wait for user input.
6. Once input is received, the `main` thread should enter a `synchronized(lock)` block and call `lock.notify()` to wake up the worker.
7. Print the worker's state one last time after it finishes.

### Problem 2: The Timed Sentry

Build a "Sentry" thread that monitors a condition but doesn't hog the CPU.

**Requirements:**

1. Create a thread that runs a loop 5 times.
2. In each iteration, the thread should print "Sentry scanning..." and then enter the `TIMED_WAITING` state for 500 milliseconds using `Thread.sleep()`.
3. The `main` thread should monitor this sentry. Use a loop in the `main` thread to print the sentry's state every 200 milliseconds.
4. You should see the state toggle between `RUNNABLE` and `TIMED_WAITING`.
