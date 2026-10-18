Functional Interfaces are the contract that makes lambdas possible. Every time you write a lambda, the compiler is silently matching it to a Functional Interface's single abstract method. Understanding these interfaces deeply is the difference between writing lambdas that "just work" and designing APIs that are a joy to use.

The `java.util.function` package is your toolbox. If you find yourself creating a new interface with one abstract method, stop and check this package first — the interface you need almost certainly already exists.

---

## 🎤 Frequently Asked Interview Questions

### 1. Can a Functional Interface have more than one method?

**Answer:** It can have more than one *method*, but only **one abstract method**. It's allowed to have any number of `default` methods, `static` methods, and methods inherited from `java.lang.Object` (like `toString`, `equals`, `hashCode`). For example, `Comparator<T>` has one abstract method (`compare`), but also has default methods like `reversed()` and `thenComparing()`.

### 2. What is the difference between `Function` and `UnaryOperator`?

**Answer:** `UnaryOperator<T>` extends `Function<T, T>`. The difference is semantic: `UnaryOperator` is used when the input and output types are the **same**. For example, `UnaryOperator<String>` is equivalent to `Function<String, String>`, but it communicates intent more clearly — "I'm transforming a String into another String."

### 3. Why does Java have `BiFunction` but not `TriFunction`?

**Answer:** The Java team made a design decision to stop at two parameters. For three or more, you can either create a custom functional interface, use currying (`Function<A, Function<B, Function<C, R>>>`), or wrap the parameters in a container object. In practice, needing more than two parameters in a lambda is a code smell that suggests you should refactor.

### 4. What's the difference between `Predicate.and()` and using `&&` inside a lambda?

**Answer:** Functionally, they produce the same result. However, `Predicate.and()` allows you to **compose** predicates dynamically at runtime, which is useful for building flexible filter chains. Using `&&` inside a single lambda is simpler but less composable.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Chained Predicate

**Task:** Create a `Predicate<String>` that checks if a string is not empty AND starts with "J" AND has length > 3. Use `.and()` composition.
**Solution:**

```java
Predicate<String> check = ((Predicate<String>) s -> !s.isEmpty())
    .and(s -> s.startsWith("J"))
    .and(s -> s.length() > 3);

System.out.println(check.test("Java"));  // true
System.out.println(check.test("JS"));    // false
```

### Challenge 2: The Pipeline

**Task:** Create a `Function` pipeline that trims a string, converts to lowercase, and replaces spaces with hyphens.
**Solution:**

```java
Function<String, String> slugify = ((Function<String, String>) String::trim)
    .andThen(String::toLowerCase)
    .andThen(s -> s.replaceAll("\\s+", "-"));

System.out.println(slugify.apply("  Hello World  ")); // "hello-world"
```

---

## 📝 Practice Problems

### Problem 1: The Configurable Formatter

Build a system that formats user data using composable functions.

**Requirements:**

1. Create a `User` record with `name`, `email`, and `age` fields.
2. Create three `Function<User, String>` instances: one that formats the name to uppercase, one that masks the email (show first 2 chars + "***"), and one that adds an age label.
3. Write a method `formatUser(User user, List<Function<User, String>> formatters)` that applies each formatter and prints the results.
4. **The Goal:** Show how Functions can be stored in a List and applied dynamically.

### Problem 2: The Rule Engine

Create a simple rule engine using Predicates.

**Requirements:**

1. Create a `Product` class with `name`, `price`, and `category` fields.
2. Create a method `applyRules(List<Product> products, Predicate<Product>... rules)` that returns products matching ALL rules.
3. Test with rules: price > 50, category is "Electronics", name length > 5.
4. **The Goal:** Demonstrate how Predicate composition creates a flexible, extensible filtering system.
