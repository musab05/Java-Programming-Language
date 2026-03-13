Think of the GC as the unsung hero of Java—it’s the reason you don't have to manually track every byte of memory, but it's also the reason your application might suddenly "freeze" for 200ms without warning.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between "Minor GC" and "Full GC"?

**Answer:** A **Minor GC** cleans only the Young Generation (Eden and Survivor spaces). It’s fast and happens often because most objects are short-lived. A **Full GC** cleans the entire Heap, including the Old Generation and Metaspace. Full GCs are expensive, cause longer "Stop-The-World" pauses, and if they happen too frequently, it's a sign of a memory leak or an undersized heap.

### 2. Can you explain the "G1" (Garbage First) collector?

**Answer:** Unlike older collectors that split the heap into three massive contiguous chunks, G1 carves the heap into hundreds of small **Regions**. It tracks how much "garbage" is in each region and cleans the ones that are most full first (hence "Garbage First"). This allows it to meet specific pause-time goals set by the developer.

### 3. What is a "Memory Leak" in a Garbage-Collected language?

**Answer:** In Java, a memory leak isn't "lost" memory; it's **"forgotten"** memory. It happens when an object is no longer needed by the application logic, but it is still **reachable** from a GC Root (like a static List or a thread-local variable). Since the GC can still find a path to the object, it refuses to delete it, and your heap slowly fills up until the app crashes.

### 4. What is the "Floating Garbage" problem?

**Answer:** This happens with concurrent collectors like G1 or ZGC. Because the collector runs *while* the application is still working, the application might create new trash *after* the GC has already finished its "Marking" phase. That trash survives until the *next* GC cycle.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The String Trap

**Task:** Why is the second version better for the Garbage Collector?

```java
// Version A
String log = "";
for(int i=0; i<1000; i++) { log += "error_" + i; }

// Version B
StringBuilder sb = new StringBuilder();
for(int i=0; i<1000; i++) { sb.append("error_").append(i); }

```

**Solution:** Version A creates **1,000 temporary String objects** (because Strings are immutable). This floods the Eden space and triggers frequent Minor GCs. Version B uses a single, resizable buffer, creating almost zero garbage.

### Challenge 2: The Static Collector

**Task:** Identify the memory leak here.

```java
public class UserSession {
    public static List<User> activeUsers = new ArrayList<>();
    
    public void login(User u) { activeUsers.add(u); }
    public void logout(User u) { /* logic here */ }
}

```

**Solution:** The `logout` method doesn't remove the user from the `static` list. Since `static` variables are GC Roots, those `User` objects will stay in memory forever, even after the user leaves the site.

---

## 📝 Practice Problems

### Problem 1: Triggering the "Stop-The-World"

Let's see if we can witness a GC pause by creating memory pressure.

**Requirements:**

1. Run a Java program with `-Xmx128m` (small heap) and `-XX:+PrintGCDetails`.
2. In a `while(true)` loop, add large `byte[]` arrays to a `List`.
3. **The Goal:** Watch the console logs. You will see `[GC (Allocation Failure) ...]` (Minor GC) and eventually `[Full GC ...]` as the Old Generation fills up before the inevitable `OutOfMemoryError`.

### Problem 2: The "ZGC" Experience (Low Latency)

If you are on Java 15+, you can try the most advanced collector.

**Requirements:**

1. Take the code from Problem 1.
2. Run it with `-XX:+UseZGC -Xmx128m`.
3. **Observation:** Compare the logs. Notice how ZGC tries to keep the "Stop-The-World" pauses significantly shorter (often invisible) compared to the default G1 collector.
