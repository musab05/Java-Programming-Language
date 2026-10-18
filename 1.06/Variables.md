In Java, a **variable** is a container that holds data during the execution of a program. Unlike some languages you may have used, Java is **strongly typed**, meaning every variable must be declared with a specific data type before it can be used.

---

## 1. Variable Declaration and Initialization

To create a variable, you must specify the **type**, followed by the **name**, and optionally an **initial value**.

```java
// Syntax: type name = value;
int age = 25; 
String name = "Musab";
double salary = 55000.50;
boolean isStudent = true;

```

* **Declaration:** `int age;` (Telling the compiler: "I need a box for an integer named age").
* **Initialization:** `age = 25;` (Putting a value into that box).

---

## 2. Types of Variables

In Java, where a variable is declared determines its "scope" (where it can be accessed) and its lifetime.

### **Local Variables**

* Declared **inside a method** or a block (like a loop).
* They only exist while that method is running.
* **Must be initialized** before use, or the compiler will throw an error.

### **Instance Variables (Non-Static Fields)**

* Declared **inside a class** but outside any method.
* These belong to an "Object" of the class. Every time you create a new instance of a class, it gets its own copy of these variables.

### **Static Variables (Class Variables)**

* Declared with the `static` keyword inside a class.
* There is only **one copy** of this variable shared by all instances of the class.

---

## 3. Data Types: The Two Categories

Java divides data types into two main groups: **Primitive** and **Non-Primitive**.

### **Primitive Data Types**

These are predefined by the language and represent simple values. They are stored directly in the **Stack** memory.

| Category | Type | Size | Description |
| --- | --- | --- | --- |
| **Integer** | `byte`, `short`, `int`, `long` | 1 to 8 bytes | Whole numbers (e.g., 100, -50) |
| **Floating Point** | `float`, `double` | 4 to 8 bytes | Numbers with decimals (e.g., 19.99) |
| **Character** | `char` | 2 bytes | A single character/ASCII (e.g., 'A') |
| **Boolean** | `boolean` | 1 bit | `true` or `false` |

### **Non-Primitive (Reference) Data Types**

These refer to objects and are stored in the **Heap** memory.

* **Strings:** `String text = "Hello";`
* **Arrays:** `int[] numbers = {1, 2, 3};`
* **Classes/Interfaces:** Any custom object you create.

---

## 4. Naming Rules (Identifiers)

To keep your code clean and valid, follow these rules:

1. Names can contain letters, digits, underscores (`_`), and dollar signs (`$`).
2. Names **must begin** with a letter, `$`, or `_` (not a digit).
3. Java is **case-sensitive** (`myVar` is not the same as `myvar`).
4. You cannot use **Reserved Keywords** (like `class`, `static`, or `void`) as variable names.

> **Pro-Tip:** In Java, it is standard practice to use **lowerCamelCase** for variable names (e.g., `userAccountBalance`).
