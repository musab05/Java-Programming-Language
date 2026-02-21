## 🎤 Frequently Asked Interview Questions

### 1. Does the `finally` block *always* execute? Are there any scenarios where it doesn't?

**Answer:** While `finally` is designed to execute almost universally, there are a few extreme cases where it won't:

* If you call `System.exit(0)` inside the `try` or `catch` block, the JVM shuts down immediately.
* If the JVM crashes (e.g., due to an `OutOfMemoryError` or a power failure).
* If an infinite loop occurs within the `try` or `catch` block before the `finally` block is reached.

### 2. Can you have a `try` block without a `catch` block?

**Answer:** Yes! A `try` block must be followed by *either* a `catch` block, a `finally` block, or both. You can absolutely write a `try-finally` block without catching anything, which is useful when you want to ensure resources are closed but want the exception to propagate up to the caller. (Alternatively, a Try-With-Resources block doesn't strictly require a `catch` or `finally`).

### 3. What happens if you catch an `Exception` before catching an `IOException`?

**Answer:** The code will not compile. Java enforces that you must catch the most **specific** exceptions first, followed by the more **general** ones. Since `IOException` is a subclass of `Exception`, catching `Exception` first makes the `IOException` block unreachable code, which the Java compiler flags as an error.

### 4. What is a Custom Exception, and how do you create one?

**Answer:** A custom exception is a class you create to represent a specific error domain in your application (e.g., `InsufficientFundsException`).

* To create a **Checked** exception, your class must extend `Exception`.
* To create an **Unchecked** exception, your class must extend `RuntimeException`.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The "Return" Override Trap

**Task:** What will the following method return?

```java
public int testMethod() {
    try {
        return 10;
    } finally {
        return 20;
    }
}

```

**Solution:** It returns `20`. The `finally` block always executes after the `try` block completes but *before* control is handed back to the caller. If `finally` contains a return statement, it overrides any return value from the `try` or `catch` blocks. (Note: Doing this is considered a bad practice!).

### Challenge 2: The Multi-Catch Hierarchy

**Task:** Identify the compilation error in this Java 7+ multi-catch block.

```java
try {
    // some risky file operations
} catch (FileNotFoundException | IOException e) {
    System.out.println("File error occurred");
}

```

**Solution:** You cannot combine exceptions in a multi-catch block if one is a subclass of the other. Because `FileNotFoundException` inherits from `IOException`, catching `IOException` already covers both. The compiler will throw an error.

---

## 📝 Practice Problems

Here are two practical problems. The first tests your ability to design custom exceptions, and the second tests your mastery of modern resource handling.

### Problem 1: The Bank Account Validator (Custom Exceptions)

Sometimes built-in exceptions don't describe the business logic error well enough.

**Requirements:**

1. Create a custom unchecked exception called `InsufficientFundsException` that extends `RuntimeException`. Give it a constructor that accepts a custom error message.
2. Create a `BankAccount` class with a `double balance`.
3. Create a method `public void withdraw(double amount)`.
4. Inside `withdraw`, if the `amount` is greater than the `balance`, **throw** your new `InsufficientFundsException` with the message "Attempted to withdraw [amount] but only have [balance]".
5. In your `Main` class, instantiate an account, give it $100, and try to withdraw $500 inside a `try-catch` block. Catch your custom exception and print its message.

### Problem 2: The Robust File Parser (Try-With-Resources)

This problem will force you to combine Java's modern `try-with-resources` syntax with a `try-catch` block.

**Requirements:**

1. Create a method `public void readFirstLineAsNumber(String filePath)`.
2. Use a **Try-With-Resources** block to instantiate a `BufferedReader` reading a `FileReader`.
3. Read the first line of the file and parse it into an `Integer` using `Integer.parseInt()`.
4. You need to handle two specific things that could go wrong:
* The file might not exist.
* The first line might be text instead of a valid number.


5. Catch these errors using a **Multi-Catch** block (or separate catch blocks if you prefer) and print appropriate error messages.
6. *Hint:* Because you are using Try-With-Resources, you do not need a `finally` block to close the reader!
