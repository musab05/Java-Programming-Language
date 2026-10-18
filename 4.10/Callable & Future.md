Welcome to the next level of the Executors Framework. We’ve already seen how to run tasks in the background, but so far, our "workers" have been like couriers who deliver a package and then disappear. We have no idea if they succeeded or what they found.

In any real-world enterprise system—like an API that fetches a user's credit score or a service that calculates a complex tax report—you need the worker to **return a result**. This is where **`Callable`** and **`Future`** come into play.

---

## 1. The Limitation of `Runnable`

As we’ve seen, `Runnable` is simple:

* It has a `run()` method.
* It **cannot** return a result (it’s `void`).
* It **cannot** throw checked exceptions (you have to wrap everything in `try-catch`).

If you need a result, you’d have to use a hacky workaround like a shared variable, which introduces thread-safety headaches.

---

## 2. Enter `Callable<V>`

`Callable` is the upgraded version of `Runnable`. It is a functional interface that:

* Has a `call()` method.
* **Returns a value** of type `V`.
* **Can throw** checked exceptions.

```java
// A task that calculates something and returns a result
Callable<Integer> complexTask = () -> {
    Thread.sleep(2000); // Simulating heavy work
    return 42;          // The "Answer"
};

```

---

## 3. The "Claim Ticket": `Future<V>`

When you submit a `Callable` to an `ExecutorService`, it doesn't wait for the task to finish. Instead, it immediately hands you a **`Future`** object.

Think of a `Future` as a **claim ticket** at a dry cleaner. You give them your clothes (the task), they give you a ticket (the Future), and you can go do other things. When you're ready, you bring the ticket back to see if your clothes are ready.

### Core Methods of `Future`:

* **`.get()`**: This is the big one. It retrieves the result. If the task isn't finished yet, **this method blocks** (waits) until it is.
* **`.isDone()`**: Returns `true` if the task is finished.
* **`.cancel(boolean)`**: Attempts to stop the task if it hasn't finished.
* **`.get(long timeout, TimeUnit unit)`**: A safer version of `get()` that only waits for a specific amount of time.

---

## 4. Implementation Example: The "Price Fetcher"

Let's say we are building a travel site. We need to fetch a flight price in the background while the rest of the page loads.

```java
import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("Submitting flight price request...");
        
        // Task starts running in the background
        Future<Double> priceTicket = executor.submit(() -> {
            Thread.sleep(3000); // Simulating network latency
            return 850.50;
        });

        // We can do other things here!
        System.out.println("Processing UI animations...");

        // Now we need the result. This will block for 3 seconds.
        try {
            Double price = priceTicket.get(5, TimeUnit.SECONDS); // Safe wait
            System.out.println("Flight Price Found: $" + price);
        } catch (TimeoutException e) {
            System.err.println("Request timed out!");
        }

        executor.shutdown();
    }
}

```

---

## 5. Tip: Handling Exceptions

One of the most powerful features of `Future` is how it handles failures. If your `Callable` throws an exception, the worker thread doesn't just crash silently. The exception is **captured** and re-thrown when you call `.get()`, wrapped inside an `ExecutionException`.

```java
try {
    priceTicket.get();
} catch (ExecutionException e) {
    // This gets the REAL exception that happened inside the thread
    Throwable actualCause = e.getCause();
    System.out.println("Task failed due to: " + actualCause.getMessage());
}

```

---

## 6. The Evolution: `CompletableFuture`

While `Future` is great, it has a flaw: `.get()` is blocking. In Java 8, we got **`CompletableFuture`**, which allows us to chain tasks together (e.g., "Once the price is fetched, *then* apply a discount, *then* email the user"). It follows the "Promise" pattern found in JavaScript.

---

## Summary Table: Runnable vs. Callable

| Feature | `Runnable` | `Callable` |
| --- | --- | --- |
| **Method Name** | `run()` | `call()` |
| **Return Value** | `void` | `V` (Generic type) |
| **Exception Handling** | Cannot throw checked exceptions | Can throw checked exceptions |
| **Usage** | Fire-and-forget tasks | Tasks that return data |

### Pro-Tip for the Classroom:

If you have a list of tasks, use `executor.invokeAll(listOfCallables)`. This will run all of them in parallel and return a `List<Future<V>>`. This is the standard way to perform "Parallel Processing" in Java—like processing a batch of 1,000 bank transactions by splitting them across 10 threads.
