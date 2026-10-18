In Java, **Polymorphism** is the third pillar of Object-Oriented Programming. The word itself comes from Greek, meaning "many forms."

In programming, it describes the ability of a single action or method to behave differently based on the exact object that is performing it.

There are two distinct types of polymorphism in Java: **Compile-Time** and **Run-Time**.

---

## 1. Compile-Time Polymorphism (Static Binding)

You have actually already seen this! **[Method Overloading](https://github.com/musab05/Java-Programming-Language/tree/main/1.13)** is how Java achieves compile-time polymorphism.

When you have multiple methods with the same name but different parameters (e.g., `attack(int damage)` vs. `attack(String weapon)`), the Java compiler knows exactly which form of the method to call at the moment the code is compiled.

---

## 2. Run-Time Polymorphism (Dynamic Binding)

This is the true power of polymorphism and is achieved through **Method Overriding**.

Method overriding happens when a child class provides its own specific implementation of a method that is already defined in its parent class. The JVM decides which version of the method to run _during execution_ (at run-time) based on the actual object created in memory, not the reference type.

---

## 3. How Method Overriding Works

Imagine you are structuring entities for a game. You have a general `Enemy` class, and specific types of enemies that inherit from it.

To override a method, the child class must use the exact same method name, return type, and parameters as the parent class. We also use the `@Override` annotation to tell the compiler, "Hey, I am intentionally replacing the parent's version of this method."

```java
// 1. Parent Class
class Enemy {
    public void attack() {
        System.out.println("The enemy attacks basic melee!");
    }
}

// 2. Child Class 1
class Zombie extends Enemy {
    @Override
    public void attack() {
        System.out.println("The Zombie lunges and bites!");
    }
}

// 3. Child Class 2
class Dragon extends Enemy {
    @Override
    public void attack() {
        System.out.println("The Dragon breathes a cone of fire!");
    }
}

```

---

## 4. The Magic of "Upcasting"

The real benefit of Run-Time Polymorphism is seen when we use a **Parent reference variable to hold a Child object**. This is called Upcasting.

Look at what happens when we tell different enemies to attack:

```java
public class GameEngine {
    public static void main(String[] args) {
        // We use the PARENT type (Enemy) as the reference,
        // but create the CHILD type (Zombie/Dragon) in memory.

        Enemy standard = new Enemy();
        Enemy walker = new Zombie();
        Enemy boss = new Dragon();

        // Polymorphism in action: The same method call triggers different behaviors!
        standard.attack(); // Output: The enemy attacks basic melee!
        walker.attack();   // Output: The Zombie lunges and bites!
        boss.attack();     // Output: The Dragon breathes a cone of fire!
    }
}

```

### Why is this so powerful?

Because of polymorphism, you don't need to write separate logic to handle every single enemy type. You can group them all into a single `Enemy[]` array and loop through them. The JVM is smart enough to look at the object in the Heap memory and trigger the correct `.attack()` method for each one.

This makes your code highly scalable—if you add a `Vampire` class tomorrow, you don't have to change your game loop at all!
