In Java, **Type Casting** is the process of converting a value from one data type to another. Because Java is strongly typed, you cannot always assign a value of one type to another without following specific rules.

There are two distinct types of casting in Java: **Widening** and **Narrowing**.

---

## 1. Widening Casting (Implicit)

Widening casting happens automatically when you pass a smaller type to a larger type size. Since there is no risk of losing data (the "container" is getting bigger), the JVM handles this for you.

* **Direction:** `byte` → `short` → `char` → `int` → `long` → `float` → `double`
* **Safety:** Completely safe; no data loss occurs.

**Example:**

```java
int myInt = 9;
double myDouble = myInt; // Automatic casting: int to double

System.out.println(myInt);    // Outputs 9
System.out.println(myDouble); // Outputs 9.0

```

---

## 2. Narrowing Casting (Explicit)

Narrowing casting must be done manually by placing the type in parentheses `()` in front of the value. This is necessary because you are moving from a larger type to a smaller type, which can result in **data loss** (truncation).

* **Direction:** `double` → `float` → `long` → `int` → `char` → `short` → `byte`
* **Safety:** Dangerous; decimal points will be chopped off, or numbers might "wrap around" if they exceed the smaller type's limit.

**Example:**

```java
double myDouble = 9.78d;
int myInt = (int) myDouble; // Manual casting: double to int

System.out.println(myDouble); // Outputs 9.78
System.out.println(myInt);    // Outputs 9 (the .78 is lost)

```

---

## 3. Comparison Summary

| Feature | Widening Casting | Narrowing Casting |
| --- | --- | --- |
| **Size Change** | Small to Large | Large to Small |
| **Process** | Automatic (Implicit) | Manual (Explicit) |
| **Data Loss** | None | Possible (Truncation) |
| **Complexity** | Low | High (requires developer care) |

---

## 4. Object Type Casting (Reference Types)

Just like primitives, objects can also be cast if they have an inheritance relationship (Parent and Child classes).

* **Upcasting:** Casting a child object to a parent type (Automatic).
* **Downcasting:** Casting a parent object back to a child type (Manual/Explicit).

> **Note:** If you try to downcast an object to a type it doesn't actually belong to, Java will throw a `ClassCastException` at runtime.

---

### Pro-Tip: The "Integer Division" Trap

A common mistake occurs when dividing two integers and expecting a decimal result:

```java
int a = 5;
int b = 2;
double result = a / b; // Result is 2.0 because (a/b) happens as integers first!

// To fix it, use casting:
double correctResult = (double) a / b; // Result is 2.5

```
