In Java, **Object-Oriented Programming (OOP)** is the core paradigm that allows you to structure programs by grouping related data and behaviors into "Blueprints" called **Classes**, and then creating individual instances called **Objects**.

---

## 1. What is a Class?

A **Class** is a user-defined template or blueprint from which objects are created. It defines the properties (data) and behaviors (methods) that all objects of that type will share.

Think of a class as a **sketch** for a house. The sketch itself isn't a house, but it defines where the windows, doors, and walls will be.

### Components of a Class:

* **Fields (Attributes):** Variables that store the state of the object.
* **Methods:** Functions that define what the object can do.
* **Constructors:** Special methods used to initialize new objects.

---

## 2. What is an Object?

An **Object** is an instance of a class. When a class is defined, no memory is allocated until an object of that class is created.

If the class is the "House Sketch," then the **Object** is the actual physical house built in a specific neighborhood. You can build many houses (Objects) from a single sketch (Class), each with its own paint color or furniture (State).

---

## 3. Creating a Class and Object

To create an object in Java, you use the `new` keyword.

```java
// 1. Define the Class (The Blueprint)
class Car {
    String color;
    String model;

    void displayDetails() {
        System.out.println("Model: " + model + ", Color: " + color);
    }
}

public class Main {
    public static void main(String[] args) {
        // 2. Create the Object (The Instance)
        Car myCar = new Car(); 

        // 3. Access Attributes and Methods
        myCar.model = "Tesla Model 3";
        myCar.color = "Red";
        myCar.displayDetails(); // Output: Model: Tesla Model 3, Color: Red
    }
}

```

---

## 4. Class vs. Object: Key Differences

| Feature | Class | Object |
| --- | --- | --- |
| **Definition** | A logical template/blueprint. | A physical entity (instance). |
| **Memory** | Does not occupy memory when defined. | Occupies memory in the **Heap** when created. |
| **Existence** | Declared once per program. | Can be created multiple times. |
| **Analogy** | A Recipe. | The Cake baked from the recipe. |

---

## 5. Real-World Application

Since you are interested in **Game Development**, think of a `Player` class in a game:

* **Fields:** `health`, `mana`, `position`.
* **Methods:** `move()`, `attack()`, `takeDamage()`.
Each enemy or player character in your game would be a separate **Object** created from that single `Player` class.
