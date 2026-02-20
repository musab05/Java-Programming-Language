In Java, **Multiple Inheritance** refers to the ability of a single class to inherit behaviors and attributes from more than one parent. While Java strictly forbids this for *Classes*, it fully supports it for *Interfaces*.

To understand why this distinction exists, we first have to look at the problem Java's creators were trying to avoid.

---

## 1. The "Diamond Problem" (Why Classes Fail)

Imagine a scenario where Java allowed a class to extend two parent classes.

If Class `B` and Class `C` both inherit from Class `A`, and they both override a `start()` method in different ways. If a new Class `D` tries to inherit from *both* `B` and `C`, and you call `D.start()`, which version of the method should the JVM execute? Class `B`'s version, or Class `C`'s version?

Because the compiler gets confused and cannot resolve this ambiguity, it is known as the **Diamond Problem**. To prevent this catastrophic confusion, Java outright bans `class D extends B, C`.

---

## 2. How Interfaces Solve the Problem

Traditionally, interfaces only contained **abstract methods** (methods without bodies). Because there is no implementation (no actual code inside the method), there is nothing to conflict!

If a class implements two interfaces that both ask for a `draw()` method, the compiler doesn't care. The implementing class is forced to write its own single, unique version of the `draw()` method anyway.

### **Game Engine Example:**

Here is how you can use multiple interfaces to build a modular entity in a game.

```java
// Contract 1
interface Renderable {
    void draw();
}

// Contract 2
interface Collidable {
    void onCollision();
}

// A class can implement as many interfaces as it needs, separated by commas
class Asteroid implements Renderable, Collidable {
    
    @Override
    public void draw() {
        System.out.println("Rendering a jagged, grey rock texture to the screen.");
    }

    @Override
    public void onCollision() {
        System.out.println("Asteroid shatters into smaller pieces!");
    }
}

```

---

## 3. The Java 8 Conflict (Default Methods)

With the introduction of Java 8, interfaces were given the ability to have `default` methods (methods *with* bodies). This created a loophole where the Diamond Problem could technically happen again via interfaces.

**The Scenario:** If a class implements two interfaces that both have a `default` method with the exact same signature, the compiler gets confused once again.

**The Fix:** Java's rule is that the developer *must* override the conflicting method in the implementing class and manually specify which one to use, or write an entirely new one.

```java
interface Engine {
    default void start() {
        System.out.println("Engine is humming.");
    }
}

interface AI {
    default void start() {
        System.out.println("AI systems booting up.");
    }
}

// The class implements both, causing a collision on the start() method
class SmartCar implements Engine, AI {
    
    // You MUST override the method to resolve the ambiguity
    @Override
    public void start() {
        // You can explicitly call a specific interface's default method using 'super'
        Engine.super.start(); 
        AI.super.start();
        System.out.println("SmartCar is ready to drive.");
    }
}

```

By forcing the developer to manually write an override, Java maintains safety and predictability while still granting the immense flexibility of multiple inheritance.
