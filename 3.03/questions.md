## 🎤 Frequently Asked Interview Questions

### 1. Why create a custom exception when Java already has `IllegalArgumentException` or `IllegalStateException`?

**Answer:** While standard exceptions are great for generic programming errors, they lack business context. If you throw an `IllegalArgumentException` when a user enters a weak password, a generic catch block might intercept it without knowing *how* to recover. A custom `WeakPasswordException` clearly communicates the exact domain failure, allowing you to write a specific `catch` block that prompts the user to enter a stronger password.

### 2. Should my custom exception extend `Exception` (Checked) or `RuntimeException` (Unchecked)?

**Answer:** In modern Java development, the best practice is to extend `RuntimeException` (Unchecked). Checked exceptions force every calling method to declare `throws`, which tightly couples your code and creates massive boilerplate. Unchecked exceptions keep your method signatures clean while still crashing the process cleanly if the error isn't handled.

### 3. Can I add my own fields and methods to a custom exception?

**Answer:** Absolutely! Because an exception is just a normal Java class, you can add fields (like `errorCode`, `transactionId`, or `timestamp`), constructors to initialize them, and getter methods. This is highly recommended as it passes rich, structured context to the `catch` block for logging or user feedback.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Error Code Exception

**Task:** Define a custom unchecked exception called `DatabaseConnectionException`. Give it an `int errorCode` field, and a constructor that accepts both a `String message` and the `int errorCode`.

**Solution:**

```java
public class DatabaseConnectionException extends RuntimeException {
    private final int errorCode;

    public DatabaseConnectionException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

```

### Challenge 2: The Catch Order Trap

**Task:** Assume `UserNotFoundException` is a custom exception that extends `RuntimeException`. What is wrong with the following code?

```java
try {
    userService.findUser("john_doe");
} catch (RuntimeException e) {
    System.out.println("A general error occurred.");
} catch (UserNotFoundException e) {
    System.out.println("Please register first.");
}

```

**Solution:** It will not compile. Because `UserNotFoundException` inherits from `RuntimeException`, the first `catch` block acts as a blanket that catches *everything*. The second `catch` block becomes unreachable code. You must always catch the most specific exceptions first!

---

## 📝 Practice Problems

Here are two advanced problem statements. The first tests your ability to add **custom state** to an exception. The second tests your ability to build an **exception hierarchy** to handle complex business logic cleanly.

### Problem 1: The E-Commerce Cart (Stateful Exceptions)

Sometimes a simple text message isn't enough. The UI needs actual data from the exception to show the user exactly what to fix.

**Requirements:**

1. Create a custom unchecked exception called `ItemOutOfStockException`.
2. Add two `private final` fields to it: `String itemId` and `int availableQuantity`.
3. Create a constructor that takes the `message`, `itemId`, and `availableQuantity`, passing the message to `super()`. Create getters for your custom fields.
4. Create a `ShoppingCart` class with a method `public void addItem(String itemId, int requestedQuantity)`.
5. Hardcode a scenario where "ITEM-99" only has 2 units left. If the `requestedQuantity` is greater than 2, throw your custom exception, passing in the exact state.
6. In `Main`, call `addItem("ITEM-99", 5)` inside a `try-catch` block. Catch your custom exception and print a user-friendly message: *"Sorry, you wanted 5 but we only have 2 of ITEM-99 left."*

### Problem 2: The Authentication Gateway (Exception Hierarchies)

In large systems, you often create a base custom exception for a specific module, and then create subclasses for specific errors within that module.

**Requirements:**

1. Create a base unchecked exception `AuthenticationException` (extends `RuntimeException`).
2. Create two specific exceptions that extend `AuthenticationException`:
* `InvalidPasswordException`
* `AccountLockedException`


3. Create a `LoginService` class with a `public void login(String username, String password)` method.
4. Add the following hardcoded logic:
* If the username is "admin" and password is "wrong123", throw `InvalidPasswordException`.
* If the username is "hacker", throw `AccountLockedException`.
* Otherwise, print "Login successful".


5. In `Main`, write a `try` block that attempts to login.
6. Write three separate `catch` blocks: one for locked accounts (print "Contact support"), one for invalid passwords (print "Try again"), and a final fallback block for the base `AuthenticationException` (print "General auth failure").
