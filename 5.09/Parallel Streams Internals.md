We touched on Parallel Streams briefly in Section 4, but now that you understand the Stream API deeply, it's time to look **under the hood**. A Parallel Stream isn't magic — it's built on the **Fork/Join Framework** we studied earlier, and understanding its internals is the difference between getting a 4x speedup and getting a 4x slowdown.

---

## 1. How Parallel Streams Work Internally

When you call `.parallelStream()` or `.parallel()`, the Stream API uses the **Common Fork/Join Pool** to split your data across multiple threads.

```java
// Sequential: one thread processes all elements in order
list.stream().map(this::transform).collect(Collectors.toList());

// Parallel: multiple threads process chunks simultaneously
list.parallelStream().map(this::transform).collect(Collectors.toList());
```

### The Internal Steps:

1. **Splitting:** The data source is split into chunks using a `Spliterator`. For an `ArrayList`, this splits the array in half, recursively.
2. **Mapping:** Each chunk is processed by a separate thread from the Common Fork/Join Pool.
3. **Combining:** Results from each thread are combined back together (like the "join" in Fork/Join).

```
[1, 2, 3, 4, 5, 6, 7, 8]
         │
    ┌────┴────┐
 [1,2,3,4]  [5,6,7,8]       ← Split
    │           │
 [process]   [process]       ← Each thread works independently
    │           │
 [result1]   [result2]       ← Combine
         │
    [final result]
```

---

## 2. The Common Fork/Join Pool

By default, all parallel streams in your application share a **single** thread pool: the Common Fork/Join Pool.

```java
// Check the pool size (defaults to number of CPU cores - 1)
System.out.println(ForkJoinPool.commonPool().getParallelism());
// On a 8-core machine: 7

// You can configure it at startup
// -Djava.util.concurrent.ForkJoinPool.common.parallelism=16
```

### The Danger of Sharing:

If one parallel stream is doing I/O-bound work (database calls, HTTP requests), it will **block** threads in the common pool, starving other parallel streams in your application.

```java
// DANGEROUS: I/O in a parallel stream blocks the common pool
urls.parallelStream()
    .map(url -> httpClient.fetch(url))  // Each blocks a thread!
    .collect(Collectors.toList());
```

### The Fix: Use a Custom Pool

```java
ForkJoinPool customPool = new ForkJoinPool(16); // 16 threads for I/O work

List<String> results = customPool.submit(() ->
    urls.parallelStream()
        .map(url -> httpClient.fetch(url))
        .collect(Collectors.toList())
).get();

customPool.shutdown();
```

---

## 3. When Parallel Streams HELP

Parallel streams shine under specific conditions:

```java
// ✅ GOOD: Large dataset, CPU-intensive, independent operations
List<BigDecimal> prices = getMillionsOfPrices();
BigDecimal total = prices.parallelStream()
    .map(price -> price.multiply(TAX_RATE))   // CPU-heavy, no shared state
    .reduce(BigDecimal.ZERO, BigDecimal::add);

// ✅ GOOD: Embarrassingly parallel computation
long count = IntStream.range(0, 10_000_000)
    .parallel()
    .filter(this::isPrime)  // CPU-heavy, independent
    .count();
```

**Ideal conditions:**
- Dataset has **> 10,000 elements**
- Operations are **CPU-intensive** (not I/O)
- Operations are **stateless** and **independent**
- Source is **easily splittable** (ArrayList, arrays, IntStream.range)

---

## 4. When Parallel Streams HURT

```java
// ❌ BAD: Small dataset — overhead > benefit
List<Integer> small = List.of(1, 2, 3, 4, 5);
small.parallelStream().map(n -> n * 2).toList(); // Slower than sequential!

// ❌ BAD: LinkedList — terrible splitting performance
LinkedList<Integer> linked = new LinkedList<>(data);
linked.parallelStream().map(...); // Can't split efficiently

// ❌ BAD: Ordered operations like limit()
IntStream.range(0, 1_000_000)
    .parallel()
    .limit(10)         // Forces threads to coordinate on order
    .forEach(System.out::println);

// ❌ BAD: Shared mutable state
List<Integer> results = new ArrayList<>(); // NOT thread-safe!
numbers.parallelStream().forEach(results::add); // RACE CONDITION!

// ✅ Fix: Use collect instead
List<Integer> results = numbers.parallelStream().collect(Collectors.toList());
```

---

## 5. Spliterator: The Secret Engine

Every collection has a `Spliterator` that determines how well it can be parallelized.

| Source | Splitting Quality | Why |
| --- | --- | --- |
| `ArrayList` | ⭐⭐⭐ Excellent | Backed by array — splits by index |
| `IntStream.range()` | ⭐⭐⭐ Excellent | Perfectly balanced binary split |
| `HashSet` | ⭐⭐ Good | Splits by hash buckets |
| `TreeSet` | ⭐⭐ Good | Splits by subtree |
| `LinkedList` | ⭐ Poor | Must traverse to find midpoint |
| `Stream.iterate()` | ⭐ Poor | Sequential by nature |
| `BufferedReader.lines()` | ⭐ Poor | Sequential I/O source |

---

## 6. Order vs Performance

Parallel streams try to maintain **encounter order** by default. This costs performance.

```java
// Ordered — threads must coordinate to maintain element order
list.parallelStream().map(transform).forEachOrdered(System.out::println);

// Unordered — threads work freely, much faster
list.parallelStream().unordered().map(transform).forEach(System.out::println);

// For sets and maps, order is already unordered — no cost
set.parallelStream().map(transform).collect(Collectors.toSet()); // Fast!
```

---

## Summary: The Decision Checklist

| Question | If Yes → Parallel | If No → Sequential |
| --- | --- | --- |
| Is the dataset large (>10K)? | ✅ | ❌ |
| Is the work CPU-intensive? | ✅ | ❌ |
| Is the source easily splittable? | ✅ | ❌ |
| Are operations stateless? | ✅ | ❌ |
| Is order unimportant? | ✅ | ❌ |
| No shared mutable state? | ✅ | ❌ |

### Tip:

**Always benchmark before going parallel.** The overhead of splitting, thread coordination, and combining can easily exceed the benefit for small or I/O-bound workloads. Use `System.nanoTime()` or JMH (Java Microbenchmark Harness) to measure. The golden rule: **sequential by default, parallel by evidence.**
