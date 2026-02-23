public class TimedSentry {
    public static void main(String[] args) throws InterruptedException {
        Thread sentry = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("\n[Sentry] Scanning perimeter (Iteration " + i + ")...");
                try {
                    // This puts the thread into TIMED_WAITING
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    System.out.println("Sentry interrupted!");
                }
            }
        });

        sentry.start();

        // Monitor the sentry from the main thread
        while (sentry.isAlive()) {
            Thread.State currentState = sentry.getState();
            System.out.println("Main: Monitoring Sentry... Current State: " + currentState);
            
            // Check frequently (every 300ms) to catch the state changes
            Thread.sleep(300); 
        }

        System.out.println("\nMain: Sentry duty finished. Final State: " + sentry.getState());
    }
}