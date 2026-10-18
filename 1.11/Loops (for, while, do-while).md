In Java, **Loops** are used to execute a block of code repeatedly as long as a specified condition remains `true`. They are essential for iterating through data structures or performing repetitive calculations without rewriting code.

Java provides four primary types of loops:

---

## 1. The `for` Loop

The `for` loop is most commonly used when you know exactly how many times the code should run. It groups the initialization, condition, and increment/decrement in one line.

**Syntax:**

```java
for (initialization; condition; update) {
    // code to be executed
}

```

**Example:**

```java
for (int i = 0; i < 5; i++) {
    System.out.println("Iteration: " + i);
}

```

---

## 2. The `while` Loop

The `while` loop is used when the number of iterations is not known in advance. It checks the condition **before** executing the loop body. If the condition is false initially, the code inside never runs.

**Syntax:**

```java
while (condition) {
    // code block
}

```

---

## 3. The `do-while` Loop

The `do-while` loop is a variant of the `while` loop, but with one critical difference: it checks the condition **after** executing the block. This guarantees the code will execute **at least once**, regardless of the condition.

**Syntax:**

```java
do {
    // code block
} while (condition);

```

---

## 4. The Enhanced `for-each` Loop

Introduced in Java 5, this loop is specifically designed to iterate through elements in **arrays** or **collections**. It is cleaner and prevents "off-by-one" errors because you don't need to manage an index ().

**Example:**

```java
String[] languages = {"Java", "Rust", "Python"};

for (String lang : languages) {
    System.out.println(lang);
}

```

---

## Comparison Summary

| Loop Type | Best Used When... | Check Condition | Guaranteed Execution |
| --- | --- | --- | --- |
| **`for`** | You know the exact number of iterations. | Before | 0 or more times |
| **`while`** | The loop depends on a dynamic condition. | Before | 0 or more times |
| **`do-while`** | You need the code to run at least once. | After | 1 or more times |
| **`for-each`** | You want to traverse an entire array/list. | Hidden | 0 or more times |

---

## Control Keywords in Loops

Sometimes you need to exit a loop early or skip a specific step:

* **`break`**: Immediately exits the entire loop.
* **`continue`**: Skips the rest of the current iteration and jumps to the next one (re-evaluating the condition).
