Parallel streams are the easiest way to write multithreaded code in Java — one method call turns your pipeline parallel. But "easy" doesn't mean "free." Understanding the Fork/Join pool, Spliterators, and the conditions that make parallelism worthwhile is what separates productive use from accidental slowdowns.

The biggest trap is assuming parallel is always faster. In most real-world scenarios, sequential streams outperform parallel ones because the data is too small or the operations are too trivial to justify the overhead of thread management.

---

## 🎤 Frequently Asked Interview Questions

### 1. What thread pool do parallel streams use by default?

**Answer:** They use the **Common Fork/Join Pool**, shared across the entire application. Its default parallelism is `Runtime.getRuntime().availableProcessors() - 1`. This means all parallel streams compete for the same threads. You can override this by submitting the stream to a custom `ForkJoinPool`, or by setting the system property `-Djava.util.concurrent.ForkJoinPool.common.parallelism`.

### 2. Why are parallel streams bad for I/O-bound operations?

**Answer:** I/O operations (database queries, HTTP calls, file reads) block threads. Since the Common Fork/Join Pool has a limited number of threads (CPU cores - 1), blocked threads quickly exhaust the pool, starving other parallel operations. For I/O-bound work, use `CompletableFuture` with a dedicated `ExecutorService` instead.

### 3. What is a `Spliterator` and why does it matter?

**Answer:** A `Spliterator` is an object that knows how to split a data source for parallel processing. It determines how efficiently a collection can be divided into chunks. `ArrayList` has an excellent Spliterator (splits by array index), while `LinkedList` has a poor one (must traverse to find the midpoint). The quality of the Spliterator directly impacts parallel stream performance.

### 4. How do you handle shared mutable state with parallel streams?

**Answer:** You don't — you avoid it entirely. Never modify shared mutable state inside a parallel stream. Instead, use thread-safe collection operations like `collect()` with `Collectors`, or use `reduce()` which combines results safely. If you must accumulate into a collection, use `Collectors.toConcurrentMap()` or similar thread-safe collectors.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Benchmark

**Task:** Write code that compares the execution time of a sequential vs parallel stream for summing 10 million random doubles.
**Solution:**

```java
double[] data = new Random().doubles(10_000_000).toArray();

long start = System.nanoTime();
double seqSum = Arrays.stream(data).sum();
long seqTime = System.nanoTime() - start;

start = System.nanoTime();
double parSum = Arrays.stream(data).parallel().sum();
long parTime = System.nanoTime() - start;

System.out.printf("Sequential: %.2fms%n", seqTime / 1e6);
System.out.printf("Parallel:   %.2fms%n", parTime / 1e6);
```

### Challenge 2: The Thread Inspector

**Task:** Prove that parallel streams use multiple threads by printing the thread name inside `forEach`.
**Solution:**

```java
List.of(1, 2, 3, 4, 5, 6, 7, 8).parallelStream()
    .forEach(n -> System.out.println(n + " → " + Thread.currentThread().getName()));
```

---

## 📝 Practice Problems

### Problem 1: Sequential vs Parallel Showdown

Benchmark and compare performance for different scenarios.

**Requirements:**

1. Create a CPU-intensive function (e.g., calculating if a number is prime using trial division).
2. Test it on 1 million integers using both sequential and parallel streams.
3. Also test a trivial operation (multiply by 2) on 1 million integers — show that parallel is slower here.
4. **The Goal:** Demonstrate through measurement when parallel helps and when it hurts.

### Problem 2: The Custom Pool Isolation

Prevent a slow parallel stream from blocking other parallel operations.

**Requirements:**

1. Simulate a "slow I/O task" with `Thread.sleep(100)` inside a stream operation.
2. Show that running it on the common pool blocks other parallel streams.
3. Fix it by using a custom `ForkJoinPool` to isolate the slow stream.
4. **The Goal:** Demonstrate thread pool exhaustion and the custom pool solution.
