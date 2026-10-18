The Stream API is a paradigm shift in how you think about data processing in Java. Instead of telling the machine *how* to iterate, filter, and collect, you describe *what* you want. The JVM figures out the rest — and it can even parallelize it for free.

The biggest mental shift is understanding **laziness**: nothing in a stream pipeline happens until a terminal operation triggers it. This isn't just an optimization trick — it fundamentally changes how you design your code.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between a Collection and a Stream?

**Answer:** A Collection is a data structure that holds elements in memory. You can add, remove, and access elements. A Stream is a pipeline of operations on data — it doesn't store anything. Collections are eagerly populated; Streams are lazily evaluated. You can iterate a Collection multiple times; a Stream can only be consumed once. Finally, Streams support functional-style operations like `map`, `filter`, and `reduce`, while Collections use iterators and loops.

### 2. What does "lazy evaluation" mean in the Stream API?

**Answer:** Lazy evaluation means intermediate operations (`filter`, `map`, `sorted`) don't execute immediately. They are stored as a recipe. Execution only happens when a terminal operation (`collect`, `count`, `forEach`) is invoked. This allows the JVM to optimize the pipeline — for example, if you use `findFirst()` after `filter()`, it stops processing as soon as the first match is found, rather than filtering the entire collection.

### 3. Can you reuse a Stream?

**Answer:** No. Once a terminal operation has been called, the Stream is "consumed" and cannot be reused. Attempting to call another terminal operation will throw an `IllegalStateException`. If you need to perform multiple operations, either call `.stream()` again on the source, or use a `Supplier<Stream<T>>` to create fresh streams on demand.

### 4. What happens if you never call a terminal operation?

**Answer:** Nothing. The intermediate operations are never executed. The stream pipeline is just a set of instructions waiting to be triggered. No data is processed, no side effects occur. This is a common bug — developers sometimes build a pipeline and forget the terminal operation, wondering why nothing happened.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Stream from Scratch

**Task:** Create an infinite stream of even numbers starting from 2 and print the first 10.
**Solution:**

```java
Stream.iterate(2, n -> n + 2)
    .limit(10)
    .forEach(System.out::println);
// Output: 2, 4, 6, 8, 10, 12, 14, 16, 18, 20
```

### Challenge 2: Count the Words

**Task:** Given a sentence, use streams to count how many words have more than 4 letters.
**Solution:**

```java
String sentence = "The quick brown fox jumps over the lazy dog";
long count = Arrays.stream(sentence.split(" "))
    .filter(word -> word.length() > 4)
    .count();
System.out.println("Words with more than 4 letters: " + count); // 2 (quick, jumps, brown)
```

---

## 📝 Practice Problems

### Problem 1: The Student Report Card

Process a list of students using the Stream API.

**Requirements:**

1. Create a `Student` class with `name`, `grade` (int), and `subject` fields.
2. Create a list of at least 8 students across subjects "Math", "Science", and "English".
3. Using streams, find: (a) all students who scored above 80, (b) the average grade per subject, and (c) the top 3 students by grade.
4. **The Goal:** Show the power of stream pipelines for data analysis without any manual loops.

### Problem 2: The File Word Counter

Build a word frequency analyzer using streams.

**Requirements:**

1. Create a `String` containing a multi-line paragraph (simulate file content).
2. Using streams, split the text into words, convert to lowercase, remove punctuation, and count the frequency of each word.
3. Display the top 5 most frequent words.
4. **The Goal:** Chain multiple stream operations to perform text analysis in a single pipeline.
