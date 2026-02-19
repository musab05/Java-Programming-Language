# Access Modifiers in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems focused on Java’s visibility keywords and their role in Encapsulation.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of visibility and code security.*

1. **What is the difference between "Default" and "Protected" access?**
* **Default** (no keyword) allows access *only* within the same package.
* **Protected** allows access within the same package *plus* access by subclasses, even if those subclasses are located in different packages.


2. **Why is it standard practice to mark instance variables as `private`?**
* This is the core of **Encapsulation**. By making variables `private`, you prevent external classes from directly modifying data. You can then provide `public` getters and setters to control how that data is read or changed (e.g., adding validation).


3. **Can a top-level class be marked as `private` or `protected`?**
* No. Top-level classes can only be `public` or `default`. Only nested (inner) classes can be marked as `private` or `protected`.


4. **If a method is `private`, can it be inherited or overridden by a subclass?**
* No. Because `private` methods are invisible outside their own class, a subclass doesn't even know they exist and therefore cannot inherit or override them.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands the boundaries of package-level and class-level security.*

### 🗣️ Verbal/Conceptual Check

* **The "World" Access Test:** "If I am building an API for **Academiq** and I want developers from other companies to use my `Login` class, which access modifier must I use for the class and its main login method?" *(Answer: `public` for both).*
* **The Subclass Mystery:** "I have a `protected` variable in Package A. A class in Package B extends the class in Package A. Can the class in Package B see that variable?" *(Answer: Yes, because `protected` grants visibility to subclasses across different packages).*

### 💻 Practical/Coding Check

* **The Broken Encapsulation:** "Identify the security flaw in this code and suggest how to fix it using the Getter/Setter pattern."
```java
public class BankAccount {
    public double balance;
}

```


* **Solution:** The `balance` is `public`, meaning any class can set it to a negative number or zero without permission. Fix: Change `balance` to `private` and create a `public void deposit(double amount)` method with a check for `amount > 0`.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of access control and data protection.*

### Problem 1: The Hidden Password (Private & Public)

**Scenario:** You are building a `User` class for an authentication system. You need to store a password that should never be directly accessible, but you need a way to update it.
**Task:**

1. Create a class named `User`.
2. Declare a `private String password`.
3. Create a `public` method named `setPassword`.
4. Inside `setPassword`, add logic to only update the password if the new password length is greater than 6 characters.
5. In a `main` method, try to access `user.password` directly and observe the compiler error, then use the method to set it correctly.

### Problem 2: The Module Internal (Default Access)

**Scenario:** You have a helper class that calculates internal taxes. This should be used by other classes in your `finance` package but should not be visible to the `ui` package.
**Task:**

1. Create a class named `TaxCalculator` in the package `com.academiq.finance`.
2. Do **not** use the `public` keyword for the class.
3. Define a method `double calculate(double amount)` inside it.
4. Explain: If you try to import and use this class in `com.academiq.ui.Dashboard`, why will it fail?
