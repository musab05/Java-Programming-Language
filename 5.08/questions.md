`Optional` is Java's answer to the null safety problem. But it's a sharp tool — used wrong, it creates more confusion than it solves. The key insight is that Optional is a **return type convention**, not a general-purpose null wrapper.

The best Java developers use Optional to build fluent, null-safe chains of transformations using `map`, `flatMap`, and `filter` — turning what would be nested null checks into clean, readable pipelines.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between `orElse()` and `orElseGet()`?

**Answer:** `orElse(defaultValue)` always evaluates the default value, even if the Optional contains a value. `orElseGet(supplier)` only evaluates the supplier when the Optional is empty (lazy evaluation). This matters when the default involves expensive computation — use `orElseGet` to avoid unnecessary work.

### 2. Why shouldn't you use Optional as a method parameter?

**Answer:** It adds unnecessary complexity. The caller now has three choices: pass `Optional.of(value)`, `Optional.empty()`, or even `null`. This defeats the purpose. Instead, use method overloading, a nullable parameter with documentation, or a Builder pattern. Optional was designed specifically as a **return type**.

### 3. What is the difference between `map()` and `flatMap()` on Optional?

**Answer:** `map()` applies a function that returns a plain value, and wraps the result in an Optional. `flatMap()` applies a function that itself returns an `Optional`, and doesn't double-wrap it. Use `map` when your transformation returns `T`, use `flatMap` when it returns `Optional<T>`. Without `flatMap`, you'd get `Optional<Optional<T>>`, which is useless.

### 4. Is Optional serializable?

**Answer:** No. `Optional` does not implement `Serializable`. This is intentional — it's meant to be a method return type, not stored in fields or serialized. If your class needs to be serializable, store nullable fields and provide Optional-returning getter methods instead.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Null-Safe Chain

**Task:** Given a potentially null `User` who has a potentially null `Address` which has a potentially null `city`, safely extract the uppercase city name or return "UNKNOWN".
**Solution:**

```java
String city = Optional.ofNullable(user)
    .map(User::getAddress)
    .map(Address::getCity)
    .map(String::toUpperCase)
    .orElse("UNKNOWN");
```

### Challenge 2: The Fallback Chain

**Task:** Try to find a user by username, then by email, then by phone. Return the first match or throw an exception.
**Solution:**

```java
User user = findByUsername("alice")
    .or(() -> findByEmail("alice@mail.com"))
    .or(() -> findByPhone("555-1234"))
    .orElseThrow(() -> new RuntimeException("User not found anywhere"));
```

---

## 📝 Practice Problems

### Problem 1: The Config Resolver

Build a layered configuration system using Optional chaining.

**Requirements:**

1. Create a `ConfigSource` interface with `Optional<String> get(String key)`.
2. Implement three sources: `EnvironmentConfig`, `FileConfig`, and `DefaultConfig` (in-memory maps).
3. Create a `ConfigResolver` that checks sources in priority order: Environment → File → Default.
4. Use Optional's `or()` method to chain the lookups elegantly.
5. **The Goal:** Replace cascading null checks with a clean Optional chain.

### Problem 2: The User Profile Builder

Build a user profile display using Optional transformations.

**Requirements:**

1. Create a `UserProfile` class with optional fields: `nickname` (nullable), `bio` (nullable), `avatarUrl` (nullable).
2. Create a method `getDisplayName()` that returns nickname if present, otherwise first 10 chars of bio + "...", otherwise "Anonymous".
3. Create a method `getAvatarOrDefault()` that returns the avatar URL if valid (starts with "http"), otherwise a default URL.
4. Use only Optional methods — no if/else or ternary operators.
5. **The Goal:** Show how Optional eliminates null-checking boilerplate.
