# Memory Management in Java: Assessment Guide

This guide provides targeted interview questions and conceptual checks focused on the architectural differences between Stack and Heap memory and the mechanics of Garbage Collection.

## 🎤 Frequent Interview Questions

_Common technical questions assessing a developer's understanding of JVM internals and memory efficiency._

1. **What is the primary difference between Stack and Heap storage?**

- **Stack** is used for static memory allocation and thread execution. It stores primitive local variables and references to objects. It is fast and follows LIFO.
- **Heap** is used for dynamic memory allocation. It stores all objects and instance variables. It is larger, shared across threads, and managed by the Garbage Collector.

2. **What causes a `StackOverflowError` versus an `OutOfMemoryError`?**

- A **`StackOverflowError`** occurs when the stack memory is full, typically due to deep or infinite recursion (too many method frames).
- An **`OutOfMemoryError`** occurs when the Heap is full, meaning the application has created too many objects that the Garbage Collector cannot remove because they are still being referenced.

3. **How does the Garbage Collector know when an object can be deleted?**

- The Garbage Collector looks for "unreachable" objects. If an object in the Heap no longer has any active references (pointers) in the Stack or other live objects, it is marked for deletion.

4. **Why is Stack memory access faster than Heap memory access?**

- Stack memory is linear and highly organized (LIFO), allowing for extremely fast allocation and deallocation. Heap memory requires complex lookup to find available space and pointers to navigate to the object's address.

---

## 💡 Concept Check Questions

_Use these questions to verify if the user understands how variables are mapped to memory at runtime._

### 🗣️ Verbal/Conceptual Check

- **The Reference Mapping:** "If I write `String name = new String("Musab");`, which part of that statement goes on the Stack and which part goes on the Heap?" _(Answer: The variable `name` is a reference stored on the **Stack**. The actual string data `"Musab"` is an object stored on the **Heap**)._
- **The Lifecycle Puzzle:** "When a method finishes executing, what happens to the objects that were created inside that method?" _(Answer: The references in the Stack are immediately cleared. The objects themselves remain on the Heap until the Garbage Collector determines they are no longer reachable and deletes them)._

### 💻 Practical/Logic Check

- **The Static Leak:** "If I have a `static List` in my **Academiq** app and I keep adding user data to it without ever clearing it, which part of memory will eventually fail?"
- **Solution:** The **Heap**. Because `static` variables live as long as the application runs, the references to those objects will never disappear. This prevents the Garbage Collector from cleaning up the objects, eventually leading to a `java.lang.OutOfMemoryError`.

---

## 🛠️ Memory Visualization Scenario

_Since this topic is more theoretical than syntax-based, let's visualize the memory state for the following code snippet._

```java
public void createStudent() {
    int id = 101;
    Student s = new Student();
}

```

**Memory Snapshot:**

1. **Stack:** Contains the integer `id` (value 101) and the variable `s` (a reference/pointer).
2. **Heap:** Contains the actual `Student` object data.
3. **Action:** Once `createStudent()` finishes, `id` and `s` are popped off the Stack. The `Student` object on the Heap is now "orphaned" (unreachable) and waiting for the Garbage Collector.
