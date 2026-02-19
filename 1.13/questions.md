# Method Overloading in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's compile-time polymorphism through method overloading, including the rules for parameter lists and the return type trap.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of static polymorphism and compiler behavior.*

1. **What is Method Overloading, and what type of polymorphism does it represent?**
* Method Overloading occurs when a class has multiple methods with the exact same name, but different parameter lists. It is a form of **Compile-Time (Static) Polymorphism** because the Java compiler determines exactly which method to execute during the build process based on the arguments provided.


2. **Can you overload a method by only changing its return type?**
* No. Changing only the return type is invalid. The compiler differentiates overloaded methods strictly by their parameter lists (method signature). If two methods have the exact same name and parameters but different return types, the compiler will throw a duplicate method error.


3. **What are the three specific ways you can change a parameter list to legally overload a method?**
* You can change the **number** of parameters, the **data types** of the parameters, or the **sequence** (order) of the data types.


4. **What is an "Ambiguity Error" in the context of overloading?**
* It happens when the compiler cannot determine which overloaded method is the best match for the provided arguments. This frequently occurs when implicit type casting makes the arguments technically valid for more than one overloaded method.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands the strict rules of method signatures.*

### 🗣️ Verbal/Conceptual Check

* **The Sequence Swap:** "If I declare `void save(int id, String name)` and then declare `void save(String name, int id)`, will the compiler allow this? Why?" *(Answer: Yes, it is perfectly valid. Even though the types are the same, the sequence/order of the parameters is different, which satisfies the overloading rules).*
* **The Return Type Trap:** "I want a method that returns a whole number and one that returns a decimal. Can I write `int getScore()` and `double getScore()` in the same class?" *(Answer: No. Since the parameter lists are identical (both are empty), the compiler sees them as the exact same method and will throw an error, regardless of the return types).*

### 💻 Practical/Coding Check

* **Find the Compilation Error:** "A developer wrote this utility class. Which method is causing a compilation error and why?"
```java
public class Printer {
    public void print(String text) { System.out.println(text); }
    public void print(int number) { System.out.println(number); }
    private String print(String message) { return "Printed: " + message; }
}

```


* **Solution:** The third method (`private String print(String message)`) causes an error. Even though the access modifier (`private`), the parameter name (`message`), and the return type (`String`) are different, the actual parameter *type* is identical to the first method (`String`). The compiler sees a duplicate signature.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of compile-time polymorphism.*

### Problem 1: The Shape Area Calculator (Changing Parameter Count)

**Scenario:** You are building a geometry tool that calculates the area of different shapes. You want a single, clean method name to handle both squares and rectangles.
**Task:**

1. Create a class named `AreaCalculator`.
2. Write a method named `calculateArea` that takes one `int` parameter (representing the side of a square) and returns the area (`side * side`).
3. Overload the `calculateArea` method to take two `int` parameters (representing the length and width of a rectangle) and return the area (`length * width`).
4. In your `main` method, call both overloaded methods and print their results.

### Problem 2: The Multi-Type Data Formatter (Changing Data Types)

**Scenario:** You need a utility that formats different types of raw data into clean console output.
**Task:**

1. Create a class named `DataFormatter`.
2. Write a method named `format` that accepts an `int` and prints: `"Integer value: [value]"`.
3. Overload the `format` method to accept a `double` and print: `"Decimal value: [value]"`.
4. Overload the `format` method one more time to accept a `String` and print: `"Text string: [value]"`.
5. Call all three methods in your `main` method to prove the compiler routes them correctly.
