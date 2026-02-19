# Control Statements in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's decision-making and branching statements.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of control flow and execution paths.*

1. **What is the difference between an `else if` ladder and a `switch` statement?**
* Both are used to choose between multiple paths. However, an `else if` ladder evaluates boolean conditions (e.g., `x > 10`), while a `switch` statement checks a single variable for exact equality against multiple constant values. `switch` is generally preferred for readability when checking the same variable against many specific values.


2. **Which data types are allowed to be evaluated inside a `switch` statement?**
* Java allows `byte`, `short`, `char`, `int`, `String`, and `Enums`. (Notably, it does *not* allow floating-point numbers like `float` or `double`, nor `boolean`).


3. **What is "fall-through" in a `switch` statement?**
* If you do not include the `break` keyword at the end of a `case`, the JVM will continue executing the code in the subsequent cases, even if their conditions don't match, until it hits a `break` or the end of the `switch` block.


4. **Explain the difference between `break` and `continue`.**
* `break` completely terminates the loop or switch block it is inside, moving the execution to the code directly after the block.
* `continue` only skips the *current iteration* of a loop and immediately jumps back to the top to evaluate the next iteration.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands execution order and specific syntax rules.*

### 🗣️ Verbal/Conceptual Check

* **The Float Trap:** "Can I write a `switch` statement to check if a `double price = 19.99;` matches exactly `19.99` or `20.00`?" *(Answer: No. Java does not allow floating-point numbers in a `switch` statement because of precision issues. You must use `if-else` for this).*
* **The Independent Ifs:** "If I use three separate `if` statements instead of an `if`, `else if`, `else` structure, how does that change the execution?" *(Answer: In an `else if` ladder, the JVM stops checking as soon as it finds a true condition. With separate `if` statements, the JVM is forced to evaluate every single condition, which is less efficient and can cause multiple blocks to trigger if you aren't careful).*

### 💻 Practical/Coding Check

* **The Missing Break Bug:** "A developer wrote this code to print the name of the day. If `day = 2`, what will actually print to the console?"
```java
int day = 2;
switch (day) {
    case 1: System.out.println("Monday");
    case 2: System.out.println("Tuesday");
    case 3: System.out.println("Wednesday");
}

```


* **Solution:** It will print *both* `Tuesday` and `Wednesday`. Because there is no `break` statement after `case 2`, it "falls through" and executes `case 3` as well.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of decision-making structures.*

### Problem 1: The Ticket Pricing System (`if-else` ladder)

**Scenario:** A movie theater charges different prices based on age.

* Children (under 12): $8
* Seniors (65 and older): $10
* Everyone else (Standard): $15
**Task:**

1. Declare an `int` variable named `age` and set it to any valid age.
2. Write an `if-else if-else` structure to determine the ticket price based on the age.
3. Print the final ticket price to the console.

### Problem 2: The Role-Based Access Menu (`switch` statement)

**Scenario:** A system grants different dashboard access based on a user's exact role.
**Task:**

1. Declare a `String` variable named `userRole` and set it to `"ADMIN"`, `"EDITOR"`, or `"GUEST"`.
2. Write a `switch` statement that checks `userRole`.
* For `"ADMIN"`: Print "Full System Access Granted."
* For `"EDITOR"`: Print "Content Modification Access Granted."
* For `"GUEST"`: Print "Read-Only Access Granted."
* Provide a default case that prints "Access Denied: Unknown Role."


3. Make sure to include the proper branching keywords so the cases don't fall through!
