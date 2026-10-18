In Java, **Inheritance** is the second major pillar of Object-Oriented Programming (OOP). It is a mechanism where one class acquires the properties (fields) and behaviors (methods) of another class.

Inheritance represents an **IS-A relationship**. For example, a `Car` IS-A `Vehicle`, or a `Boss` IS-A `Character`.

---

## 1. The Terminology

* **Superclass (Parent Class):** The class whose features are inherited.
* **Subclass (Child Class):** The class that inherits the other class. The subclass can add its own fields and methods in addition to the superclass fields and methods.

---

## 2. The `extends` Keyword

To inherit from a class in Java, you use the `extends` keyword.

Here is a practical example using a game character structure. Notice how the `Boss` class automatically has access to the `health` variable and the `takeDamage` method, even though they were never explicitly written inside the `Boss` class.

```java
// 1. Parent Class
class Character {
    public int health = 100;

    public void takeDamage(int amount) {
        health -= amount;
        System.out.println("Took damage! Health is now: " + health);
    }
}

// 2. Child Class inheriting from Character
class Boss extends Character {
    public int shield = 50; // New specific property

    public void ultimateAttack() { // New specific behavior
        System.out.println("Boss uses a massive area attack!");
    }
}

public class Game {
    public static void main(String[] args) {
        Boss finalBoss = new Boss();
        
        // Inherited from Character
        finalBoss.takeDamage(20); 
        
        // Specific to Boss
        System.out.println("Shield remaining: " + finalBoss.shield);
        finalBoss.ultimateAttack();
    }
}

```

---

## 3. Types of Inheritance in Java

Java supports several types of inheritance, but with one major restriction compared to languages like C++: **Java does not support Multiple Inheritance with classes**. A class can only `extend` one parent class at a time to prevent ambiguity (known as the "Diamond Problem").

| Type | Description | Allowed in Java? |
| --- | --- | --- |
| **Single** | Class B extends Class A. | Yes |
| **Multilevel** | Class C extends Class B, which extends Class A. | Yes |
| **Hierarchical** | Class B and Class C both extend Class A. | Yes |
| **Multiple** | Class C extends both Class A and Class B. | **No** (Only via Interfaces) |

---

## 4. Why Use Inheritance?

* **Code Reusability:** You don't have to write the same logic multiple times. If an `Enemy`, `Player`, and `NPC` all need health and movement logic, you put it in a single `Entity` parent class.
* **Method Overriding:** It is the prerequisite for achieving **Polymorphism**, allowing a child class to provide a specific implementation of a method that is already provided by its parent class.

---

## 5. What is NOT Inherited?

It is important to note that a subclass does not inherit *everything*:

1. **Constructors:** A subclass cannot inherit its parent's constructors. (However, the parent's constructor is always executed before the subclass's constructor).
2. **Private Members:** Any fields or methods marked as `private` in the parent class cannot be directly accessed by the child class (unless the parent provides public `getters`/`setters`).
