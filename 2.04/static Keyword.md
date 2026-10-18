In Java, the **`static`** keyword is primarily used for memory management. When you apply `static` to a member (variable, method, block, or nested class), it means that the member belongs to the **Class itself**, rather than to any specific object (instance) of that class.

Because it belongs to the class, a static member is shared across all objects of that class.

---

## 1. Static Variables (Class Variables)

When a variable is declared as `static`, a single copy of that variable is created and shared among all objects at the class level.

* **Memory Efficiency:** It is stored in a special section of memory (formerly the Method Area, now part of the Metaspace/Heap) only once, when the class is first loaded.
* **Best Use Case:** Common properties shared by all objects, like a company name for employees or a counter tracking how many objects have been created.

```java
public class Student {
    String name;                  // Instance variable (unique to each object)
    static String college = "MIT"; // Static variable (shared by all objects)

    public Student(String name) {
        this.name = name;
    }
}

```

If you create 100 `Student` objects, there will be 100 different `name` variables in memory, but only **one** `college` variable. If one student changes `college` to "Harvard", it changes for all 100 students.

---

## 2. Static Methods

A method marked as `static` can be called directly using the Class name, without needing to create an object first using the `new` keyword.

* **Best Use Case:** Utility or helper functions that don't depend on object state (e.g., `Math.max(10, 20)`).

### Strict Rules for Static Methods:

1. They can only directly access **other static variables and methods**. They cannot access instance (non-static) variables directly because they don't belong to any specific object.
2. They cannot use the **`this`** or **`super`** keywords, as there is no object instance to point to.

```java
public class Calculator {
    // Static method
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        // Called directly via the class name, no object needed
        int sum = Calculator.add(5, 10); 
    }
}

```

> **Tie-back:** This is exactly why the `public static void main(String[] args)` method is static! The JVM needs to run this method to start the program before any objects even exist.

---

## 3. Static Blocks

A static block is a set of instructions used to initialize static variables.

* **Execution:** It is executed exactly **once**, the very moment the class is loaded into memory by the Class Loader, even before the `main` method or any constructors run.

```java
public class DatabaseConfig {
    static String dbUrl;

    // Static block
    static {
        System.out.println("Loading configuration...");
        dbUrl = "jdbc:mysql://localhost:3306/mydb";
    }

    public static void main(String[] args) {
        System.out.println("Main method executing.");
    }
}
// Output:
// Loading configuration...
// Main method executing.

```

---

## 4. Summary Comparison

| Feature | Static (Class Member) | Non-Static (Instance Member) |
| --- | --- | --- |
| **Belongs to** | The Class. | The individual Object. |
| **Memory Allocation** | Once, when the class is loaded. | Every time an object is created. |
| **How to Access** | `ClassName.memberName` | `objectName.memberName` |
| **Can access static data?** | Yes. | Yes. |
| **Can access instance data?** | **No** (unless it creates an object first). | Yes. |
