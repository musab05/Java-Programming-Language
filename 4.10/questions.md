## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between `Future.get()` and `Future.get(long, TimeUnit)`?

**Answer:** `Future.get()` is an **unbounded blocking call**. Your thread will sit there forever if the background task hangs (e.g., a deadlocked database or a frozen API). In production, this is dangerous because it can lead to "Thread Exhaustion." You should almost always use the version with a **timeout** to ensure your application remains responsive even if a background worker fails.

### 2. How do you cancel a task that is already running?

**Answer:** You use `future.cancel(true)`. The `true` parameter tells the JVM to send an **interrupt** to the thread. However, this only works if your task code is "interruption-aware" (meaning it checks `Thread.currentThread().isInterrupted()` or calls blocking methods like `Thread.sleep()`). If the task is in a tight `while(true)` loop that doesn't check for interrupts, it will keep running despite the cancellation.

### 3. What happens if the `Callable` throws an exception?

**Answer:** The exception is "swallowed" by the executor thread and stored inside the `Future` object. It only resurfaces when you call `.get()`. At that point, the JVM throws an `ExecutionException`. You must then call `e.getCause()` to see the original exception (like a `NullPointerException` or `SQLException`) that actually happened inside the background thread.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Parallel Batch

**Task:** You have a list of 5 `Callable<String>` tasks. Write the code to run them all at once and wait for the results.
**Solution:**

```java
List<Callable<String>> tasks = Arrays.asList(task1, task2, task3, task4, task5);
// invokeAll blocks until EVERY task in the list is done
List<Future<String>> results = executor.invokeAll(tasks);

```

### Challenge 2: The Status Check

**Task:** How do you check if a task is finished without actually stopping to wait for it?
**Solution:**

```java
if (priceTicket.isDone()) {
    System.out.println("Result is ready!");
} else {
    System.out.println("Still working... I'll check back later.");
}

```

---

## 📝 Practice Problems

### Problem 1: The Multi-Currency Converter (`Callable`)

Imagine you are building a dashboard that needs to fetch exchange rates for USD to EUR, GBP, and JPY simultaneously.

**Requirements:**

1. Create an `ExecutorService` with a fixed pool of 3.
2. Create a `Callable<Double>` that takes a currency name, sleeps for a random amount of time (1–3 seconds), and returns a "mock" exchange rate.
3. Submit 3 tasks for EUR, GBP, and JPY.
4. Capture the 3 `Future` objects.
5. In `main`, print "Fetching rates..." and then retrieve and print the results from the futures.

### Problem 2: The "Failing" Task (`ExecutionException`)

You need to see how a background failure is reported back to the main thread.

**Requirements:**

1. Submit a `Callable` that intentionally throws a `RuntimeException("Database Connection Failed!")`.
2. In the `main` thread, call `.get()` inside a `try-catch` block.
3. Catch the `ExecutionException` and print: "Caught background error: " + `e.getCause().getMessage()`.
4. Verify that the main thread didn't crash until you called `.get()`.
