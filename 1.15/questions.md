# Comments in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on how Java handles internal documentation, code disabling, and API generation through comments.

## 🎤 Frequent Interview Questions

*Common technical/behavioral questions assessing a developer's understanding of code maintainability and documentation standards.*

1. **What exactly happens to comments during the Compilation Phase?**
* The Java compiler (`javac`) completely ignores all comments. They are stripped out and do not exist in the final `.class` (Bytecode) file, meaning they have zero impact on the execution speed or memory footprint of the running program.


2. **What is the structural difference between a Multi-Line comment and a Javadoc comment?**
* A standard multi-line comment begins with `/*` and ends with `*/`. It is strictly for internal notes or disabling code.
* A Javadoc comment begins with `/**` (two asterisks) and ends with `*/`. It is specifically parsed by the `javadoc` tool to generate external, professional HTML documentation.


3. **What are Javadoc tags, and can you name three common ones?**
* Tags are special keywords starting with `@` used inside Javadoc comments to provide specific metadata about the code structure. Common examples include `@param` (describes an input parameter), `@return` (describes the output), and `@author` (lists the creator).


4. **Why is it considered a bad practice to "over-comment" code with single-line comments?**
* Code should ideally be "self-documenting" through clear, descriptive variable and method names. Using comments to explain *what* simple code is doing (e.g., `int x = 5; // sets x to 5`) creates clutter. Comments are best used to explain the *why* behind complex business logic.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user understands the proper use cases for each comment type.*

### 🗣️ Verbal/Conceptual Check

* **The Inline Test:** "If I have a valid line of code like `double tax = 0.05;`, can I place a `//` comment on the exact same line, or does it need its own line?" *(Answer: Yes, you can place it at the end of the line. The compiler executes the code and ignores everything after the `//` on that specific line).*
* **The Disabling Trap:** "I have a block of 20 lines of code causing a bug. Should I put `//` in front of all 20 lines, or is there a better way?" *(Answer: While you could use `//` on every line, wrapping the entire 20-line block in `/*` and `*/` is much faster and cleaner for temporarily disabling chunks of code).*

### 💻 Practical/Coding Check

* **The Nested Comment Error:** "A developer tried to comment out a section of code that already contained a multi-line comment. Will this compile? Why or why not?"
```java
/*
  System.out.println("Start");
  /* System.out.println("Middle"); */
  System.out.println("End");
*/

```


* **Solution:** It will not compile. You cannot nest `/* ... */` comments in Java. The compiler sees the first `/*`, and then considers the comment finished as soon as it hits the *first* `*/` (after "Middle"). This leaves the bottom `System.out.println("End");` and the final `*/` exposed as illegal syntax.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of commenting conventions and Javadoc structure.*

### Problem 1: The API Documenter (Javadoc)

**Scenario:** You have written a complex utility method for a banking application, and the QA team needs an HTML manual generated for it.
**Task:**

1. Create a class named `BankUtils`.
2. Write a `public static double calculateInterest(double principal, double rate, int years)` method that returns `principal * rate * years`.
3. Add a proper **Javadoc block** directly above the method.
4. Include a brief description of what the method does.
5. Include three `@param` tags (one for each parameter) with descriptions.
6. Include one `@return` tag describing the final calculated value.

### Problem 2: The Bug Squasher (Disabling Code)

**Scenario:** A junior developer committed some broken code. You need to quickly comment out the broken lines so the program can compile, while leaving a note for them to fix it later.
**Task:**

1. Copy the following code into your IDE/editor:
```java
public class App {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int result = a + b;
        System.out.println(result);
        System.out.println(brokenVariable);
        calculateNothing();
    }
}

```


2. Use a **Single-Line comment** to leave a note saying `"TODO: Fix the broken variables below"`.
3. Use a **Multi-Line comment** to completely disable the two broken lines of code (`System.out.println(brokenVariable);` and `calculateNothing();`) so the file compiles and prints `30`.
