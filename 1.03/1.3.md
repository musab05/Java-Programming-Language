In Java, every line of code must reside inside a **class**. The structure is strictly hierarchical, moving from the broad (package) to the specific (statements within a method).

---

## 1. The Standard Java Program Template

A basic Java file (e.g., `HelloWorld.java`) typically follows this specific order:

```java
// 1. Documentation Section (Optional)
/**
 * This program prints a message to the console.
 */

// 2. Package Statement (Optional)
package com.myproject.utils;

// 3. Import Statements (Optional)
import java.util.Scanner;

// 4. Class Definition (Mandatory)
public class MyProgram {

    // 5. Main Method (Entry Point)
    public static void main(String[] args) {
        
        // 6. Statements/Logic
        System.out.println("Hello, Java!");
    }
}

```

---

## 2. Key Components Explained

### **The Class Definition**

In Java, the class name must match the filename exactly (case-sensitive). If your file is `Main.java`, your class must be `public class Main`.

* **`public`**: An access modifier meaning this class is accessible from anywhere.
* **`class`**: The keyword used to declare a class.

### **The `main` Method**

This is the "Starting Line" of any Java application. Without this exact signature, the JVM will not know how to run your program.

* **`public`**: Allows the JVM to execute the method from outside the class.
* **`static`**: Allows the method to run without creating an "instance" (object) of the class first.
* **`void`**: The return type, meaning this method doesn't return any value.
* **`String[] args`**: A parameter that accepts command-line arguments as an array of strings.

### **Packages and Imports**

* **Packages**: Think of these as folders used to organize your classes and prevent naming conflicts.
* **Imports**: These allow you to use pre-written code from the Java Library (like `Scanner` for user input or `ArrayList` for collections).

---

## 3. Rules of Java Syntax

Java is "picky" about how you write your code. Keeping these rules in mind prevents common compiler errors:

1. **Semicolons (`;`):** Every statement (instruction) must end with a semicolon.
2. **Curly Braces (`{ }`):** These define a "block" of code. Everything belonging to a class or method must be inside them.
3. **Case Sensitivity:** Java treats `myVariable` and `MyVariable` as two completely different things.
4. **Comments:**
* `//` for single-line comments.
* `/* ... */` for multi-line comments.



---

## Here is a quick visual on how the structure differs:

| Feature | Java |
| --- | --- |
| **Entry Point** | Must be inside `public static void main` |
| **Blocks** | Defined by Curly Braces `{ }` |
| **Variables** | Static (must declare `int`, `String`, etc.) |
| **End of Line** | Semicolon `;` |
