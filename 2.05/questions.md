# Encapsulation in Java: Assessment Guide

This guide provides an exhaustive list of interview questions, conceptual checks, and targeted coding practice problems strictly focused on Java's Encapsulation, data hiding, and the getter/setter pattern.

## 🎤 Frequent Interview Questions

*Common technical questions assessing a developer's understanding of data security and OOP design principles.*

1. **What is Encapsulation and how do you achieve it in Java?**
* Encapsulation is the OOP principle of binding data (variables) and the methods that operate on that data into a single unit (a class). It is achieved by declaring the class variables as `private` and providing `public` getter and setter methods to access and update those variables safely.


2. **What is the difference between Encapsulation and Abstraction?**
* This is a classic trick question. **Encapsulation** is about *hiding and protecting* the internal state of an object from unauthorized access (Data Hiding). **Abstraction** is about *simplifying* the interface and hiding the complex implementation details from the user (showing only "what" it does, not "how" it does it).


3. **What is "Data Hiding" and is it the exact same thing as Encapsulation?**
* Data Hiding is a subset of Encapsulation. By making variables `private`, you hide the data from outside classes. Encapsulation is the broader concept that includes both hiding that data *and* grouping it with the public methods (getters/setters) used to manipulate it.


4. **How can you make a class completely "Read-Only" in Java?**
* You make the class read-only by declaring all variables as `private` and only providing `public` getter methods. If you omit the setter methods, outside classes can view the data but mathematically cannot alter it after the object is created (usually initialized via a constructor).


5. **Can you achieve true Encapsulation if your variables are marked as `public` or `protected`?**
* No. If variables are `public`, any outside class can access and modify them directly, bypassing any validation logic in your methods. If they are `protected`, subclasses and classes in the same package can still bypass the setters. True encapsulation requires `private` variables.



---

## 💡 Concept Check Questions

*Use these questions to verify if you truly understand the security benefits of the getter/setter pattern.*

### 🗣️ Verbal/Conceptual Check

* **The Negative Age Trap:** "I have a `Student` class with a `public int age;`. A developer writes `student.age = -15;`. How does Encapsulation prevent this exact scenario?" *(Answer: By making `age` private, the developer is forced to use `student.setAge(-15)`. Inside the `setAge` method, you can write an `if (age > 0)` check to block the invalid data from ever being saved).*
* **The Write-Only Scenario:** "Is it possible to have a variable that a user can update, but can never look at or read?" *(Answer: Yes. This is a 'Write-Only' property. You achieve it by making the variable `private`, providing a `public` setter method, but entirely omitting the getter method. This is useful for things like entering a secure password).*

### 💻 Practical/Coding Check

* **Identify the Security Flaw:** "This code technically compiles and runs. Why would a senior developer reject it in a code review for failing Encapsulation principles?"
```java
public class UserProfile {
    private String username;
    public String email;

    public void setEmail(String newEmail) {
        if (newEmail.contains("@")) {
            this.email = newEmail;
        }
    }
}

```


* **Solution:** The `email` variable is `public`. Even though the developer wrote a nice `setEmail` method with validation logic, another class can completely ignore it and write `profile.email = "not_an_email";`. The `email` variable must be `private` to force the use of the setter.



---

## 🛠️ Practice Problem Statements

*Write code to solve the following scenarios to build muscle memory for protecting object state and enforcing business rules.*

### Problem 1: The Read-Only Configuration

**Scenario:** You have a system configuration object holding a database URL. Once the object is created, the URL must be readable by the rest of the application, but it should be impossible to change it.
**Task:**

1. Create a class named `DatabaseConfig`.
2. Declare a strictly hidden `String dbUrl`.
3. Create a parameterized constructor to set the `dbUrl` when the object is first instantiated.
4. Create a method to allow outside classes to read the URL.
5. Ensure there is absolutely no way for an outside class to modify the URL after creation.

### Problem 2: The Smart Thermostat (Validation Logic)

**Scenario:** You are programming a smart home thermostat. The hardware will break if the target temperature is set below 50 degrees or above 90 degrees Fahrenheit.
**Task:**

1. Create a class named `Thermostat`.
2. Encapsulate an integer variable called `targetTemperature`.
3. Provide a getter method for the temperature.
4. Provide a setter method. Inside the setter, write logic so the temperature only updates if the provided value is between 50 and 90 (inclusive). If it is outside that range, print `"Error: Temperature out of safe bounds."` and leave the variable unchanged.
5. In your `main` method, instantiate a `Thermostat`, try to set it to 100, and print the temperature to prove it was blocked.

### Problem 3: The Secure Vault (Write-Only Data)

**Scenario:** You are building a digital vault. Users can set a PIN code, but for security reasons, there should be no method available in the code to print or return that PIN to the screen once it is set.
**Task:**

1. Create a class named `Vault`.
2. Encapsulate an integer variable named `secretPin`.
3. Create a public method to set the PIN.
4. Do **not** create a getter for the PIN.
5. Create a `public boolean checkPin(int attempt)` method that returns `true` if the `attempt` matches the `secretPin`, and `false` otherwise. (This proves the data works internally without ever exposing the raw data to the outside world).
