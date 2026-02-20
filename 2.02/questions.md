# Constructors in Java: Assessment Guide

This guide provides a comprehensive list of interview questions, conceptual checks, and coding practice problems strictly focused on Java Constructors, their rules, and how they differ from standard methods.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of object initialization and compiler behavior.*

1. **What is the primary purpose of a Constructor in Java?**
* A constructor is a special block of code used to initialize the state (attributes) of an object immediately when it is created in memory using the `new` keyword.


2. **What happens if you do not write any constructors in your class?**
* The Java compiler automatically inserts a hidden "Default Constructor" (a no-argument constructor) into the compiled bytecode. This default constructor initializes all object references to `null` and primitives to their default values (e.g., `0` for ints, `false` for booleans).


3. **If you explicitly write a Parameterized Constructor, does the compiler still provide a Default Constructor?**
* **No.** This is a very common trap. As soon as you write *any* constructor, the compiler steps back and assumes you are taking full control of object creation. If you still want to be able to create objects without passing arguments, you must explicitly write your own no-argument constructor.


4. **Can a constructor have a return type like `void` or `int`? What happens if you add one?**
* A constructor strictly **cannot** have a return type. If you give it a return type (even `void`), the compiler simply treats it as a standard method that happens to share the name of the class, and it will *not* run automatically when the object is instantiated.


5. **Can a constructor be marked as `static`, `final`, `abstract`, or `synchronized`?**
* No. Constructors are inherently tied to creating a specific instance of an object, so they cannot be `static` (which belongs to the class). They cannot be `abstract` because they must have a body, and they cannot be `final` because they are not inherited by subclasses.


6. **What is Constructor Overloading?**
* It is the practice of having multiple constructors within the same class, each with a different parameter list (different number, type, or sequence of arguments). This allows objects to be initialized in different ways depending on the provided data.



---

## 💡 Concept Check Questions

*Use these questions to verify if you truly understand strict constructor syntax and compiler rules.*

### 🗣️ Verbal/Conceptual Check

* **The `void` Trap:** "I wrote this block inside my `Car` class: `public void Car() { System.out.println("Car built!"); }`. However, when I run `Car myCar = new Car();`, nothing prints to the console. Why?" *(Answer: Because of the `void` keyword, the compiler sees `public void Car()` as a normal method, not a constructor. The code doesn't run because the method was never explicitly called).*
* **The Missing Default:** "I have a `User` class with only one constructor: `public User(String name) { ... }`. What happens if I try to create an object using `User u1 = new User();`?" *(Answer: It will result in a compilation error. Because a parameterized constructor was defined, the compiler did not generate a default no-argument constructor, so `new User()` is now an invalid call).*

### 💻 Practical/Coding Check

* **Find the Syntax Errors:** "Identify the three rule-breaking errors in this constructor definition."
```java
public class DatabaseConnection {
    static final DatabaseConnection() {
        return "Connected";
    }
}

```


* **Solution:** 1. It uses `static` and `final` modifiers, which are illegal for constructors.
2. It attempts to `return` a String value, but constructors cannot return anything.
3. (Implicitly) Because it attempts to return a String, it's functioning like a method missing a return type declaration, fundamentally breaking constructor rules.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for constructor overloading and object initialization.*

### Problem 1: The NPC Generator (Constructor Overloading)

**Scenario:** You are building a game and need a class to spawn Non-Player Characters (NPCs). Some NPCs are generic villagers, while others are specific named quest givers.
**Task:**

1. Create a class named `NPC`.
2. Add fields: `String name` and `int level`.
3. Create a **No-Argument Constructor** that sets the `name` to `"Villager"` and the `level` to `1`.
4. Create a **Parameterized Constructor** that accepts a custom `String` and `int` to set the `name` and `level` dynamically.
5. In a `main` method, instantiate one generic NPC and one custom NPC (e.g., "Blacksmith", level 10) and print their details.

### Problem 2: The E-Commerce Cart Item (Strict Initialization)

**Scenario:** In an online store, an item cannot be added to a cart without a known product ID and a price. You must force the creation of the object to include this data.
**Task:**

1. Create a class named `CartItem`.
2. Add fields: `int productId` and `double price`.
3. Create a single **Parameterized Constructor** that requires both `productId` and `price`.
4. *Do not* create a no-argument constructor.
5. In a `main` method, create a valid `CartItem` object. Then, write a comment explaining why typing `CartItem item = new CartItem();` would cause your program to crash.

### Problem 3: The Library Book (Chained Data)

**Scenario:** You are managing a library system. Books always have a title and an author. If the publication year isn't provided during data entry, it should default to -1 (indicating 'Unknown').
**Task:**

1. Create a class `Book` with `String title`, `String author`, and `int year`.
2. Create a constructor that accepts all three parameters (`title`, `author`, `year`).
3. Create an overloaded constructor that accepts only two parameters (`title`, `author`). Inside this constructor, set the title and author, but manually set `year` to `-1`.
4. In `main`, test both constructors to ensure the object state is initialized correctly.
