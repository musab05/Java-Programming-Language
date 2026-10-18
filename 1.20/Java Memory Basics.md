In Java, memory management is an automated process handled by the JVM. Unlike lower-level languages where you must manually allocate and free memory, Java uses a system of **Automatic Memory Management** to handle these tasks for you.

To understand Java memory, you must distinguish between the two primary areas where data lives: the **Stack** and the **Heap**.

---

## 1. Stack Memory

Stack memory is used for the execution of a thread. It contains method-specific values that are short-lived.

* **What it stores:** Local variables (primitives like `int`, `double`) and references (memory addresses) to objects stored in the Heap.
* **How it works:** It follows the **LIFO (Last-In-First-Out)** principle. When a method is called, a new block (stack frame) is created. When the method finishes, that block is immediately cleared.
* **Visibility:** Scope is private to the thread.

---

## 2. Heap Memory

The Heap is a large pool of memory used for dynamic allocation. It is created when the JVM starts.

* **What it stores:** All objects (e.g., `String`, `Scanner`, or custom classes) and their instance variables.
* **How it works:** Objects stay here as long as they are being "referenced" by something in the Stack.
* **Visibility:** Shared across all threads in the application.

---

## 3. Comparison Table

| Feature | Stack Memory | Heap Memory |
| --- | --- | --- |
| **Application** | Method execution and local variables. | Object and JRE classes storage. |
| **Size** | Much smaller, fixed size. | Much larger, dynamic size. |
| **Access** | Very fast. | Slower (requires pointer lookup). |
| **Lifecycle** | Exists only during method execution. | Exists as long as the application runs. |
| **Error** | `StackOverflowError` (too many method calls). | `OutOfMemoryError` (heap is full). |

---

## 4. Garbage Collection (GC)

Since the Heap is shared and large, it needs a cleanup crew. This is the **Garbage Collector**.

When an object in the Heap no longer has a "reference" (pointer) pointing to it from the Stack, it becomes "unreachable." The Garbage Collector periodically identifies these unreachable objects and deletes them to free up space.

---

## 5. Summary of the Flow

1. When you declare `int x = 10;`, the value `10` is stored on the **Stack**.
2. When you declare `Student s = new Student();`:
* The `new Student()` part creates the actual object data on the **Heap**.
* The variable `s` is stored on the **Stack**, containing the **memory address** of the data on the Heap.



### Pro-Tip: Memory Leaks

Even though Java has a Garbage Collector, "Memory Leaks" can happen if you keep references to objects you no longer need (e.g., adding objects to a `static` List and never removing them). Because a reference still exists, the GC will never delete them, eventually filling the Heap.
