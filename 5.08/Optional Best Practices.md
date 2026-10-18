`NullPointerException` is the most common exception in Java — a billion-dollar mistake, as its inventor Tony Hoare called it. Java 8 introduced **`Optional<T>`** as a container that may or may not hold a value, forcing developers to explicitly handle the "no value" case instead of silently returning `null`.

But `Optional` is one of the most **misused** features in Java. Used correctly, it eliminates entire categories of bugs. Used incorrectly, it adds complexity without benefit. This lesson covers both the API and the best practices.

---

## 1. Creating Optionals

```java
// An Optional containing a value
Optional<String> name = Optional.of("Alice");

// An empty Optional
Optional<String> empty = Optional.empty();

// From a value that might be null
String possiblyNull = getUserName(); // might return null
Optional<String> maybe = Optional.ofNullable(possiblyNull);

// WARNING: Optional.of(null) throws NullPointerException!
// Always use ofNullable() when the value might be null.
```

---

## 2. Consuming Optionals (The Safe Way)

### ❌ The WRONG Way (Anti-Pattern):
```java
// Don't do this — it defeats the purpose!
Optional<String> opt = findUser();
if (opt.isPresent()) {
    String user = opt.get();
    System.out.println(user);
}
// This is just a verbose null check. You've gained nothing.
```

### ✅ The RIGHT Way (Functional Style):

```java
Optional<String> opt = findUser();

// ifPresent — do something if value exists
opt.ifPresent(System.out::println);

// ifPresentOrElse (Java 9+) — handle both cases
opt.ifPresentOrElse(
    user -> System.out.println("Found: " + user),
    () -> System.out.println("User not found")
);

// orElse — provide a default value
String user = opt.orElse("Anonymous");

// orElseGet — provide a default via Supplier (lazy evaluation)
String user = opt.orElseGet(() -> fetchDefaultUser());

// orElseThrow — throw if empty
String user = opt.orElseThrow(() -> new UserNotFoundException("No user found"));

// orElseThrow() without args (Java 10+) — throws NoSuchElementException
String user = opt.orElseThrow();
```

---

## 3. Transforming Optionals (The Powerful Part)

This is where `Optional` truly shines — chaining transformations like a mini stream pipeline.

```java
// map — transform the value if present
Optional<String> upperName = findUser().map(String::toUpperCase);

// flatMap — when the transformation itself returns an Optional
Optional<String> city = findUser()
    .flatMap(user -> findAddress(user))    // findAddress returns Optional<Address>
    .flatMap(address -> findCity(address)) // findCity returns Optional<String>
    .map(String::toUpperCase);

// filter — keep the value only if it matches a condition
Optional<String> longName = findUser()
    .filter(name -> name.length() > 5);

// or (Java 9+) — provide an alternative Optional if empty
Optional<String> user = findInCache()
    .or(() -> findInDatabase())
    .or(() -> Optional.of("Default User"));

// stream (Java 9+) — convert to a Stream (0 or 1 elements)
Stream<String> userStream = findUser().stream();
```

---

## 4. The Best Practices (Rules to Live By)

### ✅ DO: Use Optional as a Return Type

```java
// Perfect use case: a method that might not find a result
public Optional<User> findById(int id) {
    User user = database.lookup(id);
    return Optional.ofNullable(user);
}
```

### ❌ DON'T: Use Optional as a Method Parameter

```java
// Bad — the caller can pass null, Optional.empty(), or Optional.of(value)
public void updateUser(Optional<String> name) { ... }

// Good — just use overloading or nullable parameter
public void updateUser(String name) { ... }
```

### ❌ DON'T: Use Optional for Fields

```java
// Bad — Optional is not Serializable, wastes memory
class User {
    Optional<String> middleName; // Don't do this
}

// Good — use null with a getter that returns Optional
class User {
    private String middleName; // nullable

    public Optional<String> getMiddleName() {
        return Optional.ofNullable(middleName);
    }
}
```

### ❌ DON'T: Use `isPresent()` + `get()`

```java
// Bad — you're just doing a null check with extra steps
if (opt.isPresent()) {
    doSomething(opt.get());
}

// Good
opt.ifPresent(this::doSomething);
```

### ❌ DON'T: Use Optional for Collections

```java
// Bad — return Optional<List<User>>
public Optional<List<User>> findUsers() { ... }

// Good — return an empty list instead
public List<User> findUsers() {
    return results != null ? results : Collections.emptyList();
}
```

---

## 5. `orElse` vs `orElseGet`: A Critical Performance Difference

```java
// orElse — the default is ALWAYS evaluated
String result = opt.orElse(expensiveComputation()); // runs even if opt has a value!

// orElseGet — the default is evaluated ONLY if opt is empty
String result = opt.orElseGet(() -> expensiveComputation()); // lazy, runs only when needed
```

**Rule of thumb:** Use `orElse` only for simple constants. Use `orElseGet` when the fallback involves computation, I/O, or object creation.

---

## Summary: Optional Cheat Sheet

| Method | Returns | When to Use |
| --- | --- | --- |
| `of(value)` | `Optional<T>` | Value is guaranteed non-null |
| `ofNullable(value)` | `Optional<T>` | Value might be null |
| `empty()` | `Optional<T>` | Explicitly "no value" |
| `ifPresent(consumer)` | void | Do something if present |
| `orElse(default)` | T | Simple constant default |
| `orElseGet(supplier)` | T | Computed/expensive default |
| `orElseThrow(supplier)` | T | Throw if empty |
| `map(function)` | `Optional<U>` | Transform the value |
| `flatMap(function)` | `Optional<U>` | Chain Optional-returning functions |
| `filter(predicate)` | `Optional<T>` | Conditional keep |

### Tip:

Think of `Optional` as a **communication tool**. When a method returns `Optional<T>`, it clearly says: "I might not have an answer for you — prepare for both cases." This is infinitely better than returning `null` and hoping the caller remembers to check.
