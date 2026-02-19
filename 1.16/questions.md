# Naming Conventions in Java: Assessment Guide

This guide provides targeted interview questions, conceptual checks, and coding practice problems strictly focused on Java's industry-standard naming conventions and identifier rules.

## 🎤 Frequent Interview Questions

*Common technical/code-review questions assessing a developer's understanding of professional coding standards.*

1. **What is the standard naming convention difference between a Class and a Method in Java?**
* **Classes** use **PascalCase** (UpperCamelCase), meaning every word starts with a capital letter (e.g., `UserAccount`). They should be nouns.
* **Methods** use **lowerCamelCase**, meaning the first word is lowercase and subsequent words are capitalized (e.g., `calculateTotal()`). They should typically be verbs.


2. **How should constants be named in Java?**
* Constants (variables declared as `static final`) should be written in **ALL UPPERCASE** letters. If there are multiple words, they must be separated by an underscore (`_`). This is known as SCREAMING_SNAKE_CASE (e.g., `MAX_VALUE`).


3. **What is the standard convention for naming packages in professional Java projects?**
* Packages should be written in **all lowercase** letters without spaces. Professionally, they follow a reverse domain name pattern to prevent conflicts (e.g., `com.companyname.projectname`).


4. **What are the strict compiler rules for naming any identifier in Java?**
* The name cannot contain spaces.
* It can only contain letters, digits, underscores (`_`), and dollar signs (`$`).
* It **cannot** start with a digit.
* It **cannot** be a reserved Java keyword (like `class` or `void`).



---

## 💡 Concept Check Questions

*Use these questions to verify if the user can distinguish between compiler rules (what is legal) and conventions (what is professional).*

### 🗣️ Verbal/Conceptual Check

* **The Visual Identification:** "If you are reading someone else's code and you see `CustomerData` and `customerData`, what Java components do those two names likely represent?" *(Answer: `CustomerData` is PascalCase, so it is almost certainly a Class or Interface. `customerData` is lowerCamelCase, so it is likely an instance variable or an object reference).*
* **The Legal vs. Best Practice Trap:** "Is `int $total_amount = 5;` legally allowed by the Java compiler? Does it follow standard industry best practices?" *(Answer: It is legally allowed because identifiers can contain `$` and `_`. However, it violates standard Java best practices, which dictate it should be `int totalAmount;` using lowerCamelCase without special characters).*

### 💻 Practical/Coding Check

* **The Code Reviewer:** "You are reviewing a junior developer's code. Identify the 4 naming convention violations and explain how to fix them."
```java
class user_profile {
    static final int maxRetries = 3;

    public void Save_Data(String 1stName) {
        // logic here
    }
}

```


* **Solution:**
1. `user_profile` should be PascalCase: `UserProfile`.
2. `maxRetries` is a constant and should be SCREAMING_SNAKE_CASE: `MAX_RETRIES`.
3. `Save_Data` is a method and should be lowerCamelCase: `saveData`.
4. `1stName` is an illegal identifier because it starts with a digit. The compiler will throw an error. It should be changed to something like `firstName`.





---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to test your ability to apply strict naming conventions.*

### Problem 1: The Refactoring Challenge

**Scenario:** You have been handed a messy piece of code written by someone who previously only coded in Python and C. You need to reformat all the names to match Java standards.
**Task:** Rewrite the following snippet so that every class, method, variable, and constant perfectly follows Java naming conventions.

*Messy Code:*

```java
class payment_processor {
    static final double processing_fee_percentage = 0.03;
    
    void Process_Payment(double PaymentAmount, boolean Is_Valid) {
        double final_total = PaymentAmount + (PaymentAmount * processing_fee_percentage);
    }
}

```

### Problem 2: Building from Scratch (Academiq Platform)

**Scenario:** You are starting a brand new module for the "Academiq" platform. You need to set up the skeleton of the file using the correct conventions.
**Task:**

1. Write a package declaration for the `security` module of the `academiq` project (assume a `.com` domain).
2. Create a public interface representing an action that can be authenticated. Name it appropriately (an adjective).
3. Inside the interface, define a constant integer for a standard timeout set to 5000 milliseconds. Name it appropriately.
4. Inside the interface, define a method signature for verifying a user token. Name it appropriately.
