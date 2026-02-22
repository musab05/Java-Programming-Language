## 🎤 Frequently Asked Interview Questions

### 1. What happens if a superclass is NOT `Serializable`, but the subclass IS?

**Answer:** The code will actually compile and run, but there is a major catch. During deserialization, the JVM will rebuild the subclass state from the byte stream, but for the non-serializable superclass, it will invoke its **no-argument constructor** to initialize its fields. If the superclass doesn't have a no-argument constructor, a `java.io.InvalidClassException` will be thrown at runtime.

### 2. How do `transient` and `static` variables behave differently during serialization?

**Answer:** The outcome is the same (neither is saved to the file), but the *reason* is entirely different.

* A `transient` variable is an instance variable that you explicitly tell the JVM to ignore during serialization.
* A `static` variable is ignored because serialization only saves the state of an **object** (instance). Static variables belong to the **class**, not the object, so they are never included in the byte stream to begin with.

### 3. If an object contains a reference to another object (e.g., a `User` has a `Profile`), what happens during serialization?

**Answer:** Java attempts to serialize the entire **Object Graph**. It will automatically traverse the references and serialize the `User`, the `Profile`, and any objects the `Profile` holds. However, if *any* object in that entire web of references does not implement `Serializable`, the JVM immediately aborts and throws a `NotSerializableException`.

### 4. Why is native Java Serialization often considered a major security vulnerability today?

**Answer:** Because it is susceptible to **"Gadget Chain" attacks** (Remote Code Execution). When Java deserializes a stream, it instantiates objects and can trigger certain "magic methods" (like `readObject()`) before you even cast the object or check what it is. If a malicious user intercepts the byte stream and injects a carefully crafted, malicious object payload, the JVM will execute it during the deserialization process. This is why the industry has largely shifted to JSON/XML for external data transfer.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Default Value Trap

**Task:** You serialize an instance of this `Account` class where `balance = 500.0` and `pin = 1234`. When you deserialize it, what will the values be?

```java
public class Account implements Serializable {
    private double balance;
    private transient int pin;
}

```

**Solution:** `balance` will be `500.0`, but `pin` will be `0`. When a `transient` field is deserialized, the JVM assigns it the default value for its data type (`0` for primitives, `null` for Objects, `false` for booleans).

### Challenge 2: The Version Mismatch

**Task:** You save a `Player` object to disk. The next day, you add `private int level;` to the `Player` class, but you forgot to declare a `serialVersionUID`. What happens when you try to load yesterday's saved file?
**Solution:** The JVM will throw an `InvalidClassException`. Because you didn't lock in the `serialVersionUID`, the JVM auto-generates a new hash based on the class structure. Since the class structure changed (you added a field), the hash of the file and the hash of the current class no longer match.

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to protect sensitive data and manage class versions. The second is an advanced "Senior Dev" trick that uses serialization for in-memory cloning.

### Problem 1: The Secure Game Save

You are building the save-state system for an RPG.

**Requirements:**

1. Create a `GameCharacter` class that implements `Serializable`.
2. Give it a hardcoded `serialVersionUID` of `1L`.
3. Add three fields: `String name`, `int health`, and `String sessionToken`.
4. Ensure the `sessionToken` is **never** saved to disk.
5. In your `Main` class, create a character, assign values to all three fields, and serialize the object to a file named `"savegame.ser"` using Try-With-Resources and `ObjectOutputStream`.
6. Read the object back from the file using `ObjectInputStream`. Print the deserialized object's fields to verify the name and health were saved, but the session token is `null`.

### Problem 2: The Deep Copy Utility (In-Memory Serialization)

Sometimes you need a perfect, disconnected clone of a complex object. If you use `ByteArrayOutputStream` and `ByteArrayInputStream`, you can serialize an object into a byte array in RAM (instead of a file) and instantly deserialize it to create a flawless "Deep Copy."

**Requirements:**

1. Create a `Serializable` class called `Company` that holds a `String name` and an `Address` object (which must also be `Serializable`).
2. Write a utility method `public static Company deepCopy(Company original)`.
3. Inside the method, serialize the `original` object into a `ByteArrayOutputStream`.
4. Immediately take the byte array from that stream, pass it into a `ByteArrayInputStream`, and deserialize it back into a new `Company` object.
5. Return the new object. Verify in `Main` that changing the `Address` of the clone does not affect the `Address` of the original.
