# Multiple Inheritance in Java: Assessment Guide

Java’s approach to Multiple Inheritance is a delicate balance between flexibility and safety. It allows a class to perform multiple roles (Interfaces) while strictly preventing the structural ambiguity that comes with multiple parent classes.

## 🎤 Comprehensive Interview Questions

*These questions focus on the architectural "Why" and the specific syntax for resolving conflicts.*

1. **Why does Java not support multiple inheritance with classes?**

* **The Concept:** To avoid the **Diamond Problem**. If two parent classes provide different implementations of the same method, the child class wouldn't know which one to inherit, leading to ambiguity. Java prioritizes a clean, predictable inheritance tree over the complexity of multiple parent classes.

2. **How do Interfaces enable multiple inheritance without causing ambiguity?**

* **The Concept:** Since interface methods are (traditionally) abstract, they don't provide any implementation. The implementing class is forced to provide its own single implementation, so there is never a conflict between two different pieces of code—there is only ever one piece of code (the one in the child class).

3. **How do you resolve a method conflict when two interfaces have the same `default` method?**

* **The Concept:** The implementing class **must** override the conflicting method. Inside that override, you can either write completely new logic or explicitly choose one of the parent implementations using the syntax: `InterfaceName.super.methodName();`.

4. **Can a class extend one class and implement multiple interfaces at the same time?**

* **The Concept:** Yes. The syntax is `class MyClass extends ParentClass implements Interface1, Interface2`. The `extends` keyword must always come before the `implements` keyword.

5. **What happens if a class implements two interfaces that have the same constant variable name?**

* **The Concept:** This causes a name collision. The code will compile, but you cannot access the variable directly by its name in the class. You must resolve the ambiguity by using the interface name: `InterfaceA.VARIABLE_NAME`.

---

## 💡 Common Hurdles & Interview Pitfalls

* **The Keyword Order Trap:** You cannot write `class A implements B extends C`. The compiler strictly requires the "Identity" (extends) to be defined before the "Capabilities" (implements).
* **The `super` Resolution Trap:** In a conflict resolution, you cannot just write `super.start()`. Because there are multiple "supers" (parents), you must specify which one you are talking about: `Engine.super.start()`.
* **The Abstract Implementation Secret:** If a class implements two interfaces but doesn't want to resolve a method conflict yet, that class **must** be declared `abstract`. The responsibility then falls on the first concrete (non-abstract) subclass.

---

## 🛠️ Practice Problem Statements

### Problem 1: The Hybrid Character (Basic Multiple Inheritance)

**Scenario:** You are building a game with a `Mage` class. The Mage needs to be able to use magic but also needs to be able to trade with merchants.
**Task:**

1. Create an interface `SpellCaster` with an abstract method `void castSpell()`.
2. Create an interface `Trader` with an abstract method `void trade()`.
3. Create a class `Mage` that **implements both**.
4. Implement the methods and, in `main`, call them using a `Mage` object.

### Problem 2: The Amphibian Vehicle (Conflict Resolution)

**Scenario:** You are designing a `Hovercraft` that is both a `Boat` and a `Plane`. Both have a `default` method called `refuel()`.
**Task:**

1. Create interface `Boat` with a `default void refuel()` that prints `"Filling water tanks."`.
2. Create interface `Plane` with a `default void refuel()` that prints `"Filling fuel tanks."`.
3. Create a class `Hovercraft` that implements both.
4. **Override** the `refuel()` method in `Hovercraft`. Inside, call both the `Boat` and `Plane` versions of `refuel`.
5. Test it in `main`.

### Problem 3: The Identity & Capability Mix

**Scenario:** An `ElectricCar` is a type of `Vehicle` (Class), but it is also `Rechargeable` (Interface) and `Autonomous` (Interface).
**Task:**

1. Create a base class `Vehicle` with a method `void drive()`.
2. Create interfaces `Rechargeable` (`void plugIn()`) and `Autonomous` (`void selfDrive()`).
3. Create the class `ElectricCar` that inherits from `Vehicle` and implements both interfaces.
4. In `main`, use **upcasting** to prove that an `ElectricCar` object can be stored in a `Vehicle` reference, a `Rechargeable` reference, or an `Autonomous` reference.
