# Abstraction in Java: Assessment Guide

This guide focuses on the "Hurdles and Secrets" of Abstraction. Since we are skipping practice problems for now, let's focus on the deep conceptual nuances and the "Why" behind this final OOP pillar.

## 🎤 Frequent Interview Questions

*These questions are designed to test if you understand the structural rules and the architectural purpose of abstract classes.*

1. **Why can't we instantiate an abstract class?**
* **The Concept:** Because an abstract class is an incomplete blueprint. It may contain abstract methods that have no implementation (no body). If Java allowed you to create an object of that class and call an abstract method, the JVM wouldn't have any code to execute, causing a crash.


2. **Can an abstract class have zero abstract methods?**
* **The Concept:** Yes! This is a common interview trick. You can mark a class as `abstract` even if all its methods are concrete. You do this when you want to prevent developers from creating instances of that class directly, forcing them to use a subclass instead.


3. **Can an abstract class have a constructor?**
* **The Concept:** Yes. Even though you can't say `new MyAbstractClass()`, the constructor is used by the subclasses via the `super()` keyword. It is used to initialize common fields shared by all child classes (like a `seed` in your terrain generator).


4. **Is it mandatory for a subclass to implement all abstract methods of the parent?**
* **The Concept:** Yes, unless the subclass itself is also declared as `abstract`. If a concrete class inherits from an abstract class, it must "finish" the blueprint by providing bodies for all inherited abstract methods.


5. **Can an abstract method be `private` or `static`?**
* **The Concept:** No.
* It cannot be `private` because it *must* be seen by child classes to be overridden.
* It cannot be `static` because static methods belong to the class and cannot be overridden, but abstract methods *depend* on being overridden.





---

## 💡 Concept Check Questions

*Use these scenarios to test your architectural judgment.*

### 🗣️ Verbal/Conceptual Check

* **The "Contract" Analogy:** "If I am building a payment system for **Academiq**, why would I make `PaymentMethod` an abstract class instead of a regular class?" *(Answer: Because 'Payment' is a concept, not a thing. You can't just 'do a payment'—you must do a 'CreditCardPayment' or a 'PayPalPayment'. By making it abstract, you force every payment type to implement its own `processPayment()` logic while sharing common data like `amount`).*
* **The Static Trap:** "Can I have a `static` method inside an abstract class?" *(Answer: Yes. You can have static utility methods (like `Math` functions) in an abstract class. You call them using `AbstractClassName.methodName()`, no object required).*

### 💻 Structural Check

* **Identify the Rule Violation:** "What is wrong with this code snippet?"
```java
abstract class Generator {
    abstract final void start(); 
}

```


* **The Hurdle:** The keywords `abstract` and `final` are total opposites. `abstract` demands that a method *must* be overridden, while `final` strictly *forbids* a method from being overridden. They can never be used together.
