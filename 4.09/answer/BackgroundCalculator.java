import java.util.concurrent.*;

public class BackgroundCalculator {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 1. Define a task that returns a result (Callable)
        Callable<Integer> mathTask = () -> {
            System.out.println("[Worker] Starting complex calculation...");
            Thread.sleep(2000); // Simulate a 2-second calculation
            
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };

        // 2. Submit the task and get a "claim ticket" (Future)
        System.out.println("[Main] Submitting task to executor.");
        Future<Integer> claimTicket = executor.submit(mathTask);

        try {
            // 3. Main thread continues doing other things
            System.out.println("[Main] Waiting for result... (I am not blocked yet)");
            
            // 4. Collect the result. THIS IS THE BLOCKING CALL.
            // Main thread will pause here until the worker finishes.
            Integer result = claimTicket.get(); 
            
            System.out.println("[Main] Calculation complete! The sum of 1-100 is: " + result);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error during calculation: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}