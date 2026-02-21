## 🎤 Frequently Asked Interview Questions

### 1. Can you have a `try` block without a `catch` block?

**Answer:** Yes! A `try` block cannot stand completely alone, but it only requires _either_ a `catch` or a `finally` block (or both). A `try-finally` structure is perfectly valid and is often used when you want to ensure a resource is closed, but you want the actual exception to propagate up to the calling method.

### 2. What happens if an exception is thrown _inside_ the `catch` block itself?

**Answer:** If the `catch` block throws an exception, the remaining code in the `catch` block is skipped. However, the `finally` block **will still execute**. After the `finally` block finishes, the new exception from the `catch` block is propagated up the call stack.

### 3. If a `try` block has a `return` statement, does the `finally` block still execute? Which value is actually returned?

**Answer:** Yes, the `finally` block always executes. When the JVM hits the `return` statement in the `try` block, it "pauses" the return, jumps to the `finally` block, executes it, and then completes the return. **Crucially:** If the `finally` block _also_ has a `return` statement, it will override the `return` from the `try` block. (Note: Returning from a `finally` block is considered a bad practice for this exact reason).

### 4. Why must variables used in the `finally` block often be declared _outside_ the `try` block?

**Answer:** Because of variable scope. If you declare a `Scanner` or a `Connection` inside the `try` block, it ceases to exist the moment the `try` block ends. To access it in the `finally` block to close it, it must be declared before the `try` starts.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Return Override Trap

**Task:** What will the following method return?

```java
public int getNumber() {
    try {
        System.out.println("Inside try");
        return 1;
    } catch (Exception e) {
        return 2;
    } finally {
        System.out.println("Inside finally");
        return 3;
    }
}

```

**Solution:** It prints `"Inside try"`, then prints `"Inside finally"`, and returns `3`. The `return 3` in the `finally` block completely overrides the `return 1` from the `try` block.

### Challenge 2: The Unreachable Catch

**Task:** Why will this code fail to compile?

```java
try {
    int result = 10 / 0;
} catch (Exception e) {
    System.out.println("General error");
} catch (ArithmeticException e) {
    System.out.println("Math error");
}

```

**Solution:** `ArithmeticException` is a subclass of `Exception`. Because the broader `Exception` catch block comes first, it intercepts _everything_. The `ArithmeticException` block becomes unreachable code, which the Java compiler strictly forbids. Always catch the most specific exceptions first!

---

## 📝 Practice Problems

Here are two practical coding problems. The first tests your ability to handle variable scoping with legacy `finally` blocks. The second tasks you with building a resilient "retry" mechanism.

### Problem 1: The Legacy Resource Manager (Variable Scoping)

Before modern Java, handling resources safely required a very specific variable setup.

**Requirements:**

1. Create a class `LegacyDatabase`.
2. Write a method `public void connect()`. Inside, write code that randomly either prints "Connected!" or throws a new `RuntimeException("Connection timeout")`. _(Hint: use `Math.random() > 0.5`)_
3. Write a method `public void closeConnection()` that prints "Connection closed safely."
4. In your `Main` class, write a `try-catch-finally` block.
5. You must declare a `LegacyDatabase` variable, instantiate it, try to `connect()`, catch the potential exception (print the error), and **always** call `closeConnection()` in the `finally` block.
6. _The catch:_ You will need to handle the fact that if the database variable is declared inside the `try` block, the `finally` block won't be able to see it!

### Problem 2: The "Three Strikes" Input Validator

Sometimes, handling an exception means giving the user another chance to get it right.

**Requirements:**

1. Write a program that asks a user to enter their age.
2. Use a `Scanner` to read the input using `Integer.parseInt(scanner.nextLine())`.
3. Wrap this logic inside a `while` loop that gives the user a maximum of **3 attempts**.
4. If they type text instead of a number, it will throw a `NumberFormatException`.
5. Use a `try-catch` block inside the loop. If it succeeds, print "Age accepted: [age]", break the loop, and finish.
6. If it fails, catch the exception, print "Invalid input. You have [X] attempts left.", and let the loop run again.
7. Use a `finally` block outside the loop (or a Try-With-Resources) to ensure the `Scanner` is closed at the very end of the program.
