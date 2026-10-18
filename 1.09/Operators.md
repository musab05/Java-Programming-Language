In Java, **Operators** are special symbols used to perform operations on variables and values. Java classifies them into several groups based on the type of functionality they provide.

---

## 1. Arithmetic Operators

These are used to perform basic mathematical calculations.

| Operator | Name | Description | Example () |
| --- | --- | --- | --- |
| `+` | Addition | Adds two values |  |
| `-` | Subtraction | Subtracts one from another |  |
| `*` | Multiplication | Multiplies two values |  |
| `/` | Division | Divides one by another |  (Integer division) |
| `%` | Modulus | Returns the division remainder |  |
| `++` | Increment | Increases value by 1 | `++a` becomes 11 |
| `--` | Decrement | Decreases value by 1 | `--a` becomes 9 |

> **Note on Division:** If you divide two integers, the result is an integer (decimal part is discarded). To get a decimal, at least one operand must be a `float` or `double`.

---

## 2. Assignment Operators

These are used to assign values to variables. The most common is `=`, but there are "Compound" versions that combine math and assignment.

* **Simple:** `x = 10`
* **Addition assignment:** `x += 3` (Same as `x = x + 3`)
* **Subtraction assignment:** `x -= 3` (Same as `x = x - 3`)

---

## 3. Comparison (Relational) Operators

These compare two values and always return a **boolean** (`true` or `false`). They are the backbone of conditional logic.

| Operator | Name | Example () | Result |
| --- | --- | --- | --- |
| `==` | Equal to |  | `false` |
| `!=` | Not equal |  | `true` |
| `>` | Greater than |  | `true` |
| `<` | Less than |  | `false` |
| `>=` | Greater than or equal |  | `true` |
| `<=` | Less than or equal |  | `false` |

---

## 4. Logical Operators

Used to determine the logic between variables or values, typically to combine multiple comparisons.

* **`&&` (Logical AND):** Returns `true` if **both** statements are true.
* **`||` (Logical OR):** Returns `true` if **at least one** statement is true.
* **`!` (Logical NOT):** Reverses the result (returns `false` if the result is true).

---

## 5. Ternary Operator

Java provides a shorthand for `if-else` statements called the ternary operator. It consists of three operands.

**Syntax:** `variable = (condition) ? expressionTrue : expressionFalse;`

```java
int time = 20;
String result = (time < 18) ? "Good day." : "Good evening.";
System.out.println(result); // Outputs "Good evening."

```

---

## 6. Bitwise Operators (Advanced)

These perform operations at the bit level (0 and 1). While less common in standard web development, they are crucial for performance-heavy tasks like game development or encryption.

| Operator | Name | Description |
| --- | --- | --- |
| `&` | Bitwise AND | Sets bit to 1 if both bits are 1 |
| `\|` | Bitwise OR | Sets bit to 1 if either bit is 1 |
| `^` | Bitwise XOR | Sets bit to 1 if only one of the bits is 1 |
| `~` | Bitwise NOT | Inverts all bits |
| `<<` | Left Shift | Shifts bits to the left |
| `>>` | Right Shift | Shifts bits to the right |

---

### Operator Precedence

Just like in math (BODMAS/PEMDAS), Java has a specific order for evaluating operators. For example, multiplication `*` happens before addition `+`. When in doubt, always use **parentheses `()**` to make your intent clear.
