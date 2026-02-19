# Command Line Arguments in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems focused on how Java handles external parameters passed during execution via the `String[] args` array.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of runtime configuration and array handling.*

1. **What is the data type of the `args` parameter in the `main` method?**
* It is an **array of Strings** (`String[]`). Even if you pass numbers or boolean values from the terminal, the JVM captures them as String literals.


2. **How does the JVM distinguish between multiple arguments passed in the command line?**
* The JVM uses **spaces** as delimiters. Each word separated by a space is placed into a new index of the `args` array. To pass a single argument containing spaces, you must enclose it in double quotes (e.g., `"Musab Khan"`).


3. **If you run `java MyProgram 10 20`, what is the value of `args.length` and what is stored in `args[0]`?**
* `args.length` will be `2`. `args[0]` will store the string `"10"`.


4. **How does Java's `args[0]` differ from C or C++?**
* In C/C++, the first element of the argument array is usually the name of the program itself. In Java, `args[0]` is the **very first parameter** provided by the user after the class name.



---

## 💡 Concept Check Questions

*Use these questions to verify understanding of data conversion and error prevention.*

### 🗣️ Verbal/Conceptual Check

* **The Numeric Conversion:** "If I pass the argument `5` to my program and want to add it to another integer, can I just write `int result = args[0] + 10;`?" *(Answer: No. You must first parse the String into an integer using `Integer.parseInt(args[0])` because all command line arguments are Strings).*
* **The Index Guard:** "What happens if my code tries to print `args[0]` but I forget to type any arguments when I run the program?" *(Answer: The program will throw an `ArrayIndexOutOfBoundsException` and crash because the array is empty (length 0)).*

### 💻 Practical/Coding Check

* **The Quote Logic:** "How many arguments will the `args` array contain if I run: `java WeatherScanner "New York" London Tokyo`?"
* **Solution:** It will contain **3** arguments.
* `args[0]` = `"New York"` (treated as one because of quotes)
* `args[1]` = `"London"`
* `args[2]` = `"Tokyo"`





---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your ability to handle runtime input and data parsing.*

### Problem 1: The Secure Multiplier (Parsing & Length Check)

**Scenario:** You need a utility that takes two numbers from the command line and prints their product.
**Task:**

1. Create a class `Multiplier`.
2. In the `main` method, first check if `args.length` is exactly `2`. If not, print an error message and exit.
3. If valid, parse `args[0]` and `args[1]` into integers.
4. Calculate the product and print: `"Result: [product]"`.

### Problem 2: The System Mode Switcher (String Comparison)

**Scenario:** You are building a module for **Academiq**. The program should behave differently depending on whether it's started in "test" mode or "production" mode.
**Task:**

1. Create a class `AppConfig`.
2. Check if the first argument (`args[0]`) is provided.
3. If the argument is `"test"`, print `"Running in Test Mode: Debugging enabled."`.
4. If the argument is `"prod"`, print `"Running in Production Mode: Security protocols active."`.
5. If it's anything else or empty, print `"Default Mode: Limited access."`.
