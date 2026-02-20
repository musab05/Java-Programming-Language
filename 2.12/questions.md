# Interfaces in Java: Assessment Guide

Interfaces are the ultimate tool for **decoupling** code. While Abstract Classes define what an object *is*, Interfaces define what an object *can do*.

## 🎤 Comprehensive Interview Questions

*These questions cover everything from basic syntax to Java 8+ architectural changes.*

1. **Why can't we have instance variables in an interface?**
* **The Concept:** Interfaces are designed to define behavior, not state. Since they cannot be instantiated and have no constructors, there is no way to initialize "instance" data for a specific object. Therefore, all variables are forced to be `static` (belong to the interface) and `final` (constant).


2. **What is a "Marker Interface"?**
* **The Concept:** An interface with zero methods (e.g., `Serializable`, `Cloneable`). It is used to "tag" a class so the JVM or other libraries can treat it specifically at runtime.


3. **What is the "Diamond Problem" in Interfaces (Java 8+)?**
* **The Concept:** If a class implements two interfaces that both have a `default` method with the exact same signature, the compiler gets confused. **The fix:** The developer *must* override the conflicting method in the implementing class and manually specify which one to use (or write a new one).


4. **Why use an Interface if we already have Abstract Classes?**
* **The Concept:** Multiple Inheritance. A class can only have one parent class, but it can play many roles (implement many interfaces). Also, interfaces allow unrelated classes to share a capability (e.g., a `User` and a `Document` can both be `Searchable`).


5. **Can an interface extend another interface?**
* **The Concept:** Yes. An interface can `extend` one or more other interfaces. This is called "Interface Inheritance." Note that interfaces use `extends` to inherit from each other, but classes use `implements` to use them.



---

## 💡 Common Hurdles & Interview Pitfalls

* **The Access Modifier Trap:** If you write `void move();` in an interface, and then write `void move() { ... }` in the class, it will **fail to compile**.
* **The Secret:** All interface methods are implicitly `public`. In Java, you cannot reduce the visibility of a method when overriding. You **must** explicitly type `public void move()` in your class.


* **The `final` Variable Trap:** You cannot change the value of a variable defined in an interface. Since they are implicitly `public static final`, trying to update `interfaceName.myVar = 10;` will result in a compiler error.
* **The "Contract" Misconception:** If a class implements an interface but is marked as `abstract`, it **does not** have to implement the interface methods. It can pass that responsibility down to its first concrete child class.

---

## 🛠️ Practice Problem Statements

### Problem 1: The Multi-Functional Device (Multiple Inheritance)

**Scenario:** You are building software for a Smart Printer. It can print, scan, and fax. Each of these is a distinct capability that other devices (like a camera) might also share.
**Task:**

1. Create three interfaces: `Printable`, `Scannable`, and `Faxable`. Each should have one corresponding method (e.g., `printDoc()`).
2. Create a class `SmartDevice` that **implements all three**.
3. Provide unique print statements for each method.
4. In `main`, instantiate `SmartDevice` and call all three methods.

### Problem 2: The Default Evolution (Default Methods)

**Scenario:** You have a `MessagingApp` interface used by many classes. You want to add a "Video Call" feature without breaking the older classes that only support text.
**Task:**

1. Create an interface `Messenger` with an abstract method `void sendText(String msg)`.
2. Add a `default` method `void startVideoCall()` that prints `"Video call not supported on this device."`.
3. Create a class `OldPhone` that implements `Messenger` but **only** implements `sendText`.
4. Create a class `SmartPhone` that implements `Messenger` and **overrides** `startVideoCall` to print `"Connecting video call..."`.
5. In `main`, call `startVideoCall` on objects of both classes.

### Problem 3: The Constant Provider (Interface Variables)

**Scenario:** You need a central place to store physics constants for a simulation engine so that any class (Bullet, Player, Car) can access them easily.
**Task:**

1. Create an interface `PhysicsConstants`.
2. Define `double GRAVITY = 9.8;` and `double FRICTION = 0.4;`.
3. Create a class `PhysicsEngine` that implements this interface.
4. Inside a method in `PhysicsEngine`, use these constants to calculate a "Falling Speed" and print it.
5. In `main`, prove you can also access the variables directly via `PhysicsConstants.GRAVITY`.
