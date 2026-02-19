# Operators in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's operators, including arithmetic, logical, comparison, and the ternary operator.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of logic flow and mathematical operations in Java.*

1. **What is the fundamental difference between `=` and `==`?**
* `=` is an **Assignment Operator**. It is used to assign a value to a variable (e.g., `int x = 5;`).
* `==` is a **Comparison Operator**. It checks if two values are equal and returns a boolean `true` or `false` (e.g., `if (x == 5)`).


2. **Explain the Modulus (`%`) operator and give a common real-world use case.**
* The modulus operator divides two numbers and returns the **remainder**. A very common use case is determining if a number is even or odd (e.g., `number % 2 == 0` means it's even).


3. **What is the Ternary Operator, and why would you use it?**
* It is a shorthand, one-line replacement for a simple `if-else` statement. It takes three operands: a condition, a result if true, and a result if false. It is used to make code cleaner and more concise.


4. **How do Logical AND (`&&`) and Logical OR (`||`) differ when evaluating conditions?**
* `&&` requires **both** sides of the condition to be `true` for the whole statement to be true.
* `||` only requires **at least one** side of the condition to be `true` for the whole statement to be true.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands operator logic and precedence.*

### 🗣️ Verbal/Conceptual Check

* **The Logic Puzzle:** "Assume `boolean isWeekend = true;` and `boolean isRaining = false;`. What is the result of `(isWeekend || isRaining) && !isRaining`?" *(Answer: `true`. The OR statement evaluates to `true`, and NOT raining evaluates to `true`. `true && true` is `true`)*.
* **The Order of Operations:** "If I write `int result = 5 + 5 * 2;`, will the result be `20` or `15`? How can I force it to be `20`?" *(Answer: It will be `15` because multiplication has higher precedence. To force `20`, use parentheses: `(5 + 5) * 2`)*.
* **The Compound Question:** "What does the statement `score += 10;` actually mean under the hood?" *(Answer: It is a compound assignment operator that translates to `score = score + 10;`)*.

### 💻 Practical/Coding Check

* **The Accidental Assignment:** "A junior developer wrote this code to check a user's access level, but it's not working. What is the bug?"
```java
int accessLevel = 1;
// Checking if access level is 5 (Admin)
boolean isAdmin = (accessLevel = 5); 

```


* **Solution:** They used a single `=` (assignment) instead of `==` (comparison). The code is actually trying to change `accessLevel` to `5` instead of checking its value, which will cause a compilation error here since it's expecting a boolean result.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of logical combinations and the ternary operator.*

### Problem 1: The Even/Odd Team Assigner (Modulus & Ternary)

**Scenario:** You are writing logic for a multiplayer game. Players are assigned an integer ID when they join. If their ID is an even number, they go to the "Red" team. If it is odd, they go to the "Blue" team.
**Task:** 1. Declare an `int` variable named `playerId` and assign it any number.
2. Use the **modulus** operator and the **ternary** operator to evaluate the ID in a single line.
3. Store the result in a `String` named `team` and print it.

### Problem 2: The VIP Discount (Logical Operators)

**Scenario:** An e-commerce store gives a discount if a customer meets *specific* criteria: They must either be a registered VIP member OR they must be spending over $100. However, they CANNOT have a suspended account.
**Task:**

1. Declare three variables: `boolean isVip = true;`, `double cartTotal = 85.50;`, and `boolean isSuspended = false;`.
2. Write a single boolean expression using `&&`, `||`, and `!` to determine if they get the discount.
3. Store the result in a `boolean` variable named `getsDiscount` and print it. *(Hint: Use parentheses to group your OR logic before applying the AND logic!)*
