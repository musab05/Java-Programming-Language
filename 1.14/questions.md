# Input and Output (I/O) in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's console I/O mechanisms, specifically the differences between `Scanner` and `BufferedReader`.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of stream processing and performance trade-offs.*

1. **What are the primary differences between `Scanner` and `BufferedReader`?**
* `Scanner` (from `java.util`) has built-in parsing methods (like `nextInt()`), a smaller buffer (1 KB), is not thread-safe, and hides exceptions. It is generally slower but easier to use.
* `BufferedReader` (from `java.io`) only reads strings, has a larger buffer (8 KB), is thread-safe (synchronized), and forces the developer to handle `IOException`. It is much faster.


2. **If you are participating in a competitive programming contest, which I/O class should you use and why?**
* You should use `BufferedReader`. Because competitive programming often involves reading massive amounts of data from the console or text files very quickly, the larger buffer size and lack of heavy regex-parsing overhead make it significantly faster than `Scanner`.


3. **What is the difference between `print()`, `println()`, and `printf()`?**
* `print()` outputs the text and keeps the cursor on the same line.
* `println()` outputs the text and moves the cursor to a new line.
* `printf()` allows for formatted output (like rounding decimals or aligning columns) using specific format specifiers.


4. **Why do we need to manually parse data when using `BufferedReader`?**
* Because `BufferedReader` only reads text as a character stream (Strings). If you need to perform math on a user's input, you must manually convert that String into a primitive type using helper methods like `Integer.parseInt()`.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands exception handling rules and thread safety regarding I/O.*

### 🗣️ Verbal/Conceptual Check

* **The Thread-Safety Check:** "If you are building a complex, multi-threaded server application that reads continuous incoming data, which class is the safer choice to prevent data corruption?" *(Answer: `BufferedReader`, because it is synchronized and thread-safe, whereas `Scanner` is not).*
* **The Resource Leak:** "Why is it considered a best practice to call `sc.close()` at the end of a program when using the `Scanner` class?" *(Answer: To release the memory resources tied to the input stream. Leaving it open can cause resource leaks).*

### 💻 Practical/Coding Check

* **The Missing Exception Bug:** "A developer wrote this code to read a fast input, but the compiler is throwing a massive error. What rule of `BufferedReader` did they forget?"
```java
import java.io.*;

public class FastReader {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        System.out.println(input);
    }
}

```


* **Solution:** They forgot to handle the Checked Exception. `BufferedReader` requires you to either wrap the `.readLine()` call in a `try-catch` block for `IOException`, or add `throws IOException` to the `main` method signature.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of input collection and formatting.*

### Problem 1: The Profile Builder (`Scanner` & `printf`)

**Scenario:** You are creating a simple console profile builder for a new user.
**Task:**

1. Import and initialize a `Scanner` object.
2. Prompt the user to enter their username (String) and read it.
3. Prompt the user to enter their account balance (double) and read it.
4. Use `System.out.printf()` to print the final result formatted to exactly two decimal places. Example output: `"User: Musab | Balance: $55000.50"`.
5. Properly close the scanner.

### Problem 2: The Fast Number Doubler (`BufferedReader` & Parsing)

**Scenario:** You need a high-performance script that takes a number from the user, doubles it, and prints it back.
**Task:**

1. Import the necessary `java.io` classes.
2. Initialize a `BufferedReader` object.
3. Wrap your reading logic in a `try-catch` block to handle `IOException`.
4. Read the user's input as a String.
5. Manually parse that String into an `int`.
6. Multiply the integer by 2 and print the result using `println()`.
