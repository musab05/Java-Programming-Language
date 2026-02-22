## 🎤 Frequently Asked Interview Questions

### 1. What is the exact difference between `<T extends Number>` and `<? extends Number>`?

**Answer:** * `<T extends Number>` is used in **declarations** (creating a generic class or generic method). It defines a named type variable `T` that you can reuse throughout the class or method.

* `<? extends Number>` is a **wildcard** used in **instantiations** or method parameters (e.g., `List<? extends Number> list`). It simply means "some unknown type that extends Number." You use the wildcard when you don't need to reuse the specific type later in the method.

### 2. Why does the compiler throw an error if you try to `add()` an item to a `List<? extends Number>`?

**Answer:** This is the most famous Generics "gotcha." `<? extends Number>` means the list holds *some* specific subclass of Number, but the compiler doesn't know *which one*. It could be a `List<Double>`. If the compiler allowed you to `add(new Integer(5))` to it, you would corrupt the `List<Double>`. Therefore, an upper-bounded wildcard collection is strictly **read-only** (except for `null`).

### 3. When using Multiple Bounds (`<T extends ClassA & InterfaceB>`), why does Java strictly enforce that the Class must be listed first?

**Answer:** Because Java does not support multiple inheritance for classes. A generic type can only extend a maximum of **one** class, but it can implement **many** interfaces. Forcing the class to be declared first makes it easier for the compiler to parse the inheritance tree and generate the correct bytecode for method dispatching under the hood.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Multiple Bound Syntax

**Task:** You are writing a method that needs to sort elements. The generic type `T` must extend the `Number` class AND implement the `Comparable` interface. Write the correct generic method signature.
**Solution:**

```java
public <T extends Number & Comparable<T>> void sortNumbers(List<T> list) { ... }

```

### Challenge 2: The PECS Puzzle

**Task:** Look at the following code. Which of the three `add()` methods will result in a compilation error?

```java
public void populateList(List<? super Integer> list) {
    list.add(new Integer(10)); // Line A
    list.add(new Object());    // Line B
    list.add(null);            // Line C
}

```

**Solution:** **Line B** will fail to compile. Because the list uses `<? super Integer>`, it guarantees it can *hold* an `Integer`. However, the actual list passed in might be a `List<Number>`. If you try to force an `Object` into a `List<Number>`, it violates type safety.

---

## 📝 Practice Problems

Here are two architectural problems designed to test your ability to use bounds effectively. The first focuses on Upper Bounds to create a mathematical utility. The second tests Multiple Bounds to enforce strict business rules on an object.

### Problem 1: The Math Utility (Upper Bounds)

You are building a library that processes geometric shapes and numbers.

**Requirements:**

1. Create a generic class `MathUtil<T>`.
2. Add an **upper bound** to `T` so that it only accepts subclasses of `Number`.
3. Write a method `public double sum(T a, T b)`.
4. Inside the method, use the `.doubleValue()` method (which is guaranteed to exist because of your bound) to add `a` and `b` together and return the result.
5. In `Main`, test it successfully with Integers and Doubles. Then, try to instantiate `MathUtil<String>` and verify the compiler stops you.

### Problem 2: The Secure Event Logger (Multiple Bounds)

You are writing a core logging system. The system can only log items that are actual `Event` objects AND implement the `Encryptable` interface.

**Requirements:**

1. Create an empty class `SystemEvent`.
2. Create an interface `Encryptable` with a method `String encryptData()`.
3. Create a class `LoginEvent` that `extends SystemEvent` and `implements Encryptable`. Have `encryptData()` return "Encrypted Login Data".
4. Create a generic class `SecureLogger<T>`.
5. Apply **Multiple Bounds** to `T` so that it MUST extend `SystemEvent` and implement `Encryptable`.
6. Write a method `public void logEvent(T event)`. Inside, print "Logging: " followed by the result of the event's `encryptData()` method.
