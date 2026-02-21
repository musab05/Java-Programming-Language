## 🎤 Frequently Asked Interview Questions

### 1. What happens if you use `instanceof` on a `null` reference? Does it throw a `NullPointerException`?

**Answer:** No, it does not throw an exception. `null instanceof AnyClass` will beautifully and safely return `false`. This makes `instanceof` an excellent tool for defensive programming before casting.

### 2. When writing an `equals(Object obj)` method, should you use `instanceof` or `.getClass()`?

**Answer:** This is a famous debate!

* Using `instanceof` is standard and allows subclasses to be equal to their parent class. However, it can break the **symmetric contract** of `equals()` (if A equals B, B must equal A) when subclasses add new fields.
* Using `.getClass()` enforces strict, exact-type checking, ensuring that a `Parent` and a `Child` are never considered equal.

### 3. What is "Pattern Matching for `instanceof`" in Java 16+ and why is it useful?

**Answer:** It allows you to declare a binding variable right inside the `if` condition (e.g., `if (obj instanceof String s)`). It removes the boilerplate of explicitly casting the object on the very next line, reducing visual clutter and minimizing the risk of copy-paste casting errors.

### 4. Why is a long chain of `if-else` statements using `instanceof` considered an anti-pattern (a "code smell")?

**Answer:** It violates the **Open-Closed Principle** of SOLID design. If you add a new type to your system, you have to go find that `if-else` chain and modify it. Instead, you should use **Polymorphism**, defining an abstract method in a base class or interface and letting each specific type handle its own behavior.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Null Trap

**Task:** What will the following code print?

```java
String name = null;
if (name instanceof Object) {
    System.out.println("It's an object!");
} else {
    System.out.println("Not an object.");
}

```

**Solution:** It prints `"Not an object."` because `null` evaluates to `false` for any `instanceof` check.

### Challenge 2: The Java 16 Upgrade

**Task:** Upgrade the following legacy code to use Java 16 Pattern Matching.

```java
public void printLength(Object obj) {
    if (obj instanceof String) {
        String s = (String) obj;
        System.out.println("String length: " + s.length());
    }
}

```

**Solution:**

```java
public void printLength(Object obj) {
    if (obj instanceof String s) {
        System.out.println("String length: " + s.length());
    }
}

```

---

## 📝 Practice Problems

Here are two problems. The first tests your ability to use the new Java 16 syntax effectively. The second tests your architecture skills by asking you to *remove* an `instanceof` code smell.

### Problem 1: The Mixed-Type Processor (Java 16+ required)

Sometimes you receive raw `Object` data from a legacy API or a highly generic system, and you must route it safely.

**Requirements:**

1. Create a class `DataProcessor`.
2. Write a method `public void process(Object data)`.
3. Inside this method, use **Java 16 Pattern Matching** (`instanceof` with binding variables) to handle three specific cases:
* If `data` is a `String`, print the string entirely in uppercase.
* If `data` is an `Integer`, print the integer multiplied by 10.
* If `data` is a `List` (you can use raw `List` or `List<?>`), print "List size: " followed by the number of elements.
* If it's anything else, print "Unknown data type".


4. In your `Main` class, pass in `"hello"`, `5`, a `new ArrayList<>()`, and a `Double` to test it.

### Problem 2: The "Code Smell" Refactoring

This is a classic senior-level interview test. You are given bad code that heavily relies on `instanceof`, and you must fix it using Object-Oriented principles.

**The Bad Code:**

```java
class Dog { public void bark() { System.out.println("Woof"); } }
class Cat { public void meow() { System.out.println("Meow"); } }
class Cow { public void moo() { System.out.println("Moo"); } }

public class Farm {
    public void makeNoise(Object animal) {
        if (animal instanceof Dog) {
            ((Dog) animal).bark();
        } else if (animal instanceof Cat) {
            ((Cat) animal).meow();
        } else if (animal instanceof Cow) {
            ((Cow) animal).moo();
        }
    }
}

```

**Requirements:**

1. Refactor this code so that `Farm.makeNoise()` does **not** use `instanceof` at all.
2. *Hint:* You will need to create a common interface or abstract class (e.g., `Animal`) with a unified method (e.g., `speak()`). Make `Dog`, `Cat`, and `Cow` implement this interface.
3. Update `makeNoise` to accept the new interface type instead of `Object` and trigger the polymorphic behavior.
