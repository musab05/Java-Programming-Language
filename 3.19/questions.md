## 🎤 Frequently Asked Interview Questions

### 1. Why shouldn't you use `Optional.get()`?

**Answer:** Because it defeats the entire purpose of the class. If you call `.get()` on an empty `Optional`, it throws a `NoSuchElementException`, which is essentially just a differently named `NullPointerException`. Instead, you should always use safer alternatives like `.orElse()`, `.orElseGet()`, or `.ifPresent()`.

### 2. What is the difference between `orElse("Default")` and `orElseGet(() -> "Default")`?

**Answer:** This is a classic "Senior Dev" question.

- **`orElse`** is evaluated **immediately**, regardless of whether the `Optional` is empty or not. If you pass a method call to `orElse`, that method runs every time.
- **`orElseGet`** is **lazy**. It takes a Supplier lambda and _only_ executes it if the `Optional` is actually empty.
- **Rule of thumb:** Use `orElse` for constants; use `orElseGet` for expensive computations or database lookups.

### 3. Can an `Optional` itself be `null`?

**Answer:** Technically, yes, because it is an object. However, returning `null` instead of `Optional.empty()` is a "cardinal sin" of Java development. It forces the caller to check for `null` on an object that was specifically designed to eliminate `null` checks, creating a recursive nightmare.

### 4. How do `map` and `flatMap` differ in `Optional`?

**Answer:** \* **`map`** is for transformations that return a plain object. If the mapping function returns `U`, `map` returns `Optional<U>`.

- **`flatMap`** is for transformations that _already_ return an `Optional`. If your `User::getAddress` method returns `Optional<Address>`, using `map` would give you a nested `Optional<Optional<Address>>`. `flatMap` flattens that into a single `Optional<Address>`.

### 5. Explain the difference between `Optional` and `OptionalInt` (primitive optionals)?

**Answer:** Java provides primitive-specialized versions of `Optional` to avoid the performance overhead of boxing/unboxing.

- **`Optional<T>`** is a generic container for reference types. If you use `Optional<Integer>`, each value must be boxed into an `Integer` object.
- **`OptionalInt`**, **`OptionalLong`**, and **`OptionalDouble`** are specialized classes that hold primitive values (`int`, `long`, `double`) directly without boxing.

**Key differences:**
| Feature | `Optional<T>` | `OptionalInt` / `OptionalLong` / `OptionalDouble` |
|---------|---------------|---------------------------------------------------|
| Value type | Reference type (e.g., `Integer`) | Primitive (`int`, `long`, `double`) |
| Method to get value | `.get()` | `.getAsInt()`, `.getAsLong()`, `.getAsDouble()` |
| Has `.map()` | Yes | No (use `.ifPresent()` instead) |
| Has `.flatMap()` | Yes | No |
| Performance | Boxing overhead | No boxing, more efficient |

**Example:**

```java
// Generic Optional - boxing overhead
Optional<Integer> boxed = Optional.of(42);

// Primitive OptionalInt - no boxing
OptionalInt primitive = OptionalInt.of(42);
int value = primitive.orElse(0);
```

**Rule of thumb:** When working with primitive numeric values in streams (like `IntStream`, `LongStream`, `DoubleStream`), prefer the primitive optionals to avoid unnecessary boxing and improve performance.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Safer Logger

**Task:** You have an `Optional<String> message`. Write a single line of code that prints the message to the console _only if_ it exists.
**Solution:**

```java
// Use ifPresent to avoid if-else blocks entirely
message.ifPresent(System.out::println);

```

### Challenge 2: The Default User

**Task:** You are fetching a username from a system that might return `null`. Wrap the potential `null` and provide the default name `"Anonymous"` if it's missing or empty.
**Solution:**

```java
String result = Optional.ofNullable(inputName)
                       .filter(name -> !name.isEmpty())
                       .orElse("Anonymous");

```

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to handle deeply nested objects cleanly. The second tests your ability to handle business exceptions using `Optional`.

### Problem 1: The Deep Link Explorer (`flatMap`)

You are building a system for a library. A `Book` might have an `Author`, and an `Author` might have a `Website`.

**Requirements:**

1. Create three classes: `Book`, `Author`, and `Website`.
2. `Book.getAuthor()` should return `Optional<Author>`.
3. `Author.getWebsite()` should return `Optional<Website>`.
4. `Website.getUrl()` returns a plain `String`.
5. Write a method `public String getAuthorUrl(Book book)` that uses a functional chain to extract the URL.
6. If the author is missing, or the website is missing, return `"URL not available"`.

### Problem 2: The Secure Login Guard (`orElseThrow`)

You are building a security module. When a user tries to log in, you look them up in the database by their ID.

**Requirements:**

1. Create a `User` class with a `String username`.
2. Create a `UserRepository` with a method `public Optional<User> findById(int id)`.
3. Write a service method `public User login(int id)`.
4. If the user exists, return the `User` object.
5. If the user does not exist, use `.orElseThrow()` to throw a custom `SecurityException` with the message `"Access Denied: Invalid User ID"`.
