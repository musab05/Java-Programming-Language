Method references are syntactic sugar over lambdas, but they communicate intent more clearly. When you see `String::toUpperCase`, you instantly know what's happening — no parameter names, no arrow, no noise. They're the finishing touch that makes stream pipelines read like English.

The tricky part is distinguishing between the four types — especially Type 2 (instance method of a specific object) vs. Type 3 (instance method of an arbitrary object). If you can explain that difference clearly, you'll impress any interviewer.

---

## 🎤 Frequently Asked Interview Questions

### 1. What are the four types of Method References?

**Answer:** (1) Reference to a static method: `ClassName::staticMethod`. (2) Reference to an instance method of a particular object: `object::instanceMethod`. (3) Reference to an instance method of an arbitrary object of a given type: `ClassName::instanceMethod`. (4) Reference to a constructor: `ClassName::new`. Each replaces a specific lambda pattern where parameters are passed directly to the referenced method without modification.

### 2. How does the compiler decide between Type 1 (static) and Type 3 (instance)?

**Answer:** When you write `ClassName::method`, the compiler checks whether `method` is static or an instance method. If it's static, the parameters of the lambda are passed as arguments to the static method. If it's an instance method, the first parameter of the lambda becomes the instance on which the method is invoked, and remaining parameters are passed as arguments.

### 3. Can you use method references with overloaded methods?

**Answer:** Yes, but the compiler uses the **target type** (the functional interface) to resolve which overloaded version to use. For example, `String::valueOf` is overloaded for `int`, `double`, `Object`, etc. The compiler picks the right one based on the expected `Function<?, String>` type. If it's ambiguous, you'll get a compile error.

### 4. Are method references faster than lambdas?

**Answer:** No. They compile to virtually identical bytecode. Method references are purely a **readability** feature. The JVM treats them the same as the equivalent lambda expression. Use whichever is more readable in context.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Convert to Method References

**Task:** Replace each lambda with a method reference.
**Solution:**

```java
// 1. Lambda: s -> s.isEmpty()        → String::isEmpty
// 2. Lambda: s -> System.out.println(s)  → System.out::println
// 3. Lambda: () -> new HashMap<>()    → HashMap::new
// 4. Lambda: (a, b) -> a.equals(b)   → Object::equals
// 5. Lambda: x -> Math.abs(x)        → Math::abs
```

### Challenge 2: The Constructor Factory

**Task:** Use a constructor reference to create a `Function<String, StringBuilder>` and use it to convert a list of strings to StringBuilders.
**Solution:**

```java
Function<String, StringBuilder> factory = StringBuilder::new;
List<String> words = List.of("Hello", "World");
List<StringBuilder> builders = words.stream()
    .map(factory)
    .collect(Collectors.toList());
builders.forEach(sb -> System.out.println(sb.reverse()));
```

---

## 📝 Practice Problems

### Problem 1: The Stream Refactor

Refactor a verbose stream pipeline to use method references wherever possible.

**Requirements:**

1. Start with this pipeline that uses only lambdas:
   - Filter a list of strings for non-empty ones
   - Convert to uppercase
   - Sort naturally
   - Print each one
2. Replace every lambda with a method reference.
3. Create a helper class with static utility methods (e.g., `isValid(String s)`) and reference those too.
4. **The Goal:** Practice identifying which lambdas can become method references and which cannot.

### Problem 2: The Object Factory

Build a factory system using constructor references.

**Requirements:**

1. Create three classes: `Circle(double radius)`, `Square(double side)`, `Triangle(double base, double height)`.
2. Store `Function<Double, Circle>` and `Function<Double, Square>` as constructor references.
3. Create a method `createShapes(List<Double> sizes, Function<Double, ?> factory)` that creates shapes from sizes.
4. **The Goal:** Demonstrate how constructor references enable generic factory patterns.
