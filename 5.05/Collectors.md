We've been using `collect(Collectors.toList())` throughout the Stream lessons, but `Collectors` is a massive utility class with over 30 built-in collectors. It's the "Swiss Army knife" of stream terminal operations — mastering it is what separates a beginner from someone who writes truly elegant stream pipelines.

The `Collectors` class lives in `java.util.stream` and provides factory methods for the most common "mutable reduction" operations: grouping, partitioning, joining, summarizing, and more.

---

## 1. Basic Collectors: Gathering into Collections

```java
List<String> names = List.of("Alice", "Bob", "Charlie", "Alice", "Bob");

// To a List
List<String> list = names.stream().collect(Collectors.toList());

// To an unmodifiable List (Java 10+)
List<String> unmodifiable = names.stream().collect(Collectors.toUnmodifiableList());

// To a Set (removes duplicates)
Set<String> set = names.stream().collect(Collectors.toSet());

// To a specific collection type
TreeSet<String> treeSet = names.stream()
    .collect(Collectors.toCollection(TreeSet::new));
// Result: [Alice, Bob, Charlie] — sorted and unique
```

---

## 2. Joining Strings

One of the most commonly used collectors — combining strings into a single result.

```java
List<String> words = List.of("Java", "Streams", "Are", "Powerful");

// Simple join
String joined = words.stream().collect(Collectors.joining());
// "JavaStreamsArePowerful"

// Join with delimiter
String csv = words.stream().collect(Collectors.joining(", "));
// "Java, Streams, Are, Powerful"

// Join with delimiter, prefix, and suffix
String formatted = words.stream().collect(Collectors.joining(" | ", "[", "]"));
// "[Java | Streams | Are | Powerful]"
```

---

## 3. Grouping: `groupingBy`

This is the `GROUP BY` of SQL — one of the most powerful collectors.

```java
List<Employee> employees = getEmployees();

// Group by department
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));

// Group by department, but collect only names (downstream collector)
Map<String, List<String>> namesByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.mapping(Employee::getName, Collectors.toList())
    ));

// Group by department, count employees
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
    ));

// Group by department, calculate average salary
Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

// Multi-level grouping (nested)
Map<String, Map<String, List<Employee>>> byDeptThenRole = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.groupingBy(Employee::getRole)
    ));
```

---

## 4. Partitioning: `partitioningBy`

A special case of grouping — splits into exactly **two groups**: `true` and `false`.

```java
Map<Boolean, List<Employee>> partitioned = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalary() > 75000));

List<Employee> highEarners = partitioned.get(true);
List<Employee> others = partitioned.get(false);
```

---

## 5. Summarizing: Statistics in One Call

```java
List<Integer> scores = List.of(85, 92, 78, 95, 88, 72, 91);

IntSummaryStatistics stats = scores.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));

System.out.println("Count: " + stats.getCount());     // 7
System.out.println("Sum: " + stats.getSum());          // 601
System.out.println("Min: " + stats.getMin());          // 72
System.out.println("Max: " + stats.getMax());          // 95
System.out.println("Average: " + stats.getAverage());  // 85.86
```

---

## 6. Converting to Maps: `toMap`

```java
// Simple: name → salary
Map<String, Double> salaryMap = employees.stream()
    .collect(Collectors.toMap(
        Employee::getName,   // key
        Employee::getSalary  // value
    ));

// Handle duplicate keys with a merge function
Map<String, Double> merged = employees.stream()
    .collect(Collectors.toMap(
        Employee::getDepartment,
        Employee::getSalary,
        Double::sum  // If same key, sum the salaries
    ));

// Control the map type
TreeMap<String, Double> sorted = employees.stream()
    .collect(Collectors.toMap(
        Employee::getName,
        Employee::getSalary,
        (a, b) -> a,       // merge function
        TreeMap::new        // map factory
    ));
```

---

## 7. Downstream Collectors (Composing Collectors)

The real power comes from **nesting** collectors. A downstream collector processes the elements *after* they've been grouped.

```java
// Group by department → get max salary in each
Map<String, Optional<Employee>> highestPaidPerDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
    ));

// Group by department → join names with commas
Map<String, String> nameListPerDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.mapping(Employee::getName, Collectors.joining(", "))
    ));
```

---

## Summary: Collector Cheat Sheet

| Collector | Purpose | Returns |
| --- | --- | --- |
| `toList()` | Collect to List | `List<T>` |
| `toSet()` | Collect to Set | `Set<T>` |
| `toMap(key, value)` | Collect to Map | `Map<K, V>` |
| `joining(delim)` | Concatenate strings | `String` |
| `groupingBy(fn)` | Group by classifier | `Map<K, List<T>>` |
| `partitioningBy(pred)` | Split into true/false | `Map<Boolean, List<T>>` |
| `counting()` | Count elements | `Long` |
| `summarizingInt()` | Get statistics | `IntSummaryStatistics` |
| `mapping(fn, downstream)` | Transform before collecting | Depends on downstream |
| `maxBy()` / `minBy()` | Find extremes | `Optional<T>` |

### Tip:

When you find yourself writing a `for` loop to populate a Map from a List, stop and use `Collectors.toMap()` or `Collectors.groupingBy()`. When you need a "report" (counts, averages, summaries per category), reach for `groupingBy` with a downstream collector. It's the single most important collector to master.
