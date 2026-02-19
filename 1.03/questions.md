# 1.3 Java Program Structure & Syntax: Assessment Guide

This guide contains frequent interview questions and conceptual checks based purely on the standard Java program template, key components, and syntax rules.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of Java's architectural rules.*

1. **Why must the `main` method be declared as `public static void`?**
* **`public`**: It allows the JVM to execute the method from outside the class.
* **`static`**: It allows the method to run without needing to create an instance (object) of the class first.
* **`void`**: It signifies that this method does not return any value.


2. **What is the relationship between a Java filename and its class definition?**
* Java is strictly case-sensitive. The `public class` name must match the filename exactly. For example, a file named `Main.java` must contain `public class Main`.


3. **What is the purpose of `package` and `import` statements at the top of a Java file?**
* **Packages** act like folders to organize classes and prevent naming conflicts.
* **Imports** allow the program to use pre-written code from the Java Library (like bringing in `java.util.Scanner` for user input).


4. **How does the JVM know where a single instruction ends and where a block of code begins?**
* Every individual statement must end with a semicolon (`;`). Blocks of code, which group statements belonging to a class or method, are defined by curly braces (`{ }`).



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands the strict rules of writing a Java program.*

### 🗣️ Verbal/Conceptual Check

* **The Case Sensitivity Test:** "If I declare a class called `MyProgram`, but save the file as `myprogram.java`, will the compiler accept it? Why or why not?" (Answer: No, Java is completely case-sensitive and the filename must match the public class name exactly).
* **The Entry Point:** "If a Java class contains methods and logic but is missing `public static void main(String[] args)`, what will happen when you try to run it?" (Answer: The JVM will not know how to run the program because it lacks the mandatory starting line).
* **Parameter Understanding:** "What is the purpose of the `String[] args` parameter inside the `main` method?" (Answer: It is an array of strings that accepts command-line arguments passed to the program when it runs).

### 💻 Practical/Coding Check

* **The Syntax Fix:** "Find the 4 strict syntax and structure errors in this code snippet, assuming the file is saved as `App.java`."

```java
public class app {
    public void main(String[] args) {
        System.out.println("Hello, Java!")
    }
}

```

* **Solution:**
1. The class name `app` does not match the filename `App.java` (case sensitivity).
2. The `main` method is missing the `static` keyword.
3. The `System.out.println` statement is missing a semicolon (`;`) at the end.
4. (Optional but good to note) Missing documentation, packages, or imports, though these are technically optional according to the template.
