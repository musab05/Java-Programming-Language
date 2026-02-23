## 🎤 Frequently Asked Interview Questions

### 1. Why does the "Visibility Problem" even exist?

**Answer:** Speed. Accessing Main RAM is relatively slow compared to CPU operations. To optimize performance, each CPU core has its own local caches (L1, L2, L3). A thread will often copy a shared variable into its local cache to work on it faster. Without `volatile`, the CPU doesn't feel obligated to check if someone else changed the "real" value in the RAM, leading to threads living in "parallel universes" with different data.

### 2. Can you use `volatile` to synchronize a counter (e.g., `volatile int count`)?

**Answer:** **Absolutely not.** `volatile` only ensures that when you *read* the variable, you get the latest version, and when you *write* it, it goes to RAM. It does not stop two threads from reading the same value at the exact same time, adding one, and writing the same result back (the "Lost Update" problem). For counters, you need `synchronized` or `AtomicInteger`.

### 3. What is "Instruction Reordering" and how does `volatile` stop it?

**Answer:** To improve performance, the JVM and the CPU sometimes swap the order of instructions if they think the outcome won't change. However, in multithreaded code, this can be disastrous (like initializing an object *after* assigning its reference). `volatile` creates a "memory barrier," telling the compiler: "Do not move any instructions from before this write to after it, and vice versa."

### 4. If `synchronized` also provides visibility, why ever use `volatile`?

**Answer:** **Performance and Liveness.** `synchronized` is a "blocking" operation—it forces threads to wait in line, which is slow and can lead to "starvation." `volatile` is "non-blocking." It provides the same visibility guarantee as `synchronized` but without the performance cost of acquiring and releasing locks.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Infinite Loop

**Task:** In the following code, `Thread A` sets `keepRunning` to `false`. `Thread B` is running the loop. Without `volatile`, what is the most likely outcome on a multi-core machine?

```java
boolean keepRunning = true; 
// Thread B code:
while(keepRunning) { /* busy work */ }

```

**Solution:** `Thread B` will likely run **forever**. Because `keepRunning` isn't `volatile`, `Thread B` caches the value `true` in its CPU core and never checks the Main RAM again to see that `Thread A` changed it.

### Challenge 2: The Singleton Check

**Task:** Why is the `volatile` keyword necessary for the `instance` variable in a Double-Checked Locking Singleton?
**Solution:** To prevent **instruction reordering**. Without `volatile`, a thread might see a non-null `instance` and try to use it *before* the constructor has actually finished running, leading to a "half-baked" object crash.

---

## 📝 Practice Problems

### Problem 1: The Status Heartbeat

You are building a monitoring system where one thread (the **Sensor**) updates a temperature reading, and another thread (the **Display**) shows it.

**Requirements:**

1. Create a class `WeatherStation`.
2. Define a `volatile double temperature`.
3. Create a "Sensor" thread that updates the temperature to a random value every 500ms.
4. Create a "Display" thread that prints the current temperature every 200ms.
5. **Question:** Why is `volatile` sufficient here without `synchronized`? (Hint: Only one thread is writing).

### Problem 2: The Race for the Flag (`volatile` vs `Atomic`)

Let's prove `volatile` fails at math.

**Requirements:**

1. Create a `volatile int count = 0`.
2. Start 10 threads. Each thread should increment `count` 1,000 times using `count++`.
3. Use `thread.join()` to wait for all threads to finish.
4. Print the final `count`.
5. **The Goal:** Notice that the result is almost never 10,000.
6. **Task:** Change the code to use `AtomicInteger` and see it hit exactly 10,000 every time.
