In Java, data types specify the size and type of values that can be stored in an identifier. Java is a **statically-typed language**, meaning you must declare the type of a variable before you use it.

The language categorizes these types into two distinct groups: **Primitive** and **Non-Primitive (Reference)** types.

---

## 1. Primitive Data Types

Primitive types are the most basic data types built into the language. They serve as the building blocks for data manipulation and are stored directly in the **Stack memory**. There are 8 primitive types in Java:

| Category | Type | Size | Range / Value |
| --- | --- | --- | --- |
| **Integer** | `byte` | 1 byte | -128 to 127 |
|  | `short` | 2 bytes | -32,768 to 32,767 |
|  | **`int`** | 4 bytes | -2 billion to 2 billion (Standard for numbers) |
|  | `long` | 8 bytes | Very large numbers (Append an 'L') |
| **Floating Point** | `float` | 4 bytes | 6-7 decimal digits (Append an 'f') |
|  | **`double`** | 8 bytes | 15 decimal digits (Standard for decimals) |
| **Character** | `char` | 2 bytes | Single ASCII/Unicode character (e.g., 'A') |
| **Logical** | `boolean` | 1 bit | `true` or `false` |

---

## 2. Non-Primitive (Reference) Data Types

Non-primitive types are created by the programmer and are not predefined by Java (except for `String`). They are called **Reference types** because they do not store the actual value; instead, they store a **memory address (reference)** to the location where the object is stored in the **Heap memory**.

### Key Characteristics:

* **Nullable:** Unlike primitives, non-primitive types can be assigned a value of `null`.
* **Methods:** They can be used to call methods to perform certain operations.
* **Size:** They all have the same size (the size of a memory address/pointer).

### Common Examples:

* **Strings:** A sequence of characters. `String name = "Musab";`
* **Arrays:** Collections of data. `int[] scores = {90, 85, 88};`
* **Classes:** User-defined blueprints for objects.
* **Interfaces:** Contracts for what a class can do.

---

## 3. Key Differences

| Feature | Primitive Types | Non-Primitive Types |
| --- | --- | --- |
| **Definition** | Predefined in Java. | Defined by the programmer. |
| **Value Storage** | Stores the actual value. | Stores a reference/address. |
| **Memory** | Stored in the Stack. | Stored in the Heap. |
| **Default Value** | Always has a value (e.g., `0`, `false`). | Default is `null` if not initialized. |
| **Operations** | Cannot call methods on them. | Can call methods (e.g., `name.length()`). |

---

## 4. Why distinguish between them?

Memory efficiency is the primary reason. Primitives are "cheap" and fast because they are fixed in size and stored on the stack. Non-primitives are "flexible"—they allow for complex data structures like lists, maps, and custom objects, but they require more overhead because the JVM has to look up their location in the heap.
