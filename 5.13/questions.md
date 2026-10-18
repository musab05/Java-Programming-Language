Pattern matching represents a monumental leap in Java's expressiveness. By unifying type testing, conditional branching, null safety, and structural data deconstruction, modern Java lets you write concise, declarative, and type-safe code that was previously only seen in languages like Scala, Kotlin, or Haskell.

---

## 🎤 Frequently Asked Interview Questions

### 1. How does Pattern Matching for `instanceof` work, and what is "Flow Scoping"?

**Answer:** Pattern matching for `instanceof` introduces a binding variable directly in the `instanceof` check (e.g., `if (obj instanceof String s)`). If the check evaluates to `true`, the object is automatically cast to the specified type and assigned to the variable `s`. 
**Flow Scoping** means the binding variable is only in scope in code branches where the `instanceof` condition is guaranteed to have evaluated to `true`. For example, `s` is in scope inside `if (obj instanceof String s && s.length() > 0)`, but using `||` results in a compilation error because `s` might not be initialized if the left side were false.

### 2. How did Java 21 change `null` handling inside `switch` statements and expressions?

**Answer:** Historically, evaluating `switch (x)` when `x == null` immediately threw a `NullPointerException`. In Java 21, `null` is a valid case label: `case null -> ...`. You can also combine it with the default branch: `case null, default -> ...`. If `case null` is omitted and `null` is passed, it preserves backwards compatibility by throwing a `NullPointerException`.

### 3. What is a "Guarded Pattern" and what keyword is used for it?

**Answer:** A guarded pattern is a pattern check combined with a boolean condition that must also evaluate to true for the branch to match. In Java 21, the `when` keyword is used to declare guards (e.g., `case Integer i when i > 100 -> ...`).

### 4. What are Record Patterns (deconstruction patterns)?

**Answer:** Record patterns allow decomposing a record into its constituent components directly within an `instanceof` check or `switch` case (e.g., `case Point(int x, int y)`). The compiler validates that the components match the record's canonical constructor components and extracts them into local variables without requiring explicit accessor method calls.

### 5. What is "Pattern Dominance" and what happens if a switch case is dominated?

**Answer:** Pattern dominance occurs when a case pattern covers a superset of a subsequent case pattern, making the subsequent case unreachable. For example, `case Object o` covers all objects, so having `case String s` after it means `String s` would never execute. The Java compiler strictly detects dominance and raises a **compile-time error**.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Flow Scoping with Inversion
**Task:** Use pattern matching for `instanceof` with an early-exit guard clause to process an `Order` object.

**Solution:**
```java
public void processOrder(Object obj) {
    if (!(obj instanceof Order order)) {
        throw new IllegalArgumentException("Expected an Order instance");
    }
    // 'order' is safely in scope here!
    System.out.println("Processing order ID: " + order.id());
}
```

### Challenge 2: Guarded Switch Expression
**Task:** Write a switch expression that accepts an `Object` and returns:
- `"Positive Integer: <val>"` if it's an `Integer > 0`
- `"Negative or Zero: <val>"` if it's an `Integer <= 0`
- `"Blank String"` if it's a `String` with no text
- `"Non-empty String: <val>"` if it's a non-blank `String`
- `"Other"` for all else

**Solution:**
```java
public static String categorize(Object obj) {
    return switch (obj) {
        case Integer i when i > 0        -> "Positive Integer: " + i;
        case Integer i                   -> "Negative or Zero: " + i;
        case String s when s.isBlank()  -> "Blank String";
        case String s                    -> "Non-empty String: " + s;
        case null, default               -> "Other";
    };
}
```

---

## 💻 Practice Problems

### Problem 1: Recursive Arithmetic Expression Tree
Model an abstract syntax tree for mathematical expressions using sealed records and evaluate them using recursive record pattern matching:
- Sealed interface `Expr` permitting `Constant`, `Add`, `Multiply`, and `Negate`.
- Write an evaluation method `double eval(Expr expr)` using a switch expression with record deconstruction.
- Add optimization rules using guarded patterns (e.g., multiplying by 0 yields 0, adding 0 returns the other operand).
- File: [ExpressionTreeMain.java](file:///c:/Java%20Programming%20Language/5.13/answer/ExpressionTreeMain.java)

### Problem 2: Multi-Channel Notification Dispatcher
Build an event-driven notification engine that routes notifications across channels (Email, SMS, Push Notification) using guarded pattern matching:
- Define records for each notification payload with sender, recipient, and message metadata.
- Implement priority filtering (e.g., skip SMS if silent mode is enabled unless priority is "URGENT").
- Handle `null` payloads gracefully using modern switch null labels.
- File: [NotificationDispatcherMain.java](file:///c:/Java%20Programming%20Language/5.13/answer/NotificationDispatcherMain.java)
