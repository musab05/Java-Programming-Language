## 🎤 Frequently Asked Interview Questions

### 1. What is "Type Erasure" and why did Java design it this way?

**Answer:** Type Erasure is the process where the Java compiler removes all Generic type information (`<T>`, `<String>`, etc.) during compilation. By the time the code becomes bytecode and runs on the JVM, a `List<String>` and a `List<Integer>` are both just a plain raw `List` containing `Object` references. Java designed it this way strictly for **backward compatibility**. When Generics were introduced in Java 5, they needed to ensure that new code could still seamlessly interact with legacy, pre-Java 5 codebases that used raw collections.

### 2. Why does `List<Number> list = new ArrayList<Integer>();` fail to compile?

**Answer:** Because Generics are **invariant**. Even though `Integer` is a subclass of `Number`, a `List<Integer>` is *not* a subclass of `List<Number>`. If the compiler allowed this, you could take that `list` reference, call `list.add(3.14)` (a Double), and successfully put a Double into what is actually an ArrayList of Integers! This would crash your program later. To fix this and allow polymorphism, you must use wildcards: `List<? extends Number> list = new ArrayList<Integer>();`.

### 3. Explain the PECS rule with wildcards (`<? extends T>` vs `<? super T>`).

**Answer:** PECS stands for **Producer Extends, Consumer Super**.

* Use `<? extends T>` when the collection is a **Producer** of data. You want to *read* items from the collection (e.g., iterating through it). The compiler guarantees that whatever you read will be at least of type `T`. However, you **cannot add** anything to this collection because the compiler doesn't know the exact specific subclass it holds.
* Use `<? super T>` when the collection is a **Consumer** of data. You want to *write* (add) items to the collection. The compiler guarantees it is safe to add a `T` (or its subclasses) because the list is typed to `T` or one of its parent classes.

### 4. Can you create a Generic Array like `T[] array = new T[10];`?

**Answer:** No, the compiler will not allow this. Arrays in Java are evaluated at runtime (they need to know their exact type in memory to throw an `ArrayStoreException` if you put the wrong object in). Generics, due to Type Erasure, lose their exact type at runtime. Because these two memory models fundamentally clash, Java strictly forbids the instantiation of generic arrays.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Generic Method Signature

**Task:** Write the method signature for a generic method named `pickOne` that takes two arguments of the *exact same* generic type, and returns that same generic type.
**Solution:**

```java
// The <T> before the return type is required to declare the generic variable!
public <T> T pickOne(T first, T second) { ... }

```

### Challenge 2: The Unbounded Wildcard

**Task:** You have a method `public void printList(List<Object> list)`. You try to pass a `List<String>` into it, but it fails to compile due to invariance. Fix the method signature so it accepts a list of *absolutely anything*, but only for reading/printing.
**Solution:**

```java
// Using the Unbounded Wildcard (?)
public void printList(List<?> list) { ... }

```

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to build a custom, multi-type Generic class. The second forces you to apply the PECS rule to safely transfer data between collections.

### Problem 1: The Key-Value Tuple (Generic Classes)

Sometimes you need to return two related pieces of data from a method, but Java only allows one return value. A Generic "Pair" or "Tuple" class solves this perfectly.

**Requirements:**

1. Create a generic class `Pair<K, V>`.
2. Give it two private fields: `K key` and `V value`.
3. Create a constructor that accepts both, and provide standard getters for them.
4. In your `Main` class, instantiate two completely different pairs:
* A pair holding an `Integer` (ID) and a `String` (Name).
* A pair holding a `String` (Stock Ticker) and a `Double` (Price).


5. Print the keys and values of both pairs to verify your single class handles both type profiles safely.

### Problem 2: The Safe Data Migrator (PECS Rule)

You are building an archive system. You need to read old records from various specific collections and dump them into a master, generalized archive.

**Requirements:**

1. Create a `DataMigrator` class.
2. Write a `public static` method called `migrateData`.
3. This method needs two parameters: a `source` list and a `destination` list.
4. Apply the **PECS** rule to the parameters:
* The `source` should be able to produce `Number` objects or any of its subclasses (like `Integer` or `Double`).
* The `destination` should be able to consume `Number` objects (meaning it could be a `List<Number>` or a `List<Object>`).


5. Inside the method, iterate through the `source` and `.add()` each item to the `destination`.
6. In `Main`, test this by creating a `List<Integer>` as your source, and a `List<Number>` as your destination. Pass them to your method and print the destination list.
