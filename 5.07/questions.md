Default methods were Java's elegant solution to the "interface evolution" problem. Without them, the entire Stream API revolution would have been impossible — you can't add methods to `Collection` and break every library on earth. They also opened the door to a limited form of multiple inheritance in Java.

The key to mastering default methods is understanding the resolution rules when conflicts arise. In interviews, the "diamond problem" question comes up constantly.

---

## 🎤 Frequently Asked Interview Questions

### 1. What was the main motivation for adding default methods to interfaces?

**Answer:** The primary motivation was **backward compatibility**. Java 8 needed to add new methods (like `stream()`, `forEach()`, `sort()`) to existing core interfaces (`Collection`, `Iterable`, `List`). Without default methods, every class implementing those interfaces would need to be modified, breaking all existing code. Default methods allowed the JDK team to evolve these interfaces while maintaining full backward compatibility.

### 2. How does Java resolve the "diamond problem" with default methods?

**Answer:** Java uses three rules in order: (1) A class's own implementation always wins over any default method. (2) Among interfaces, the most specific (sub-interface) wins. (3) If there's a true conflict between unrelated interfaces, the compiler forces the class to explicitly override the method and choose which version to use (or provide its own). You can call a specific interface's version using `InterfaceName.super.method()`.

### 3. Can a default method override a method from `Object`?

**Answer:** No. An interface cannot provide a default implementation for `equals()`, `hashCode()`, or `toString()` (methods from `java.lang.Object`). This is because every class already inherits these from `Object`, and allowing interfaces to override them would create ambiguity and violate Java's class hierarchy guarantees.

### 4. What is the difference between a default method and a static method in an interface?

**Answer:** A default method is inherited by implementing classes and can be overridden. It's called on instances. A static method belongs to the interface itself, is not inherited, and is called using `InterfaceName.staticMethod()`. Static methods are useful for utility/helper functions related to the interface's purpose.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Conflict Resolver

**Task:** Create two interfaces `Printable` and `Loggable`, both with a default `output(String msg)` method. Create a class that implements both and resolves the conflict.
**Solution:**

```java
interface Printable {
    default void output(String msg) { System.out.println("[PRINT] " + msg); }
}
interface Loggable {
    default void output(String msg) { System.out.println("[LOG] " + msg); }
}
class Report implements Printable, Loggable {
    @Override
    public void output(String msg) {
        Printable.super.output(msg); // Explicitly choose
    }
}
```

### Challenge 2: The Enhanced Interface

**Task:** Create a `Measurable` interface with an abstract `getArea()` method and a default `describe()` method that prints "Area: [area]".
**Solution:**

```java
interface Measurable {
    double getArea();
    default String describe() {
        return String.format("Area: %.2f", getArea());
    }
}
```

---

## 📝 Practice Problems

### Problem 1: The Plugin System

Design a plugin system where interfaces define capabilities with sensible defaults.

**Requirements:**

1. Create a `Plugin` interface with: abstract `String getName()`, default `void initialize()` that prints "[name] initialized", default `void shutdown()` that prints "[name] shut down", default `int getPriority()` returning 5.
2. Create three plugins: `DatabasePlugin` (overrides priority to 1), `CachePlugin` (overrides initialize to also print cache setup), and `LogPlugin` (uses all defaults).
3. Store them in a list, sort by priority, and call initialize → shutdown on each.
4. **The Goal:** Show how default methods provide sensible behavior while allowing customization.

### Problem 2: The Multi-Interface Diamond

Resolve a complex multiple-inheritance scenario.

**Requirements:**

1. Create interfaces: `Readable` (default `read()` returns "Reading..."), `Writable` (default `write()` returns "Writing..."), and `ReadWritable extends Readable, Writable` (adds default `readWrite()` combining both).
2. Create a class `SmartFile` implementing `ReadWritable` that overrides `read()`.
3. Show that `SmartFile` gets `write()` and `readWrite()` for free but uses its custom `read()`.
4. **The Goal:** Demonstrate inheritance, overriding, and the resolution hierarchy.
