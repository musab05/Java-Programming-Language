**Method Overloading** is a feature in Java that allows a class to have more than one method with the **same name**, provided that their **parameter lists are different**.

It is a form of **Compile-Time Polymorphism** (or Static Polymorphism), meaning the Java compiler determines which method to call based on the arguments provided at the time of compilation.

---

## 1. How to Overload a Method

To overload a method, you must change at least one of the following:

1. **The number of parameters.**
2. **The data types of parameters.**
3. **The sequence of data types of parameters.**

> **Note:** Changing the **return type** alone is **not** enough to overload a method. If two methods have the same name and parameters but different return types, the compiler will throw an error.

---

## 2. Examples of Overloading

### **A. Changing the Number of Parameters**

```java
class Calculator {
    // Method with 2 parameters
    int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with 3 parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }
}

```

### **B. Changing the Data Types**

```java
class Display {
    // Method accepting an integer
    void show(int i) {
        System.out.println("Integer: " + i);
    }

    // Overloaded method accepting a String
    void show(String s) {
        System.out.println("String: " + s);
    }
}

```

---

## 3. Why Use Method Overloading?

* **Readability:** It’s easier to remember one method name (like `print`) for different data types rather than `printInt`, `printString`, etc.
* **Consistency:** It allows similar logic to be grouped under a single name, making the API cleaner.
* **Flexibility:** It allows methods to handle different scenarios or levels of detail based on the input provided.

---

## 4. Key Rules and "Gotchas"

| Feature | Can it define overloading? |
| --- | --- |
| **Method Name** | Must be the SAME. |
| **Parameter List** | Must be DIFFERENT. |
| **Return Type** | Can be different, but doesn't define overloading on its own. |
| **Access Modifier** | Can be different (e.g., one `public`, one `private`). |

### **The Ambiguity Error**

If the compiler cannot figure out which method is the best match (often due to automatic Type Casting, it will throw an "ambiguous" error. For example:

```java
void sum(int a, long b) { ... }
void sum(long a, int b) { ... }

// Calling sum(10, 10) will cause an error because 10 can be both int or long.

```
