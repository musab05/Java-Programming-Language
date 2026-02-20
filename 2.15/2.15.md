In Java, the **`final`** keyword is a non-access modifier used to apply strict restrictions on a program's components. When you mark something as `final`, you are essentially telling the compiler: **"This is complete and cannot be modified."**

The `final` keyword can be applied to three main entities in Java: **Variables**, **Methods**, and **Classes**.

---

## 1. Final Variables (Constants)

When a variable is declared as `final`, its value cannot be changed once it has been initialized. It becomes a constant.

### **Types of Final Variables:**

* **Initialized Final Variable:** A variable assigned a value at the exact moment of declaration.
* **Blank Final Variable:** A final variable that is not initialized during declaration. It **must** be initialized strictly inside a constructor. If it is a `static` blank final variable, it must be initialized inside a `static` block.

```java
public class GameSettings {
    // Initialized final variable (Constant)
    final int MAX_PLAYERS = 4; 
    
    // Blank final variable
    final String SERVER_NAME; 

    public GameSettings(String serverName) {
        // Must be initialized in the constructor
        this.SERVER_NAME = serverName; 
    }

    public void changeSettings() {
        // MAX_PLAYERS = 8; // ERROR: Cannot assign a value to a final variable
    }
}

```

### **The "Reference Variable" Catch**

If you make an object reference (like an array or a custom class) `final`, you cannot make that reference point to a brand new object in memory. However, you **can** still change the internal state (the fields) of that object.

```java
final int[] scores = {10, 20, 30};
scores[0] = 99; // VALID: Changing the internal state is allowed.
// scores = new int[]{1, 2, 3}; // INVALID: Cannot point the 'scores' reference to a new array.

```

---

## 2. Final Methods

When a method is declared as `final`, it **cannot be overridden** by any subclass.

This is crucial for security and architectural integrity. If you have a core algorithm that must execute in a very specific way, marking it `final` prevents a rogue child class from altering that behavior.

```java
class Vehicle {
    // This method can be overridden
    public void startEngine() {
        System.out.println("Engine is starting...");
    }

    // This method is locked down
    public final void verifyVIN() {
        System.out.println("Verifying Vehicle Identification Number securely.");
    }
}

class Car extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Car engine purrs to life.");
    }

    // @Override
    // public void verifyVIN() { ... } // ERROR: Cannot override the final method from Vehicle
}

```

---

## 3. Final Classes

When a class is declared as `final`, it **cannot be inherited** (extended) by any other class.

The most famous example of this in Java is the `String` class. The creators of Java made `String` final so that no one could create a custom `MyString` class, override its methods, and inject malicious code or break the way strings are pooled in memory.

```java
// Locked class
final class SecureDataBox {
    public void encrypt() {
        System.out.println("Encrypting data...");
    }
}

// class UserData extends SecureDataBox { } // ERROR: Cannot inherit from final 'SecureDataBox'

```

---

## 4. Summary Guide

| Applied To | Effect of `final` Keyword | Best Use Case |
| --- | --- | --- |
| **Variable** | Value cannot be reassigned. | Creating universal constants (e.g., `PI`, `MAX_SPEED`). |
| **Method** | Cannot be overridden by a subclass. | Protecting core algorithms and security checks in parent classes. |
| **Class** | Cannot be inherited. | Creating immutable classes (like `String`) or strict utility classes. |
