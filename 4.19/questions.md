Reflection is Java’s "X-Ray vision." It allows a program to look at itself in a mirror and even perform "self-surgery" while it’s still running. While most of your daily code is **static** (you know exactly what classes and methods you're calling), Reflection is **dynamic**. It’s the difference between following a fixed recipe and a chef who decides what to cook based on what they find in the fridge *after* they've already started the stove.

---

## 🎤 Frequently Asked Interview Questions

### 1. Why is Reflection used so heavily in Frameworks like Spring?

**Answer:** Frameworks need to work with *your* code, which didn't exist when the framework was written. Spring uses Reflection to scan your project for classes marked with `@Service` or `@Component`, instantiate them, and "inject" dependencies into private fields without you ever having to write `new MyService()`.

### 2. What is the difference between `getField()` and `getDeclaredField()`?

**Answer:** * `getField()`: Only returns **public** fields, but it includes those inherited from parent classes.

* `getDeclaredField()`: Returns **all** fields (private, protected, etc.) defined in that specific class, but it **does not** include inherited ones.

### 3. How do you instantiate a class if it has a private constructor?

**Answer:** Just like with fields, you use Reflection. You get the `Constructor` object, call `constructor.setAccessible(true)`, and then call `newInstance()`. This is how "Singleton" patterns are often broken in unit tests or by malicious code.

### 4. What is the "Performance Penalty" of Reflection?

**Answer:** When you call a method normally, the JVM performs "Inlining" and other optimizations because it knows the structure at compile time. With Reflection, the JVM has to perform security checks, resolve types, and search for method names every single time the code runs. It can be **10 to 100 times slower** than a direct call.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Method Invoker

**Task:** Given an object `obj`, invoke a method named `"calculate"` that takes an `int` parameter.
**Solution:**

```java
Method method = obj.getClass().getDeclaredMethod("calculate", int.class);
method.setAccessible(true);
method.invoke(obj, 42);

```

### Challenge 2: The Annotation Hunter

**Task:** Check if a class has the `@Deprecated` annotation at runtime.
**Solution:**

```java
if (MyClass.class.isAnnotationPresent(Deprecated.class)) {
    System.out.println("This class is old!");
}

```

---

## 📝 Practice Problems

### Problem 1: The "DIY" Dependency Injector

Build a mini-version of Spring's dependency injection.

**Requirements:**

1. Create an annotation `@InjectMe`.
2. Create a class `Engine` and a class `Car`. `Car` has a `private Engine engine;` field marked with `@InjectMe`.
3. Write a `Starter` class with a `static void inject(Object obj)` method.
4. The method should use Reflection to find all fields marked with `@InjectMe`, create an instance of that field's type, and set it into the object.
5. **The Goal:** Run `Car myCar = new Car(); Starter.inject(myCar);` and verify `myCar.getEngine()` is no longer null.

### Problem 2: The Object Inspector (Logger)

Create a utility that prints the values of **all** private fields of any object passed to it.

**Requirements:**

1. Write a method `public static void inspect(Object obj)`.
2. Get all "declared fields" of the object.
3. For each field, make it accessible and print its name and its current value.
4. **The Goal:** Pass a `User` or `Account` object to it and see it "spill the beans" on all private data.

---

**You’ve just peered behind the curtain of the JVM.** Reflection is a "break glass in case of emergency" tool. It’s brilliant for writing generic libraries and frameworks, but it should be used sparingly in standard business logic.
