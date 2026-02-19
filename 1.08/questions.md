# Type Casting in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on how Java handles converting one data type into another through Widening, Narrowing, and Object Casting.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of data conversion and data loss prevention.*

1. **What is the difference between Implicit (Widening) and Explicit (Narrowing) casting?**
* **Widening (Implicit):** Converting a smaller data type to a larger one (e.g., `int` to `double`). It happens automatically because there is no risk of data loss.
* **Narrowing (Explicit):** Converting a larger data type to a smaller one (e.g., `double` to `int`). It must be done manually by the programmer because it can result in data loss (like chopped-off decimals).


2. **When converting a `double` like `9.99` to an `int`, does Java round the number up to `10`?**
* No. Java simply *truncates* (chops off) the decimal portion during a narrowing cast. The result will be `9`.


3. **What is the "Integer Division" trap in Java?**
* If you divide two integers (e.g., `5 / 2`), Java performs integer division and drops the remainder, resulting in `2` instead of `2.5`. To get the decimal, you must cast at least one of the integers to a `double` before dividing: `(double) 5 / 2`.


4. **What happens if you perform an invalid Downcast on an object?**
* If you try to downcast a parent object into a child type that it doesn't actually belong to, the code will compile, but the JVM will throw a `ClassCastException` at runtime and crash the program.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands the rules of casting and compiler behavior.*

### 🗣️ Verbal/Conceptual Check

* **The Compiler Catch:** "If I write `int myNumber = 100.50;`, what will the compiler do?" *(Answer: It will throw a compilation error saying "incompatible types: possible lossy conversion from double to int". You must explicitly cast it like `int myNumber = (int) 100.50;`)*.
* **Upcasting vs. Downcasting:** "Is upcasting (Child to Parent) an implicit or explicit process in Java?" *(Answer: Implicit. A child always shares an "is-a" relationship with its parent, so it's perfectly safe and automatic).*

### 💻 Practical/Coding Check

* **The Buggy Calculator:** "Why is `result` outputting `0.0`, and how do we fix it to output `0.5`?"
```java
int a = 1;
int b = 2;
double result = a / b;
System.out.println(result);

```


* **Solution:** Fix it by explicitly casting `a` (or `b`) to a double *before* the division happens: `double result = (double) a / b;`.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of explicit casting and the integer division rules.*

### Problem 1: The Average Score (Integer Division Fix)

**Scenario:** You are writing a grading program. A student took 3 exams and scored `88`, `92`, and `89`. You need to calculate their exact average, including decimals.
**Task:** 1. Declare an `int` variable for the total sum of the scores.
2. Declare an `int` variable for the number of exams (3).
3. Calculate the exact average and store it in a `double` variable. Print the result.
*(Hint: Make sure you don't fall into the integer division trap!)*

### Problem 2: The Data Truncator (Explicit Cast)

**Scenario:** A bank system calculates daily interest and stores it as a `double` (e.g., `$45.89`). However, the display module only shows whole dollar amounts to the user.
**Task:**

1. Declare a `double` named `exactInterest` and set it to `45.89`.
2. Convert this value into an `int` called `displayDollars` using the correct casting syntax.
3. Print both variables to show how the decimal was dropped.
