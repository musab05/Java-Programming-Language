import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class ParallelMinFinder {
    public static void main(String[] args) throws InterruptedException {
        // Initialize with the largest possible value
        AtomicInteger minFound = new AtomicInteger(Integer.MAX_VALUE);
        Thread[] threads = new Thread[10];
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    int nextNum = random.nextInt(10000);
                    
                    // accumulateAndGet takes the new value and a function
                    // It ensures that even if multiple threads find a new 'min',
                    // only the absolute smallest wins the race.
                    minFound.accumulateAndGet(nextNum, (currentMin, newVal) -> 
                        Math.min(currentMin, newVal)
                    );
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("The absolute minimum value found was: " + minFound.get());
    }
}