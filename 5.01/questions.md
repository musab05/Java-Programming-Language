Lambda expressions are the gateway drug to functional programming in Java. Once you start using them, you'll wonder how you ever lived without them. But to use them well, you need to understand their rules, their limits, and the subtle traps that trip up even experienced developers.

The key insight is that lambdas aren't just "shorter anonymous classes." They have fundamentally different scoping rules, different compilation behavior, and different semantics around `this`. Understanding these differences is what separates a developer who *uses* lambdas from one who *masters* them.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between a lambda expression and an anonymous inner class?

**Answer:** There are several key differences. First, `this` inside a lambda refers to the enclosing class, while in an anonymous class it refers to the anonymous class itself. Second, lambdas use `invokedynamic` at the bytecode level instead of generating a new class file, making them more memory-efficient. Third, lambdas cannot shadow variables from the enclosing scope, while anonymous classes can. Finally, lambdas can only implement Functional Interfaces (one abstract method), while anonymous classes can implement any interface or extend any class.

### 2. What does "effectively final" mean in the context of lambdas?

**Answer:** A variable is "effectively final" if its value is never changed after initialization, even if it isn't explicitly declared `final`. Lambdas can only capture local variables that are effectively final. This restriction exists because the lambda might be executed later on a different thread, and allowing mutation of captured variables would introduce race conditions without any synchronization.

### 3. Can a lambda expression throw checked exceptions?

**Answer:** A lambda can throw a checked exception **only if** the abstract method of the target Functional Interface declares that exception in its `throws` clause. For example, `Callable<V>` declares `throws Exception`, so its lambda can throw checked exceptions. However, `Runnable.run()` does not declare any checked exceptions, so you cannot throw one from a Runnable lambda without wrapping it in a try-catch.

### 4. Why can't lambdas modify local variables from their enclosing scope?

**Answer:** Lambdas capture a *copy* of local variables, not a reference to them. Local variables live on the stack and are destroyed when the method returns, but the lambda may outlive the method. If you could modify the local variable, the lambda's copy wouldn't reflect the change (or vice versa), leading to confusing bugs. By requiring effectively-final variables, Java ensures consistency.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Sorter

**Task:** Sort a `List<String>` of names by their **last character** using a lambda.
**Solution:**

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");
names.sort((a, b) -> a.charAt(a.length() - 1) - b.charAt(b.length() - 1));
// Result: [Charlie, Alice, Bob, Dave] — sorted by e, e, b, e
```

### Challenge 2: The Converter

**Task:** Write a lambda that takes a String and returns its length. Assign it to a `Function<String, Integer>`.
**Solution:**

```java
Function<String, Integer> lengthOf = s -> s.length();
System.out.println(lengthOf.apply("Lambda")); // Output: 6
```

---

## 📝 Practice Problems

### Problem 1: The Custom Filter

Build a flexible filtering system using lambdas.

**Requirements:**

1. Create a method `filterList(List<Integer> list, Predicate<Integer> condition)` that returns a new list containing only elements that pass the condition.
2. Use it three times with different lambdas: filter for even numbers, filter for numbers greater than 10, and filter for negative numbers.
3. Print the results of each filter operation.
4. **The Goal:** Demonstrate how lambdas let you pass *behavior* as a parameter.

### Problem 2: The Operation Executor

Build a simple calculator that uses lambdas for its operations.

**Requirements:**

1. Create a method `calculate(int a, int b, BinaryOperator<Integer> operation)` that applies the operation and prints the result.
2. Call it with lambdas for addition, subtraction, multiplication, and a custom "max" operation.
3. **The Goal:** Replace what would traditionally require a Strategy Pattern with inline lambdas.
