# The `java.lang.Object` Class: Assessment Guide

The `Object` class is the foundation of Java's type system. Because every class inherits from it, its methods are the "universal language" of all Java objects.

## 🎤 Comprehensive Interview Questions

*These questions focus on the strict contracts between methods and the mechanics of the JVM.*

1. **Why is it said that if you override `equals()`, you must override `hashCode()`?**

* **The Concept:** This is the most frequent `Object` class question. Hash-based collections (like `HashMap`) use `hashCode()` to find the "bucket" where an object is stored and `equals()` to find the exact object within that bucket. If two objects are equal but have different hash codes, the collection will fail to find the object, leading to duplicate entries or data loss.

2. **What is the difference between `==` and the `equals()` method?**

* **The Concept:** `==` is an operator that compares **references** (memory addresses). It checks if two variables point to the exact same object. `equals()` is a method designed for **logical comparison** (content). By default, `equals()` behaves like `==` until you override it to compare specific fields (like a social security number or email).

3. **Can we override the `getClass()` method?**

* **The Concept:** No. It is marked as `final` in the `Object` class. This ensures that the metadata of an object remains reliable and cannot be spoofed through inheritance.

4. **What is the `clone()` method's relationship with the `Cloneable` interface?**

* **The Concept:** `Cloneable` is a **Marker Interface** (it has no methods). However, the `Object.clone()` method checks if the object's class implements this interface. If it doesn't, it throws a `CloneNotSupportedException`. This is a unique case where a method's behavior depends on the presence of an interface "tag."

5. **What are the `wait()` and `notify()` methods used for, and where must they be called?**

* **The Concept:** They are used for **Inter-Thread Communication**. A thread calls `wait()` to give up a lock and pause, and another thread calls `notify()` to wake it up. **Crucially**, these must only be called inside a `synchronized` block/method, or they will throw an `IllegalMonitorStateException`.

---

## 💡 Common Hurdles & Interview Pitfalls

* **The `toString()` Default Trap:** If you don't override `toString()`, printing your object will result in something like `com.myapp.User@15db9742`. In an interview, always mention that overriding this is essential for debugging and logging.
* **The `finalize()` Misconception:** Interviewers may ask how to ensure a file is closed using `finalize()`.
* **The Secret:** You should tell them `finalize()` is **deprecated** and unreliable. The correct answer is to use a `try-with-resources` block or the `AutoCloseable` interface.
* **The Shallow Copy vs. Deep Copy Trap:** By default, `clone()` performs a **shallow copy** (it copies the field values). If your object contains references to other objects, both the original and the clone will point to the same sub-objects. To fix this, you must manually perform a **deep copy**.

---

## 🛠️ Practice Problem Statements

### Problem 1: Implementing Logical Equality

**Scenario:** You have a `Product` class. Two products should be considered "equal" if they have the same `productID`, regardless of their price or stock levels.
**Task:**

1. Create a class `Product` with `int productID`, `String name`, and `double price`.
2. Override the `equals()` method to compare only the `productID`.
3. Override the `hashCode()` method to return a hash based on the `productID`.
4. In `main`, create two separate `Product` objects with the same ID and verify that `p1.equals(p2)` returns `true`.

### Problem 2: Meaningful String Representation

**Scenario:** You are building a student management system. When a student object is printed, it should display their name and GPA in a clean format instead of the memory address.
**Task:**

1. Create a `Student` class with `String name` and `double gpa`.
2. Override the `toString()` method to return: `"Student Name: [name], GPA: [gpa]"`.
3. In `main`, instantiate a student and simply use `System.out.println(myStudent);` to verify the output.

### Problem 3: The Cloning Contract

**Scenario:** You need to duplicate a `Settings` object so a user can experiment with changes without affecting the original "Default Settings."
**Task:**

1. Create a class `Settings` that **implements `Cloneable**`.
2. Add a field `int volumeLevel`.
3. Override the `clone()` method and change its visibility from `protected` to `public`.
4. In `main`, clone the settings, change the volume on the clone, and print both to ensure the original `volumeLevel` remained unchanged.
