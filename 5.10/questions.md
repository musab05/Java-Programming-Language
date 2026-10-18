Immutability is the foundation of writing safe, predictable code. In functional programming, you never change data — you create new versions of it. This eliminates race conditions in concurrent code and makes your programs easier to reason about.

The most common mistake is thinking "I made the fields final, so my class is immutable." If any field holds a mutable object like a `List` or `Date`, you need defensive copies on both entry and exit — otherwise your "immutable" class is leaking mutability.

---

## 🎤 Frequently Asked Interview Questions

### 1. How do you create an immutable class in Java?

**Answer:** Five steps: (1) Declare the class as `final` to prevent subclassing. (2) Make all fields `private` and `final`. (3) Provide no setter methods. (4) Initialize all fields through the constructor. (5) For any mutable field (like a List or Date), perform **defensive copies** — copy the value both when receiving it in the constructor and when returning it via getters.

### 2. What is the difference between an unmodifiable collection and an immutable collection?

**Answer:** An unmodifiable collection (from `Collections.unmodifiableList()`) is just a read-only *view* of the original mutable collection. Changes to the original are reflected in the view. An immutable collection (from `List.of()` or `List.copyOf()`) is a completely independent copy that cannot be changed by anyone, including the original owner.

### 3. Why is `String` immutable in Java?

**Answer:** String is immutable for three reasons: (1) **Security** — Strings are used in network connections, file paths, and class loading; mutation could be a security hole. (2) **Thread safety** — Immutable Strings can be shared freely between threads. (3) **String pooling** — The JVM caches String literals in the String Pool; this is only safe because Strings never change. (4) **HashMap keys** — String's hashCode is cached because its content never changes, making it an ideal map key.

### 4. What is a "defensive copy"?

**Answer:** A defensive copy is creating a new copy of a mutable object when it enters or leaves your immutable class. On construction, you copy the input so the caller can't modify your internal state through their reference. On access (getters), you return a copy (or an unmodifiable view) so callers can't modify your internals through the returned reference.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Spot the Bug

**Task:** This class claims to be immutable. Find and fix the mutability leak.
```java
public final class Event {
    private final String name;
    private final List<String> attendees;
    public Event(String name, List<String> attendees) {
        this.name = name;
        this.attendees = attendees; // BUG!
    }
    public List<String> getAttendees() { return attendees; } // BUG!
}
```
**Solution:**

```java
public final class Event {
    private final String name;
    private final List<String> attendees;
    public Event(String name, List<String> attendees) {
        this.name = name;
        this.attendees = List.copyOf(attendees); // Defensive copy
    }
    public List<String> getAttendees() { return attendees; } // Safe — List.copyOf is unmodifiable
}
```

### Challenge 2: The Functional Update

**Task:** Add a `withName(String newName)` method to an immutable `Person` class that returns a new instance.
**Solution:**

```java
public final class Person {
    private final String name;
    private final int age;
    public Person(String name, int age) { this.name = name; this.age = age; }
    public Person withName(String newName) { return new Person(newName, this.age); }
    public Person withAge(int newAge) { return new Person(this.name, newAge); }
}
```

---

## 📝 Practice Problems

### Problem 1: The Immutable Shopping Cart

Build a fully immutable shopping cart.

**Requirements:**

1. Create an immutable `CartItem` class with `productName` and `price`.
2. Create an immutable `ShoppingCart` class with a `List<CartItem>` field.
3. Add methods: `addItem(CartItem item)` returns a NEW cart with the item added, `removeItem(String name)` returns a NEW cart without that item, `getTotal()` returns the sum.
4. Demonstrate that modifying the returned cart doesn't affect the original.
5. **The Goal:** Show the "return new instance" pattern for immutable data structures.

### Problem 2: The Immutable Builder

Create an immutable configuration object using the Builder pattern.

**Requirements:**

1. Create an immutable `AppConfig` class with fields: `host`, `port`, `maxRetries`, `timeout`, `List<String> features`.
2. Implement a nested `Builder` class that collects values and produces an `AppConfig` in the `build()` method.
3. Ensure the `features` list is defensively copied.
4. **The Goal:** Show how Builders solve the "too many constructor parameters" problem for immutable classes.
