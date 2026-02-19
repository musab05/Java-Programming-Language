# Loops in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's iteration structures: `for`, `while`, `do-while`, and the enhanced `for-each` loop.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of iteration and loop choice.*

1. **What is the fundamental difference between a `while` loop and a `do-while` loop?**
* A `while` loop evaluates its condition *before* executing the code block, meaning it might run zero times. A `do-while` loop executes the code block first and checks the condition *after*, guaranteeing that the code runs at least once.


2. **Why was the enhanced `for-each` loop introduced, and when should you use it?**
* It was introduced to make iterating over arrays and collections cleaner and less error-prone. You should use it when you need to traverse an entire dataset from start to finish without needing to know or manipulate the exact index number.


3. **What happens if you omit the "update" (increment/decrement) statement in a standard `for` loop?**
* If the condition never becomes false because the variable isn't being updated, it will create an **infinite loop**, which will eventually freeze the program or crash it due to memory exhaustion.


4. **How do the `break` and `continue` keywords alter a loop's flow?**
* `break` completely terminates the loop and moves on to the rest of the program.
* `continue` simply skips the remaining code in the *current iteration* and jumps back to the top to evaluate the loop's condition for the next round.



---

## 💡 Concept Check Questions

*Use these questions to verify if the user truly understands loop mechanics and execution guarantees.*

### 🗣️ Verbal/Conceptual Check

* **The "At Least Once" Guarantee:** "If I am writing a program that prompts a user for a password, and I want to ensure the prompt appears at least one time before checking if the password is correct, which loop is the best choice?" *(Answer: The `do-while` loop).*
* **The Index Requirement:** "I need to loop through an array of 10 names, but I only want to print the names at the even indexes (0, 2, 4...). Should I use a standard `for` loop or a `for-each` loop?" *(Answer: A standard `for` loop. The `for-each` loop hides the index, making it difficult to skip specific index numbers).*

### 💻 Practical/Coding Check

* **Find the Infinite Loop:** "A developer wrote this code to count down from 5 to 1. What is the critical bug here?"
```java
int count = 5;
while (count > 0) {
    System.out.println(count);
    count++; 
}

```


* **Solution:** The update statement is `count++` (incrementing) instead of `count--` (decrementing). The count will go 6, 7, 8... and will always be greater than 0, resulting in an infinite loop.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your grasp of different loop structures and control keywords.*

### Problem 1: The Trading System Analyzer (`for-each` & `break`)

**Scenario:** You are building a rapid stock price analyzer for a high-frequency trading firm. You are given an array of today's closing prices. You need to iterate through the prices and trigger an alert if any price drops below $50.00, immediately stopping the loop to save processing time.
**Task:**

1. Declare a `double[]` array named `stockPrices` and initialize it with: `{105.50, 89.20, 75.00, 48.90, 55.00, 42.10}`.
2. Write an enhanced `for-each` loop to iterate through the array.
3. Inside the loop, use an `if` statement to check if the price is below `50.00`.
4. If it is, print "ALERT: Price dropped to [price]!" and use the correct keyword to completely exit the loop early.

### Problem 2: The Survival Game Resource Gatherer (`while` loop)

**Scenario:** You are programming the resource gathering mechanic for a survival crafting game. A player is chopping a tree, and they will continue to swing their axe *while* their inventory is not full.
**Task:**

1. Declare an `int` named `inventorySpace` and set it to `0`. The maximum capacity is `5`.
2. Write a `while` loop that continues to run as long as `inventorySpace` is less than `5`.
3. Inside the loop, print "Chopping wood...".
4. Increment the `inventorySpace` by 1.
5. After the loop finishes, print "Inventory full! Return to base."
