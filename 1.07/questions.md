# Data Types in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's primitive and non-primitive data types, their memory allocation, and their specific characteristics.

## 🎤 Frequent Interview Questions

*Common technical questions to assess a developer's understanding of Java's type system and memory management.*

1. **What is the fundamental difference between how Primitive and Non-Primitive types are stored in memory?**
* **Primitives** store the actual data value directly in the **Stack** memory.
* **Non-Primitives (Reference types)** store a memory address (a pointer) in the Stack, which points to the actual object data stored in the **Heap** memory.


2. **Why does Java bother having Primitive types if Non-Primitive objects are more flexible?**
* **Memory Efficiency and Speed:** Primitives are "cheap" and fast. Because they have a fixed size and live on the Stack, the JVM can access and manage them much faster than objects that require Heap allocation and garbage collection.


3. **What happens if you declare a variable but do not initialize it with a value?**
* If it is a class-level variable, a **Primitive** will default to a standard value (like `0` for numeric types or `false` for booleans). A **Non-Primitive** will default to `null`.


4. **Can you call a method on an `int` or a `double`?**
* No. Primitive types are just raw values; they do not have methods. Only Non-Primitive (Reference) types can invoke methods (e.g., `myString.length()`).



---

## 💡 Concept Check Questions

*Use these questions to verify if a user understands the practical implications of Java's strict typing.*

### 🗣️ Verbal/Conceptual Check

* **The Null Trap:** "If I am building an application and want to represent a user who hasn't provided their age yet, can I write `int age = null;`? Why or why not?" *(Answer: No. Primitives cannot be null. You would either need to use a dummy value like -1, or use a Reference type wrapper class like `Integer` which can be null).*
* **The Suffix Rule:** "When declaring variables, why do we sometimes write `float price = 19.99f;` or `long distance = 5000000000L;`?" *(Answer: Because Java defaults to `double` for decimals and `int` for whole numbers. The 'f' and 'L' explicitly tell the compiler to treat and size the values as `float` and `long`).*
* **Memory Address Question:** "If I create an array `int[] numbers = {1, 2, 3};`, is the array itself a primitive? Where does it live?" *(Answer: No, an array is a Non-Primitive Reference type. The reference variable `numbers` lives on the Stack, but the actual array `{1, 2, 3}` is constructed in the Heap).*

---

## 💻 Practice Problem Statements

*Write code to solve the following scenarios. These test your ability to choose the correct data types based on memory constraints and rules.*

### Problem 1: Optimizing Memory (The Smart Declarations)

**Scenario:** You are building a system for a tiny embedded device with very limited memory. You need to store the following data points about a single smart-thermostat.
**Task:** Declare and initialize variables for each of these using the *most memory-efficient* data type possible based on the provided limits.

1. The current temperature in whole numbers (ranges from -20 to 120).
2. The exact price of the unit (e.g., 149.99).
3. The Wi-Fi connection status (connected or not).
4. The manufacturer's initial (e.g., 'N' for Nest).

### Problem 2: The Compilation Fixer

**Scenario:** A junior developer wrote the following code snippet, but the compiler is throwing multiple errors regarding types and methods.
**Task:** Identify the three errors and rewrite the code so it compiles successfully.

```java
public class DataTypeTest {
    public static void main(String[] args) {
        boolean isActive = null;
        int maxUsers = 10000000000;
        double pi = 3.14;
        
        System.out.println("Max users length is: " + maxUsers.length());
    }
}

```

* **Solution/Hints for the user:**
1. `boolean` is a primitive and cannot be `null` (change to `false` or `true`).
2. `10000000000` is too large for a standard `int` (change type to `long` and append `L`).
3. `maxUsers` is a primitive and does not have methods like `.length()` (must just print the variable directly).
