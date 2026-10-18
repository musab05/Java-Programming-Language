Text Blocks bring readability and elegance to modern Java when working with embedded DSLs, SQL queries, JSON payloads, HTML templates, and complex regex patterns. Understanding whitespace stripping rules, escape sequences, and formatting methods ensures you avoid subtle indentation or formatting traps.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is a Text Block in Java, and in which version was it finalized?

**Answer:** A text block is a multi-line string literal enclosed by triple double quotes (`"""`). It avoids the need for common escape sequences (like `\"` and `\n`), automatically normalizes line terminators, and calculates/strips incidental indentation. It was finalized in Java 15.

### 2. Can you place content on the same line as the opening `"""`?

**Answer:** No. The opening delimiter `"""` must be followed by zero or more white space characters and an immediate **line terminator**. Placing content on the same line as the opening delimiter produces a compilation error.

### 3. What is the difference between "incidental whitespace" and "essential whitespace" in a text block?

**Answer:** 
- **Incidental whitespace** is the whitespace added simply to align the text block code with the indentation of the surrounding Java code. The compiler calculates the common minimum leading whitespace across all non-blank lines and strips it away.
- **Essential whitespace** is any indentation beyond that common baseline that is intended to be part of the string's actual content.

### 4. What do the `\` and `\s` escape sequences do in modern text blocks?

**Answer:**
- `\` (at the end of a line) is a **line continuation** escape sequence: it suppresses the insertion of a newline character, letting long lines be split across multiple source lines.
- `\s` preserves a single space character: because text blocks strip trailing whitespace by default, `\s` guarantees that trailing spaces (e.g. for Markdown or table padding) are preserved.

### 5. Are line terminators normalized in text blocks across different Operating Systems?

**Answer:** Yes! Whether the file was saved using Windows CRLF (`\r\n`) or Unix LF (`\n`), the Java compiler normalizes all line breaks in a text block to standard `\n` (LF) characters.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Clean JSON Formatting
**Task:** Convert this messy concatenated JSON string into a text block with interpolated variables using `.formatted()`:
```java
String json = "{\n  \"userId\": " + id + ",\n  \"email\": \"" + email + "\"\n}";
```

**Solution:**
```java
String json = """
        {
          "userId": %d,
          "email": "%s"
        }
        """.formatted(id, email);
```

### Challenge 2: Long SQL Query without Newlines
**Task:** Write a multi-line SQL statement using text blocks where the resulting string has no newline characters.

**Solution:**
```java
String query = """
        SELECT id, name, department \
        FROM employees \
        WHERE active = true \
        ORDER BY name ASC\
        """;
```

---

## 💻 Practice Problems

### Problem 1: Dynamic SQL and JSON Query Builder
Create a utility class `QueryBuilderMain` that formats complex SQL queries and JSON configuration bodies:
- Use text blocks with line continuation (`\`) and `\s` spacing.
- Dynamically format parameters using `String.formatted()`.
- Compare how `stripIndent()` and `indent(n)` transform strings.
- File: [QueryBuilderMain.java](file:///c:/Java%20Programming%20Language/5.14/answer/QueryBuilderMain.java)

### Problem 2: HTML Email Report Generator
Implement an email reporting engine that generates responsive HTML email summaries for sales figures:
- Use nested text blocks to assemble header, summary table, and footer.
- Format table rows dynamically based on list of transactions.
- File: [HtmlReportMain.java](file:///c:/Java%20Programming%20Language/5.14/answer/HtmlReportMain.java)
