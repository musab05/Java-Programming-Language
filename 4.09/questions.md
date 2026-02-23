## 🎤 Frequently Asked Interview Questions

### 1. Why is `submit()` generally preferred over `execute()`?

**Answer:** `execute()` is "fire and forget." If the task fails, the thread might print a stack trace to `System.err`, but your main code remains unaware of the failure. `submit()` returns a `Future` object. This "claim ticket" allows you to call `future.get()`, which will re-throw any exception that occurred inside the task, giving you a chance to handle it properly.

### 2. What happens if a Fixed Thread Pool's queue becomes completely full?

**Answer:** This depends on the **RejectedExecutionHandler**. By default, it uses the `AbortPolicy`, which throws a `RejectedExecutionException`. However, you can configure it to `CallerRunsPolicy` (the thread that tried to submit the task runs it themselves, slowing down the submission rate) or `DiscardPolicy` (the task is silently dropped).

### 3. Why is `newCachedThreadPool` considered dangerous for high-volume public APIs?

**Answer:** Because it has an **unbounded** maximum thread count. If your server receives a massive spike of 50,000 requests, a cached pool will attempt to create 50,000 threads. Each thread takes significant memory, which will likely lead to an `OutOfMemoryError` (OOM), crashing your entire JVM.

### 4. What is the difference between `shutdown()` and `shutdownNow()`?

**Answer:** `shutdown()` is "polite"—it stops accepting new tasks but allows currently queued and running tasks to finish. `shutdownNow()` is "aggressive"—it attempts to stop running tasks immediately (via `Thread.interrupt()`) and returns a list of tasks that were waiting in the queue but never started.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The One-at-a-Time Worker

**Task:** You need to process a list of 10 sensitive bank transactions. They **must** be processed one after another in the exact order they appear. Which executor do you use?
**Solution:**

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
// Every task submitted here is guaranteed to run sequentially.

```

### Challenge 2: The Heartbeat Task

**Task:** Write a snippet that creates a pool and schedules a task to print "System Healthy" every 5 seconds, starting after an initial delay of 1 second.
**Solution:**

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
scheduler.scheduleAtFixedRate(() -> System.out.println("System Healthy"), 1, 5, TimeUnit.SECONDS);

```

---

## 📝 Practice Problems

### Problem 1: The Image Processor (Fixed Pool)

Imagine you have 20 high-resolution images to process, but your server only has 4 CPU cores. You want to process them 4 at a time to avoid melting the CPU.

**Requirements:**

1. Create a `newFixedThreadPool` with 4 threads.
2. Loop 20 times and submit a task that prints "Processing image [ID] on [ThreadName]" and sleeps for 500ms.
3. Use the **Shutdown Protocol** (shutdown + awaitTermination) to ensure the program waits for all images to finish before printing "All images processed."

### Problem 2: The Future Calculation (`submit`)

You need to perform a complex math calculation in the background that takes 2 seconds and returns a result.

**Requirements:**

1. Create a `SingleThreadExecutor`.
2. Use `executor.submit(Callable)` to calculate the sum of numbers from 1 to 100.
3. In the `main` thread, print "Waiting for result..."
4. Call `.get()` on the returned `Future` to retrieve and print the sum.
5. Notice how the `main` thread blocks until the background work is done.
