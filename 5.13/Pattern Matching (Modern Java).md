# Lesson 5.13: Pattern Matching in Modern Java

For decades, writing polymorphic or heterogeneous data-handling code in Java was infamous for clumsy boilerplate: `instanceof` checks followed by explicit casts, cascading `if-else` blocks, and error-prone type assertions.

Between Java 14 and Java 21, Java introduced **Pattern Matching**, fundamentally transforming how we test, cast, and extract data from objects.

---

## 1. Pattern Matching for `instanceof` (Java 16)

### The Old Way (Pre-Java 14)
```java
if (obj instanceof String) {
    String s = (String) obj; // Redundant, manual, error-prone cast!
    System.out.println(s.toUpperCase());
}
```

### The Modern Way: Type Patterns
A pattern combines a **predicate** (type check) with a **binding variable**:

```java
if (obj instanceof String s) {
    // 's' is automatically cast and in scope here!
    System.out.println(s.toUpperCase());
}
```

### Flow Scoping
The binding variable is only in scope where the condition is **guaranteed to be true**:

```java
// ✅ Allowed: 's' is only reached if 'obj' is indeed a String
if (obj instanceof String s && !s.isBlank()) {
    System.out.println("Valid string: " + s);
}

// ❌ COMPILE ERROR: Cannot use '||' because 's' might not be a String!
// if (obj instanceof String s || s.length() > 0)

// ✅ Early return pattern
public void printLength(Object obj) {
    if (!(obj instanceof String s)) {
        return; // Exits early
    }
    // 's' is safely in scope here because non-Strings were returned!
    System.out.println("String length: " + s.length());
}
```

---

## 2. Pattern Matching for `switch` (Java 21)

Java 21 finalized pattern matching for `switch`, allowing you to switch directly on the **type** of an object rather than just primitives and strings.

```java
public static String formatValue(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("Integer: %,d", i);
        case Double d  -> String.format("Floating-point: %.2f", d);
        case Long l    -> String.format("Long integer: %d L", l);
        case String s  -> "String of length " + s.length() + ": " + s;
        case null      -> "Explicit null value!"; // Java 21 handles null directly in switch!
        default        -> "Unknown object: " + obj.getClass().getSimpleName();
    };
}
```

> [!NOTE]
> Previously, passing `null` to a `switch` threw an immediate `NullPointerException`. In modern Java, `case null` is a first-class citizen!

---

## 3. Guarded Patterns (`when` Clauses)

Often, matching the type isn't enough — you need to test a condition on the matched value. Modern Java provides the `when` clause (guarded pattern):

```java
public static String evaluateScore(Object obj) {
    return switch (obj) {
        case Integer i when i >= 90 -> "Grade A: " + i;
        case Integer i when i >= 75 -> "Grade B: " + i;
        case Integer i when i >= 50 -> "Grade C: " + i;
        case Integer i              -> "Failing Grade: " + i;
        case String s when s.equalsIgnoreCase("PASS") -> "Audited: Passed";
        case String s                                 -> "Status: " + s;
        case null, default                            -> "No score recorded";
    };
}
```

---

## 4. Record Patterns (Deconstruction) (Java 21)

While type patterns test whether an object has a type, **Record Patterns** go one step further: they **deconstruct** the record into its individual components in a single step!

Suppose we have:
```java
public record Point(int x, int y) {}
public record ColoredPoint(Point point, String color) {}
public record Rectangle(Point topLeft, Point bottomRight) {}
```

### Basic Record Deconstruction
```java
// Modern Java 21 Record Pattern with instanceof
if (obj instanceof Point(int x, int y)) {
    System.out.println("Coordinates: x=" + x + ", y=" + y);
}
```

### Nested Record Deconstruction
You can deconstruct arbitrarily nested records:

```java
public static void printRectangleInfo(Object obj) {
    if (obj instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        System.out.printf("Rectangle dimensions: %d x %d%n", width, height);
    }
}
```

### Record Patterns inside `switch`
```java
public static double calculateArea(Object shape) {
    return switch (shape) {
        case Circle(double radius) -> Math.PI * radius * radius;
        case Rectangle(Point(int x1, int y1), Point(int x2, int y2)) -> {
            int w = Math.abs(x2 - x1);
            int h = Math.abs(y2 - y1);
            yield (double) w * h;
        }
        case null, default -> 0.0;
    };
}
```

---

## 5. Dominance of Pattern Cases (Ordering Matters!)

The compiler enforces pattern hierarchy. A broader, more general pattern **must never appear before** a more specific pattern, or a compile-time "pattern dominance" error will occur:

```java
// ❌ COMPILE ERROR: Case String s dominates Case String s when ...
switch (obj) {
    case String s -> System.out.println("Any string");
    case String s when s.length() > 5 -> System.out.println("Long string"); // Unreachable!
}

// ✅ Correct order: Specific with guard BEFORE general
switch (obj) {
    case String s when s.length() > 5 -> System.out.println("Long string: " + s);
    case String s -> System.out.println("Other string: " + s);
}
```

Similarly:
```java
// ❌ COMPILE ERROR: Object obj dominates all subclasses
switch (obj) {
    case Object o    -> System.out.println("Object");
    case String s    -> System.out.println("String"); // Dominated & unreachable!
}
```

---

## 6. Summary: The Modern Java Pattern Matching Arsenal

| Feature | Introduced | Syntax Example |
| :--- | :--- | :--- |
| **`instanceof` Type Pattern** | Java 16 | `if (obj instanceof String s)` |
| **`switch` Type Pattern** | Java 21 | `case String s -> ...` |
| **Guarded Patterns** | Java 21 | `case Integer i when i > 0 -> ...` |
| **Record Patterns (Deconstruction)** | Java 21 | `case Point(int x, int y) -> ...` |
| **Nested Deconstruction** | Java 21 | `case Rect(Point(int x1, int y1), ...) -> ...` |
| **Null handling in `switch`** | Java 21 | `case null -> ...` or `case null, default -> ...` |
