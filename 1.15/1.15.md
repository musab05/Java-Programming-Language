In Java, comments are non-executable statements used to explain code logic, make the program more readable, and generate external documentation. The Java compiler completely ignores these lines during the **Compilation Phase**.

There are three distinct types of comments in Java:

---

## 1. Single-Line Comments

Used for brief explanations or to temporarily disable a line of code. They begin with two forward slashes (`//`).

```java
int speed = 100; // Set the initial speed to 100
// System.out.println("This won't print");

```

---

## 2. Multi-Line Comments

Used for longer descriptions or to comment out entire blocks of code. They start with `/*` and end with `*/`.

```java
/*
 * This block calculates the 
 * total area of a circle 
 * based on a given radius.
 */
double area = Math.PI * r * r;

```

---

## 3. Documentation Comments (Javadoc)

This is a special type of comment used to create **API documentation** in HTML format. They start with `/**` and end with `*/`. Java SDK provides a tool called `javadoc` that parses these comments to generate professional-looking documentation.

They often include **Tags** starting with `@` to provide metadata about the code.

### Common Javadoc Tags:

* `@param`: Describes a method parameter.
* `@return`: Describes what the method returns.
* `@author`: Specifies the developer's name.
* `@version`: Indicates the version of the software.
* `@throws`: Describes exceptions the method might throw.

**Example:**

```java
/**
 * Calculates the sum of two integers.
 * @param a The first number.
 * @param b The second number.
 * @return The sum of a and b.
 */
public int add(int a, int b) {
    return a + b;
}

```

---

## 4. Why Documentation Matters

In a professional environment—especially when you're building a platform like **Academiq**—clean comments serve several purposes:

1. **Maintenance:** Helps you (or your teammates) understand complex logic written months ago.
2. **Collaboration:** Essential when working in teams to define how a shared method should be used.
3. **API Generation:** By using Javadoc, you can automatically generate a manual for other developers using your classes without them ever seeing your source code.

---

## Summary Comparison

| Type | Syntax | Best Use Case |
| --- | --- | --- |
| **Single-Line** | `//` | Brief notes or disabling a single line. |
| **Multi-Line** | `/* ... */` | Large blocks of notes or disabling code blocks. |
| **Documentation** | `/** ... */` | Generating HTML documentation and describing APIs. |
