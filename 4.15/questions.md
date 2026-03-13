Welcome to the "engine room" of the JVM. Memory management in Java is often called a "managed" environment, but a better term would be "managed... until it isn't."

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between a `StackOverflowError` and an `OutOfMemoryError`?

**Answer:** It’s all about *where* the failure happens.

* **`StackOverflowError`**: Usually caused by deep recursion. You’ve filled up a thread's private Stack with too many method frames.
* **`OutOfMemoryError`**: Usually caused by creating too many objects or very large objects (like a 2GB byte array) that the Heap cannot accommodate, even after the Garbage Collector tries its best.

### 2. Why does the Heap have "Generations"?

**Answer:** Performance. If the GC had to scan every single object in a 100GB heap every time it ran, the "Stop-The-World" pause would last minutes. By separating "Young" objects from "Old" ones, the JVM can perform a "Minor GC" on the small Young Generation very quickly, ignoring the millions of stable objects in the Old Generation.

### 3. What is a "Stop-The-World" (STW) event?

**Answer:** It’s exactly what it sounds like. To move objects around and "compact" memory safely, the GC must pause all application threads. During an STW event, your app is effectively frozen. Modern collectors like **G1** or **ZGC** try to keep these pauses under 10 milliseconds.

### 4. Can you force the Garbage Collector to run?

**Answer:** You can *suggest* it by calling `System.gc()`, but the JVM is free to ignore you. In production code, calling `System.gc()` is considered a "code smell" because the JVM's internal heuristics are almost always smarter than a manual trigger.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Infinite Loop vs. Recursion

**Task:** Which error will these two snippets throw?

```java
// Snippet A
void test() { test(); }

// Snippet B
List<int[]> list = new ArrayList<>();
while(true) { list.add(new int[1_000_000]); }

```

**Solution:** Snippet A throws `StackOverflowError` (infinite recursion). Snippet B throws `OutOfMemoryError: Java heap space` (filling the heap with arrays).

### Challenge 2: The Static Leak

**Task:** Why is this a memory leak?

```java
public class Cache {
    public static List<byte[]> data = new ArrayList<>();
}

```

**Solution:** Because the list is `static`, it belongs to the Class itself, not an instance. It will **never** be garbage collected as long as the application is running. If you keep adding to it, you will eventually crash the JVM.

---

## 📝 Practice Problems

### Problem 1: Visualizing the Generations

Let's see if we can trigger a Minor GC.

**Requirements:**

1. Run a simple Java program with the JVM argument `-XX:+PrintGCDetails`.
2. In a loop, create 1,000,000 short-lived `String` objects (e.g., `String s = new String("val" + i);`).
3. Observe the console output.
4. **The Goal:** Identify when the "Eden Space" fills up and a "Minor GC" occurs.

### Problem 2: The "Ghost" Reference (WeakReferences)

Sometimes you want to reference an object *without* preventing the GC from deleting it.

**Requirements:**

1. Research the `java.lang.ref.WeakReference` class.
2. Create a large `byte[]` and wrap it in a `WeakReference`.
3. Set the original reference to `null`.
4. Call `System.gc()`.
5. Check if `weakRef.get()` returns `null`.
6. **The Goal:** Understand how caches use `WeakReferences` to prevent `OutOfMemoryErrors`.
