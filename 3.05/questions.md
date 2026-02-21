## 🎤 Frequently Asked Interview Questions

### 1. What is the fundamental difference between `throw` and `throws`?

**Answer:** * **`throw`** is the physical action of creating an error. It is used inside a method body, followed by a specific object instance (`throw new Exception()`), and can only throw one exception at a time.

* **`throws`** is a warning label. It goes in the method signature, is followed by class names (`throws IOException, SQLException`), and tells the compiler that the caller of this method is responsible for handling the potential errors.

### 2. Why does Java prevent a child class from throwing a broader or new Checked Exception when overriding a parent method?

**Answer:** This enforces polymorphism and the Liskov Substitution Principle. Imagine a program expects to use a `Parent` object and knows it only has to handle an `IOException`. If you pass it a `Child` object instead, and that child suddenly throws a completely new `SQLException`, the program wouldn't have a `catch` block ready for it. The program would crash! Java prevents this at compile time.

### 3. If a parent method does not throw any exceptions, how can a child class handle a scenario where it *needs* to trigger an error?

**Answer:** The child class cannot add a Checked Exception to the method signature. It has two options:

1. Wrap the dangerous code in a `try-catch` block and handle the Checked Exception internally.
2. Throw an **Unchecked Exception** (like `RuntimeException` or `IllegalArgumentException`), because the compiler does not force unchecked exceptions to be declared in the method signature.

### 4. Can you use `throw` without `throws`? Can you use `throws` without `throw`?

**Answer:** Yes to both!

* You can `throw` an Unchecked Exception without declaring `throws` in the signature.
* You can declare `throws IOException` on a method without actually writing a `throw` statement inside it (usually because you are calling *another* method inside it that throws the exception).

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Syntax Mix-Up

**Task:** Identify the two compilation errors in this method.

```java
public void validateUser(String name) throw Exception {
    if (name == null) {
        throws new IllegalArgumentException("Name cannot be null");
    }
}

```

**Solution:** The keywords are swapped!

1. The method signature should use `throws` (the warning label), followed by the class name.
2. The method body should use `throw` (the action), followed by the object instance.
*(Corrected: `public void validateUser(String name) throws Exception { ... throw new ... }`)*

### Challenge 2: The Overriding Hierarchy

**Task:** Look at the `NetworkDevice` parent class. Which of the overridden `ping()` methods in the child classes will fail to compile?

```java
import java.io.IOException;
import java.net.ConnectException; // Subclass of IOException

class NetworkDevice {
    public void ping() throws IOException { }
}

class Router extends NetworkDevice {
    @Override public void ping() throws ConnectException { } // A
}

class Switch extends NetworkDevice {
    @Override public void ping() throws Exception { } // B
}

class Firewall extends NetworkDevice {
    @Override public void ping() { } // C
}

```

**Solution:** **Class B (`Switch`) will fail to compile.** It attempts to throw `Exception`, which is *broader* than the parent's `IOException`. Class A is perfectly valid (narrower subclass exception) and Class C is perfectly valid (safer, no exceptions).

---

## 📝 Practice Problems

Here are two problems designed to test your mastery. The first tests your ability to combine `throw` and `throws` manually. The second tests your ability to refactor a class hierarchy according to the strict overriding rules.

### Problem 1: The Registration Validator

You are building the backend for a user registration system. You need to validate input and warn the caller about potential system failures.

**Requirements:**

1. Create a custom checked exception: `class DatabaseTimeoutException extends Exception`.
2. Create a `RegistrationService` class with a method `public void register(String username, int age)`.
3. The method signature must warn callers that it `throws DatabaseTimeoutException`.
4. Inside the method, write logic to validate the input:
* If `age < 18`, **throw** a standard unchecked `IllegalArgumentException`.
* If the `username` is "admin", simulate a database crash by **throwing** a new `DatabaseTimeoutException`.


5. In `Main`, call `register("admin", 20)` and properly handle the required exceptions.

### Problem 2: The "Safe API" Refactoring (Method Overriding)

You are handed legacy code where an interface is overly broad, and you need to implement a safer, concrete version of it without breaking the compiler's overriding rules.

**Requirements:**

1. You are given this interface:
```java
interface DataExporter {
    void exportData(String data) throws Exception;
}

```


2. Create a class `CSVExporter` that implements `DataExporter`.
3. Override the `exportData` method. However, because you are writing to a file, you want to narrow the risk. Change the signature to only throw an `IOException` (which is a valid narrowing of `Exception`).
4. Inside the method, if `data` is empty, physically `throw` a new `IOException("No data to export")`.
5. Create a second class `ConsoleExporter` that also implements `DataExporter`.
6. Override the method, but this time, make it completely safe—declare **no exceptions** in the signature. Inside, just print the data to the console.
