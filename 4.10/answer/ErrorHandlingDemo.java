import java.util.concurrent.*;

public class ErrorHandlingDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 1. A task destined to fail
        Callable<String> failingTask = () -> {
            System.out.println("[Worker] Attempting to connect to database...");
            Thread.sleep(1000);
            throw new RuntimeException("Database Connection Failed!");
        };

        // 2. Submit the task
        Future<String> future = executor.submit(failingTask);

        try {
            System.out.println("[Main] Waiting for database response...");
            
            // 3. This will throw an ExecutionException
            String result = future.get(); 
            
        } catch (ExecutionException e) {
            // 4. Unwrap the real error using getCause()
            System.err.println("Caught background error: " + e.getCause().getMessage());
        } catch (InterruptedException e) {
            System.err.println("Main thread was interrupted!");
        } finally {
            executor.shutdown();
        }
    }
}