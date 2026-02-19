# Packages in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on how Java organizes files, resolves naming conflicts, and protects code through packages.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of project architecture and access control.*

1. **What are the three primary reasons we use packages in Java?**
* **Organization:** Grouping related classes together (like all database models in one folder).
* **Name Resolution:** Preventing naming conflicts (allowing two classes named `User` to exist in the same project, provided they are in different packages).
* **Security/Access Protection:** Utilizing "package-private" access modifiers to restrict certain classes so they can only be used by other classes within the same package.


2. **Do you need to explicitly import the `java.lang` package to use classes like `String` or `Math`?**
* No. The Java compiler automatically imports `java.lang` into every single Java file by default.


3. **What is the difference between `import java.util.Scanner;` and `import java.util.*;`?**
* The first imports *only* the specific `Scanner` class. The second uses the wildcard (`*`) to import *all* classes located directly inside the `java.util` package.


4. **If a class declares `package com.academiq.logic;`, where *must* the physical `.java` file be located on the computer?**
* The file's physical directory structure must exactly mirror the package name. It must be located inside a folder path ending in `com/academiq/logic/`.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands compilation rules and wildcard limitations.*

### 🗣️ Verbal/Conceptual Check

* **The First Line Rule:** "Can I put my `import` statements at the very top of my file, above the `package` declaration, to keep things organized?" *(Answer: No. If a file belongs to a package, the `package` declaration must be the absolute first non-comment line in the file).*
* **The Wildcard Trap:** "If I write `import java.util.*;`, does it also import classes located inside sub-packages, like `java.util.concurrent`?" *(Answer: No. The wildcard only imports classes directly inside that specific folder/package. It does not recursively import sub-packages).*

### 💻 Practical/Coding Check

* **The Conflict Resolution:** "A developer is writing a program that requires Java's built-in `java.util.Date` class, but they also need to use a custom `com.company.Date` class in the exact same file. Since they have the same name, how can the developer use both without the compiler getting confused?"
* **Solution:** They cannot import both. They must import one (or neither) and use the **Fully Qualified Class Name** (the package name + class name) when declaring the variables. For example: `java.util.Date today = new java.util.Date();`



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of package creation and cross-package importing.*

### Problem 1: The Fully Qualified Name (Conflict Resolution)

**Scenario:** You are building the "Academiq" platform. You have two different `User` classes because administrators and students have different data structures. One is in `com.academiq.admin` and the other is in `com.academiq.student`.
**Task:** 1. Create a `main` method inside a new class.
2. Without using *any* `import` statements at the top of the file, instantiate an object of the Admin's `User` class.
3. Instantiate an object of the Student's `User` class.
*(Hint: Use the fully qualified class names).*

### Problem 2: The Cross-Package Utility

**Scenario:** You are separating your project into different modules. You need a utility class in one package to be used by the main application in another package.
**Task:** Describe the folder structure and write the code for two separate files:

1. **File 1 (`MathHelper.java`):** Declare it in the `com.academiq.utils` package. Create a `public static int multiply(int a, int b)` method inside it.
2. **File 2 (`App.java`):** Declare it in the `com.academiq.main` package. Write the `import` statement required to get the `MathHelper` class. Write a `main` method that calls `MathHelper.multiply(5, 5)` and prints the result.
