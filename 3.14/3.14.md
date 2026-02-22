We’ve established that Generics allow us to write flexible code, but sometimes "too much" flexibility is a bad thing. In a real-world production environment, you often need to put guardrails on your types.

This is where **Bounded Generics** come in. They allow you to restrict the types that can be passed as type arguments to a class, interface, or method.

---

## 1. Why Bounded Generics?

Imagine you are writing a generic `Calculator` class. If you use a simple `<T>`, someone could try to pass a `String` or a `User` object into it. A calculator should only work with **Numbers**.

By using Bounded Generics, you can tell the compiler: *"I will accept any type, **as long as** it is a subclass of Number."*

---

## 2. Upper Bounded Type Parameters

An upper bound restricts the unknown type to be either a specific type or a **subtype** of that type. We use the `extends` keyword for this.

### Syntax: `<T extends SuperClass>`

> **Note:** In the context of Generics, `extends` is used for both **classes** and **interfaces**.

### Practical Example: The Math Engine

```java
// T can be Integer, Double, Float, etc., but NOT String.
public class Stats<T extends Number> {
    T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    public double getAverage() {
        double sum = 0.0;
        for (T n : nums) {
            // Because T extends Number, we can safely call doubleValue()
            sum += n.doubleValue(); 
        }
        return sum / nums.length;
    }
}

```

If you tried to instantiate this with `Stats<String>`, the compiler would throw an error before you even ran the code. This is **Type Safety** at its finest.

---

## 3. Multiple Bounds

Java allows you to restrict a type even further by requiring it to implement multiple interfaces or extend a class and implement an interface.

### Syntax: `<T extends Class & Interface1 & Interface2>`

* **Rule:** If one of the bounds is a **class**, it must be specified first.
* **Example:** You want a type that is both a `Shape` (Class) and is `Serializable` (Interface).

```java
public <T extends Shape & Serializable> void processShape(T shape) {
    // Logic here
}

```

---

## 4. Lower Bounded Wildcards

While Upper Bounds limit the type to a specific branch *down* the inheritance tree, **Lower Bounds** do the opposite. They restrict the type to be a specific type or a **superclass** (up the tree).

### Syntax: `<? super SubClass>`

This is most commonly used with the **PECS** principle (**P**roducer `extends`, **C**onsumer `super`). We use lower bounds when we want to **write** or **add** data into a collection.

**Example: Adding Integers safely**

```java
public static void addNumbers(List<? super Integer> list) {
    // This list could be List<Integer>, List<Number>, or List<Object>
    list.add(10); 
    list.add(20);
}

```

Why do we use `super` here? Because an `Integer` can safely be added to a list of `Numbers` or a list of `Objects`.

---

## 5. Comparison: Extends vs. Super

| Feature | `<? extends T>` (Upper Bound) | `<? super T>` (Lower Bound) |
| --- | --- | --- |
| **Focus** | **Reading** data from a collection. | **Writing** data to a collection. |
| **Flexibility** | Any subtype of T. | Any supertype of T. |
| **Guarantees** | You are guaranteed to get at least a `T`. | You are guaranteed that the list can hold a `T`. |
| **Example** | `List<? extends Number>` | `List<? super Integer>` |

---

## Tip: Why the "Extends" name for Interfaces?

One point of confusion for many developers is why we use `<T extends Serializable>` instead of `<T implements Serializable>`.

In the world of Generics, the keyword `extends` was chosen to represent a "general" bounding relationship. It basically means **"is a"** or **"behaves as."** Don't let it trip you up—just remember: in angle brackets `< >`, `extends` is the universal symbol for an upper bound.
