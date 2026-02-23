import java.util.concurrent.*;
import java.util.Random;

public class SystemBootstrapper {
    public static void main(String[] args) throws InterruptedException {
        // 1. We need 3 services to finish before we start
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Random random = new Random();

        String[] services = {"Database", "Cache", "MessageQueue"};

        for (String service : services) {
            executor.submit(() -> {
                try {
                    int initTime = random.nextInt(2000) + 500;
                    System.out.println("[INIT] " + service + " starting (will take " + initTime + "ms)...");
                    Thread.sleep(initTime);
                    System.out.println("[READY] " + service + " is online.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // 2. Decrement the count regardless of success/failure
                    latch.countDown();
                }
            });
        }

        System.out.println("Main: Waiting for all services to initialize...");
        
        // 3. Block here until the count hits 0
        latch.await();

        System.out.println("\nSUCCESS: All systems go! Application started.");
        
        executor.shutdown();
    }
}