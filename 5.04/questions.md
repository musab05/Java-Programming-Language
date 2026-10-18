Mastering stream operations is about knowing which tool to reach for. Intermediate operations build the pipeline; terminal operations fire it. The order you chain intermediates directly impacts both correctness and performance.

The most important insight: streams process elements **one at a time through the entire pipeline**, not one operation at a time across all elements. This is how short-circuiting works — the stream can stop the moment it has what it needs.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between `map` and `flatMap`?

**Answer:** `map` transforms each element into exactly one output element (one-to-one mapping). `flatMap` transforms each element into zero or more elements and flattens the results into a single stream (one-to-many mapping). For example, `map` on a list of sentences gives you a `Stream<String[]>` (each sentence split into a word array), while `flatMap` gives you a `Stream<String>` (all words in a single flat stream).

### 2. What is the difference between `findFirst` and `findAny`?

**Answer:** `findFirst` always returns the first element in encounter order, even in parallel streams (at a performance cost). `findAny` is allowed to return any matching element, making it faster in parallel streams because threads don't need to coordinate on ordering. In sequential streams, `findAny` typically returns the first element, but this is not guaranteed.

### 3. Can `peek` be used for modifying elements?

**Answer:** Technically, if the elements are mutable objects, you *can* modify them inside `peek`. However, this is a very bad practice and violates the principle of non-interference. `peek` is designed for **debugging and logging only** — observing what flows through the pipeline without side effects. Modifying elements in `peek` leads to confusing, unpredictable behavior, especially in parallel streams.

### 4. What happens if you call `sorted()` on an infinite stream?

**Answer:** The program will never terminate (or will throw an `OutOfMemoryError`). `sorted()` is a stateful intermediate operation that must see **all** elements before it can produce output. On an infinite stream, it would try to buffer all elements forever. You must use `limit()` *before* `sorted()` when working with infinite streams.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The FlatMap Flatten

**Task:** Given a list of lists of integers, flatten them and find all unique even numbers, sorted.
**Solution:**

```java
List<List<Integer>> data = List.of(
    List.of(1, 2, 3), List.of(2, 4, 6), List.of(5, 6, 7)
);
List<Integer> result = data.stream()
    .flatMap(Collection::stream)
    .filter(n -> n % 2 == 0)
    .distinct()
    .sorted()
    .toList();
System.out.println(result); // [2, 4, 6]
```

### Challenge 2: The Short-Circuit

**Task:** Given an infinite stream of random integers (0-100), find the first number greater than 95.
**Solution:**

```java
int result = Stream.generate(() -> (int)(Math.random() * 101))
    .filter(n -> n > 95)
    .findFirst()
    .orElse(-1);
System.out.println("Found: " + result);
```

---

## 📝 Practice Problems

### Problem 1: The E-Commerce Order Processor

Simulate processing a list of orders through a multi-stage pipeline.

**Requirements:**

1. Create an `Order` class with `id`, `amount`, `status` (PENDING, SHIPPED, DELIVERED), and `customerName`.
2. Create at least 10 orders with varied data.
3. Build a stream pipeline that: filters for PENDING orders, sorts by amount descending, takes the top 5, maps to a formatted string like "Order #123 - $99.99 - Alice", and collects to a List.
4. Also use `anyMatch` to check if any order exceeds $500, and `reduce` to calculate total of all PENDING orders.
5. **The Goal:** Combine multiple intermediate and terminal operations in real-world scenarios.

### Problem 2: The Nested Data Flattener

Practice `flatMap` with real-world nested structures.

**Requirements:**

1. Create a `Department` class with `name` and `List<Employee>` employees.
2. Create a `Employee` class with `name` and `salary`.
3. Create 3 departments, each with 3-4 employees.
4. Using `flatMap`, get a single stream of all employees across all departments. Find: the highest-paid employee, the average salary, and all employees earning above the overall average.
5. **The Goal:** Show how `flatMap` turns nested hierarchies into flat, processable streams.
