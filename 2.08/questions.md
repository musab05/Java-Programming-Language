# Method Overriding in Java: Assessment Guide

This guide provides an exhaustive list of interview questions, conceptual checks, and targeted coding practice problems strictly focused on Java's Method Overriding rules, access modifier limits, and the crucial differences between overriding and overloading.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of inheritance rules and dynamic binding.*

1. **What is Method Overriding, and how does it relate to Run-Time Polymorphism?**
* Method overriding occurs when a child class provides a specific implementation for a method that is already defined in its parent class. It is the backbone of Run-Time Polymorphism because the JVM decides *at runtime* which version of the method to execute based on the actual object in memory, not the reference type.


2. **Can you override `private`, `static`, or `final` methods?**
* **`private`:** No. Private methods are hidden from child classes and are not inherited.
* **`static`:** No. Static methods belong to the class, not the object. If a child class writes a static method with the exact same signature, it is called **Method Hiding**, not overriding.
* **`final`:** No. The `final` keyword specifically locks a method to prevent any child class from changing its behavior.


3. **What is the rule for Access Modifiers when overriding a method?**
* The overriding method in the child class cannot have a *more restrictive* access modifier than the parent class. For example, if the parent method is `protected`, the child method can be `protected` or `public`, but it cannot be narrowed down to `private` or `default`.


4. **What is a "Covariant Return Type"?**
* It means an overriding method can change the return type, *provided* the new return type is a valid subclass of the original return type. For example, if a parent method returns a `Vehicle` object, the overriding child method is allowed to return a `Car` object.


5. **What happens if you don't use the `@Override` annotation?**
* The code will still compile and run if written perfectly. However, omitting it removes the compiler's safety check. If you accidentally misspell the method name or change a parameter type, the compiler will silently treat it as a brand-new method (overloading) instead of warning you that your override failed.



---

## 💡 Concept Check Questions

*Use these questions to verify if you truly understand strict compilation rules and method signatures.*

### 🗣️ Verbal/Conceptual Check

* **The Access Downgrade:** "A parent class has a `public void draw()` method. The child class overrides it and declares it as `protected void draw()`. Will this compile?" *(Answer: No. `protected` is more restrictive than `public`. You cannot reduce the visibility of an inherited method).*
* **The Static Trap:** "Both Parent and Child have a `public static void printInfo()` method. You write `Parent p = new Child(); p.printInfo();`. Whose method runs?" *(Answer: The Parent's method runs. Because they are static, dynamic binding does not happen. The JVM looks strictly at the reference type (`Parent`) at compile-time).*

### 💻 Practical/Coding Check

* **Find the Bug:** "A developer meant to override the `takeDamage` method, but the child class behavior isn't triggering in the game loop. Why?"
```java
class Character {
    public void takeDamage(int amount) { ... }
}

class Boss extends Character {
    public void takeDamage(double amount) { ... }
}

```


* **Solution:** The parameter types don't match (`int` vs `double`). Because the parameters are different, the developer accidentally **Overloaded** the method instead of Overriding it. If they had used the `@Override` annotation, the compiler would have caught this mistake immediately.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for access modifiers, annotations, and covariant returns.*

### Problem 1: The `@Override` Safety Net

**Scenario:** You are updating a physics engine. You need to override the parent's gravity calculation, but you want to prove why the annotation is essential.
**Task:**

1. Create a parent class `PhysicsObject` with a method `public void applyGravity(float gravityForce)`.
2. Create a child class `FallingCrate` that extends `PhysicsObject`.
3. Inside `FallingCrate`, write a method `public void applygravity(float gravityForce)` (notice the lowercase 'g').
4. Add the `@Override` annotation above the method in the child class and observe the compiler error.
5. Fix the typo so the override is successful and prints `"Crate falls heavily!"`.

### Problem 2: Expanding Access Modifiers

**Scenario:** A library provides a parent class with restricted internal methods, but your specific implementation needs to expose that method to the public API.
**Task:**

1. Create a parent class `BankAccount` with a `protected void calculateInterest()` method that prints `"Base interest calculated."`
2. Create a child class `HighYieldAccount` that extends `BankAccount`.
3. Override the `calculateInterest()` method.
4. Change the access modifier in the child class to `public` (expanding visibility) and make it print `"High yield interest calculated!"`
5. In `main`, instantiate the child class and call the method to prove it compiles and runs.

### Problem 3: Covariant Return Types

**Scenario:** You are writing an item generation system for an RPG. The base generator returns generic items, but the weapon generator should specifically return weapons.
**Task:**

1. Create a `Loot` class. Then create a `Weapon` class that `extends Loot`.
2. Create a parent class `Chest` with a method `public Loot open()`. It should return a new `Loot` object.
3. Create a child class `WeaponChest` that `extends Chest`.
4. Override the `open()` method. Change the return type from `Loot` to `Weapon` (this is the covariant return), and have it return a new `Weapon` object.
5. In `main`, call `open()` on the `WeaponChest` to prove it returns the more specific subtype successfully.
