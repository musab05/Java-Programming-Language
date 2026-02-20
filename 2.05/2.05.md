In Java, **Encapsulation** is one of the four fundamental pillars of Object-Oriented Programming (OOP). It is the mechanism of wrapping the data (variables) and code acting on the data (methods) together as a single unit.

Think of it like a medical capsule: the active medicine (the data) is hidden and protected inside a secure outer shell (the methods).

---

## 1. How to Achieve Encapsulation in Java

To properly encapsulate a class, you must do two things:

1. Declare the variables of a class as **`private`**. This restricts direct access from outside the class (a concept known as **Data Hiding**).
2. Provide **`public` getter and setter methods** to view or modify the values of those private variables safely.

---

## 2. Code Example

Here is a classic example of an encapsulated `BankAccount` class. Notice how the internal balance cannot be changed directly by outside code; it must pass through the `deposit` or `setBalance` methods.

```java
public class BankAccount {
    // 1. Private data (hidden from the outside world)
    private double balance;
    private String ownerName;

    // 2. Public Getter for balance (Read-only access)
    public double getBalance() {
        return balance;
    }

    // 3. Public Setter with validation logic (Controlled access)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Getters and Setters for ownerName
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

```

If another class tries to do this: `myAccount.balance = -5000;`, the Java compiler will throw an error because `balance` is private. They *must* use `myAccount.deposit()`.

---

## 3. Why is Encapsulation Important?

| Benefit | Description |
| --- | --- |
| **Control Over Data** | You can add validation logic inside your setters (e.g., ensuring an age cannot be negative or a password meets security requirements). |
| **Data Hiding** | The internal workings of your class are hidden from other classes, preventing them from accidentally corrupting your object's state. |
| **Flexibility** | You can make a class **read-only** (by only providing getters) or **write-only** (by only providing setters). |
| **Maintainability** | You can change the internal implementation of the class later without breaking the code of anyone who is using your public methods. |
