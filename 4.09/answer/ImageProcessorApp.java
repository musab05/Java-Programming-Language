import java.util.concurrent.*;

public class ImageProcessorApp {
    public static void main(String[] args) {
        // 1. Create a pool limited to 4 concurrent threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("--- Image Processing Started ---");

        // 2. Submit 20 tasks
        for (int i = 1; i <= 20; i++) {
            int imageId = i;
            executor.submit(() -> {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Processing image " + imageId + " via " + threadName);
                    Thread.sleep(500); // Simulate heavy processing
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 3. Professional Shutdown Protocol
        executor.shutdown(); // Stop accepting new tasks
        try {
            // Wait up to 30 seconds for existing tasks to finish
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force kill if they hang
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("--- All images processed. System shutting down. ---");
    }
}