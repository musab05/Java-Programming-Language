In Java, the **`super`** keyword is a reference variable used to refer directly to the **immediate parent class** object.

Just as `this` acts as a mirror for an object to look at itself, `super` acts as a window for an object to look at its parent. It is primarily used in three specific scenarios to resolve conflicts between a parent and child class.

---

## 1. Accessing Parent Class Variables

If a child class has a variable with the exact same name as a variable in its parent class, the child's variable will "shadow" or hide the parent's variable. You use `super.variableName` to explicitly access the parent's version.

```java
class Enemy {
    int health = 100; // Parent variable
}

class Boss extends Enemy {
    int health = 500; // Child variable shadows parent variable

    public void displayHealth() {
        System.out.println("Boss Health: " + health);       // Outputs 500
        System.out.println("Standard Health: " + super.health); // Outputs 100
    }
}

```

---

## 2. Invoking Parent Class Methods

When a child class **overrides** a parent method, calling that method will always trigger the child's version. However, sometimes you want the child to do everything the parent does, *plus* a little extra, rather than completely replacing it. You use `super.methodName()` to trigger the parent's logic.

```java
class Player {
    public void jump() {
        System.out.println("Player jumps 5 feet into the air.");
    }
}

class SuperPlayer extends Player {
    @Override
    public void jump() {
        super.jump(); // Calls the parent's jump logic first
        System.out.println("Player activates jetpack and flies higher!");
    }
}

```

---

## 3. Invoking Parent Class Constructors

When you create a child object, the parent's constructor must be executed first to build the foundational state of the object. Java automatically inserts a hidden `super()` (a call to the parent's no-argument constructor) at the start of every child constructor.

If the parent class only has a **parameterized constructor**, you *must* manually use `super(arguments)` to pass the required data up to the parent.

**Crucial Rule:** If you explicitly write `super()`, it **must be the very first statement** inside the child's constructor.

```java
class Character {
    String name;

    // Parameterized parent constructor
    public Character(String name) {
        this.name = name;
        System.out.println("Character created: " + name);
    }
}

class Warrior extends Character {
    int stamina;

    public Warrior(String name, int stamina) {
        // Must be the first line! Passes 'name' up to the Character class.
        super(name); 
        this.stamina = stamina;
        System.out.println("Warrior initialized with stamina: " + stamina);
    }
}

```

---

## 4. `this` vs. `super`

It is common to see these two keywords used together in complex object initializations. Here is a quick breakdown of how they compare:

| Feature | `this` Keyword | `super` Keyword |
| --- | --- | --- |
| **Reference** | Points to the **current** class instance. | Points to the **parent** class instance. |
| **Variables** | Accesses current class fields. | Accesses shadowed parent fields. |
| **Methods** | Calls other methods in the current class. | Calls overridden parent methods. |
| **Constructors** | `this()` calls another constructor in the same class. | `super()` calls the parent class constructor. |
