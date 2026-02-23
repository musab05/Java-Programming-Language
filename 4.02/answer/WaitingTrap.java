import java.util.Scanner;

public class WaitingTrap {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Scanner scanner = new Scanner(System.in);

        Thread worker = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Worker: I'm entering the WAITING state now...");
                    lock.wait(); // Drops the lock and sleeps
                    System.out.println("Worker: I've been notified! Finishing up...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Initial State: " + worker.getState()); // NEW
        worker.start();

        // Give the worker a moment to hit the wait() call
        Thread.sleep(500); 
        System.out.println("Worker State after wait(): " + worker.getState()); // WAITING

        System.out.println("\n--- Press ENTER to wake up the worker ---");
        scanner.nextLine();

        synchronized (lock) {
            lock.notify(); // Sends the signal to the worker
        }

        worker.join(); // Wait for worker to finish
        System.out.println("Final State: " + worker.getState()); // TERMINATED
    }
}