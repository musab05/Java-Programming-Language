## 🎤 Frequently Asked Interview Questions

### 1. Why is `Runnable` considered better for "Separation of Concerns"?

**Answer:** When you extend `Thread`, you are saying your class *is* a worker. When you implement `Runnable`, you are saying your class *is* a task. This separation allows you to manage tasks independently of the workers. For example, you can send the same `Runnable` task to a single thread, a thread pool, or even a distributed task queue without changing your business logic.

### 2. Can you start the same `Thread` object twice?

**Answer:** **No.** Once a thread has finished its execution (reaches the `TERMINATED` state), it cannot be restarted. If you call `.start()` a second time on the same `Thread` instance, Java will throw an `IllegalThreadStateException`. If you need to run the same logic again, you must create a new `Thread` instance.

### 3. What is the benefit of sharing a `Runnable` instance among multiple threads?

**Answer:** This is perfect for scenarios where multiple "workers" need to access a shared resource or a shared state (like a counter or a queue). Because the threads are distinct objects but they all reference the same `Runnable` object, they can easily interact with the same instance variables. (Note: This is also where you must be careful with **Synchronization**!)

### 4. If `Runnable` is so much better, why does the `Thread` class even exist for us to extend?

**Answer:** The `Thread` class provides utility methods for managing the thread's own state (like `interrupt()`, `isAlive()`, and `setPriority()`). In very rare, low-level system programming where you need to override the internal behavior of the thread itself—rather than just defining a task to run—extending `Thread` is appropriate. For 99% of business applications, `Runnable` is the way.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The One-Line Worker

**Task:** Using a Lambda expression, write a single line of code that creates and starts a thread that prints "Hello from the background!" to the console.
**Solution:**

```java
new Thread(() -> System.out.println("Hello from the background!")).start();

```

### Challenge 2: The Multiple Runner

**Task:** Look at the following code. Will this result in one thread running or two?

```java
MyTask task = new MyTask();
Thread t1 = new Thread(task);
Thread t2 = new Thread(task);
t1.start();
t2.start();

```

**Solution:** **Two threads.** While they share the same `task` (the `Runnable`), they are two distinct execution units (workers) in the eyes of the Operating System.

---

## 📝 Practice Problems

These problems help you practice the architectural benefits of `Runnable` and the convenience of modern Lambdas.

### Problem 1: The Reusable Counter (`Runnable` Sharing)

You want to see how two different workers can update a single shared piece of data.

**Requirements:**

1. Create a class `CounterTask` that implements `Runnable`.
2. Inside, define a `private int count = 0`.
3. In the `run()` method, use a loop to increment `count` 5 times, printing the name of the thread and the current count each time.
4. **The Goal:** In your `main` method, create **one** instance of `CounterTask`.
5. Create **two** different `Thread` objects, passing that same single task into both.
6. Start both threads. Notice how they both modify the same `count` variable!

### Problem 2: The Parallel Service (Lambda Architecture)

Imagine you have three services (Email, Database, and Logging) that all need to initialize at the same time to save time on startup.

**Requirements:**

1. In your `main` method, use three separate Lambda-based threads to simulate starting these services.
2. The "Email" thread should print "Email Service starting..." and sleep for 1000ms.
3. The "Database" thread should print "Database Service starting..." and sleep for 1500ms.
4. The "Logging" thread should print "Logging Service starting..." and sleep for 500ms.
5. **Challenge:** Use the `.join()` method to ensure the `main` thread waits until **all three** are finished before printing "System fully initialized."
