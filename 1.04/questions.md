# The `main()` Method: Assessment Guide

This guide provides interview and concept-check questions strictly focused on the signature, purpose, and JVM interaction of Java's `main()` method.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of Java's entry point.*

1. **Why must the `main` method be declared as `static`?**
* The JVM needs to execute the `main` method **before** any objects exist. The `static` keyword allows the method to be called directly through the class itself, without needing to create an instance (object) of the class first.


2. **Why is the access modifier for `main` set to `public`?**
* The JVM operates "outside" of your class. If the method were `private` (or anything other than `public`), the JVM would not have the necessary permissions to see and execute the code to start the program.


3. **What is the purpose of the `String[] args` parameter?**
* It allows the program to accept Command Line Arguments. It is an array of Strings that captures any extra input provided by the user when running the program from the terminal.


4. **Explain the exact sequence of events when a user types `java MyProgram` in the terminal.**
* First, the JVM is launched.
* Second, the Class Loader loads the `MyProgram.class` file.
* Third, the JVM searches specifically for the `public static void main(String[] args)` method.
* Finally, the JVM creates a "Stack Frame" for the method and begins executing the statements inside the curly braces.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands the strict rules and acceptable variations of the `main` method.*

### 🗣️ Verbal/Conceptual Check

* **The Case Sensitivity Test:** "If I capitalize the method name and write `public static void Main(String[] args)`, what will happen when I try to run the program?" (Answer: The program will fail to start and throw a "Main method not found" error because the JVM is hard-coded to look for exactly `main`).
* **The Return Type:** "Why does the `main` method use `void` instead of returning an `int` like in some other programming languages?" (Answer: Because it is just the starting point of the application and does not need to return a value back to the JVM).
* **The Missing Keyword:** "What happens if you accidentally leave out the `static` keyword? Will the compiler catch it?" (Answer: The program will actually compile just fine, but it will crash at runtime because the JVM won't be able to execute it as the entry point).

### 💻 Practical/Coding Check

* **The Valid Signatures Challenge:** "Review the following four `main` method signatures. Which ones are completely valid and will run successfully, and why do the others fail?"

```java
// Option A
public static void main(string[] args) { }

// Option B
static public void main(String[] myInput) { }

// Option C
public static void main(String... args) { }

// Option D
private static void main(String[] args) { }

```

* **Solution:**
* **Option A is Invalid:** `string` is lowercase. Java is case-sensitive and requires the class name `String`.
* **Option B is Valid:** The order of `static public` is legally allowed, and the parameter name can be changed from `args` to `myInput`.
* **Option C is Valid:** Using the varargs syntax `String... args` is a perfectly legal variation of `String[] args`.
* **Option D is Invalid:** The method must be `public` so the JVM can access it from the outside.

