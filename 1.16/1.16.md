In Java, **Naming Conventions** are a set of rules and best practices that help developers write clean, readable, and consistent code. While the compiler only enforces a few strict rules (like not starting with a digit), following these industry standards is essential for professional development.

Java uses **CamelCase** as the primary styling method, where the first letter of each internal word is capitalized.

---

## 1. Classes and Interfaces

Classes and Interfaces should be **nouns** and follow **PascalCase** (UpperCamelCase).

* **Classes:** Start with an uppercase letter. If the name contains multiple words, every word starts with an uppercase letter.
* *Examples:* `Student`, `UserAccount`, `DataProcessor`.


* **Interfaces:** Similar to classes, but often describe a capability (adjectives).
* *Examples:* `Runnable`, `Serializable`, `Payable`.



---

## 2. Methods and Variables

Methods and Variables follow **lowerCamelCase**. They start with a lowercase letter, and subsequent words start with uppercase.

* **Methods:** Usually **verbs** that describe an action.
* *Examples:* `calculateTotal()`, `getName()`, `sendEmail()`.


* **Variables:** Should be descriptive and short.
* *Examples:* `totalAmount`, `userAge`, `isValid`.



---

## 3. Packages

Packages are used to prevent naming conflicts. They should be written in **all lowercase** letters. In professional projects, they follow a reverse domain name pattern.

* **Syntax:** `com.companyname.projectname.modulename`
* *Examples:* `java.util`, `com.academiq.security`, `org.apache.maven`.

---

## 4. Constants

Constants (variables marked with `static final`) should be in **ALL UPPERCASE**. If the name contains multiple words, they are separated by an **underscore** (`_`). This is known as **SCREAMING_SNAKE_CASE**.

* *Examples:* `MAX_VALUE`, `PI`, `DEFAULT_TIMEOUT`, `API_KEY`.

---

## 5. Summary Table

| Component | Convention | Example |
| --- | --- | --- |
| **Class** | PascalCase | `EmployeeDetails` |
| **Interface** | PascalCase | `Calculatable` |
| **Method** | lowerCamelCase | `getSalary()` |
| **Variable** | lowerCamelCase | `monthlyBonus` |
| **Package** | lowercase | `com.myproject.util` |
| **Constant** | UPPER_SNAKE_CASE | `MIN_AGE` |

---

## 6. General Rules for Identifiers

Regardless of the convention, all Java identifiers must follow these strict rules:

1. **No Spaces:** You cannot have spaces in a name.
2. **No Special Characters:** Only letters, digits, underscores (`_`), and dollar signs (`$`) are allowed.
3. **Cannot start with a digit:** `1stUser` is invalid; `user1` is valid.
4. **No Keywords:** You cannot use names like `class`, `void`, or `public`.

> **Pro-Tip:** Avoid using the dollar sign (`$`) or underscores in variable names unless you are writing constants or working with auto-generated code. Stick to alphanumeric characters for better readability.
