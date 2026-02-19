# Variables and Data Types in Java: Assessment Guide

This guide provides interview questions, concept checks, and a hands-on coding problem focused strictly on how Java handles variable declarations, scopes, data types, and naming conventions.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of Java's memory and variable management.*

1. **Why is Java considered a "strongly typed" language?**
* Because every variable must be explicitly declared with a specific data type before it can be used, and it can only hold values compatible with that type.


2. **What is the difference between Primitive and Non-Primitive (Reference) data types?**
* **Primitives** (like `int`, `boolean`, `double`) hold simple, predefined values and are stored directly in the **Stack** memory.
* **Non-Primitives** (like `String`, Arrays, Objects) refer to complex data structures and are stored in the **Heap** memory.


3. **Explain the difference between Local, Instance, and Static variables.**
* **Local:** Declared inside a method/block, destroyed when the method ends, and *must* be initialized before use.
* **Instance:** Declared in a class. Each object created from the class gets its own unique copy.
* **Static:** Declared with the `static` keyword in a class. There is only *one* shared copy for all instances of the class.


4. **What happens if you try to use a local variable without initializing it?**
* The Java compiler will throw an error. Unlike instance variables (which get default values like `0` or `null`), local variables must be explicitly given a value before they are read.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands the strict rules of Java variables.*

### 🗣️ Verbal/Conceptual Check

* **The Shared Value Test:** "If I have a class called `Player` with a variable `int score = 0;`, and I create two players, does changing Player 1's score affect Player 2's score? What if the variable was `static int score = 0;`?" *(Answer: As an instance variable, they have separate scores. If it were static, they would share the exact same score variable).*
* **Naming Rules:** "Are `int $totalAmount` and `int _totalAmount` valid variable names in Java? What about `int 1stPlace`?" *(Answer: The first two are valid because names can start with `$` or `_`. The last one is invalid because a variable name cannot start with a digit).*
* **The Keyword Trap:** "Can I name my boolean variable `false` or my integer variable `class`?" *(Answer: No, those are Reserved Keywords in Java and cannot be used as identifiers).*

### 💻 Practical/Coding Check

* **Find the Syntax Errors:** "Identify the 4 mistakes in this variable declaration block."
```java
int user age = 25;
double salary = 50,000.50;
String Name = "Musab";
boolean is Student = True;
int void = 10;

```


* **Solution:**
1. Space in variable name (`user age` -> `userAge`).
2. Comma in numeric literal (`50,000.50` -> `50000.50`).
3. `True` is capitalized (must be lowercase `true` for the boolean primitive).
4. `void` is a reserved keyword and cannot be used as a name. *(Note: `Name` with a capital 'N' is technically legal syntax, but violates the lowerCamelCase convention).*





---

## 🛠️ Practice Problem Statement

*Write code to solve the following scenario, testing your grasp of variable scope and types.*

**Scenario: The Employee Management System**
You are building the foundation of an HR system. Create a Java class named `EmployeeProfile` that demonstrates your understanding of the different variable types and scopes.

**Requirements:**

1. **Static Variable:** Create a variable to hold the company name (e.g., "TechCorp"). This should be shared by all employees.
2. **Instance Variables:** Create variables for an individual employee's `fullName` (String), `employeeId` (int), and `isFullTime` (boolean).
3. **Local Variable:** Create a method called `calculateBonus()`. Inside this method, declare and initialize a local `double` variable called `bonusAmount` and set it to `500.00`. Print the bonus amount.
4. **Naming:** Ensure all variable names strictly follow Java's `lowerCamelCase` naming conventions.

**Goal:** Write out the `public class EmployeeProfile` containing these variables and the method. Compile it to ensure there are no syntax or typing errors!
