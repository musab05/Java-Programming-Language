In our previous session on Memory Management, we looked at the "neighborhoods" of the JVM (the Stack and the Heap). Today, we’re looking at the **Garbage Collector (GC)**—the municipal waste management service that keeps the Heap from overflowing.

---

## 1. How the GC Knows What to Delete

The GC uses a concept called **Reachability**. It doesn't look for "trash"; it looks for "treasure." It starts at the **GC Roots** and follows every reference to find every object that is still in use.

### Common GC Roots:

* Local variables currently on the **Stack**.
* Active **Threads**.
* **Static** variables in loaded classes.
* JNI (Native) references.

Anything that cannot be reached from these roots is considered "unreachable" and is marked for deletion.

---

## 2. The Standard GC Process: Mark, Sweep, and Compact

Most Garbage Collectors follow this three-step dance:

1. **Mark:** The GC identifies which objects are still in use (reachable).
2. **Sweep:** The GC removes the unreachable objects, leaving holes in the memory.
3. **Compact:** Because those "holes" make it hard to allocate large new objects, the GC moves all remaining objects together to one end of the memory, creating a single large block of free space.

---

## 3. Generational Garbage Collection

As we discussed in the Memory Management lecture, the JVM uses a "Generational" approach because **most objects die young**.

* **Minor GC:** Happens in the **Young Generation** (Eden and Survivor spaces). It is frequent and usually very fast.
* **Major GC / Full GC:** Happens in the **Old Generation**. This cleans up objects that have lived a long time. These events are much slower and can cause noticeable "pauses" in your application.

---

## 4. Types of Garbage Collectors

In the real world, you choose your GC based on your application's needs. Here are the "Big Three" you'll encounter in modern Java:

| GC Name | Best For | Strategy |
| --- | --- | --- |
| **G1 (Garbage First)** | General purpose (Default since Java 9) | Splits the heap into regions and cleans the ones with the most "garbage" first. |
| **Parallel GC** | Batch processing (High throughput) | Uses multiple threads to perform GC but causes longer "Stop-The-World" pauses. |
| **ZGC / Shenandoah** | Real-time apps / Low latency | Performs almost all cleaning while the application is still running. Pauses are usually < 1ms! |

---

## 5. Tip: The "Stop-The-World" (STW) Event

Every GC event (except parts of ZGC) requires a "Stop-The-World" pause. During this time, **every single thread** in your application is frozen so the GC can safely move objects around in memory.

If you see your application "stuttering" every few seconds, it’s likely because you are creating too many short-lived objects, forcing the JVM into constant Minor GC cycles.

---

## 6. Tuning the GC (Common Flags)

If you need to optimize your memory, these are the flags you’ll provide to the JVM at startup:

* `-Xms` and `-Xmx`: Set the initial and maximum Heap size.
* `-XX:+UseG1GC`: Explicitly tells the JVM to use the G1 collector.
* `-XX:MaxGCPauseMillis=200`: Tells the G1 collector to try and keep pauses under 200ms (it’s a goal, not a guarantee!).

---

## Summary Checklist

1. **Minimize Object Creation:** Don't create objects inside high-frequency loops if you can avoid it.
2. **Nullify References:** If you have a large object in a long-running method, set it to `null` when you're done so the GC can claim it sooner.
3. **Use the Right Tool:** Use `StringBuilder` instead of constant String concatenation to avoid creating thousands of throw-away String objects.
