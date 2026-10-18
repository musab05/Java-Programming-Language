In Java, handling input and output (I/O) is done through **Streams**. For reading user input from the console, the two most common tools are the **Scanner** class and the **BufferedReader** class.

---

## 1. Scanner Class

Part of the `java.util` package, `Scanner` is the most popular way to read input because it is easy to use and provides built-in methods for parsing primitive types (int, double, etc.).

### **Key Features:**

- **Ease of Use:** Has specific methods like `nextInt()`, `nextDouble()`, and `nextLine()`.
- **Tokenization:** It breaks input into tokens using whitespace as a default delimiter.
- **Performance:** Slower than BufferedReader because it performs heavy parsing.

**Example:**

```java
import java.util.Scanner; // Required for user input

public class Main {
    public static void main(String[] args) {
        // Initializing the Scanner object to read from System.in (the keyboard)
        Scanner sc = new Scanner(System.in);

        // Reading a Non-Primitive (Reference) Type: String
        System.out.print("Enter your name: ");
        String name = sc.nextLine(); // Stores reference in Stack, content in Heap

        // Reading a Primitive Type: int
        System.out.print("Enter age: ");
        int age = sc.nextInt(); // Stores 50 directly as 4 bytes in Stack

        // Displaying the gathered data
        System.out.println("\n--- User Details ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        // Best practice: Close the scanner to release memory resources
        sc.close();
    }
}
```

---

## 2. BufferedReader Class

Part of the `java.io` package, `BufferedReader` reads text from a character-input stream, buffering characters to provide efficient reading of characters, arrays, and lines.

### **Key Features:**

- **Speed:** Much faster than Scanner because it simply reads a large chunk of data into a buffer.
- **Synchronized:** It is thread-safe, meaning it can be used in multi-threaded environments.
- **String-only:** It only reads strings. You must manually parse data (e.g., using `Integer.parseInt()`).
- **Checked Exceptions:** Requires handling `IOException` (using try-catch or throws).

**Example:**

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
try {
    System.out.print("Enter input: ");
    String input = reader.readLine();
    int num = Integer.parseInt(input); // Manual parsing
} catch (IOException e) {
    e.printStackTrace();
}

```

---

## 3. Comparison: Scanner vs. BufferedReader

| Feature           | Scanner                      | BufferedReader                      |
| ----------------- | ---------------------------- | ----------------------------------- |
| **Package**       | `java.util`                  | `java.io`                           |
| **Parsing**       | Built-in (e.g., `nextInt()`) | Manual (e.g., `Integer.parseInt()`) |
| **Buffer Size**   | Small (1 KB)                 | Large (8 KB)                        |
| **Performance**   | Slower                       | Faster                              |
| **Exceptions**    | Hides exceptions             | Throws `IOException`                |
| **Thread-Safety** | Not Thread-safe              | Thread-safe                         |

---

## 4. Output: System.out

Java uses the `System.out` object (of type `PrintStream`) to print data to the console.

- **`print()`**: Prints text on the same line.
- **`println()`**: Prints text and moves to the next line.
- **`printf()`**: Formatted output (similar to C), useful for aligning columns or rounding decimals.

```java
System.out.printf("The value of PI is %.2f", 3.14159); // Outputs: 3.14

```

---

### Which should you use?

- For **competitive programming** or large data files: Use `BufferedReader`.
- For **general applications** or school projects: Use `Scanner`.
