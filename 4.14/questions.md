## 🎤 Frequently Asked Interview Questions

### 1. Why does `forEach()` behave differently in a Parallel Stream?

**Answer:** In a sequential stream, `forEach` maintains the encounter order of the collection. In a parallel stream, `forEach` is executed by multiple threads simultaneously; whichever thread finishes its "chunk" first prints its result. If you absolutely need to maintain order while processing in parallel, you must use `forEachOrdered()`, but be warned: this adds synchronization overhead that can negate the speed gains of parallelization.

### 2. What is "Spliterator" and why does it matter for Parallel Streams?

**Answer:** A `Spliterator` (Split-Iterator) is the engine behind the stream. It defines how a data source is partitioned. If you use an `ArrayList`, the Spliterator knows the exact size and can split the list perfectly in half ($O(1)$). If you use a `LinkedList`, the Spliterator has to crawl through the nodes to find the midpoint ($O(N)$), making it a terrible candidate for parallel processing.

### 3. Can you run a Parallel Stream in a custom `ForkJoinPool`?

**Answer:** Technically, yes. Although Parallel Streams use the common pool by default, you can wrap the stream execution inside a `pool.submit(() -> stream.parallel()...).get()` call. This is a common trick used to prevent a heavy parallel task from "starving" the rest of the application by hogging the common pool.

### 4. What is the "Identity" requirement in a parallel `reduce()`?

**Answer:** When reducing in parallel (e.g., summing numbers), the identity value must be a "neutral" element. For addition, it's `0`; for multiplication, it's `1`. If you use an identity like `10` for a sum, the parallel stream will add `10` to *every chunk* before combining them, leading to a mathematically incorrect (and much larger) final result.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Race Condition

**Task:** What is wrong with this parallel code?

```java
List<Integer> list = new ArrayList<>();
IntStream.range(0, 1000).parallel().forEach(list::add);

```

**Solution:** `ArrayList` is **not thread-safe**. Multiple threads will try to resize the internal array at the same time, leading to `ArrayIndexOutOfBoundsException` or lost data. You should use `.collect(Collectors.toList())` instead.

### Challenge 2: The Overhead Check

**Task:** You are summing the numbers 1 to 100. Should you use `.parallel()`?
**Solution:** **No.** The overhead of creating threads and joining the results for only 100 numbers will take significantly longer than a simple $O(N)$ sequential sum.

---

## 📝 Practice Problems

### Problem 1: The Parallel Prime Counter (CPU Bound)

Finding prime numbers is computationally expensive, making it a perfect candidate for parallelization.

**Requirements:**

1. Create a method `isPrime(int n)` that checks if a number is prime using a slow, brute-force method.
2. Generate a list of 100,000 random integers.
3. Use a **Sequential Stream** to count the primes and record the time taken.
4. Use a **Parallel Stream** to count the primes and record the time taken.
5. **Goal:** Compare the results. On a multi-core machine, the parallel version should be $2\times$ to $4\times$ faster.

### Problem 2: The Safe Grouping (Stateless Logic)

You have a list of `Employee` objects and you want to group them by department.

**Requirements:**

1. Create an `Employee` class with `name` and `department`.
2. Generate 1,000,000 mock employees across 5 departments.
3. Use `parallelStream()` and `Collectors.groupingByConcurrent()` to group them.
4. **The Catch:** Why use `groupingByConcurrent` instead of just `groupingBy`? (Hint: It’s faster because it uses a shared, thread-safe result container instead of merging multiple maps).
