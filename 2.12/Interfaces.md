In Java, an **Interface** is a completely "abstract class" that is used to group related methods with empty bodies. It represents a strict **contract**—any class that signs this contract (by implementing the interface) absolutely must provide the exact behavior specified by the interface.

If an abstract class is a "partial blueprint," an interface is a "pure blueprint."

---

## 1. How to Create and Use an Interface

To declare an interface, you use the `interface` keyword. To use an interface in a class, you use the `implements` keyword (instead of `extends`).

```java
// 1. Define the Interface (The Contract)
interface Movable {
    void move(); // No body, just the signature!
}

interface Attackable {
    void attack();
}

// 2. Implement the Interface (Signing the Contract)
class Knight implements Movable, Attackable {
    
    // Must provide the body for Movable's method
    @Override
    public void move() {
        System.out.println("Knight walks forward heavily in armor.");
    }

    // Must provide the body for Attackable's method
    @Override
    public void attack() {
        System.out.println("Knight swings a broadsword!");
    }
}

```

---

## 2. The Strict Rules of Interfaces

By default, the Java compiler makes several assumptions about the code inside an interface, even if you don't type the specific keywords:

1. **Methods are Abstract:** Every method you declare without a body is automatically considered `public` and `abstract`.
2. **Variables are Constants:** You cannot have regular instance variables in an interface. Any variable you define is automatically `public`, `static`, and `final`. You must assign it a value immediately.
3. **No Instantiation:** Just like abstract classes, you cannot use the `new` keyword to create an object from an interface.
4. **No Constructors:** Because they cannot hold state (instance variables) and cannot be instantiated, interfaces do not have constructors.

---

## 3. The Big Advantage: Multiple Inheritance

As you learned during the Inheritance topic, Java classes suffer from the "Diamond Problem"—a class can only `extends` one single parent class.

Interfaces completely bypass this limitation. **A single class can implement an unlimited number of interfaces.** This allows you to mix and match capabilities across completely unrelated classes.

For example, a `Helicopter` and a `Bird` don't share a parent class (one is a Machine, one is an Animal), but they can both implement a `Flyable` interface!

---

## 4. Modern Java Additions (Java 8+)

Historically, interfaces were 100% abstract. However, starting with Java 8, two massive features were added to solve the "Fragile Base Class" problem we discussed earlier:

* **`default` Methods:** You can now provide a default method body inside an interface. If a class implements the interface but doesn't override the default method, it just uses the interface's version. This allows you to add new methods to an interface without breaking the thousands of classes that already implement it!
* **`static` Methods:** Interfaces can now hold static helper methods, just like regular classes.

```java
interface Renderable {
    void draw(); // Abstract method

    // Default method (Has a body!)
    default void displayShadow() {
        System.out.println("Drawing a generic drop shadow.");
    }
}

```

---

## 5. Interface vs. Abstract Class Summary

Knowing when to use which is a common interview question and a critical architectural decision:

| Feature | Interface | Abstract Class |
| --- | --- | --- |
| **Inheritance** | A class can implement **multiple** interfaces. | A class can inherit only **one** abstract class. |
| **Variables** | Only `public static final` (constants). | Can have regular, static, and final variables. |
| **Methods** | `abstract`, `default`, or `static`. | Can be any mix of concrete and abstract. |
| **Constructors** | No constructors allowed. | Has constructors (called via `super()`). |
| **Relationship** | Defines a **"CAN-DO"** relationship (Capability). | Defines an **"IS-A"** relationship (Identity). |
