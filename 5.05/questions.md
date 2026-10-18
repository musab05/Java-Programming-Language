Collectors are the final mile of your stream pipeline. Everything you've filtered, mapped, and sorted needs to land somewhere — and Collectors determine that destination. Mastering `groupingBy` with downstream collectors is arguably the single highest-leverage skill in the entire Stream API.

The key insight is that collectors are composable: you can nest them inside each other to perform complex aggregations in a single, readable pipeline that would otherwise require multiple loops and temporary maps.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between `groupingBy` and `partitioningBy`?

**Answer:** `groupingBy` divides elements into multiple groups based on a classifier function, resulting in a `Map<K, List<T>>` with any number of keys. `partitioningBy` always divides elements into exactly two groups (true and false) based on a predicate, resulting in a `Map<Boolean, List<T>>`. Even if no elements match the predicate, the map will contain both `true` and `false` keys (with the non-matching one having an empty list).

### 2. What happens with duplicate keys in `Collectors.toMap()`?

**Answer:** By default, `toMap()` throws an `IllegalStateException` if two elements map to the same key. To handle duplicates, you must provide a **merge function** as the third argument — for example, `(existing, replacement) -> existing` to keep the first value, or `Double::sum` to combine values.

### 3. What is a "downstream collector"?

**Answer:** A downstream collector is a second collector passed to `groupingBy`, `partitioningBy`, or `mapping` that processes the elements *within each group* after they've been classified. For example, `groupingBy(Employee::getDept, counting())` groups by department and then counts the employees in each group. Without a downstream collector, the default is `toList()`.

### 4. How do you create a custom Collector?

**Answer:** You can use `Collector.of(supplier, accumulator, combiner, finisher, characteristics)` to build a custom collector. However, this is rarely needed because the built-in collectors in the `Collectors` utility class cover nearly all common use cases. Composition of existing collectors is preferred over custom ones.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The CSV Builder

**Task:** Given a list of integers, produce a string like "(1, 2, 3, 4, 5)" using `Collectors.joining()`.
**Solution:**

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);
String result = nums.stream()
    .map(String::valueOf)
    .collect(Collectors.joining(", ", "(", ")"));
System.out.println(result); // (1, 2, 3, 4, 5)
```

### Challenge 2: The Frequency Map

**Task:** Given a list of words, create a frequency map using `groupingBy` and `counting`.
**Solution:**

```java
List<String> words = List.of("apple", "banana", "apple", "cherry", "banana", "apple");
Map<String, Long> freq = words.stream()
    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
System.out.println(freq); // {apple=3, banana=2, cherry=1}
```

---

## 📝 Practice Problems

### Problem 1: The Sales Report Generator

Build a comprehensive sales report using advanced collectors.

**Requirements:**

1. Create a `Sale` class with `product`, `category`, `amount`, and `region` fields.
2. Create at least 12 sales entries across categories ("Electronics", "Clothing", "Food") and regions ("North", "South", "East", "West").
3. Generate: (a) total sales per category, (b) average sale amount per region, (c) the highest single sale per category, and (d) a comma-separated list of products per category.
4. **The Goal:** Use `groupingBy` with different downstream collectors (`summingDouble`, `averagingDouble`, `maxBy`, `mapping` + `joining`).

### Problem 2: The Data Partitioner

Build a student grading system using `partitioningBy`.

**Requirements:**

1. Create a `Student` class with `name` and `score`.
2. Partition students into passing (score >= 60) and failing groups.
3. For each group, display the count, the average score, and all names joined by commas.
4. **The Goal:** Chain `partitioningBy` with downstream collectors to build a complete report from a single stream pipeline.
