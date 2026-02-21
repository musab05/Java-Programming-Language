While they look almost identical and both deal with exceptions, **`throw`** and **`throws`** serve two completely different purposes in Java.

The easiest way to remember the difference is this: **`throw`** is the _action_ of creating an error, while **`throws`** is the _warning_ that an error might happen.

---

## 1. The `throw` Keyword (The Action)

You use `throw` **inside the body of a method** when you want to explicitly trigger an exception. It is followed by an actual _instance_ (an object) of an exception class.

You typically use this when validating user input or enforcing business logic. If something is wrong, you "throw" a red flag.

- **Rule:** You can only throw one exception at a time using `throw`.

```java
public void setAge(int age) {
    if (age < 0) {
        // We are physically creating and throwing the exception object here
        throw new IllegalArgumentException("Age cannot be negative!");
    }
    this.age = age;
}

```

---

## 2. The `throws` Keyword (The Warning)

You use `throws` **in the method signature** (the top line where you define the method's name and parameters). It does not throw an exception itself; instead, it tells the compiler, _"Hey, this method contains code that might fail. I'm not going to handle it here; whoever calls this method needs to deal with it."_

It is followed by the _class names_ of the exceptions, not objects.

- **Rule:** You can declare multiple exceptions with `throws` by separating them with commas.
- **Rule:** It is mandatory to use `throws` if your method contains a **Checked Exception** that isn't wrapped in a `try-catch` block.

```java
import java.io.IOException;

// The 'throws' keyword warns callers that they must handle an IOException
public void readFile(String filePath) throws IOException {
    // We aren't throwing anything here, but the FileReader might!
    FileReader reader = new FileReader(filePath);
    reader.close();
}

```

---

## The Head-to-Head Comparison

| Feature         | `throw`                                     | `throws`                                               |
| --------------- | ------------------------------------------- | ------------------------------------------------------ |
| **Meaning**     | Used to explicitly trigger an exception.    | Used to declare that an exception might occur.         |
| **Location**    | Inside the method body.                     | In the method signature.                               |
| **Followed by** | An **object instance** (`new Exception()`). | A **class name** (`Exception.class`).                  |
| **Quantity**    | Can only throw **one** exception at a time. | Can declare **multiple** exceptions (comma-separated). |
| **Use Case**    | Custom business logic validation.           | Delegating Checked Exception handling to the caller.   |

---

## Seeing Them Work Together

Often, you will use both in the exact same method. You might `throw` a checked exception manually, which then requires you to declare it with `throws` in the signature.

```java
public class Bank {
    // 1. 'throws' warns the caller that an Exception might happen
    public void withdraw(double amount) throws Exception {

        if (amount > 1000) {
            // 2. 'throw' actually triggers the error based on our logic
            throw new Exception("Cannot withdraw more than $1000 at an ATM.");
        }

        System.out.println("Dispensing $" + amount);
    }
}

```

If another class calls `bank.withdraw(1500);`, the compiler will force that caller to wrap it in a `try-catch` block because of the `throws` declaration.

---

When dealing with inheritance and method overriding, Java has a very specific philosophy: **a subclass cannot make a method more dangerous than the parent class promised it would be.** If a parent class says a method is safe, the child class isn't allowed to suddenly add a bunch of new mandatory risks (checked exceptions).

Here are the rules broken down into the two main scenarios you will encounter.

---

## Scenario 1: The Parent Method Does NOT Declare an Exception

If the superclass method does not use the `throws` keyword, the rules for the overridden method in the subclass are very restrictive.

- **Rule (Checked Exceptions):** You **cannot** declare any new Checked Exceptions in the subclass method.
- **Rule (Unchecked Exceptions):** You **can** declare Unchecked (Runtime) Exceptions.

### Code Example

```java
class Parent {
    // No exceptions declared here
    public void doSomething() {
        System.out.println("Parent doing something");
    }
}

class Child extends Parent {

    // ❌ ILLEGAL: Cannot add a new Checked Exception (IOException)
    // @Override
    // public void doSomething() throws IOException { ... }

    // ✅ LEGAL: Can add an Unchecked Exception (ArithmeticException)
    @Override
    public void doSomething() throws ArithmeticException {
        System.out.println("Child doing something, might divide by zero!");
    }
}

```

---

## Scenario 2: The Parent Method DOES Declare a Checked Exception

If the superclass method declares that it `throws` a checked exception (e.g., `IOException`), the subclass has a bit more flexibility, but it can only "narrow" the scope, never widen it.

- **Rule (Same Exception):** You can declare the exact same exception.
- **Rule (Subclass Exception):** You can declare an exception that is a _child_ (subclass) of the parent's exception (e.g., if the parent throws `IOException`, the child can throw `FileNotFoundException`).
- **Rule (No Exception):** You can choose to declare **no exceptions at all**. It is perfectly valid for a subclass to implement a safer version of a method!
- **Rule (Broader/New Exception):** You **cannot** declare a parent of the original exception (e.g., throwing `Exception` when the parent threw `IOException`), nor can you throw a completely unrelated checked exception.

### Code Example

```java
import java.io.IOException;
import java.io.FileNotFoundException;

class Parent {
    public void readFile() throws IOException {
        System.out.println("Parent reading file");
    }
}

class Child extends Parent {

    // ✅ LEGAL: Throwing the exact same exception
    // @Override
    // public void readFile() throws IOException { ... }

    // ✅ LEGAL: Throwing a narrower subclass exception
    // @Override
    // public void readFile() throws FileNotFoundException { ... }

    // ✅ LEGAL: Throwing no exceptions at all (making it safer)
    @Override
    public void readFile() {
        System.out.println("Child reading file safely from memory");
    }

    // ❌ ILLEGAL: Throwing a broader exception
    // @Override
    // public void readFile() throws Exception { ... } // Exception is wider than IOException

    // ❌ ILLEGAL: Throwing a completely new, unrelated checked exception
    // @Override
    // public void readFile() throws SQLException { ... }
}

```

---

## Summary Cheat Sheet

| Parent Method Declares                                 | Child Overridden Method Can Declare                                          | Child Overridden Method CANNOT Declare                                  |
| ------------------------------------------------------ | ---------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| **Nothing**                                            | RuntimeExceptions, or Nothing                                                | Any Checked Exceptions                                                  |
| **Specific Checked Exception** (e.g., `IOException`)   | The same Exception, narrower subclasses of it, RuntimeExceptions, or Nothing | Broader Exceptions (e.g., `Exception`), or unrelated Checked Exceptions |
| **Unchecked Exception** (e.g., `NullPointerException`) | Any Unchecked Exception, or Nothing                                          | Any Checked Exceptions                                                  |
