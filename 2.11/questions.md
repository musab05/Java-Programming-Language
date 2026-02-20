# Deep Dive: Abstract Classes in Java

This guide covers the technical nuances and "interview traps" surrounding Abstract Classes. Since this is a specialized "partial blueprint," the JVM treats it differently than any other class type.

## 🎤 Comprehensive Interview Questions

*Every frequent and advanced question regarding Abstract Class behavior.*

1. **Can an Abstract Class be declared as `final`?**
* **The Concept:** Absolutely not. This is a logical contradiction. `abstract` requires a class to be inherited to be useful, while `final` strictly forbids inheritance. The compiler will throw an error.


2. **Can we have a `static` method in an Abstract Class?**
* **The Concept:** Yes. Static methods belong to the class, not an object. You can call `AbstractClass.staticMethod()` without ever instantiating the class.


3. **If an Abstract Class has no abstract methods, is it still "abstract"?**
* **The Concept:** Yes. You can declare a class as abstract solely to prevent it from being instantiated, even if it only contains concrete (finished) methods.


4. **Explain the execution order of constructors in an Abstract Class hierarchy.**
* **The Concept:** When a child object is created, the Abstract Class constructor runs first (via `super()`), followed by the child's constructor. This ensures the base state is set before the child's specific state.


5. **Can an Abstract Method be `synchronized` or `native`?**
* **The Concept:** No. `synchronized` and `native` keywords relate to the *implementation* (the body) of a method. Since abstract methods have no body, these modifiers are illegal.


6. **Can you define an abstract class inside another class?**
* **The Concept:** Yes, Java supports nested abstract classes (Inner Classes).



---

## 💡 Common Hurdles & Interview Pitfalls

* **The Private Trap:** An interviewer might ask: *"Can I make an abstract method private so only I can see it?"* * **The Answer:** No. If it's private, the child class can't see it, which means the child can't override it. Since an abstract method *must* be overridden to be useful, `private abstract` is a compilation error.
* **The Instantiation Confusion:** *"If I can't use 'new', how do I use the methods inside the abstract class?"*
* **The Answer:** You must use **Polymorphism**. Create a child object but store it in the abstract parent's reference: `BaseClass obj = new ChildClass();`.


* **The "Interface vs. Abstract" Setup:** This is the #1 follow-up.
* **The Key Distinction:** Use an Abstract Class when you have **shared state** (instance variables like `int health` or `String name`). Use an Interface when you only want to define **shared behavior** (methods).



---

## 🛠️ Practice Problem Statements

### Problem 1: The Secure Messaging Base

**Scenario:** You are building a messaging system where every message type (Email, SMS) must be encrypted, but the actual "sending" logic depends on the hardware.
**Task:**

1. Create an **Abstract Class** `BaseMessage`.
2. Add a concrete method `void encrypt()` that prints `"Data encrypted using AES."`.
3. Add an **Abstract Method** `void send()`.
4. Create a child class `EmailMessage` that extends `BaseMessage` and implements the `send()` method.
5. In `main`, instantiate an `EmailMessage`, call `encrypt()`, and then call `send()`.

### Problem 2: The Character Stats Blueprint (Constructors)

**Scenario:** Every character in your application has a name and a level. You want to ensure these are set the moment any specific character type is created.
**Task:**

1. Create an abstract class `Hero`.
2. Add fields `String name` and `int level`.
3. Create a **constructor** in `Hero` that initializes these two fields.
4. Add an abstract method `void useAbility()`.
5. Create a child class `Mage`. Its constructor should take `(name, level)` and pass them to the parent using `super`.
6. Implement `useAbility()` in `Mage` to print the hero's name and their specific ability.

### Problem 3: The Multi-Step Initialization

**Scenario:** Some processes require a specific sequence: Start, Execute, and Stop. "Start" and "Stop" are always the same, but "Execute" changes.
**Task:**

1. Create an abstract class `WorkFlow`.
2. Create concrete methods `void start()` and `void stop()`.
3. Create an abstract method `void execute()`.
4. Create a "Final" method (using `final` keyword) `void runProcess()` that calls `start()`, then `execute()`, then `stop()` in order.
5. Create a child class `DataCleanWorkflow` that implements `execute()`.
6. In `main`, call `runProcess()` on the child object.
