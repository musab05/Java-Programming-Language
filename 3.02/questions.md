## 🎤 Frequently Asked Interview Questions

### 1. If both Checked and Unchecked exceptions ultimately inherit from the `Throwable` class, how does the compiler tell them apart?

**Answer:** The compiler looks at the direct inheritance lineage. If the exception class inherits from `RuntimeException` (or `Error`), the compiler treats it as Unchecked and ignores it. If it inherits from `Exception` but *not* `RuntimeException`, the compiler flags it as Checked and enforces the "Catch or Specify" rule.

### 2. Why doesn't Java just force us to catch `NullPointerException` everywhere? Wouldn't that make applications safer?

**Answer:** No, it would actually make the code unreadable. A `NullPointerException` is a logical bug, not an unavoidable environmental failure. Theoretically, almost *any* line of object-oriented code could throw an NPE. If Java forced you to wrap every method call in a `try-catch` for NPEs, your codebase would be 90% error-handling boilerplate. The correct fix for an NPE is to fix the underlying logic (e.g., adding null checks), not to catch it.

### 3. What does it mean when we say modern frameworks "wrap" Checked exceptions?

**Answer:** Modern frameworks (like Spring or Hibernate) often catch bulky Checked exceptions (like `SQLException`) deep inside their own libraries, and then immediately throw a custom Unchecked exception (like `DataAccessException`). This allows the exception to bubble up and crash the process if needed, without forcing every single layer of the application to declare `throws SQLException`.

### 4. Is it a good idea to just use `throws Exception` on your `main` method to avoid dealing with Checked exceptions?

**Answer:** In a quick throwaway script or tutorial, it's fine. In production code, it is a terrible practice. It defeats the entire purpose of Checked exceptions, bypassing the compiler's safety net and passing the crash directly to the JVM, which shuts down the application.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Compiler's Veto

**Task:** Will this code compile? If not, why?

```java
import java.io.File;

public class SystemCheck {
    public void createLogFile() {
        File file = new File("logs.txt");
        file.createNewFile(); 
    }
}

```

**Solution:** It will **not** compile. The `createNewFile()` method declares `throws IOException`. Because `IOException` is a Checked exception, the compiler forces you to either wrap `file.createNewFile()` in a `try-catch` block or add `throws IOException` to the `createLogFile()` method signature.

### Challenge 2: The Silent Crasher

**Task:** Will this code compile? What happens when it runs?

```java
public class TextParser {
    public int parseAge(String ageText) {
        return Integer.parseInt(ageText);
    }

    public static void main(String[] args) {
        TextParser parser = new TextParser();
        parser.parseAge("Twenty");
    }
}

```

**Solution:** It **will** compile perfectly. `Integer.parseInt()` throws a `NumberFormatException` if the text isn't a number. Because `NumberFormatException` is an Unchecked exception (a subclass of `RuntimeException`), the compiler doesn't force you to handle it. However, when run, it will crash the program.

---

## 📝 Practice Problems

Here are two problems designed to test your architectural decision-making. One focuses on recovering from a Checked exception, and the other focuses on modernizing legacy code by converting Checked exceptions to Unchecked.

### Problem 1: The Config Loader (Recovering from Checked Exceptions)

When dealing with file systems, you must anticipate missing files and provide fallbacks.

**Requirements:**

1. Create a class `ConfigLoader`.
2. Write a method `public void loadConfig(String filePath)`.
3. Inside the method, attempt to instantiate a `FileReader` with the given `filePath`. (This throws a checked `FileNotFoundException`).
4. **Do not** use `throws` on the method signature. You must handle it here.
5. Wrap the code in a `try-catch` block.
6. If the `FileNotFoundException` is caught, print a warning and then simulate loading a default configuration by printing "Loading default configuration instead."

### Problem 2: The Modern API Wrapper (Wrapping to Unchecked)

Imagine you are using an old, legacy database library that throws a highly annoying Checked exception called `LegacyDatabaseException` (you will need to create this class first). You want to hide this from the rest of your modern application.

**Requirements:**

1. Create a Checked exception: `class LegacyDatabaseException extends Exception`.
2. Create an Unchecked exception: `class DatabaseFetchException extends RuntimeException`.
3. Create a class `LegacyRepository` with a method `public String fetchData() throws LegacyDatabaseException`. Inside it, just `throw new LegacyDatabaseException("Connection timeout");`.
4. Create a class `ModernService` with a method `public String getUserData()`. Notice there is no `throws` clause here!
5. Inside `getUserData()`, call `LegacyRepository.fetchData()`.
6. Catch the `LegacyDatabaseException`, and inside the catch block, **throw** your new `DatabaseFetchException`, passing the legacy exception's message into it.
7. In `Main`, call `getUserData()` and watch the Unchecked exception bubble up without forcing compiler checks!
