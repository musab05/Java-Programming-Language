**Method Overriding** is a feature in Java that allows a subclass (child class) to provide a specific implementation of a method that is already provided by its superclass (parent class).

It is the core mechanism behind **Run-Time Polymorphism**, allowing the Java Virtual Machine (JVM) to decide which version of a method to execute based on the actual object created in memory.

---

## 1. Rules for Method Overriding

To successfully override a method, the child class's method must strictly adhere to these rules:

1. **Same Name:** The method name must exactly match the parent's method.
2. **Same Parameters:** The parameter list must be identical to the parent's method.
3. **Same Return Type:** The return type must be the same (or a subtype, known as a *covariant return type*).
4. **Access Modifier:** The overriding method cannot have a *more restrictive* access modifier than the method being overridden. (e.g., If the parent method is `protected`, the child method can be `protected` or `public`, but not `private`).

---

## 2. The `@Override` Annotation

When you override a method, it is highly recommended to place the `@Override` annotation directly above the method signature.

While not strictly required by the compiler, it serves two crucial purposes:

* **Compile-Time Checking:** It tells the compiler to check if you are actually overriding a parent method. If you accidentally misspell the method name or change the parameters, the compiler will throw an error instead of silently creating a brand new method.
* **Readability:** It makes it immediately obvious to other developers that this method's original definition comes from a parent class.

---

## 3. Code Example

Here is a clear example of how a child class alters the behavior of its parent class.

```java
// 1. Parent Class
class Animal {
    public void makeSound() {
        System.out.println("The animal makes a generic sound.");
    }
}

// 2. Child Class
class Dog extends Animal {
    
    // Overriding the parent's method
    @Override
    public void makeSound() {
        System.out.println("The dog barks: Woof! Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        myAnimal.makeSound(); // Outputs: The animal makes a generic sound.

        Dog myDog = new Dog();
        myDog.makeSound();    // Outputs: The dog barks: Woof! Woof!
        
        // Upcasting (Polymorphism in action)
        Animal myPolymorphicDog = new Dog();
        myPolymorphicDog.makeSound(); // Outputs: The dog barks: Woof! Woof!
    }
}

```

---

## 4. What Cannot Be Overridden?

Not everything in a parent class can be overridden by a child:

* **`private` methods:** They are not inherited, so they cannot be overridden.
* **`static` methods:** If a child class defines a static method with the same signature as a static method in the parent class, it is called *Method Hiding*, not overriding.
* **`final` methods:** The `final` keyword explicitly prevents a method from being overridden.
* **Constructors:** Constructors are never inherited, so they cannot be overridden.

---

## 5. Overriding vs. Overloading

Because the names are similar, these two concepts are often compared. Here is the breakdown:

| Feature | Method Overriding | Method Overloading |
| --- | --- | --- |
| **Location** | Occurs between two classes (Parent and Child). | Occurs within the same class. |
| **Parameters** | Must be exactly the SAME. | Must be DIFFERENT. |
| **Return Type** | Must be the SAME (or covariant subtype). | Can be different. |
| **Polymorphism** | Run-Time (Dynamic Binding). | Compile-Time (Static Binding). |
| **Use Case** | To change the behavior of an inherited method. | To perform a similar action with different inputs. |
