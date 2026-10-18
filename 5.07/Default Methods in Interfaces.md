Before Java 8, interfaces were a strict contract: they could only declare abstract methods. If you wanted to add a new method to an interface, every single implementing class had to be updated — a nightmare for library designers. **Default methods** changed everything.

A default method is a method in an interface that has a body — a real implementation. Classes that implement the interface inherit this method automatically without needing to override it. This allowed Java to evolve the `Collection`, `List`, and `Map` interfaces without breaking billions of lines of existing code.

---

## 1. The Syntax

```java
public interface Sortable {
    // Abstract method — must be implemented
    int compareTo(Object other);

    // Default method — has a body, inherited for free
    default boolean isGreaterThan(Object other) {
        return compareTo(other) > 0;
    }

    // Static method — called on the interface itself
    static void printInfo() {
        System.out.println("This is the Sortable interface");
    }
}
```

A class implementing `Sortable` only needs to implement `compareTo`. It gets `isGreaterThan` for free.

---

## 2. Why Default Methods Were Created

The primary motivation was **API evolution**. When Java 8 introduced the Stream API, they needed to add methods like `stream()` and `forEach()` to the `Collection` interface. Without default methods, every class that implements `Collection` (hundreds of them, including custom ones in third-party libraries) would have broken.

```java
// This was added to java.lang.Iterable in Java 8
default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
```

Now every `Iterable` (and by extension, every `Collection`) gained `forEach()` without changing a single implementation class.

---

## 3. The Diamond Problem: Multiple Inheritance Conflicts

What happens when a class implements two interfaces that both have a default method with the same signature?

```java
interface Flyer {
    default String identify() { return "I can fly"; }
}

interface Swimmer {
    default String identify() { return "I can swim"; }
}

// This WON'T compile — ambiguous!
class Duck implements Flyer, Swimmer {
    // MUST override to resolve the conflict
    @Override
    public String identify() {
        // You can call a specific interface's version
        return Flyer.super.identify() + " and " + Swimmer.super.identify();
    }
}
```

### The Resolution Rules:

1. **Class wins:** If a class provides its own implementation, it always wins over any default method.
2. **Sub-interface wins:** If `InterfaceB extends InterfaceA` and both have the same default method, `InterfaceB`'s version wins.
3. **Explicit resolution required:** If there's a true conflict (two unrelated interfaces), the class **must** override the method.

---

## 4. Real-World Examples from the JDK

```java
// Iterable: forEach (Java 8)
List.of("a", "b", "c").forEach(System.out::println);

// Collection: stream(), parallelStream() (Java 8)
List.of(1, 2, 3).stream().filter(n -> n > 1).toList();

// Map: getOrDefault, putIfAbsent, forEach, replaceAll (Java 8)
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 90);
int bobScore = scores.getOrDefault("Bob", 0); // Returns 0 instead of null

// Comparator: reversed(), thenComparing() (Java 8)
Comparator<String> byLength = Comparator.comparingInt(String::length);
Comparator<String> byLengthThenAlpha = byLength.thenComparing(Comparator.naturalOrder());

// List: sort (Java 8)
List<String> names = new ArrayList<>(List.of("Charlie", "Alice", "Bob"));
names.sort(String::compareTo);
```

---

## 5. Default Methods vs. Abstract Classes

With default methods, interfaces look a lot more like abstract classes. So when do you use which?

| Feature | Interface (with defaults) | Abstract Class |
| --- | --- | --- |
| **Multiple Inheritance** | Yes — implement many | No — extend only one |
| **State (Fields)** | No instance fields | Yes — can have fields |
| **Constructors** | No | Yes |
| **Access Modifiers** | `public` only (until Java 9) | Any (`private`, `protected`, etc.) |
| **Use Case** | Defining capabilities/behaviors | Defining "is-a" hierarchies |

### Java 9 Addition: Private Methods in Interfaces

Java 9 allowed `private` methods in interfaces to share code between default methods without exposing them:

```java
public interface Logger {
    default void logInfo(String msg) { log("INFO", msg); }
    default void logError(String msg) { log("ERROR", msg); }

    // Private helper — not visible to implementing classes
    private void log(String level, String msg) {
        System.out.println("[" + level + "] " + msg);
    }
}
```

---

## Summary

| Concept | Key Point |
| --- | --- |
| **Purpose** | Evolve interfaces without breaking implementations |
| **Syntax** | `default returnType methodName() { body }` |
| **Diamond Problem** | Resolved by: class > sub-interface > must-override |
| **Static Methods** | Called via `InterfaceName.method()`, not inherited |
| **Private Methods (Java 9+)** | Helper methods for default methods |

### Tip:

Use default methods for **optional behavior** that makes sense for most implementors but can be overridden. Don't use them as a substitute for abstract classes. If your interface starts accumulating state-dependent logic or complex method hierarchies, it's time to switch to an abstract class.
