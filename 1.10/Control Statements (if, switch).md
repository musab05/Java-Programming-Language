In Java, **Control Statements** determine the order in which the code is executed. By default, code runs line-by-line (sequentially), but control statements allow you to skip sections, repeat actions, or choose between different paths.

They are generally categorized into three types: **Decision-Making**, **Looping**, and **Branching**.

---

## 1. Decision-Making (Selection) Statements

These statements evaluate a boolean condition to decide which block of code to run.

### **If-Else Hierarchy**

* **`if` statement:** Executes a block only if the condition is true.
* **`if-else`:** Provides an alternative block if the condition is false.
* **`else if` ladder:** Used when you have multiple conditions to check in sequence.

```java
int score = 85;
if (score >= 90) {
    System.out.println("Grade: A");
} else if (score >= 80) {
    System.out.println("Grade: B"); // This will execute
} else {
    System.out.println("Grade: C");
}

```

### **Switch Statement**

The `switch` statement selects one of many code blocks to be executed. It is often more readable than a long `else if` ladder when comparing a single variable against multiple constant values.

* Works with: `byte`, `short`, `char`, `int`, `String`, and `Enums`.
* Requires a `break` keyword to stop "falling through" to the next case.

---

## 2. Branching (Jump) Statements

These are used to alter the flow within loops or methods.

| Statement | Purpose |
| --- | --- |
| **`break`** | Immediately terminates the loop or switch block. |
| **`continue`** | Skips the current iteration of a loop and moves to the next one. |
| **`return`** | Exits from the current method and optionally returns a value. |

---

## Summary Table

| Category | Statements | Best Use Case |
| --- | --- | --- |
| **Selection** | `if`, `else`, `switch` | Choosing between different paths based on data. |
| **Branching** | `break`, `continue`, `return` | Breaking out of a loop or finishing a function early. |
