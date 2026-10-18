In Java, **Command Line Arguments** are parameters passed to a program when it is executed from a terminal or command prompt. These arguments allow you to influence the program's behavior without modifying the source code or re-compiling the file.

---

## 1. How it Works: The `args` Array

Recall the standard `main` method signature:
`public static void main(String[] args)`

The `args` parameter is an **array of Strings**. When you run your program, the JVM takes whatever words you typed after the class name and places them into this array.

* **`args[0]`**: The first argument.
* **`args[1]`**: The second argument.
* **`args.length`**: The number of arguments passed.

---

## 2. Code Example

Here is a program that greets a user and checks how many arguments were provided:

```java
public class Greeting {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("Hello, " + args[0] + "!");
            System.out.println("Total arguments: " + args.length);
        } else {
            System.out.println("No arguments were provided.");
        }
    }
}

```

### **To run this from the terminal:**

1. **Compile:** `javac Greeting.java`
2. **Run:** `java Greeting Musab`
* **Output:** > Hello, Musab!
> Total arguments: 1





---

## 3. Important Rules

1. **Everything is a String:** Even if you pass a number (e.g., `java MyProg 100`), it is stored as a String `"100"`. You must use [Type Casting](https://github.com/musab05/Java-Programming-Language/tree/main/1.8) or parsing methods like `Integer.parseInt(args[0])` to use it as a number.
2. **Space as a Delimiter:** Arguments are separated by spaces. If you want to pass a phrase as a single argument, wrap it in quotes: `java MyProg "New York"`.
3. **Zero-Indexed:** Like all Java arrays, the first argument is at index `0`.
4. **No Program Name:** Unlike C or C++, `args[0]` in Java is the **first parameter**, not the name of the program itself.

---

## 4. Why Use Them?

* **Configurations:** Passing file paths, database URLs, or API keys at runtime.
* **Modes:** Telling a program to run in "debug" mode vs. "production" mode (e.g., `java App --debug`).
* **Automation:** Useful for scripts where a program needs to process different files without human interaction during execution.

---

### Pro-Tip: Avoiding `ArrayIndexOutOfBoundsException`

Always check the length of the array before accessing specific indices. If you try to access `args[0]` when the user provided no arguments, your program will crash.

```java
if (args.length < 1) {
    System.out.println("Please provide at least one argument.");
    return; // Exit the program
}

```