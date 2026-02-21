Since you've got a solid handle on the theory, let’s dive into the practical side. Interviewers love inner classes because they test your understanding of memory management, scope, and the nuances of the JVM.

---

## 🎤 Frequently Asked Interview Questions

### 1. Can an inner class be declared `private` or `protected`?

**Answer:** Yes. Unlike top-level classes (which can only be `public` or package-private), inner classes are members of the outer class. Just like a variable or a method, they can use any of the four access modifiers.

### 2. Why can’t a non-static inner class have static members?

**Answer:** A non-static inner class is associated with an **instance** of the outer class. If it had static members, those members would need to be loaded when the class is loaded, but the inner class itself can't even "exist" without an outer object.

* *Note: Since Java 16, this rule has been relaxed to allow static members in inner classes for better ergonomics.*

### 3. What is the difference between a Static Nested Class and an Inner Class?

**Answer:** The primary difference is the **dependency on an instance**.

* **Inner Class:** Tied to an instance of the outer class. It has an implicit reference to `Outer.this`.
* **Static Nested Class:** Not tied to an instance. It acts like any other top-level class that just happens to be packaged inside another for namespacing.

### 4. What is a "Shadowing" problem in inner classes?

**Answer:** If an inner class has a variable with the same name as a variable in the outer class, the inner variable "shadows" the outer one. To access the outer variable, you must use the syntax: `OuterClassName.this.variableName`.

---

## 🛠️ Coding Challenges & Exercises

### Challenge 1: The "Manual Connection"

**Task:** Given the following code, write the `main` method logic to instantiate the `Engine` class.

```java
class Car {
    private String model = "Tesla Model S";

    class Engine {
        void start() {
            System.out.println("Starting engine for: " + model);
        }
    }
}

```

**Solution:**

```java
public static void main(String[] args) {
    Car myCar = new Car();
    Car.Engine myEngine = myCar.new Engine(); // The special .new syntax
    myEngine.start();
}

```

### Challenge 2: The Anonymous Comparator

**Task:** You have a list of Strings. Use an **Anonymous Inner Class** (not a Lambda!) to sort the list based on the *length* of the strings.

**Solution:**

```java
Collections.sort(words, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
});

```

---

## 📝 Practice Problem: The "Smart Home" System

**Problem Statement:**
Create a class `SmartHome`.

1. Inside `SmartHome`, create a **Private Member Inner Class** called `LightBulb`.
2. `LightBulb` should have a method `powerOn()` that prints "The house at [Address] is now lit." (The address should be a private field in `SmartHome`).
3. Inside `SmartHome`, create a **Static Nested Class** called `SecuritySystem`.
4. The `SecuritySystem` should have a static method `alert()` that prints "Global security alert!"
5. **Requirement:** In your `Main` class, demonstrate how to call `powerOn()` and `alert()`.

**Why this is good practice:** It forces you to handle the difference between needing an instance (for the `LightBulb`) and not needing one (for the `SecuritySystem`).
