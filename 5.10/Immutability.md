Immutability is the quiet superpower of functional programming. An **immutable object** is one whose state cannot be changed after it's created. Once you build it, it's frozen forever. This might sound limiting, but it eliminates entire categories of bugs — especially in concurrent programs.

In a world of multithreading (which we covered in Section 4), immutability gives you thread safety for **free**. If no one can change an object's state, there's no need for `synchronized`, no need for locks, and no possibility of race conditions.

---

## 1. What Makes an Object Immutable?

An immutable class follows these rules:

1. **Declare the class as `final`** (so it can't be subclassed and overridden).
2. **Make all fields `private` and `final`**.
3. **Don't provide setters**.
4. **Initialize all fields via the constructor**.
5. **If a field is a mutable object (like a `List`), return a defensive copy** — never the internal reference.

```java
public final class Money {
    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }

    // Instead of setters, return NEW objects
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
        return new Money(this.amount + other.amount, this.currency);
    }
}
```

Notice: `add()` doesn't modify `this`. It creates and returns a **new** `Money` object.

---

## 2. Defensive Copying: The Mutable Field Trap

The most common mistake in immutability is exposing internal mutable objects.

```java
// ❌ BROKEN immutability
public final class Team {
    private final List<String> members;

    public Team(List<String> members) {
        this.members = members; // BUG: caller can still modify the list!
    }

    public List<String> getMembers() {
        return members; // BUG: caller can modify the returned list!
    }
}

// Usage:
List<String> names = new ArrayList<>(List.of("Alice", "Bob"));
Team team = new Team(names);
names.add("Charlie"); // Modifies the "immutable" team's internal state!
team.getMembers().add("Diana"); // Also modifies it!
```

```java
// ✅ TRUE immutability with defensive copies
public final class Team {
    private final List<String> members;

    public Team(List<String> members) {
        this.members = List.copyOf(members); // Defensive copy on entry
    }

    public List<String> getMembers() {
        return members; // List.copyOf returns unmodifiable — safe!
    }
}
```

---

## 3. Immutable Collections (Java 9+)

Java 9 introduced factory methods for creating unmodifiable collections:

```java
// Unmodifiable List
List<String> colors = List.of("Red", "Green", "Blue");
// colors.add("Yellow"); // UnsupportedOperationException!

// Unmodifiable Set
Set<Integer> primes = Set.of(2, 3, 5, 7, 11);

// Unmodifiable Map
Map<String, Integer> scores = Map.of("Alice", 95, "Bob", 88);

// For larger maps
Map<String, Integer> bigMap = Map.ofEntries(
    Map.entry("Alice", 95),
    Map.entry("Bob", 88),
    Map.entry("Charlie", 92)
);

// Copying an existing mutable collection to an immutable one
List<String> mutable = new ArrayList<>(List.of("a", "b", "c"));
List<String> immutable = List.copyOf(mutable); // Java 10+
```

---

## 4. Why Immutability Matters for Functional Programming

Functional programming is built on the idea that functions don't change things — they take input and produce output without side effects. Immutability is the natural partner of this philosophy.

```java
// Imperative (mutable) — modifies the original
List<String> names = new ArrayList<>(List.of("Alice", "Bob"));
names.replaceAll(String::toUpperCase); // Mutates in place!

// Functional (immutable) — creates a new list
List<String> names = List.of("Alice", "Bob");
List<String> upper = names.stream()
    .map(String::toUpperCase)
    .toList(); // New list, original untouched
```

### Benefits in Streams:

```java
// Immutable objects are naturally safe for parallel streams
List<Money> prices = List.of(
    new Money(10, "USD"),
    new Money(20, "USD"),
    new Money(30, "USD")
);

// No synchronization needed — Money is immutable!
Money total = prices.parallelStream()
    .reduce(new Money(0, "USD"), Money::add);
```

---

## 5. The `String` Example: Java's Most Famous Immutable Class

`String` has been immutable since Java 1.0. This is why:

```java
String greeting = "Hello";
greeting.toUpperCase(); // Returns "HELLO" — does NOT modify greeting
System.out.println(greeting); // Still "Hello"

// This is also why String pooling works
String a = "Java";
String b = "Java";
System.out.println(a == b); // true! Same object in the String Pool
// Safe because Strings can never change
```

---

## 6. Immutable vs. Unmodifiable

These are **not** the same thing:

| Concept | Meaning |
| --- | --- |
| **Immutable** | Cannot be changed by anyone, ever |
| **Unmodifiable (view)** | *You* can't change it, but the original owner still can |

```java
List<String> original = new ArrayList<>(List.of("a", "b"));
List<String> unmodifiable = Collections.unmodifiableList(original);
// unmodifiable.add("c"); // Throws UnsupportedOperationException

original.add("c"); // But this WORKS!
System.out.println(unmodifiable); // [a, b, c] — view was affected!

// Truly immutable:
List<String> immutable = List.copyOf(original); // Java 10+
original.add("d");
System.out.println(immutable); // [a, b, c] — NOT affected!
```

---

## Summary

| Principle | How to Achieve It |
| --- | --- |
| **Immutable class** | `final` class, `private final` fields, no setters, defensive copies |
| **Immutable collections** | `List.of()`, `Set.of()`, `Map.of()`, `List.copyOf()` |
| **Thread safety** | Immutable objects are inherently thread-safe |
| **Functional style** | Return new objects instead of modifying existing ones |
| **Defensive copying** | Copy on construction AND on access for mutable fields |

### Tip:

Starting with Java 16, **Records** provide a built-in way to create immutable data carriers with far less boilerplate. We'll cover them in the next lesson. But understanding manual immutability is essential — Records have limitations, and you'll still need to build immutable classes by hand for complex cases.
