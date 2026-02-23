import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionDemo {
    // This will FAIL to hit 10,000 because ++ is not atomic
    private static volatile int volatileCount = 0;
    
    // This will SUCCEED because it uses low-level CPU atomic instructions
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numberOfThreads];

        // Create 10 threads
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    volatileCount++;           // Not thread-safe!
                    atomicCount.incrementAndGet(); // Thread-safe!
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("--- Results ---");
        System.out.println("Expected Count: " + (numberOfThreads * incrementsPerThread));
        System.out.println("Volatile Result: " + volatileCount); // Likely < 10000
        System.out.println("Atomic Result:   " + atomicCount.get()); // Exactly 10000
    }
}