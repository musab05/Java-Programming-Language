Welcome back to our exploration of Multithreading. We’ve learned how to start threads and how to lock them with `synchronized` to prevent data corruption. However, in the real world, threads don't just need to stay out of each other's way—they need to **talk to each other**.

Imagine a restaurant: The **Chef** (Thread A) needs to tell the **Waiter** (Thread B) when the food is ready. If the Waiter keeps checking every second ("Is it ready? Is it ready?"), they are wasting energy. This is called **Busy Waiting**, and it's a performance killer.

**Inter-Thread Communication (ITC)** is the civilized way for threads to wait for signals and wake each other up.

---

## 1. The Core Methods: `wait()`, `notify()`, and `notifyAll()`

These methods are not actually part of the `Thread` class; they belong to the `Object` class. This is because the "signal" is attached to the **lock (monitor)** that the threads share.

### A. `wait()`

Tells the current thread to give up its lock and go to sleep until another thread wakes it up.

> **Important:** A thread *must* own the object's lock (be inside a `synchronized` block) to call `wait()`.

### B. `notify()`

Wakes up **one** random thread that is currently waiting on that object's lock. The woken thread doesn't start immediately; it waits until the current thread releases the lock.

### C. `notifyAll()`

Wakes up **all** threads waiting on that object. This is generally safer than `notify()` to avoid leaving threads "stranded" in a waiting state.

---

## 2. The Classic "Producer-Consumer" Pattern

As an experienced dev, I see this pattern everywhere—from message queues to background task processing.

* **The Producer:** Adds data to a buffer. If the buffer is full, it **waits**.
* **The Consumer:** Takes data from the buffer. If the buffer is empty, it **waits**.

```java
class SharedBuffer {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (hasData) { // If there is already data, wait for consumer
            wait();
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + data);
        notify(); // Tell the consumer data is ready
    }

    public synchronized void consume() throws InterruptedException {
        while (!hasData) { // If no data, wait for producer
            wait();
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify(); // Tell the producer buffer is empty
    }
}

```

---

## 3. Why the `while` loop? (The Spurious Wakeup)

You might wonder why I used `while(hasData)` instead of a simple `if(hasData)`.

In Java, a thread can sometimes wake up for no reason (a **Spurious Wakeup**). If you use an `if` statement, the thread might wake up, find the condition is still not met, and try to execute anyway, leading to a crash. **Always check your condition in a `while` loop.**

---

## 4. `wait()` vs. `Thread.sleep()`

This is a favorite interview question. Here is how you answer it like a senior engineer:

| Feature | `wait()` | `Thread.sleep()` |
| --- | --- | --- |
| **Class** | `java.lang.Object` | `java.lang.Thread` |
| **Lock Handling** | **Releases the lock** while waiting. | **Holds the lock** while sleeping. |
| **Wakeup** | Requires `notify()` or timeout. | Wakes up automatically after time. |
| **Context** | Must be in a `synchronized` block. | Can be called anywhere. |

---

## 5. Tip: The `notifyAll()` Rule of Thumb

In production code, prefer `notifyAll()` over `notify()`.

Imagine multiple consumers and one producer. If you use `notify()`, the producer might accidentally wake up another *producer* instead of a *consumer*. That second producer will see the buffer is full and go back to sleep. Now everyone is sleeping, and your application is hanging. `notifyAll()` ensures the right thread eventually gets the signal.

---

## 6. The Modern Alternative: `BlockingQueue`

While `wait()` and `notify()` are the "bricks and mortar," in modern Java (5+), we rarely write them ourselves. We use **Concurrent Collections** like `ArrayBlockingQueue`. It handles all the waiting and notifying for you under the hood.

```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
// Producer
queue.put(10); // Automatically waits if full
// Consumer
int val = queue.take(); // Automatically waits if empty

```

---

**Summary:**

* Inter-Thread Communication prevents "Busy Waiting."
* `wait()` releases the lock; `sleep()` does not.
* Always use `while` loops to guard your `wait()` calls.
* Use `notifyAll()` to be safe in multi-threaded scenarios.
