## 🎤 Frequently Asked Interview Questions

### 1. Can an Enum extend a class or implement an interface?

**Answer:** An Enum **cannot** extend another class. This is because every Enum in Java implicitly extends the `java.lang.Enum` class, and Java does not support multiple inheritance. However, an Enum **can** implement one or more interfaces, just like any regular class.

### 2. Can you instantiate an Enum using the `new` keyword?

**Answer:** No. Enum constructors are implicitly `private` (or package-private). The JVM guarantees that Enum constants are instantiated only once, when the Enum class is loaded. Trying to use `new MyEnum()` will result in a compilation error.

### 3. Why is an Enum considered the safest way to implement a Singleton in Java?

**Answer:** Traditional Singletons can be broken by **Reflection** (which can forcefully access a private constructor) or **Serialization** (which can deserialize a new instance). Enums are inherently protected against both by the JVM. Java guarantees that an Enum value is instantiated exactly once, making it incredibly robust and thread-safe.

### 4. What is the difference between `==` and `.equals()` when comparing Enums?

**Answer:** For Enums, `==` and `.equals()` do the exact same thing: they compare object references. Since the JVM guarantees only one instance of each Enum constant exists, using `==` is completely safe, faster, and actually preferred because it avoids potential `NullPointerException`s.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Iteration

**Task:** Given the `Level` enum (LOW, MEDIUM, HIGH), write a simple loop to print out the name and the ordinal (index) of each constant.

**Solution:**

```java
for (Level lvl : Level.values()) {
    System.out.println(lvl.name() + " is at index " + lvl.ordinal());
}

```

### Challenge 2: The Reverse Lookup

**Task:** Using the `CoffeeSize` enum from your notes (which has an `int ounces` field), write a `static` method inside the Enum called `getSizeByOunces(int ounces)` that returns the correct `CoffeeSize` enum, or throws an `IllegalArgumentException` if no match is found.

**Solution:**

```java
public static CoffeeSize getSizeByOunces(int ounces) {
    for (CoffeeSize size : CoffeeSize.values()) {
        if (size.getOunces() == ounces) {
            return size;
        }
    }
    throw new IllegalArgumentException("Invalid ounces: " + ounces);
}

```

---

## 📝 Practice Problem: The "Traffic Light" State Machine

This problem will test your ability to use Enums as full classes with state (fields) and behavior (methods).

**Problem Statement:**

1. Create an Enum named `TrafficLight`.
2. Define three constants: `RED`, `YELLOW`, and `GREEN`.
3. Give each constant a `duration` field (in seconds). For example: RED is 30, YELLOW is 5, GREEN is 45.
4. Create a private constructor to assign the duration to each state.
5. Create a getter method `getDuration()` to retrieve the time.
6. **The Tricky Part:** Add a method called `nextLight()` inside the Enum. This method should return the logical next state (e.g., if you call `RED.nextLight()`, it should return `GREEN`. If you call `GREEN.nextLight()`, it should return `YELLOW`).
7. In your `Main` class, start with `RED` and write a loop that transitions through the lights 5 times, printing the light's color and its duration.

**Why this is good practice:** It forces you to think of Enums not just as static data, but as a mini "state machine" where the data knows how to transition itself.
