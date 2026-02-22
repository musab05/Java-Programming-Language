## 🎤 Frequently Asked Interview Questions

### 1. Can you pass a pre-existing array into a Varargs method?

**Answer:** Yes! Because a Varargs parameter (`int... numbers`) is literally just an array (`int[] numbers`) under the hood, you can pass an already instantiated array directly into it. The compiler is smart enough to see it's already an array and will skip the step of wrapping it in a new one.

### 2. If you have two methods: `print(int a)` and `print(int... a)`, which one is executed when you call `print(5)`?

**Answer:** The method with the exact match, `print(int a)`, is executed. Java's compiler follows a strict resolution order: it first looks for an exact match, then it looks for widening/autoboxing matches, and it *only* falls back to a Varargs method as a last resort.

### 3. What is "Heap Pollution" in the context of Varargs, and how do you prevent it?

**Answer:** Heap pollution occurs when a variable of a parameterized type refers to an object that is not of that parameterized type. Because Varargs are arrays, and arrays enforce their types at runtime, mixing them with Generics (which erase their types at compile time) creates a blind spot. The compiler warns you about this. If you are certain your method only reads from the array and doesn't try to put different generic types into it, you suppress the warning by annotating the method with `@SafeVarargs`.

### 4. What happens if you call a Varargs method with absolutely no arguments? Does it pass `null`?

**Answer:** No, it does not pass `null`. If you call `sum()`, the compiler creates an empty array (an array with a length of 0) and passes that. This means you don't need to write `if (numbers == null)` checks before looping through a Varargs parameter, though it's still good practice if a user explicitly passes `null`!

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Compiler's Dilemma

**Task:** Will this code compile? If it does, what does it print?

```java
public void log(String... messages) {
    System.out.println("Varargs method");
}

public void log(String msg1, String msg2) {
    System.out.println("Exact match method");
}

// The call:
log("Error", "Timeout");

```

**Solution:** Yes, it compiles perfectly. It will print `"Exact match method"`. The compiler always prefers a specific, fixed-arity (fixed number of arguments) method over a variable-arity method if both are valid matches.

### Challenge 2: The Signature Trap

**Task:** Identify the compilation error in this method signature.

```java
public void registerUsers(String... usernames, boolean isActive) {
    // Logic here
}

```

**Solution:** The Varargs parameter must be the **last** parameter in the method signature. The compiler reads arguments left to right, and if the variable list is first, it has no idea when to stop assigning strings to `usernames` and start looking for the `isActive` boolean. *(Corrected: `registerUsers(boolean isActive, String... usernames)`)*.

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to structure Varargs correctly alongside standard parameters. The second forces you to think about edge cases and array manipulation within a Varargs method.

### Problem 1: The Dynamic Logger

You are building a custom logging utility for your application. You want developers to be able to pass a specific log level, followed by any number of error messages.

**Requirements:**

1. Create a `Logger` class.
2. Write a method `public void logEvents(String logLevel, String... messages)`.
3. Inside the method, check if no messages were provided (check the array length!). If none were provided, print `"[" + logLevel + "] No messages to log."`
4. If messages exist, loop through them and print each one formatted like this: `"[" + logLevel + "] Message: " + message`.
5. In `Main`, test it by passing just a log level, and then test it again by passing a log level and three separate error strings.

### Problem 2: The Math Statistics Engine

You need to build a utility that finds the maximum value from an arbitrary set of numbers.

**Requirements:**

1. Create a `StatsEngine` class.
2. Write a method `public int findMax(int... numbers)`.
3. Handle the edge case: if the caller passes no numbers, throw an `IllegalArgumentException` with the message `"Cannot find max of an empty set."`
4. If numbers are provided, iterate through the Varargs array, find the largest integer, and return it.
5. In `Main`, call it with `(10, 50, 20, 99, 5)` and print the result. Then, test the exception by calling it with zero arguments.
