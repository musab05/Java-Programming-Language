Welcome to **Section 5: Java 8+ & Functional Programming**. This is where Java stopped being "just" an Object-Oriented language and became a hybrid powerhouse. The single most important feature introduced in Java 8 is the **Lambda Expression**.

Before lambdas, if you wanted to pass a piece of behavior (a "what to do") to a method, you had to wrap it in an anonymous inner class. It was verbose, ugly, and painful. Lambdas changed the game by letting you write compact, readable, inline functions.

---

## 1. What is a Lambda Expression?

A lambda expression is essentially an **anonymous function** — a block of code that you can pass around like a variable. It has parameters, an arrow (`->`), and a body.

### Syntax:

```java
// Full form
(Type param1, Type param2) -> { statements; return value; }

// Simplified forms
(a, b) -> a + b          // Types inferred, single expression (implicit return)
x -> x * 2               // Single parameter, no parentheses needed
() -> System.out.println("Hello") // No parameters
```

### The "Before and After":

```java
// BEFORE: Anonymous Inner Class (Java 7 style)
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// AFTER: Lambda Expression (Java 8+)
Collections.sort(names, (a, b) -> a.compareTo(b));
```

From **5 lines** down to **1 line** — same result.

---

## 2. How Do Lambdas Actually Work?

A lambda expression is **not** syntactic sugar for an anonymous class. Behind the scenes, the Java compiler uses a feature called `invokedynamic` (introduced in Java 7) to generate the implementation at runtime. This is more efficient than creating a new `.class` file for every anonymous class.

The critical rule is: **A lambda can only be used where a Functional Interface is expected.** A Functional Interface is any interface with exactly **one abstract method** (we'll dive deep into this in the next lesson).

```java
// Runnable is a Functional Interface (one method: run())
Runnable task = () -> System.out.println("Running in a thread!");
new Thread(task).start();

// Comparator<T> is a Functional Interface (one method: compare())
Comparator<Integer> descending = (a, b) -> b - a;
```

---

## 3. Variable Capture: "Effectively Final"

Lambdas can access variables from the enclosing scope, but there's a strict rule: those variables must be **effectively final** — meaning their value is never changed after initialization.

```java
String greeting = "Hello"; // This is effectively final

Runnable r = () -> System.out.println(greeting + ", World!");

// greeting = "Hi"; // COMPILE ERROR! Changing it would break the lambda.
```

### Why this restriction?

Lambdas may execute on a **different thread** at a **later time**. If you could mutate the variable, you'd have a race condition with no protection. Java prevents this at compile time to keep you safe.

---

## 4. Lambda vs. Anonymous Class: The Real Differences

Many people think lambdas are just shorter anonymous classes. They're wrong. Here are key differences:

| Feature | Anonymous Class | Lambda Expression |
| --- | --- | --- |
| **`this` keyword** | Refers to the anonymous class instance | Refers to the **enclosing class** instance |
| **Compilation** | Generates a separate `.class` file | Uses `invokedynamic` (no extra class file) |
| **Scope** | Creates a new scope | Shares scope with enclosing method |
| **Performance** | Slightly higher overhead | Optimized by JVM |
| **Shadowing** | Can shadow variables from outer scope | Cannot shadow — compile error |

### The `this` trap:

```java
public class Outer {
    String name = "Outer";

    void test() {
        Runnable anonClass = new Runnable() {
            String name = "Anon";
            public void run() {
                System.out.println(this.name); // Prints "Anon"
            }
        };

        Runnable lambda = () -> {
            System.out.println(this.name); // Prints "Outer"!
        };
    }
}
```

---

## 5. Common Patterns with Lambdas

### Event Handling:
```java
button.setOnAction(event -> handleClick(event));
```

### Thread Creation:
```java
new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        System.out.println("Thread: " + i);
    }
}).start();
```

### Custom Sorting:
```java
List<String> cities = Arrays.asList("Paris", "London", "Tokyo", "Berlin");
cities.sort((a, b) -> a.length() - b.length());
// Result: [Paris, Tokyo, London, Berlin]
```

---

## Summary: When to Use Lambdas

| Use Case | Example |
| --- | --- |
| **Replacing simple anonymous classes** | `Runnable`, `Comparator`, `ActionListener` |
| **Passing behavior to methods** | Strategy pattern, callbacks |
| **Stream API operations** | `filter()`, `map()`, `forEach()` |
| **Concise event handlers** | GUI frameworks, reactive programming |

### Tip:

If your lambda body exceeds 3-4 lines, extract it into a named method and use a **Method Reference** instead (we'll cover those in Lesson 5.06). A lambda should make your code *more* readable, not less.
