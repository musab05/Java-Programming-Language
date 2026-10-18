# Lesson 5.11: Records

For decades, Java developers wrote countless lines of boilerplate just to carry data: `private final` fields, a verbose constructor, `getters`, `equals()`, `hashCode()`, and `toString()`. Tools like IDE generators and Lombok helped, but they were external workarounds for a fundamental language omission.

Java 14 previewed and Java 16 finalized **Records** (`record`), a first-class language feature designed specifically to represent **transparent, immutable data carriers**.

---

## 1. The Problem: Boilerplate Overload

Consider a simple data carrier representing a point in 2D space:

```java
// Traditional Java POJO (Boilerplate Hell)
public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point[x=" + x + ", y=" + y + "]";
    }
}
```

That's nearly 30 lines of ceremonial code for two fields!

With a **Record**, the exact same class becomes **one line**:

```java
public record Point(int x, int y) {}
```

---

## 2. What the Compiler Generates Automatically

When you declare a `record`, the Java compiler automatically generates:

1. **`private final` fields** for each record component (`x` and `y`).
2. A **Canonical Constructor** that accepts all components in order and assigns them to the fields.
3. **Public accessor methods** with the same name as the component (e.g., `x()` and `y()`, **not** `getX()` or `getY()`).
4. An implementation of `equals(Object)` that compares all components.
5. An implementation of `hashCode()` derived from all components.
6. An implementation of `toString()` displaying the record name and component values (e.g., `Point[x=10, y=20]`).
7. Automatic inheritance from `java.lang.Record`.

```java
Point p1 = new Point(10, 20);
Point p2 = new Point(10, 20);

System.out.println(p1.x());         // 10 (accessor method)
System.out.println(p1.y());         // 20
System.out.println(p1);            // Point[x=10, y=20]
System.out.println(p1.equals(p2)); // true
System.out.println(p1.hashCode() == p2.hashCode()); // true
```

---

## 3. Constructors in Records

### A. Compact Constructor (Validation & Normalization)
You don't need to redeclare arguments or write `this.x = x;`. The **compact constructor** runs *before* field assignment:

```java
public record User(String username, String email, int age) {
    // Compact constructor — no parameter list!
    public User {
        // Validation
        Objects.requireNonNull(username, "Username cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }

        // Normalization (mutating the parameters before assignment)
        username = username.trim().toLowerCase();
        email = email.trim().toLowerCase();
    }
}
```

### B. Custom Canonical Constructor
If you explicitly provide the full canonical constructor, you must assign all fields:

```java
public record User(String username, int age) {
    public User(String username, int age) {
        if (age < 0) throw new IllegalArgumentException("Negative age");
        this.username = username.trim();
        this.age = age;
    }
}
```

### C. Secondary (Non-Canonical) Constructors
You can define alternative constructors, but they **must delegate** to the canonical constructor via `this(...)`:

```java
public record Point(int x, int y) {
    // Overloaded secondary constructor
    public Point() {
        this(0, 0); // Must call canonical constructor
    }

    public Point(int coord) {
        this(coord, coord);
    }
}
```

---

## 4. Custom Methods, Static Fields, and Interfaces

Records are full-fledged Java classes under the hood:

```java
public record Rectangle(double width, double height) implements Shape {

    // 1. Static fields are allowed
    public static final Rectangle UNIT_SQUARE = new Rectangle(1.0, 1.0);

    // 2. Static methods are allowed
    public static Rectangle square(double side) {
        return new Rectangle(side, side);
    }

    // 3. Instance methods are allowed
    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    // 4. Customizing accessor methods
    @Override
    public double width() {
        return Math.max(0.0, width);
    }
}
```

---

## 5. Rules & Constraints of Records

Because records are designed as immutable, transparent data tuples:

| Rule | Explanation |
| :--- | :--- |
| **Implicitly `final`** | You cannot extend a record; no class can inherit from a record. |
| **Cannot extend other classes** | Records implicitly extend `java.lang.Record`. Single inheritance prevents extending anything else. |
| **Can implement interfaces** | Records can implement any number of interfaces (e.g., `Comparable`, `Serializable`). |
| **Cannot have instance fields** | All instance state *must* be declared in the record component header. Only `static` fields are allowed in the body. |
| **Fields are `final`** | Components cannot be reassigned. |

---

## 6. Defensive Copying with Records (Shallow Immutability)

> [!WARNING]
> Records provide **shallow immutability**. If a record component is a mutable object (e.g., `List`, `Date`, `int[]`), the reference is `final`, but the contents of the object can still be mutated!

```java
// ❌ Dangerous: Mutable component
public record Team(String name, List<String> members) {}

List<String> list = new ArrayList<>(List.of("Alice", "Bob"));
Team team = new Team("Alpha", list);
list.add("Hacker"); // Modifies team's internal state!
```

```java
// ✅ Safe: Defensive copy inside the compact constructor & accessor
public record Team(String name, List<String> members) {
    public Team {
        // Unmodifiable defensive copy
        members = List.copyOf(members);
    }
}
```

---

## 7. Local Records: Clean Intermediate Data

Java allows declaring records **locally inside methods** — perfect for temporary data grouping in Stream pipelines:

```java
public List<String> findTopSpenders(List<Order> orders) {
    // Local record scoped only inside this method!
    record CustomerSpend(String customerId, double total) {}

    return orders.stream()
        .collect(Collectors.groupingBy(
            Order::getCustomerId,
            Collectors.summingDouble(Order::getAmount)
        ))
        .entrySet().stream()
        .map(entry -> new CustomerSpend(entry.getKey(), entry.getValue()))
        .filter(cs -> cs.total() > 1000.0)
        .sorted(Comparator.comparingDouble(CustomerSpend::total).reversed())
        .map(CustomerSpend::customerId)
        .toList();
}
```

---

## 8. Summary Checklist

- Use `record` for DTOs, API responses, configuration values, event payloads, and map/tuple keys.
- Use compact constructors (`public RecordName { ... }`) for validation and defensive copies.
- Access components using `record.fieldName()`, not `getFieldName()`.
- Remember that records are shallowly immutable — wrap mutable collections with `List.copyOf()` or `Set.copyOf()`.
