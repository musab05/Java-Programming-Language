# Classes and Objects in Java: Assessment Guide

This guide provides an expanded list of interview questions, conceptual checks, and multiple practice problems strictly focused on the core definitions, creation, and memory mechanics of Java Classes and Objects.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's foundational understanding of OOP architecture.*

1. **What is the fundamental difference between a Class and an Object?**
* A **Class** is a logical blueprint or template that defines properties and behaviors. It is not a physical entity. An **Object** is a physical, concrete instance of that class that occupies memory and holds actual data.


2. **When exactly is memory allocated: when a Class is defined, or when an Object is created?**
* Memory is **not** allocated when a Class is defined (except for the metadata loaded by the ClassLoader). Memory is only allocated on the Heap when an Object is instantiated using the `new` keyword.


3. **What is the purpose of the `new` keyword in Java?**
* The `new` keyword is used to create an instance of a class. It dynamically allocates memory for the new object on the Heap and returns a reference (memory address) to that specific object.


4. **Can you have an Object without a Class in Java?**
* No. Because Java is a strictly typed, class-based Object-Oriented language, every object must be instantiated from a predefined class template.


5. **If I create 100 objects from a single class, how many copies of the instance variables exist in memory?**
* There will be 100 independent copies of the instance variables. Each object maintains its own unique state in memory.



---

## 💡 Concept Check Questions

*Use these questions to verify if you truly understand object instantiation and reference behavior.*

### 🗣️ Verbal/Conceptual Check

* **The Blueprint Test:** "If I change the `color` attribute of my `Car` object to 'Blue', does that change the color of every other `Car` object in the program?" *(Answer: No. Every object holds its own independent state. Changing one instance does not affect the others or the blueprint itself).*
* **The Ghost Object:** "What happens if I write `Player p1;` and then try to call `p1.takeDamage();` without ever using the `new` keyword?" *(Answer: The compiler will throw an error (or a `NullPointerException` at runtime if it's an instance variable) because `p1` is just an empty reference variable. It doesn't point to an actual object in memory yet).*

### 💻 Practical/Coding Check

* **The Missing Instance:** "Identify the bug in this code that prevents it from running."
```java
class Enemy {
    int health = 100;
}
public class Game {
    public static void main(String[] args) {
        Enemy boss;
        boss.health = 500; 
    }
}

```


* **Solution:** The `boss` object was declared but never initialized. It must be `Enemy boss = new Enemy();` before you can access or modify its `health` attribute.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for defining classes and instantiating objects.*

### Problem 1: The Procedural Terrain Generator

**Scenario:** You are building a system that generates map environments dynamically. You need a template for individual chunks of land.
**Task:**

1. Create a `TerrainChunk` class.
2. Add three fields: `int xPosition`, `int yPosition`, and `String biomeType` (e.g., "Desert", "Forest").
3. Add a method `void renderChunk()` that prints: `"Rendering [biomeType] chunk at [xPosition], [yPosition]"`.
4. In your `main` method, create **two separate** `TerrainChunk` objects.
5. Assign different coordinates and biomes to each, and call `renderChunk()` on both.

### Problem 2: The Trade Order Simulator

**Scenario:** You are developing a backend module for a brokerage platform. You need an object to represent a client's request to buy a stock.
**Task:**

1. Create a `TradeOrder` class.
2. Add fields: `String tickerSymbol`, `int quantity`, and `double pricePerShare`.
3. Add a method `void calculateTotal()` that calculates the total cost (`quantity * pricePerShare`) and prints the receipt: `"Order: [quantity] shares of [tickerSymbol] at $[pricePerShare]. Total: $[totalCost]"`.
4. In `main`, create a `TradeOrder` object, populate the fields, and execute the calculation method.

### Problem 3: The Server Node

**Scenario:** You are building a real-time messaging platform. You need a blueprint for individual server channels.
**Task:**

1. Create a `ChatServer` class.
2. Add fields: `String serverName` and `int onlineMembers`.
3. Add a method `void userJoined()` that increases `onlineMembers` by 1 and prints: `"A user joined [serverName]. Total online: [onlineMembers]"`.
4. In `main`, create a server object, name it, set the initial members to 5, and simulate 3 users joining by calling the method three times.
