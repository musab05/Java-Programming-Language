In Java, an **Inner Class** is simply a class defined within the scope of another class. Think of it like a nesting doll: the inner class is "contained" by the outer class, allowing for a closer relationship between the two.

Inner classes are primarily used to logically group classes that are only used in one place, which increases encapsulation and makes the code more maintainable.

---

## Types of Inner Classes

Java categorizes these into four main types, each with its own specific use case.

### 1. Member Inner Class (Non-Static)

This is the most common type. It is declared inside a class but outside any method. Because it is tied to an **instance** of the outer class, it can access all private members (fields and methods) of the outer class directly.

* **Key Rule:** You must create an instance of the outer class before you can instantiate the inner class.

```java
class Outer {
    private String secret = "I'm a private member!";

    class Inner {
        void display() {
            System.out.println("Accessing: " + secret);
        }
    }
}

```

### 2. Static Nested Class

While technically defined inside another class, it has the `static` keyword. This means it behaves like a top-level class and **cannot** access non-static members of the outer class.

* **Key Rule:** You don't need an instance of the outer class to create it.

### 3. Local Inner Class

These are defined inside a **method body**. Their scope is strictly limited to that method. They are great for "one-off" logic that shouldn't be visible to the rest of the class.

### 4. Anonymous Inner Class

An inner class without a name. It is used to declare and instantiate a class at the same time, usually when overriding a method of an interface or a class on the fly.

---

## Comparison Table

| Feature | Member Inner | Static Nested | Local Inner | Anonymous |
| --- | --- | --- | --- | --- |
| **Scope** | Inside Outer class | Inside Outer class | Inside a Method | Where it's created |
| **Access to Outer** | All members | Only static members | All (if in instance method) | All (if in instance method) |
| **Static Keyword** | No | Yes | No | No |
| **Usage** | Helper logic | Namespace grouping | Specific method logic | Event handling/Listeners |

---

## Why Use Them?

* **Encapsulation:** If Class B only exists to serve Class A, hiding B inside A keeps the package cleaner.
* **Logical Grouping:** It makes the relationship between classes explicit.
* **Access Privileges:** Inner classes can access the private data of the outer class, which avoids having to create public "getter" methods that might expose data unnecessarily.

> **Note:** Be careful with memory! Non-static inner classes hold an implicit reference to their outer class. In Android or long-running applications, this can sometimes lead to **memory leaks** if the inner class outlives the outer one.
