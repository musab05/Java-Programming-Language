# Methods in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java methods, including their structure, parameters, return types, and how they manage memory (pass-by-value).

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of reusable code blocks and method mechanics.*

1. **What is the difference between an Argument and a Parameter?**
* **Parameters** (formal parameters) are the variables defined in the method's declaration (e.g., `public void setAge(int age)`).
* **Arguments** are the actual, concrete values passed into the method when it is called (e.g., `setAge(25)`).


2. **Is Java "Pass-by-Value" or "Pass-by-Reference"? Explain what that means.**
* Java is strictly **Pass-by-Value**. When you pass an argument to a method, the JVM creates a *copy* of that value. Any modifications made to the parameter inside the method do not affect the original variable outside the method.


3. **What happens to the execution flow when a `return` statement is reached?**
* The method immediately terminates. No further code inside that method will execute, and the program's flow is handed back to the exact location where the method was originally called, along with the returned value (if any).


4. **Why would you declare a method as `static` versus non-static?**
* A **static method** belongs to the class itself and can be called directly (e.g., `Math.max()`) without creating an object.
* A **non-static (instance) method** belongs to a specific object. You must instantiate the class using the `new` keyword before you can call it, which is useful when the method needs to interact with an object's unique instance variables.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands method signatures and scope.*

### 🗣️ Verbal/Conceptual Check

* **The Void Trap:** "If a method is declared as `public void updateScore()`, can I write `return 100;` inside its body?" *(Answer: No. The `void` keyword explicitly means the method does not return any data. Attempting to return a value will cause a compilation error).*
* **The Pass-by-Value Illusion:** "I have an `int playerHealth = 100;`. I pass it to a method `public void takePoisonDamage(int hp) { hp = hp - 10; }`. After the method finishes, what is the value of `playerHealth`?" *(Answer: Still 100. Because Java is pass-by-value, only the local copy `hp` inside the method was reduced to 90. The original `playerHealth` variable remains completely unchanged).*

### 💻 Practical/Coding Check

* **The Signature Mismatch:** "A developer wrote this code, but it won't compile. What are the two structural mistakes?"
```java
public class Game {
    public String getPlayerName(String id) {
        System.out.println("Fetching player...");
    }

    public static void main(String[] args) {
        String name = getPlayerName("User123");
    }
}

```


* **Solution:** 1. The `getPlayerName` method declares a `String` return type, but it is missing a `return` statement.
2. The `main` method is `static`, but it is trying to call `getPlayerName()`, which is an instance (non-static) method, without creating a `new Game()` object first.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of method creation, return types, and instantiation.*

### Problem 1: The Damage Multiplier (Static Methods & Returns)

**Scenario:** You are programming a combat mechanic for a game. You need a quick utility method that calculates critical hit damage.
**Task:**

1. Inside your class, create a `public static` method named `calculateCrit`.
2. It should accept two parameters: an `int baseDamage` and a `double multiplier`.
3. The method must calculate the total damage and **return** the result as a `double`.
4. Inside your `main` method, call `calculateCrit(50, 1.5)` and print the returned value to the console.

### Problem 2: The Portfolio Greeter (Instance Methods)

**Scenario:** You are building the backend for a personal portfolio website. You want a personalized greeting to print out, but the greeter method should be tied to a specific visitor session object.
**Task:**

1. Inside a class named `PortfolioSite`, create a **non-static** method named `printWelcome`.
2. It should have a `void` return type and accept one `String visitorName` parameter.
3. Inside the method, print `"Welcome to my portfolio, [visitorName]!"`.
4. Inside the `main` method, create an instance of `PortfolioSite` using the `new` keyword.
5. Use that instance object to call the `printWelcome` method, passing in your own name as the argument.
