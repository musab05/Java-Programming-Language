# Lesson 5.14: Text Blocks

For decades, embedding multi-line snippets of code or data — such as **JSON, SQL, HTML, XML, or Markdown** — into Java was a painful exercise in string concatenation, explicit `\n` characters, and endless escaped quotes (`\"`).

Java 13 introduced and Java 15 finalized **Text Blocks**, a modern language feature that allows declaring multi-line string literals cleanly and naturally.

---

## 1. The Problem: The String Concatenation Nightmare

Suppose you need to send an HTTP JSON payload or execute an SQL query in traditional Java:

```java
// Traditional Java: Ugly, unreadable, error-prone
String json = "{\n" +
              "  \"id\": 101,\n" +
              "  \"name\": \"Alice\",\n" +
              "  \"role\": \"ADMIN\"\n" +
              "}";

String sql = "SELECT u.id, u.username, p.title " +
             "FROM users u " +
             "INNER JOIN profiles p ON u.id = p.user_id " +
             "WHERE u.status = 'ACTIVE' " +
             "ORDER BY u.created_at DESC;";
```

Every quote requires an escape backslash (`\"`), every newline requires `\n`, and every line requires string concatenation with `+`.

---

## 2. Text Block Syntax

A text block begins with **three double quotes** `"""` followed by a **line break**, and ends with three double quotes `"""`:

```java
// Modern Java: Clean Text Block
String json = """
        {
          "id": 101,
          "name": "Alice",
          "role": "ADMIN"
        }
        """;
```

### ⚠️ Syntax Rule: Delimiter Line Break
The opening `"""` **must be followed by a line break**. You cannot place content on the same line as the opening `"""`:

```java
// ❌ COMPILE ERROR: Illegal text block opening
String broken = """ { "name": "Alice" } """;

// ✅ Correct
String valid = """
        { "name": "Alice" }
        """;
```

---

## 3. Incidental vs. Essential Whitespace

One of the smartest design aspects of Java Text Blocks is how indentation is handled. The compiler separates:
- **Incidental whitespace:** Spaces used merely to align the code with the surrounding Java source code indentation.
- **Essential whitespace:** Indentation that belongs to the actual text content itself.

### How the Compiler Calculates Indentation
The compiler scans all non-empty lines and finds the **leftmost non-whitespace character** (or the position of the closing `"""`). Everything to the left of this baseline is discarded as incidental indentation:

```java
String html = """
..............<html>
..............    <body>
..............        <p>Hello, World!</p>
..............    </body>
..............</html>
..............""";
```
*(The dots represent discarded indentation).*

### Controlling Left-Margin with the Closing Delimiter
If the closing delimiter `"""` is moved to the left, it establishes a new baseline:

```java
String indented = """
        Line 1
        Line 2
    """; 
// The closing delimiter sets the baseline 4 spaces to the left!
// Result: Each line will have 4 leading spaces!
```

---

## 4. Double Quotes Inside Text Blocks

Inside a text block, **double quotes do not need to be escaped**:

```java
String quote = """
        He said, "Java is awesome!" without any escaping.
        """;
```

Only three consecutive double quotes require escaping: `\"\"\"`.

---

## 5. Modern Escape Sequences for Text Blocks

Java 15 added two special escape sequences specifically for text blocks:

### A. Line Continuation with `\` (Backslash)
If you have a very long line of text that you want to break across multiple lines in your source code without introducing newlines in the resulting string, end the line with `\`:

```java
String longQuery = """
        SELECT id, name, email, department, salary \
        FROM employees \
        WHERE department = 'Engineering' \
        ORDER BY salary DESC;
        """;
// 'longQuery' will be a single continuous line without \n!
```

### B. Preserving Trailing Whitespace with `\s`
By default, the Java compiler strips trailing spaces from every line in a text block. If you explicitly want trailing spaces (e.g. for fixed-width formatting or Markdown double-space line breaks), use `\s`:

```java
String fixedWidth = """
        Header   \s
        Row 1    \s
        Row 2    \s
        """;
```

---

## 6. Dynamic Formatting: The `.formatted()` Method

Java 15 introduced the `.formatted()` instance method on `String` as an elegant alternative to `String.format(...)`:

```java
String name = "Alice";
int age = 30;
String department = "Cloud Platform";

String template = """
        {
            "name": "%s",
            "age": %d,
            "department": "%s"
        }
        """.formatted(name, age, department);
```

---

## 7. Useful String Methods for Text

Java provided companion methods on `java.lang.String`:
- `str.stripIndent()`: Strips incidental indentation from a multiline string manually.
- `str.translateEscapes()`: Translates escape sequences (like `\n`, `\t`) in an unescaped raw string.
- `str.indent(int n)`: Adjusts the indentation of every line in the string by `n` spaces.

---

## 8. Summary Checklist

- Begin text blocks with `"""` followed immediately by a newline.
- No need to escape standard double quotes (`"`).
- Incidental indentation is automatically removed by the compiler.
- Use `\` at line ends for line continuation without newlines.
- Use `\s` to preserve essential trailing whitespace.
- Use `.formatted(...)` for clean template substitution.
