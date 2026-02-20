# The `super` Keyword in Java: Assessment Guide

This guide is designed to clear all the common hurdles and "trick questions" interviewers love to ask about the `super` keyword, especially regarding how the Java compiler silently handles constructors behind the scenes.

## 🎤 Frequent Interview Questions

*These questions target the specific traps and hidden mechanics of the `super` keyword that often trip up candidates.*

1. **What happens if you don't write `super()` in a child class constructor?**
* **The Hurdle:** Many developers don't realize that the Java compiler *automatically* inserts a hidden `super();` (a call to the parent's no-argument constructor) as the very first line of any child constructor if you don't write it yourself.


2. **Can you use both `this()` and `super()` in the same constructor?**
* **The Hurdle:** **No.** This is a classic interview trick. Both `this()` (to call another constructor in the same class) and `super()` (to call the parent's constructor) **must be the very first statement** in a constructor. Because they both demand to be line number one, they cannot exist in the same block.


3. **If a parent class only has a parameterized constructor (e.g., `public Parent(int x)`), what must the child class do?**
* **The Hurdle:** If the parent has no default constructor, the compiler's silent insertion of `super();` will fail and throw an error. The child class *must* explicitly define a constructor and manually call `super(x);` on the first line to satisfy the parent's requirements.


4. **Can you use the `super` keyword inside a `static` method?**
* **The Hurdle:** **No.** Just like the `this` keyword, `super` relies on an active object instance existing in memory to point to its parent. Because `static` methods belong to the class and run without objects, `super` is strictly forbidden.


5. **How does `super` behave differently with variables versus methods?**
* Variables are **Shadowed**: Both the parent and child variables exist in memory simultaneously. `super.x` accesses the parent's hidden variable.
* Methods are **Overridden**: The child's method replaces the parent's behavior for that object. `super.method()` is the only way to bypass the override and trigger the original parent logic.



---

## 💡 Concept Check Questions

*Use these questions to verify if you can spot compilation errors related to inheritance initialization.*

### 🗣️ Verbal/Conceptual Check

* **The Missing Parent Trap:** "I have a `Vehicle` class with a constructor `public Vehicle(String type)`. I create a `Car extends Vehicle` class with a constructor `public Car() { System.out.println("Car built"); }`. Will this compile?" *(Answer: No! The compiler silently adds `super();` to the `Car` constructor, but `Vehicle` doesn't have a no-argument constructor. You must explicitly write `super("some type");` inside the `Car` constructor).*
* **The Deep Hierarchy Trap:** "Class C extends Class B, which extends Class A. If I am inside Class C, can I write `super.super.methodA()` to skip Class B and talk directly to Class A?" *(Answer: No. Java strictly forbids chaining `super`. You can only ever look at your immediate, direct parent).*

### 💻 Practical/Coding Check

* **Find the Compilation Bug:** "Why will this code fail to compile?"
```java
class Base {
    public Base() { System.out.println("Base ready."); }
}

class Derived extends Base {
    public Derived() {
        System.out.println("Derived initializing...");
        super(); // COMPILER ERROR
    }
}

```


* **Solution:** The `super()` call is on the second line. It **must** be the very first statement inside the constructor block, before any `System.out.println` or variable assignments.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for the three primary uses of the `super` keyword.*

### Problem 1: The Hidden Settings (Accessing Parent Variables)

**Scenario:** You are building a configuration manager. The base system has a default timeout limit, but a specific user profile has its own custom timeout limit that shadows the default.
**Task:**

1. Create a parent class `SystemConfig` with an `int timeout = 30;`.
2. Create a child class `UserProfile` that `extends SystemConfig`. Give it an `int timeout = 60;` (shadowing the parent).
3. Inside `UserProfile`, create a method `void printTimeouts()`. It should print:
* `"Custom Timeout: [child's timeout]"`
* `"System Default Timeout: [parent's timeout]"` (Use `super` to access this).


4. Instantiate `UserProfile` in `main` and call the method.

### Problem 2: The Combo Attack (Invoking Parent Methods)

**Scenario:** A specialized enemy type should do exactly what a basic enemy does when it attacks, but then follow it up with a unique secondary action without duplicating the code.
**Task:**

1. Create a parent class `BasicEnemy` with a `void attack()` method that prints `"Enemy throws a punch!"`.
2. Create a child class `NinjaEnemy` that `extends BasicEnemy`.
3. Override the `attack()` method.
4. Inside the overridden method, use `super` to trigger the punch, and then print `"Ninja follows up with a roundhouse kick!"`.
5. Instantiate `NinjaEnemy` in `main` and call `.attack()`.

### Problem 3: The Employee ID Badge (Invoking Parent Constructors)

**Scenario:** You have a strict rule: no employee can exist without a name and ID. Therefore, the parent class enforces this via a parameterized constructor. The child class must accommodate this.
**Task:**

1. Create a parent class `Employee`. Give it `String name` and `int id`. Create a constructor `Employee(String name, int id)` that assigns these variables.
2. Create a child class `Manager` that `extends Employee`. Add a new field `int teamSize`.
3. Create a constructor for `Manager` that accepts `(String name, int id, int teamSize)`.
4. Use `super` to pass the `name` and `id` up to the parent, then assign the `teamSize` locally.
5. In `main`, create a `Manager` object and print all three of its details to prove it was constructed correctly.
