In our previous session, we introduced the concept of threads as the "workers" in our application. However, as an experienced developer, I can tell you that most multithreading bugs—like deadlocks or "zombie" threads—happen because the developer doesn't understand the **Thread Lifecycle**.

In Java, a thread isn't just "running" or "stopped." It transitions through several distinct states managed by the JVM and the underlying Operating System.

---

## 1. The 6 States of a Thread

According to the `Thread.State` enum in Java, a thread can be in one of the following six states:

### A. NEW

This is the state a thread is in after you instantiate the `Thread` class but **before** you call `.start()`.

* **State:** The code hasn't begun executing yet.
* **Transition:** Moving to the next state requires calling `thread.start()`.

### B. RUNNABLE

Once you call `.start()`, the thread moves to the **Runnable** state.

* **Crucial Note:** "Runnable" doesn't mean it's currently executing. It means the thread is *eligible* to run and is waiting for the **Thread Scheduler** (a part of the OS/JVM) to give it a "time slice" on the CPU.
* In a multi-core system, several threads might be in this state, but only as many as there are CPU cores can be "Running" at the exact same microsecond.

### C. BLOCKED

A thread enters the **Blocked** state when it is trying to enter a `synchronized` block or method but another thread is already holding the lock (the monitor).

* **Waiting for:** A monitor lock.

### D. WAITING

A thread is in the **Waiting** state because it is waiting for *another* thread to perform a specific action.

* **Trigger:** Calling `Object.wait()`, `Thread.join()`, or `LockSupport.park()`.
* **Exit:** It stays here indefinitely until another thread calls `notify()` or `notifyAll()`.

### E. TIMED_WAITING

This is identical to the Waiting state, but with a **timeout**.

* **Trigger:** Calling `Thread.sleep(ms)`, `Object.wait(ms)`, or `Thread.join(ms)`.
* **Exit:** It wakes up either when the time expires or when it receives a notification.

### F. TERMINATED (DEAD)

The thread has finished its execution. Either the `run()` method completed normally, or an unhandled exception killed it.

* **Note:** You cannot "restart" a terminated thread. Once it's dead, it's gone.

---

## 2. Visualizing the Transitions

Understanding how a thread moves between these states is key to mastering concurrency.

---

## 3. Practical Example: Spotting the States

Let’s look at a snippet that demonstrates these transitions in action.

```java
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                // Moving to TIMED_WAITING
                Thread.sleep(1000); 
                
                synchronized (ThreadStateDemo.class) {
                    // Might move to BLOCKED here if another thread has the lock
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("State after creation: " + thread.getState()); // NEW

        thread.start();
        System.out.println("State after start(): " + thread.getState()); // RUNNABLE

        Thread.sleep(200); // Give it time to hit the sleep() call
        System.out.println("State during sleep: " + thread.getState()); // TIMED_WAITING

        thread.join(); // Wait for the thread to finish
        System.out.println("State after completion: " + thread.getState()); // TERMINATED
    }
}

```

---

## 4. Tip: The "Blocked" vs. "Waiting" Distinction

In interviews, people often confuse **Blocked** and **Waiting**. Here is the simple way to remember it:

* **Blocked:** You want to enter a room, but the door is locked by someone else (`synchronized`). You are standing at the door, trying to turn the handle.
* **Waiting:** You are inside a room, but you have decided to sit in a chair and close your eyes until someone else tells you it's time to wake up (`wait()`).

---

## 5. Common Pitfall: Calling `run()` instead of `start()`

If you call `thread.run()`, you aren't actually creating a new thread. You are simply executing the code in the **current thread** (the one that called it). The thread state will never move to "Runnable" on a new stack. **Always use `.start()**` to trigger the lifecycle transitions.

---

## Summary Table: Thread State Triggers

| Current State | Target State | Triggering Method |
| --- | --- | --- |
| **NEW** | **RUNNABLE** | `thread.start()` |
| **RUNNABLE** | **TIMED_WAITING** | `Thread.sleep(n)`, `wait(n)`, `join(n)` |
| **RUNNABLE** | **WAITING** | `wait()`, `join()`, `park()` |
| **RUNNABLE** | **BLOCKED** | Trying to enter a `synchronized` block |
| **Any** | **TERMINATED** | `run()` method completes or uncaught exception |
