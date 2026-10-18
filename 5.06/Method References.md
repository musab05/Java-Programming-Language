Method References are the ultimate shorthand for lambdas. When your lambda does nothing more than call an existing method, you can replace it with a **Method Reference** — a compact, readable pointer to that method.

Think of it like this: a lambda is a recipe you write; a method reference is pointing to a recipe that already exists. Why rewrite instructions when you can just reference the original?

---

## 1. The Four Types of Method References

Java has exactly four kinds of method references. Each replaces a specific pattern of lambda.

### Type 1: Reference to a Static Method
```java
// Lambda
Function<String, Integer> parser = s -> Integer.parseInt(s);
// Method Reference
Function<String, Integer> parser = Integer::parseInt;

// Lambda
BinaryOperator<Integer> max = (a, b) -> Math.max(a, b);
// Method Reference
BinaryOperator<Integer> max = Math::max;
```

**Pattern:** `(args) -> ClassName.staticMethod(args)` → `ClassName::staticMethod`

---

### Type 2: Reference to an Instance Method of a Particular Object
```java
String greeting = "Hello, World!";

// Lambda
Supplier<Integer> length = () -> greeting.length();
// Method Reference
Supplier<Integer> length = greeting::length;

// With a specific object
PrintStream out = System.out;
Consumer<String> printer = out::println;
printer.accept("Method reference!"); // Prints: Method reference!
```

**Pattern:** `(args) -> objectRef.method(args)` → `objectRef::method`

---

### Type 3: Reference to an Instance Method of an Arbitrary Object (of a given type)

This is the trickiest one. The first parameter of the lambda *becomes* the object the method is called on.

```java
// Lambda: the first arg (s) is the object, method is called ON it
Function<String, String> upper = s -> s.toUpperCase();
// Method Reference
Function<String, String> upper = String::toUpperCase;

// Lambda: first arg is the object, second is the method parameter
BiPredicate<String, String> checker = (str, prefix) -> str.startsWith(prefix);
// Method Reference
BiPredicate<String, String> checker = String::startsWith;
```

**Pattern:** `(obj, args) -> obj.method(args)` → `ClassName::method`

**Key difference from Type 1:** In Type 1, we call a *static* method on the class. In Type 3, we call an *instance* method, and the first parameter of the lambda is the instance.

---

### Type 4: Constructor Reference
```java
// Lambda
Supplier<ArrayList<String>> listMaker = () -> new ArrayList<>();
// Constructor Reference
Supplier<ArrayList<String>> listMaker = ArrayList::new;

// With parameters
Function<String, StringBuilder> sbMaker = s -> new StringBuilder(s);
Function<String, StringBuilder> sbMaker = StringBuilder::new;

// Powerful with streams: converting to arrays
String[] names = nameList.stream().toArray(String[]::new);
```

**Pattern:** `(args) -> new ClassName(args)` → `ClassName::new`

---

## 2. Method References in Action (Real-World Patterns)

```java
List<String> names = List.of("Charlie", "Alice", "Bob", "Diana");

// Sorting
names.stream().sorted(String::compareToIgnoreCase);

// Printing
names.forEach(System.out::println);

// Mapping
List<Integer> lengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());

// Filtering with a helper method
List<String> filtered = names.stream()
    .filter(StringUtils::isNotBlank)  // Static method from a utility class
    .collect(Collectors.toList());

// Converting
List<Integer> numbers = List.of(1, 2, 3);
List<String> strings = numbers.stream()
    .map(String::valueOf)   // static method reference
    .collect(Collectors.toList());
```

---

## 3. When NOT to Use Method References

Method references aren't always the right choice:

```java
// ❌ DON'T: When you need to add logic around the call
names.stream().map(n -> n.substring(0, 3));  // Can't be a method ref

// ❌ DON'T: When readability suffers
// This is confusing — is it static or instance?
list.stream().map(SomeObscureClass::someVagueMethod);
// This lambda is clearer
list.stream().map(item -> SomeObscureClass.someVagueMethod(item));

// ❌ DON'T: When you need to capture additional variables
String prefix = "Hello, ";
names.stream().map(n -> prefix + n);  // Lambda captures 'prefix'
// No method reference equivalent!
```

---

## 4. Quick Reference: Lambda → Method Reference Conversion

| Lambda Expression | Method Reference | Type |
| --- | --- | --- |
| `x -> System.out.println(x)` | `System.out::println` | Instance (specific object) |
| `s -> s.toUpperCase()` | `String::toUpperCase` | Instance (arbitrary object) |
| `s -> Integer.parseInt(s)` | `Integer::parseInt` | Static |
| `(a, b) -> a.compareTo(b)` | `String::compareTo` | Instance (arbitrary object) |
| `() -> new ArrayList<>()` | `ArrayList::new` | Constructor |
| `arr -> Arrays.sort(arr)` | `Arrays::sort` | Static |

---

## Summary

| Method Reference Type | Syntax | Lambda Equivalent |
| --- | --- | --- |
| Static method | `ClassName::staticMethod` | `x -> ClassName.staticMethod(x)` |
| Instance (specific object) | `obj::instanceMethod` | `x -> obj.instanceMethod(x)` |
| Instance (arbitrary object) | `ClassName::instanceMethod` | `x -> x.instanceMethod()` |
| Constructor | `ClassName::new` | `x -> new ClassName(x)` |

### Tip:

Use method references when they make the code **more readable**, not less. The golden rule: if the lambda body is just a single method call with the parameters passed straight through, use a method reference. If there's any transformation, condition, or extra logic, stick with the lambda.
