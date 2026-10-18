In Java, a **method** is a block of code which only runs when it is called. You can pass data, known as parameters, into a method, and the method can perform actions and return data.

Methods are used to perform certain actions, and they are also known as **functions** in other languages. The primary reason to use methods is **code reusability**: define the code once, and use it many times.

---

## 1. Method Declaration (Syntax)

A method must be declared within a class. It is defined with the name of the method, followed by parentheses **()**.

```java
public class Main {
    // Method Definition
    public static int addNumbers(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        // Method Call
        int result = addNumbers(5, 10);
        System.out.println("The sum is: " + result);
    }
}

```

### Components of a Method:

* **Modifier:** (e.g., `public`, `static`) Defines the access type and scope.
* **Return Type:** The data type of the value returned by the method (e.g., `int`, `double`, `String`). If the method doesn't return a value, use `void`.
* **Method Name:** The unique name used to call the method.
* **Parameter List:** Variables passed into the method (comma-separated inside parentheses).
* **Method Body:** The code inside curly braces `{}` that performs the task.

---

## 2. Parameters and Arguments

Information can be passed to methods as parameters. Parameters act as variables inside the method.

* **Formal Parameters:** The variables defined in the method declaration (e.g., `int a`).
* **Arguments:** The actual values passed to the method when it is called (e.g., `5`).

### Pass by Value

Java is strictly **Pass-by-Value**. This means when you pass a variable to a method, a copy of that variable is created. Changes made to the parameter inside the method do not affect the original variable outside the method.

---

## 3. The `return` Keyword

If you want the method to send a value back to the caller, you must use a return type other than `void` and use the `return` keyword.

```java
public int getSquare(int number) {
    return number * number;
}

```

> **Note:** Once the `return` statement is executed, the method immediately stops, and the flow returns to where the method was called.

---

## 4. Static vs. Non-Static Methods

* **Static Methods:** Belong to the class itself. You can call them without creating an object of the class (e.g., `Math.sqrt()`).
* **Non-Static (Instance) Methods:** Belong to an object. You must create an instance of the class (using `new`) before you can call the method.
