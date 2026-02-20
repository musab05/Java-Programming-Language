# The `final` Keyword in Java: Assessment Guide

The `final` keyword is Java's primary tool for **immutability** and **security**. It locks down code to prevent unintended modifications that could lead to bugs or security vulnerabilities.

---

## 🎤 Comprehensive Interview Questions

*These questions focus on the strict compilation rules and the memory-level behavior of final entities.*

1. **What is a "Blank Final Variable" and where must it be initialized?**

* **The Concept:** A blank final variable is a `final` variable that isn't given a value at the time of declaration. If it is an **instance** variable, it **must** be initialized in every constructor of that class. If it is **static**, it **must** be initialized in a `static` block. Failure to do so results in a compilation error.

2. **Can we initialize a final variable using a method?**

* **The Concept:** Yes. As long as the variable is assigned a value exactly once before its first use, you can call a method to determine that value (e.g., `final long startTime = System.currentTimeMillis();`).

3. **Can an abstract class be `final`?**

* **The Concept:** No. This is a common "logic trap" question. An `abstract` class **must** be inherited to be useful, while a `final` class **forbids** inheritance. They are direct opposites and cannot be used together.

4. **Is it true that all methods of a `final` class are implicitly `final`?**

* **The Concept:** Yes. Since a `final` class cannot be inherited, none of its methods can ever be overridden. Therefore, they behave as if they were marked `final`.

5. **If an object reference is `final`, can we still modify the object's properties?**

* **The Concept:** Yes. The `final` keyword only prevents **reassignment** of the reference variable. It does not make the object itself immutable. To make the object immutable, you would need to mark the individual fields within that object as `final` as well.

---

## 💡 Common Hurdles & Interview Pitfalls

* **The Local Final Variable Trap:** You can declare local variables (inside a method) as `final`. This is often done when passing variables into anonymous inner classes or lambda expressions, where Java requires the variable to be "effectively final."
* **The Constructor Trap:** You cannot mark a **constructor** as `final`. Constructors are not inherited, so overriding them isn't possible anyway; marking them `final` is redundant and results in a syntax error.
* **The Performance Myth:** Historically, some thought `final` methods were faster because the compiler could "inline" them. In modern JVMs, the Just-In-Time (JIT) compiler is smart enough to optimize methods regardless of the `final` keyword, so use `final` for **design and security**, not performance.

---

## 🛠️ Practice Problem Statements

### Problem 1: The Secure API Key (Blank Final)

**Scenario:** You are building a service that requires a unique API key for each instance. The key is generated at runtime and should never be changed once the service starts.
**Task:**

1. Create a class `ApiService`.
2. Declare a blank final `String apiKey`.
3. Create a constructor that takes a `String` and assigns it to `apiKey`.
4. Create a method `void updateKey()` that tries to change the key and observe the compiler error.
5. In `main`, instantiate the service and print the key.

### Problem 2: Locking the Login Logic (Final Method)

**Scenario:** You have a `User` class with a login method. While child classes like `Admin` can change how they "greet" users, the actual "credential verification" must remain unchangeable for security.
**Task:**

1. Create a parent class `BaseUser`.
2. Add a `final` method `verifyPassword(String input)`.
3. Create a child class `AdminUser` that extends `BaseUser`.
4. Attempt to override `verifyPassword` in `AdminUser` and observe the failure.
5. Implement a different, non-final method in `AdminUser` to show what *can* be changed.

### Problem 3: Preventing Utility Subclassing (Final Class)

**Scenario:** You are creating a utility class `MathUtils` that only contains static methods. You want to ensure no one ever tries to create a subclass of this utility.
**Task:**

1. Create a `final` class `MathUtils`.
2. Add a static final `double PI = 3.14159;`.
3. Add a static method `double square(double n)`.
4. Create another class `AttemptedMath` and try to `extend MathUtils`.
5. Observe the error: *"Cannot inherit from final 'MathUtils'"*.
