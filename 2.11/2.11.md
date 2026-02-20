While we touched on them while discussing Abstraction, **Abstract Classes** have specific technical rules and behaviors in Java that are crucial to understand, especially when designing larger, scalable systems.

An abstract class serves as a "partial blueprint." It allows you to define some default behavior (concrete methods) while forcing child classes to figure out the rest (abstract methods).

---

## 1. The Strict Rules of Abstract Classes

When you declare a class with the `abstract` keyword, the Java compiler enforces a specific set of rules:

1. **No Direct Instantiation:** You can never write `new MyAbstractClass()`. It is physically impossible to create an object directly from it.
2. **Mix of Methods:** It can contain a mix of `abstract` methods (methods without a body) and **concrete** methods (regular methods with a body).
3. **Variables and Constants:** Unlike pure interfaces (which we will cover next), abstract classes can have regular instance variables, `static` variables, and `final` constants.
4. **Access Modifiers:** Abstract methods can be `public`, `protected`, or default (package-private). They **cannot** be `private`, because a private method cannot be inherited or overridden by a child class.

---

## 2. The Constructor Paradox

A very common question is: *"If I cannot instantiate an abstract class, why is it allowed to have a constructor?"*

The answer lies in **Inheritance**. When you create a child object, the JVM must build the parent part of that object first. The abstract class's constructor is called by the child class (using `super()`) to initialize the variables that the child is inheriting.

```java
abstract class GameEntity {
    String name;
    int x, y;

    // The abstract class constructor
    public GameEntity(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        System.out.println("Entity initialized at [" + x + ", " + y + "]");
    }

    public abstract void update(); // Must be defined by children
}

class Player extends GameEntity {
    int health;

    public Player(String name, int x, int y, int health) {
        // Calls the GameEntity constructor to set name, x, and y
        super(name, x, y); 
        this.health = health;
    }

    @Override
    public void update() {
        System.out.println("Reading keyboard input to move player.");
    }
}

```

---

## 3. Abstract Class vs. Concrete Class

| Feature | Concrete Class | Abstract Class |
| --- | --- | --- |
| **Keyword** | None required (default) | `abstract` keyword required |
| **Object Creation** | `new ClassName()` works | `new AbstractClassName()` throws error |
| **Method Types** | Only concrete methods allowed | Both concrete and abstract methods allowed |
| **Usage** | Represents a complete, finished entity | Represents a core concept or base template |

---

## 4. When should you use an Abstract Class?

You should use an abstract class when you want to share code among several closely related classes. It is the perfect tool when your child classes have an **"Is-A"** relationship with the parent and share a significant amount of underlying logic or state (variables), but still need to define their own unique behaviors.

Because Java only allows a class to extend **one** parent class, using an Abstract Class is a heavy commitment.
