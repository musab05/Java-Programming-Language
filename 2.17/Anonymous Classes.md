Think of an **Anonymous Inner Class** as a "disposable" class. It allows you to declare and instantiate a class at the same time without actually giving it a name.

In Java, you use them when you need to override the behavior of a class or interface just once, right where you need it, rather than creating a whole new `.java` file or a named member class.

---

## The Syntax

The syntax looks a bit strange at first because it combines a constructor call with a class definition.

```java
// Standard Syntax
ClassName objectName = new ClassName() {
    @Override
    public void someMethod() {
        // Your custom logic here
    }
};

```

### Key Characteristics

* **No Constructor:** Since it has no name, you cannot define a constructor for it.
* **One-time use:** You cannot reuse the class definition elsewhere; it's local to that specific instantiation.
* **Access:** It can access members of the enclosing class and local variables (provided those variables are `final` or "effectively final").

---

## Common Use Cases

### 1. Implementing an Interface

This is the most frequent use, especially in older Java code for event handling (like button clicks) or threading.

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running in a thread!");
    }
};
new Thread(task).start();

```

### 2. Extending a Class

You can also use them to quickly subclass an existing class to tweak one specific method.

---

## Anonymous Classes vs. Lambda Expressions

Since Java 8, Anonymous Classes have mostly been replaced by **Lambda Expressions** when dealing with "Functional Interfaces" (interfaces with only one method).

| Feature | Anonymous Class | Lambda Expression |
| --- | --- | --- |
| **Boilerplate** | Heavy (needs `@Override`, method name, etc.) | Very light |
| **Scope** | Creates its own scope (`this` refers to the inner class) | Shares scope with the caller (`this` refers to outer class) |
| **Capability** | Can implement interfaces with **multiple** methods | Only works for **Functional Interfaces** |

---

## When to avoid them?

While they are handy, they can make code hard to read if the logic inside the block is longer than a few lines. If your anonymous class starts getting complex, it’s usually a sign that you should promote it to a **Named Inner Class** or a separate top-level class.
