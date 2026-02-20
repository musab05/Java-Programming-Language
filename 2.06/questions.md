# Inheritance in Java: Assessment Guide

This guide provides an exhaustive list of interview questions, conceptual checks, and targeted coding practice problems strictly focused on Java's Inheritance mechanics, class hierarchies, and access limitations.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of OOP architecture and class design.*

1. **What is Inheritance and what specific relationship does it represent?**
* Inheritance is an OOP mechanism where a new class (Subclass) acquires the fields and methods of an existing class (Superclass). It strictly represents an **IS-A** relationship (e.g., a `Warrior` IS-A `Player`).


2. **Why does Java intentionally forbid Multiple Inheritance with classes?**
* Java blocks a class from `extend`ing more than one parent class to prevent the **Diamond Problem**. If Class C inherits from both Class A and Class B, and both parents have a method named `attack()`, the JVM wouldn't know which specific `attack()` implementation Class C is supposed to inherit, causing severe ambiguity.


3. **If a subclass extends a superclass, does it inherit the superclass's constructors?**
* **No.** Constructors are never inherited. However, the superclass's constructor is always silently executed *first* whenever a subclass object is created (to ensure the parent's state is initialized before the child builds on top of it).


4. **Can a subclass access the `private` variables of its parent class?**
* **No.** Private members remain strictly hidden, even from child classes. The subclass must use the parent's `public` or `protected` getter/setter methods to interact with that inherited data.


5. **What is the difference between Multilevel and Hierarchical inheritance?**
* **Multilevel:** A linear chain (Class C extends Class B, which extends Class A).
* **Hierarchical:** A branching structure (Class B and Class C both independently extend Class A).



---

## 💡 Concept Check Questions

*Use these questions to verify if you truly understand the rules of class extension and inherited access.*

### 🗣️ Verbal/Conceptual Check

* **The IS-A vs HAS-A Test:** "I am building a game. Should my `Player` class `extend` the `Weapon` class so the player can use a sword?" *(Answer: No. A Player HAS-A weapon, but a Player IS NOT a weapon. Using inheritance here is a major design flaw. `Player` should extend something like `Entity` or `Character`).*
* **The Sibling Rivalry:** "Class `Mage` and Class `Archer` both extend `Player`. `Mage` has a `castSpell()` method. Can the `Archer` object call `castSpell()`?" *(Answer: No. Siblings do not share specific methods. They only share what is written in the common `Player` parent class).*

### 💻 Practical/Coding Check

* **Identify the Access Error:** "Why will this code fail to compile, and how do you fix it?"
```java
class Enemy {
    private int health = 100;
}

class Goblin extends Enemy {
    public void takeDamage() {
        health -= 10; // COMPILER ERROR
    }
}

```


* **Solution:** `health` is `private` in the parent class, meaning `Goblin` cannot access it directly. Fix: Change `private` to `protected` in `Enemy`, or create a `public void setHealth(int h)` method in `Enemy` that `Goblin` can call.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for creating class hierarchies and managing inherited state.*

### Problem 1: The Entity System (Hierarchical Inheritance)

**Scenario:** You are structuring the foundational classes for a 2D game. Both the player and the enemies share basic physics, but have different specific actions.
**Task:**

1. Create a parent class `Entity`. Give it an `int x` and `int y` for position, and a `void move()` method that prints `"Moving entity..."`.
2. Create a child class `Player` that `extends Entity`. Give it an `int mana` and a `void heal()` method.
3. Create a second child class `Zombie` that `extends Entity`. Give it an `int biteDamage` and a `void attack()` method.
4. In `main`, create one `Player` object and one `Zombie` object. Prove they can both use the `move()` method, but only the player can `heal()`.

### Problem 2: The Vehicle Tech Tree (Multilevel Inheritance)

**Scenario:** You are building a simulation tech tree where vehicles become more advanced by building upon the previous generation's blueprints.
**Task:**

1. Create a `Vehicle` class with a `startEngine()` method.
2. Create a `Tank` class that `extends Vehicle`. Add a `fireCannon()` method.
3. Create a `HoverTank` class that `extends Tank`. Add a `hover()` method.
4. In `main`, instantiate a `HoverTank`. Call all three methods sequentially to prove it inherited behaviors from both its direct parent (`Tank`) and its grandparent (`Vehicle`).

### Problem 3: The Restricted Account (Private Parent Data)

**Scenario:** You are building a banking system. A premium account inherits from a standard account, but it cannot illegally bypass the standard account's core security rules.
**Task:**

1. Create a `BankAccount` class with a `private double balance`. Provide a `public double getBalance()` and a `protected void addFunds(double amount)` method.
2. Create a `PremiumAccount` class that `extends BankAccount`.
3. Inside `PremiumAccount`, write a `applyBonus()` method that attempts to add 100.0 to the balance. It must use the inherited `addFunds()` method because `balance` is strictly private.
4. In `main`, test the `PremiumAccount` and print its final balance.
