# Polymorphism in Java: Assessment Guide

This guide provides an exhaustive list of interview questions, conceptual checks, and targeted coding practice problems strictly focused on Java's Polymorphism, method overriding, and dynamic binding via upcasting.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of dynamic behavior and flexible code design.*

1. **What are the two types of Polymorphism in Java, and how are they achieved?**
* **Compile-Time Polymorphism (Static Binding):** Achieved through **Method Overloading**. The compiler determines which method to call at compile time based on the method signature (parameters).
* **Run-Time Polymorphism (Dynamic Binding):** Achieved through **Method Overriding**. The JVM determines which method to call at runtime based on the actual object type in memory, not the reference variable type.


2. **What are the strict rules for Method Overriding?**
* The child class method must have the exact same name, return type, and parameter list as the parent class method it is overriding.


3. **What is the purpose of the `@Override` annotation?**
* It is a safety net. It explicitly tells the compiler that you intend to override a parent method. If you make a typo in the method name or parameters, the compiler will throw an error instead of accidentally creating a brand new, overloaded method.


4. **What is "Upcasting" in Java?**
* Upcasting is the process of storing a child object in a reference variable of its parent's type (e.g., `Enemy boss = new Dragon();`). This is perfectly legal because a Dragon IS-A(n) Enemy.


5. **If `Enemy e = new Zombie();` is executed, and both classes have an `attack()` method, whose `attack()` method runs?**
* The `Zombie`'s `attack()` method runs. At compile time, the compiler checks if `attack()` exists in the `Enemy` reference type. At runtime, the JVM looks at the actual object in the Heap (`Zombie`) and executes its specific overridden version.



---

## 💡 Concept Check Questions

*Use these questions to verify if you truly understand dynamic binding and reference limitations.*

### 🗣️ Verbal/Conceptual Check

* **The Missing Method Trap:** "I have a `Vehicle` parent class and a `Car` child class. `Car` overrides `startEngine()` and adds a brand new method called `openTrunk()`. If I write `Vehicle v = new Car();`, can I call `v.openTrunk();`?" *(Answer: No. The reference type is `Vehicle`. The compiler only lets you call methods that exist in the `Vehicle` class. Even though the actual object is a `Car`, the `Vehicle` "remote control" doesn't have an `openTrunk` button).*
* **The Overloading Confusion:** "Class A has `public void draw()`. Class B extends A and writes `public void draw(int size)`. Is this Method Overriding?" *(Answer: No, this is Method Overloading. Because the parameters are different, Class B now has *two* draw methods: the one inherited from A, and its new one with the integer parameter).*

### 💻 Practical/Coding Check

* **Predict the Output:** "What will print to the console when this code runs?"
```java
class Spell {
    public void cast() { System.out.println("Casting generic magic!"); }
}
class Fireball extends Spell {
    @Override
    public void cast() { System.out.println("Casting a massive fireball!"); }
}
public class MagicSystem {
    public static void main(String[] args) {
        Spell mySpell = new Fireball();
        mySpell.cast();
    }
}

```


* **Solution:** It will print `"Casting a massive fireball!"`. Dynamic binding ensures the JVM runs the overridden method of the actual object type instantiated in memory (`Fireball`), despite the reference being of type `Spell`.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for upcasting and dynamic method dispatch.*

### Problem 1: The Particle System (Method Overriding)

**Scenario:** You are building a visual effects system. Different types of particles spawn differently, but the engine needs to treat them all as standard particles.
**Task:**

1. Create a parent class `Particle` with a method `void spawn()`. It should print `"Spawning a generic white dot."`
2. Create a child class `Spark` that extends `Particle` and overrides `spawn()` to print `"Spawning a bright yellow spark!"`
3. Create a child class `Smoke` that extends `Particle` and overrides `spawn()` to print `"Spawning a dark grey cloud."`
4. In `main`, use **upcasting** to create one `Spark` object and one `Smoke` object, storing both in `Particle` reference variables. Call `spawn()` on both.

### Problem 2: The Game Roster Array (Dynamic Binding in Loops)

**Scenario:** Your game engine needs to loop through a list of active entities and trigger their specific movement behaviors without needing to know exactly what type of entity each one is.
**Task:**

1. Create a parent class `Entity` with a method `void move()`.
2. Create child classes `Player`, `NPC`, and `Monster` that all extend `Entity` and override the `move()` method with their own unique print statements (e.g., "Player runs", "NPC walks slowly", "Monster prowls").
3. In `main`, create an array of type `Entity[]` that holds one of each child object.
4. Write a `for` loop that iterates through the array and calls `.move()` on every entity.

### Problem 3: The Notification Dispatcher (Scalable Architecture)

**Scenario:** You are writing an alert system. Different channels (Email, SMS) send messages differently.
**Task:**

1. Create a parent class `Notifier` with `void sendAlert(String message)`.
2. Create `EmailNotifier` and `SMSNotifier` that override `sendAlert()` to format the message differently (e.g., "Sending Email: [message]" vs "Texting: [message]").
3. In `main`, create a static method `public static void triggerAlarm(Notifier n, String msg)`. Inside this method, call `n.sendAlert(msg)`.
4. Call `triggerAlarm` twice from `main`, passing an `EmailNotifier` the first time and an `SMSNotifier` the second time, proving that the single method dynamically adapts to the object passed in.
