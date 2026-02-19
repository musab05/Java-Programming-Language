In Java, a **Package** is a mechanism used to group related classes, interfaces, and sub-packages together. Think of it like a **folder system** on your computer that organizes your files to prevent clutter and naming conflicts.

---

## 1. Why Use Packages?

As programs grow, you might have hundreds of classes. Packages solve three main problems:

* **Organization:** Grouping related code (e.g., all database-related classes in one place).
* **Name Conflicts:** Two classes can have the same name (like `User`) as long as they belong to different packages (e.g., `com.academiq.admin.User` and `com.academiq.student.User`).
* **Access Protection:** You can use "package-private" access to hide classes from the rest of the application, keeping your logic secure.

---

## 2. Creating and Using Packages

### **Defining a Package**

To put a class into a package, the `package` keyword must be the **very first line** of your Java file.

```java
package com.academiq.utils; // Define the package

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

```

### **Importing a Package**

To use a class from another package, you use the `import` keyword. You can import a specific class or an entire package using the wildcard (`*`).

```java
import com.academiq.utils.Calculator; // Import specific class
// OR
import com.academiq.utils.*; // Import everything in the utils package

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}

```

---

## 3. Types of Packages

Java packages are categorized into two main groups:

### **A. Built-in Packages**

These are provided by the Java API (Application Programming Interface).

* **`java.lang`:** Contains fundamental classes (like `String`, `System`, `Math`). This package is **automatically imported** into every Java program.
* **`java.util`:** Contains utility classes like `Scanner`, `ArrayList`, and `Date`.
* **`java.io`:** Classes for input and output streams.
* **`java.net`:** Classes for networking operations.

### **B. User-defined Packages**

These are packages created by you to organize your project. By convention, these names are written in **all lowercase** and usually follow a reverse domain name (e.g., `com.username.project`).

---

## 4. The Directory Structure

The package name must match the folder structure exactly. If your package is `com.academiq.logic`, your file must be located in a directory path like:
`src/com/academiq/logic/MyClass.java`

---

## Summary of Benefits

| Feature | Description |
| --- | --- |
| **Encapsulation** | Control which classes are visible to other packages. |
| **Reusability** | Easily move a package (folder) to a new project. |
| **Maintainability** | Easier to locate files in large-scale projects. |
