Sealed classes and interfaces represent one of the most important architectural upgrades to the Java type system. By providing compiler-guaranteed closed hierarchies, sealed classes enable true Algebraic Data Types (sum types) and empower pattern matching with exhaustiveness checks.

When combined with records, sealed types allow developers to model domain entities, event streams, and error flows with unparalleled clarity and type safety.

---

## 🎤 Frequently Asked Interview Questions

### 1. What are Sealed Classes in Java, and what problem do they solve?

**Answer:** Sealed classes (finalized in Java 17) restrict which other classes or interfaces may extend or implement them. Prior to sealed classes, a class could either be freely extended (`public`) or completely closed to extension (`final`). Sealed classes provide a middle ground, allowing authors to define a strictly bounded, known set of subclasses (a closed type hierarchy).

### 2. What are the three modifiers permitted subclasses must choose from?

**Answer:** Permitted subclasses must declare exactly one of the following three modifiers:
1. **`final`**: Prevents any further inheritance beyond this subclass.
2. **`sealed`**: Extends the sealed hierarchy, specifying its own explicit list of permitted subclasses.
3. **`non-sealed`**: Explicitly unseals the hierarchy, allowing any class to freely extend it from that point forward.

### 3. When can the `permits` clause be omitted in a sealed class?

**Answer:** If all permitted subclasses are declared within the **same source file** as the sealed class or interface, the `permits` clause is optional. The compiler will automatically deduce the permitted subclasses.

### 4. How do sealed classes interact with `switch` expressions?

**Answer:** Sealed classes enable **exhaustive `switch` expressions**. Because the compiler knows every possible concrete subtype of a sealed class at compile-time, a `switch` expression that handles every permitted subtype does not require a `default` clause. Furthermore, if a new permitted subtype is added later, the compiler will produce an error wherever a switch does not handle the new subtype, preventing forgotten cases.

### 5. Can a record be a permitted subtype of a sealed interface?

**Answer:** Yes! In fact, this is one of the most popular patterns in modern Java. Because records are implicitly `final`, they satisfy the requirement that a permitted subtype must be `final`, `sealed`, or `non-sealed` without requiring an explicit modifier keyword.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Omission Rule
**Task:** Define a sealed interface `TrafficSignal` with `Red`, `Yellow`, and `Green` in the same compilation unit without using the `permits` keyword.

**Solution:**
```java
public sealed interface TrafficSignal {
    record Red(int durationSeconds) implements TrafficSignal {}
    record Yellow(int durationSeconds) implements TrafficSignal {}
    record Green(int durationSeconds) implements TrafficSignal {}
}
```

### Challenge 2: Exhaustive Evaluation
**Task:** Write a method using a switch expression to convert `TrafficSignal` into a human-readable action string without a `default` branch.

**Solution:**
```java
public static String getDriverAction(TrafficSignal signal) {
    return switch (signal) {
        case TrafficSignal.Red r    -> "Stop and wait for " + r.durationSeconds() + " seconds";
        case TrafficSignal.Yellow y -> "Prepare to stop (" + y.durationSeconds() + "s remaining)";
        case TrafficSignal.Green g  -> "Proceed through the intersection";
    };
}
```

---

## 💻 Practice Problems

### Problem 1: Secure Payment Gateway
Create a sealed hierarchy representing payment processing options:
- Sealed interface `PaymentMethod` permitting `CreditCard`, `BankTransfer`, and `CryptoWallet`.
- Implement records for each payment method with appropriate fields (e.g., card number and CVV, IBAN and routing code, wallet address and coin type).
- Write a `PaymentProcessor` class that processes transactions via an exhaustive switch expression and returns a transaction receipt.
- File: [PaymentGatewayMain.java](file:///c:/Java%20Programming%20Language/5.12/answer/PaymentGatewayMain.java)

### Problem 2: Monadic Result Type Pattern
Create a sealed hierarchy `Result<T>` that models the outcome of operations that might fail without using exceptions for flow control:
- Sealed interface `Result<T>` permitting `Result.Success<T>` and `Result.Failure<T>`.
- Provide methods `T getOrElse(T fallback)`, `boolean isSuccess()`, and `Optional<T> toOptional()`.
- Implement a banking account withdrawal scenario returning `Result<Double>`.
- File: [ResultTypeMain.java](file:///c:/Java%20Programming%20Language/5.12/answer/ResultTypeMain.java)
