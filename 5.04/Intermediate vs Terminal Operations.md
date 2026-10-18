Now that you understand the Stream pipeline, it's time to master the individual operations that make up that pipeline. Every stream operation falls into one of two categories: **Intermediate** (lazy builders) and **Terminal** (eager triggers). Knowing the difference — and knowing which operations are available — is essential.

---

## 1. Intermediate Operations (The Lazy Ones)

These operations return a **new Stream** and are **lazy** — they don't process any data until a terminal operation kicks things off. You can chain as many as you want.

### Filtering & Slicing:

```java
List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);

// filter — keep only elements that match a condition
numbers.stream().filter(n -> n > 3);           // [4, 5, 9, 6, 5, 5]

// distinct — remove duplicates (uses equals/hashCode)
numbers.stream().distinct();                    // [3, 1, 4, 5, 9, 2, 6]

// limit — take the first N elements
numbers.stream().limit(4);                      // [3, 1, 4, 1]

// skip — skip the first N elements
numbers.stream().skip(3);                       // [1, 5, 9, 2, 6, 5, 3, 5]

// takeWhile (Java 9+) — take elements WHILE condition is true, stop at first failure
numbers.stream().takeWhile(n -> n < 5);         // [3, 1, 4, 1]

// dropWhile (Java 9+) — drop elements WHILE condition is true, keep the rest
numbers.stream().dropWhile(n -> n < 5);         // [5, 9, 2, 6, 5, 3, 5]
```

### Transforming:

```java
List<String> names = List.of("Alice", "Bob", "Charlie");

// map — transform each element
names.stream().map(String::toUpperCase);        // [ALICE, BOB, CHARLIE]
names.stream().map(String::length);             // [5, 3, 7]

// flatMap — flatten nested structures into one stream
List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
nested.stream().flatMap(Collection::stream);    // [1, 2, 3, 4, 5]

// peek — look at elements without changing them (for debugging)
names.stream()
    .peek(n -> System.out.println("Before: " + n))
    .map(String::toUpperCase)
    .peek(n -> System.out.println("After: " + n))
    .collect(Collectors.toList());
```

### Sorting:

```java
// sorted — natural order
names.stream().sorted();                        // [Alice, Bob, Charlie]

// sorted with Comparator
names.stream().sorted(Comparator.comparingInt(String::length));  // [Bob, Alice, Charlie]
names.stream().sorted(Comparator.reverseOrder());                // [Charlie, Bob, Alice]
```

---

## 2. Terminal Operations (The Eager Ones)

Terminal operations **trigger** the pipeline and produce a final result. After a terminal operation, the stream is consumed and cannot be reused.

### Collecting Results:

```java
// collect — gather results into a collection
List<String> list = names.stream().collect(Collectors.toList());
Set<String> set = names.stream().collect(Collectors.toSet());

// toList() (Java 16+) — shorter, returns unmodifiable list
List<String> list = names.stream().toList();

// toArray — convert to an array
String[] array = names.stream().toArray(String[]::new);
```

### Iterating:

```java
// forEach — do something with each element (no return value)
names.stream().forEach(System.out::println);

// forEachOrdered — guaranteed order (important in parallel streams)
names.parallelStream().forEachOrdered(System.out::println);
```

### Reducing:

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);

// reduce — combine all elements into one
Optional<Integer> sum = nums.stream().reduce((a, b) -> a + b);    // Optional[15]
int sumWithIdentity = nums.stream().reduce(0, Integer::sum);       // 15

// count
long count = nums.stream().filter(n -> n > 3).count();             // 2

// min / max
Optional<Integer> max = nums.stream().max(Integer::compareTo);     // Optional[5]
```

### Matching & Finding:

```java
// anyMatch — does at least one element match?
boolean hasEven = nums.stream().anyMatch(n -> n % 2 == 0);         // true

// allMatch — do ALL elements match?
boolean allPositive = nums.stream().allMatch(n -> n > 0);          // true

// noneMatch — do ZERO elements match?
boolean noneNegative = nums.stream().noneMatch(n -> n < 0);        // true

// findFirst — get the first element (respects encounter order)
Optional<Integer> first = nums.stream().filter(n -> n > 3).findFirst(); // Optional[4]

// findAny — get any element (better for parallel streams)
Optional<Integer> any = nums.stream().filter(n -> n > 3).findAny();
```

---

## 3. The `flatMap` Deep Dive

`flatMap` is the most confusing but most powerful intermediate operation. It "flattens" nested structures.

```java
// Problem: You have a list of sentences. Get all individual words.
List<String> sentences = List.of("Hello World", "Java Streams", "Are Awesome");

// map gives you Stream<String[]> — not what you want!
// flatMap gives you Stream<String> — perfect!
List<String> words = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .collect(Collectors.toList());
// Result: [Hello, World, Java, Streams, Are, Awesome]
```

---

## 4. Short-Circuiting Operations

Some operations don't need to process the entire stream. They "short-circuit" as soon as they have enough information:

| Operation | Type | Short-Circuits When... |
| --- | --- | --- |
| `limit(n)` | Intermediate | Has collected `n` elements |
| `findFirst()` | Terminal | Finds the first match |
| `findAny()` | Terminal | Finds any match |
| `anyMatch()` | Terminal | One element matches |
| `allMatch()` | Terminal | One element fails |
| `noneMatch()` | Terminal | One element matches |

This is why infinite streams work — as long as you use a short-circuiting operation:

```java
// Without limit, this would run FOREVER
Stream.iterate(1, n -> n + 1)
    .filter(n -> n % 7 == 0)
    .limit(5)
    .forEach(System.out::println); // 7, 14, 21, 28, 35
```

---

## Summary: Quick Reference Table

| Operation | Type | Returns | Lazy? |
| --- | --- | --- | --- |
| `filter` | Intermediate | Stream | ✅ |
| `map` | Intermediate | Stream | ✅ |
| `flatMap` | Intermediate | Stream | ✅ |
| `sorted` | Intermediate | Stream | ✅ (stateful) |
| `distinct` | Intermediate | Stream | ✅ (stateful) |
| `limit` / `skip` | Intermediate | Stream | ✅ |
| `peek` | Intermediate | Stream | ✅ |
| `collect` | Terminal | Result | ❌ |
| `forEach` | Terminal | void | ❌ |
| `reduce` | Terminal | Optional/Value | ❌ |
| `count` | Terminal | long | ❌ |
| `anyMatch` | Terminal | boolean | ❌ |
| `findFirst` | Terminal | Optional | ❌ |

### Tip:

**Stateful vs. Stateless Intermediate Operations:** Most intermediate ops (`filter`, `map`) are stateless — they process each element independently. But `sorted`, `distinct`, and `limit` are **stateful** — they need to "remember" things. Stateful operations can be expensive, especially in parallel streams. Always filter *before* sorting to reduce the dataset.
