## 🎤 Frequently Asked Interview Questions

### 1. Can an anonymous inner class implement multiple interfaces or extend a class *and* implement an interface simultaneously?

**Answer:** No. An anonymous inner class can either extend **one** class OR implement **one** interface. It cannot do both, and it cannot implement multiple interfaces. If you need that functionality, you must use a named local or member inner class.

### 2. How do you initialize variables in an anonymous class if it doesn't have a constructor?

**Answer:** Since an anonymous class has no name, it cannot have a traditional constructor. However, you can use an **Instance Initializer Block** (a set of curly braces `{ ... }` inside the class body) to perform constructor-like initialization logic when the object is created.

### 3. What does it mean when we say local variables accessed by an anonymous class must be "effectively final"?

**Answer:** If an anonymous class is created inside a method and tries to use a local variable from that method, that variable cannot change its value once assigned. Prior to Java 8, you had to explicitly type the `final` keyword. Now, the compiler just checks to ensure you don't modify it. If you try to reassign it, the code won't compile.

### 4. What is the difference between `this` in an Anonymous Class vs. `this` in a Lambda Expression?

**Answer:** * In an **Anonymous Class**, `this` refers to the instance of the anonymous class itself.

* In a **Lambda Expression**, `this` refers to the instance of the enclosing outer class (lexical scoping).

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Custom Thread

**Task:** Create and start a new `Thread` using an anonymous inner class that implements the `Runnable` interface. Have it print "Background task running...".
**Solution:**

```java
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Background task running...");
    }
}).start();

```

### Challenge 2: The On-the-Fly Override

**Task:** Instantiate a standard Java `Object`, but use an anonymous inner class to override its `toString()` method so that printing the object outputs "I am an anonymous object!".
**Solution:**

```java
Object myObj = new Object() {
    @Override
    public String toString() {
        return "I am an anonymous object!";
    }
};
System.out.println(myObj);

```

---

## 📝 Practice Problems

Here are two detailed problem statements to practice. **Note:** Both of these are specifically designed so that you *cannot* or *should not* use Lambda expressions, forcing you to use Anonymous Inner Classes.

### Problem 1: The UI Event Listener (Multi-method Interface)

Lambdas only work with functional interfaces (interfaces with one abstract method). Let's build a scenario where an anonymous class is strictly required.

**Requirements:**

1. Create an interface called `MouseListener` with two methods: `onClick()` and `onRightClick()`.
2. Create a class called `Button` with a method `attachListener(MouseListener listener)`. This method should just call `listener.onClick()` and `listener.onRightClick()` to simulate user actions.
3. In your `Main` class, instantiate a `Button`.
4. Call `attachListener` and pass in an **Anonymous Inner Class** that implements both methods. Print "Left click detected" and "Right click detected" respectively.

### Problem 2: The "Special Case" Employee (Class Extension)

Anonymous classes aren't just for interfaces; they can extend concrete classes to tweak behavior on the fly.

**Requirements:**

1. Create a class `Employee` with a `baseSalary` of $50,000.
2. Add a method `calculatePay()` that simply returns the `baseSalary`.
3. In your `Main` class, create a standard `Employee` object and print their pay.
4. Now, create a second `Employee` object named `ceo`. Use an **Anonymous Inner Class** to extend the `Employee` instantiation and override `calculatePay()` to return the `baseSalary` multiplied by 10.
5. Print the CEO's pay to verify the overridden behavior worked.
