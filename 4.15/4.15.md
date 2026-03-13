In Java, we don't manually allocate and free memory (like in C++). Instead, we have the **Garbage Collector (GC)**. But don't let that fool you—understanding how the JVM manages memory is the difference between a high-performance system and one that crashes with an `OutOfMemoryError`.

---

## 1. The Runtime Data Areas

The JVM divides memory into two primary zones: the **Stack** and the **Heap**.

### A. The Stack (The "Now")

The Stack is used for **static memory allocation** and thread execution.

* Each thread has its own private Stack.
* It stores primitive variables and **references** to objects.
* It follows LIFO (Last-In-First-Out). When a method finishes, its "stack frame" is popped, and memory is cleared instantly.

### B. The Heap (The "Storage")

The Heap is used for **dynamic memory allocation**. This is where all your objects live (e.g., that `ArrayList` or `HashMap` you created).

* It is shared among all threads.
* It is much larger than the Stack.
* Memory here is not cleared automatically; that is the job of the **Garbage Collector**.

---

## 2. The Structure of the Heap (Generational Strategy)

The JVM organizes the Heap based on the observation that **most objects die young**. This is called the Generational Hypothesis.

1. **Young Generation:** Where all new objects are born.
* It’s divided into **Eden Space** and two **Survivor Spaces** (S0, S1).
* When it fills up, a "Minor GC" occurs.


2. **Old (Tenured) Generation:** Objects that survive multiple rounds of GC in the Young Generation are promoted here.
* This area is much larger and GC happens less frequently.


3. **Metaspace (Permanent Generation in older Java):** This stores class metadata, method data, and the interned string pool.

---

## 3. How Garbage Collection Works

The Garbage Collector follows a **"Mark and Sweep"** algorithm:

1. **Marking:** The GC traverses the "Object Graph" starting from the roots (Stack references, static variables). It marks every object it can reach as "Alive."
2. **Sweeping:** Any object that was *not* marked is considered "Unreachable" and its memory is reclaimed.
3. **Compacting (Optional):** To prevent fragmentation, the GC moves the remaining live objects together so there is a large, contiguous block of free memory.

> **Stop-The-World (STW):** Most GC events require all application threads to pause so the GC can safely move objects. Minimizing these pauses is the holy grail of JVM tuning.

---

## 4. Modern Garbage Collectors

Depending on your application, you can choose different GC "engines":

* **G1 (Garbage First):** The default since Java 9. It’s designed for large heaps and aims for predictable, short pause times.
* **ZGC / Shenandoah:** Ultra-low latency collectors. They perform almost all their work concurrently while the application is still running.

---

## 5. The "Memory Leak" in Java

Wait—if Java has a Garbage Collector, how can it have memory leaks?
In Java, a "leak" happens when you **accidentally keep a reference** to an object you no longer need. Because the reference exists on the Stack or in a Static variable, the GC thinks the object is "Alive" and will never delete it.

**Common Culprits:**

* **Static Collections:** Adding objects to a `static List` and never removing them.
* **Unclosed Resources:** Forgetting to close database connections or file streams.
* **Inner Classes:** Anonymous inner classes (like listeners) often hold a hidden reference to their parent class.

---

## 6. Tip: The `Finalize` Myth

In old textbooks, you might see the `finalize()` method. **Never use it.** It is deprecated, unpredictable, and can actually cause memory leaks and performance drops. If you need to clean up resources, always use the **Try-With-Resources** block we discussed in the I/O lecture.

---

## Summary: Memory Management Cheat Sheet

| Feature | Stack | Heap |
| --- | --- | --- |
| **Storage** | Primitives & References | Objects |
| **Visibility** | Private to the thread | Shared by all threads |
| **Size** | Small (fixed) | Large (configurable) |
| **Management** | Instant (LIFO) | Garbage Collector |
| **Error** | `StackOverflowError` | `OutOfMemoryError` |
