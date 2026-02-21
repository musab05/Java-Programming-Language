An **Enum** (short for Enumeration) is a special "class" in Java used to define a collection of **constants**. Use them when you have a fixed set of values that won't change, like days of the week, colors in a rainbow, or game difficulty levels.

Instead of using a bunch of `final int` or `String` variables, Enums provide **type safety**. This means you can't accidentally pass an invalid value to a method.

---

## Basic Syntax

A simple Enum is declared using the `enum` keyword:

```java
enum Level {
  LOW,
  MEDIUM,
  HIGH
}

```

You access them just like static variables: `Level myVar = Level.MEDIUM;`.

---

## Enums are Classes

This is where Java Enums shine compared to other languages. In Java, an Enum is actually a full-blown class. This means they can have:

* **Fields** (state)
* **Methods** (behavior)
* **Constructors** (to initialize fields)

### Example: Enum with Fields

```java
enum CoffeeSize {
    SMALL(8), MEDIUM(12), LARGE(16);

    private final int ounces; // Field

    // Constructor (must be private or package-private)
    CoffeeSize(int ounces) {
        this.ounces = ounces;
    }

    public int getOunces() {
        return ounces;
    }
}

```

---

## Why use Enums over Constants?

| Feature | `public static final int` | `Enum` |
| --- | --- | --- |
| **Type Safety** | No (any int works) | Yes (only enum constants work) |
| **Readability** | Low (just a number) | High (descriptive names) |
| **Functionality** | None | Can have methods and logic |
| **Iteration** | Difficult | Easy (using `.values()`) |

---

## Built-in Helpful Methods

Java provides several "out of the box" methods for every Enum:

* **`values()`**: Returns an array of all constants in the enum. Great for loops!
* **`valueOf(String name)`**: Converts a string into the corresponding enum constant.
* **`ordinal()`**: Returns the position (index) of the constant (starting at 0).
* **`name()`**: Returns the name of the constant as a String.

---

## Enums in `switch` Statements

Enums are most powerful when used in a `switch` block. Since the compiler knows all possible values of an Enum, it can help ensure you've covered all cases.

```java
Level myLevel = Level.HIGH;

switch(myLevel) {
  case LOW:
    System.out.println("Low level");
    break;
  case MEDIUM:
     System.out.println("Medium level");
    break;
  case HIGH:
    System.out.println("High level");
    break;
}

```

---

## The "Singleton" Connection

An Enum with a single instance is often considered the **best way to implement a Singleton pattern** in Java. It provides implicit protection against reflection and serialization issues that standard classes struggle with.
