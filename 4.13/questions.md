The **Fork/Join Framework** is the "Turbocharger" of the Java Concurrency world. It doesn't just run tasks in parallel; it fundamentally changes how work is distributed across your CPU cores. While `ExecutorService` is great for managing a bunch of distinct, independent jobs (like handling 100 different web requests), Fork/Join is built to crush a single, massive job by shredding it into millions of tiny pieces.

In high-level technical interviews, you aren't just asked about the syntax—you're asked about the **Work-Stealing Algorithm**, as it's one of the most elegant pieces of engineering in the Java standard library.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is "Work-Stealing" and why is it better than a standard queue?

**Answer:** In a standard `ThreadPoolExecutor`, threads pull from a single, shared queue. If one thread gets stuck with a "heavy" task, other threads might finish their work and sit idle. In `ForkJoinPool`, every worker thread has its own **Deque** (double-ended queue). When a thread runs out of work, it looks at the *bottom* of another thread's deque and "steals" a task. This ensures all CPU cores stay at 100% utilization until the entire job is done.

### 2. Why should you call `left.fork()` and then `right.compute()`?

**Answer:** This is a key optimization. If you call `left.fork()` and `right.fork()`, you are essentially wasting the current thread—it just sits there waiting for two others to finish. By calling `compute()` on the second task, the current thread stays busy doing actual work while the first task is handled by someone else. You only call `join()` at the very end to sync them up.

### 3. What happens if your `THRESHOLD` is too low?

**Answer:** If the threshold is too small (e.g., splitting an array down to a single element), the **overhead** of creating task objects and managing the stack will far outweigh the time saved by parallel execution. Your "parallel" code will actually run significantly slower than a simple, single-threaded `for` loop.

### 4. What is the "Common Pool"?

**Answer:** The `ForkJoinPool.commonPool()` is a static pool used by all **Parallel Streams** and `CompletableFutures` by default. Its size is usually set to `Runtime.getRuntime().availableProcessors() - 1`. Because it's shared, you should never run long-running or blocking I/O tasks in a parallel stream, as you'll starve every other part of your app that needs that pool.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Recursive Choice

**Task:** You are writing a program to recursively search a massive file directory for a specific filename. Should you use `RecursiveTask` or `RecursiveAction`?
**Solution:** `RecursiveTask<File>`, because the search needs to **return** the File object once it's found (or `null` if it's not).

### Challenge 2: The Parallel Stream Shortcut

**Task:** Convert this `for` loop into a one-line Parallel Stream using the common Fork/Join pool.

```java
long total = 0;
for (int i : myList) { total += Math.sqrt(i); }

```

**Solution:**

```java
double total = myList.parallelStream().mapToDouble(Math::sqrt).sum();

```

---

## 📝 Practice Problems

### Problem 1: The Parallel Fibonacci (`RecursiveTask`)

The Fibonacci sequence is the classic recursive problem. While there are faster ways to calculate it, let's use it to practice Fork/Join.

**Requirements:**

1. Create a class `FibonacciTask` that extends `RecursiveTask<Integer>`.
2. Set a `THRESHOLD` of 10. (If $n < 10$, just calculate it normally).
3. If $n \geq 10$, fork `n-1` and compute `n-2`.
4. Create a `ForkJoinPool` and invoke your task for $n=30$.
5. **Bonus:** Print how much faster (or slower!) it is than a standard recursive call.

### Problem 2: The Blur Filter (`RecursiveAction`)

Imagine you are applying a blur filter to a massive image. You can split the image into "Top Half" and "Bottom Half" recursively.

**Requirements:**

1. Create a class `ImageBlurAction` that extends `RecursiveAction`.
2. The task should take a `int startRow` and `int endRow`.
3. If the number of rows is less than 1,000, process them (print "Processing rows X to Y").
4. If more than 1,000, split into two `ImageBlurAction` sub-tasks and `invokeAll(left, right)`.
5. **The Goal:** See the work-stealing in action by printing the `Thread.currentThread().getName()` in the processing step.
