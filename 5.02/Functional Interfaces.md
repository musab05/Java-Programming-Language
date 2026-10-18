In the last lesson, we said lambdas can only be used where a **Functional Interface** is expected. So what exactly is a Functional Interface, and why is it the backbone of everything functional in Java?

A Functional Interface is simply an interface with **exactly one abstract method**. That's it. This single abstract method becomes the "shape" that a lambda expression must match. Java 8 ships with dozens of pre-built Functional Interfaces in the `java.util.function` package, and mastering them means you'll almost never need to create your own.

---

## 1. The `@FunctionalInterface` Annotation

You can mark an interface with `@FunctionalInterface` to enforce the "one abstract method" rule at compile time. If someone accidentally adds a second abstract method, the compiler will throw an error.

```java
@FunctionalInterface
public interface Greeting {
    String sayHello(String name); // The single abstract method

    // You CAN have default methods — they don't count
    default String shout(String name) {
        return sayHello(name).toUpperCase();
    }

    // You CAN have static methods — they don't count either
    static void printWelcome() {
        System.out.println("Welcome!");
    }
}
```

**Key Rule:** Default methods and static methods do NOT violate the functional interface contract. Only **abstract** methods count.

---

## 2. The Big Four: Core Functional Interfaces

Java provides four foundational functional interfaces in `java.util.function`. Almost everything else is a specialization of these:

### `Function<T, R>` — Takes input, returns output
```java
Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Java")); // 4
```

### `Predicate<T>` — Takes input, returns boolean
```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(42)); // true
```

### `Consumer<T>` — Takes input, returns nothing
```java
Consumer<String> printer = s -> System.out.println(">> " + s);
printer.accept("Hello!"); // >> Hello!
```

### `Supplier<T>` — Takes nothing, returns output
```java
Supplier<Double> randomValue = () -> Math.random();
System.out.println(randomValue.get()); // 0.7234...
```

---

## 3. The Cheat Sheet: All Common Functional Interfaces

| Interface | Input | Output | Method | Use Case |
| --- | --- | --- | --- | --- |
| `Function<T,R>` | T | R | `apply(T)` | Transformations |
| `BiFunction<T,U,R>` | T, U | R | `apply(T,U)` | Two-input transformations |
| `Predicate<T>` | T | boolean | `test(T)` | Filtering, conditions |
| `BiPredicate<T,U>` | T, U | boolean | `test(T,U)` | Two-input conditions |
| `Consumer<T>` | T | void | `accept(T)` | Side effects (print, save) |
| `BiConsumer<T,U>` | T, U | void | `accept(T,U)` | Two-input side effects |
| `Supplier<T>` | — | T | `get()` | Factories, lazy values |
| `UnaryOperator<T>` | T | T | `apply(T)` | Same-type transformation |
| `BinaryOperator<T>` | T, T | T | `apply(T,T)` | Reduction (sum, max) |

---

## 4. Composing Functions

One of the most powerful features of Functional Interfaces is **composition** — chaining multiple functions together to create a pipeline.

```java
Function<String, String> trim = String::trim;
Function<String, String> toUpper = String::toUpperCase;
Function<String, Integer> length = String::length;

// Chain them: trim → toUpperCase → length
Function<String, Integer> pipeline = trim.andThen(toUpper).andThen(length);

System.out.println(pipeline.apply("  hello world  ")); // 11
```

### Predicate Composition:

```java
Predicate<Integer> isPositive = n -> n > 0;
Predicate<Integer> isEven = n -> n % 2 == 0;

Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
Predicate<Integer> isNegativeOrOdd = isPositive.negate().or(isEven.negate());

System.out.println(isPositiveAndEven.test(4));  // true
System.out.println(isPositiveAndEven.test(-2)); // false
```

---

## 5. Primitive Specializations

Boxing and unboxing (`int` ↔ `Integer`) has a performance cost. Java provides specialized interfaces to avoid it:

```java
// Instead of Function<Integer, Integer> — avoid autoboxing!
IntUnaryOperator doubleIt = n -> n * 2;
System.out.println(doubleIt.applyAsInt(5)); // 10

// Instead of Predicate<Integer>
IntPredicate isNegative = n -> n < 0;
System.out.println(isNegative.test(-3)); // true
```

Common primitives: `IntFunction`, `LongConsumer`, `DoubleSupplier`, `ToIntFunction<T>`, etc.

---

## Summary: When to Use Which Interface

| You want to... | Use |
| --- | --- |
| Transform data (A → B) | `Function<T, R>` |
| Test a condition | `Predicate<T>` |
| Do something with data (no return) | `Consumer<T>` |
| Generate/supply data (no input) | `Supplier<T>` |
| Transform within the same type | `UnaryOperator<T>` |
| Combine two values into one | `BinaryOperator<T>` |

### Tip:

Resist the urge to create your own functional interfaces. The `java.util.function` package covers 95% of use cases. Only create a custom one when you need a more descriptive name for domain clarity (e.g., `@FunctionalInterface interface Validator<T> { boolean validate(T item); }`).
