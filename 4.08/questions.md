## 🎤 Frequently Asked Interview Questions

### 1. Why is `ConcurrentHashMap` so much faster than a `Collections.synchronizedMap()`?

**Answer:** A synchronized map locks the **entire** object for every read and write. If Thread A is reading, Thread B must wait. `ConcurrentHashMap` uses **Lock Stripping**. It divides the map into segments (or buckets) and only locks the specific segment being updated. Multiple threads can read from the map simultaneously without any locking at all, and multiple threads can write to different segments at the same time.

### 2. What happens if you call `Future.get()` and the task isn't finished?

**Answer:** It is a **blocking call**. Your current thread will pause (move to the `WAITING` state) and sit idle until the background task completes and returns a value. If you don't want to wait forever, you should use the overloaded version: `future.get(5, TimeUnit.SECONDS)`.

### 3. What is "Compare-And-Swap" (CAS) in Atomic variables?

**Answer:** CAS is a low-level CPU instruction that enables "lock-free" concurrency. Instead of locking a variable, the thread reads the value, calculates the new value, and then tells the CPU: "Update this to the new value **only if** the current value is still what I read a microsecond ago." If it changed, the thread just retries in a tiny loop. This avoids the massive overhead of "parking" and "unparking" threads.

### 4. What is the difference between `CountDownLatch` and `CyclicBarrier`?

**Answer:** * **`CountDownLatch`** is a one-time use "starting gate." Once the count hits zero, it’s open forever. One thread usually waits for  other threads to finish.

* **`CyclicBarrier`** is reusable. It’s a "meeting point" where  threads wait for **each other**. Once all  arrive, the barrier trips, they all proceed, and the barrier resets for the next round.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Result Collector

**Task:** You have an `ExecutorService`. Use a `Callable` to calculate the sum of 10 + 20 in the background and print the result in `main`.
**Solution:**

```java
Future<Integer> future = executor.submit(() -> 10 + 20);
System.out.println("Result: " + future.get());

```

### Challenge 2: The Throttler

**Task:** You want to ensure that no more than 3 threads can access a specific "Download" method at the same time. Which synchronizer do you use?
**Solution:**

```java
// Use a Semaphore with 3 permits
Semaphore semaphore = new Semaphore(3);
// In the method: semaphore.acquire() ... try { download() } finally { semaphore.release() }

```

---

## 📝 Practice Problems

### Problem 1: The Multi-Service Bootstrapper (`CountDownLatch`)

Imagine your application needs to connect to a Database, a Cache, and a Messaging queue before it can start accepting traffic.

**Requirements:**

1. Create a `CountDownLatch` with a count of 3.
2. Start an `ExecutorService` with a fixed pool of 3.
3. Submit 3 tasks (Database, Cache, Messaging). Each should sleep for a random time (representing init) and then call `latch.countDown()`.
4. In the `main` thread, call `latch.await()`.
5. Print "All systems go! Application started." only after the latch hits zero.

### Problem 2: The Thread-Safe Inventory (`ConcurrentHashMap`)

Simulate a high-traffic warehouse where 10 threads are simultaneously updating the stock of various items.

**Requirements:**

1. Create a `ConcurrentHashMap<String, Integer>` representing inventory (e.g., "Apples" -> 100).
2. Create a thread pool and submit 100 tasks.
3. Each task should pick a random item and decrement its count safely.
4. **Hint:** Use the `.replace()` or `.compute()` methods of `ConcurrentHashMap` to ensure the update is thread-safe without using the `synchronized` keyword!
