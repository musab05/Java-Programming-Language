import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockPreventionDemo {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Random random = new Random();

    public static void doTask(String name) {
        while (true) {
            try {
                // Try to get the lock for 10 milliseconds
                if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(name + " acquired the lock and is working...");
                        Thread.sleep(100); // Simulate work
                        return; // Exit loop on success
                    } finally {
                        lock.unlock();
                    }
                } else {
                    // Collision occurred! Back off with random "jitter"
                    int backoff = 10 + random.nextInt(40);
                    System.out.println(name + " collided! Backing off for " + backoff + "ms...");
                    Thread.sleep(backoff);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> doTask("Thread-Alpha"));
        Thread t2 = new Thread(() -> doTask("Thread-Beta"));

        t1.start();
        t2.start();
    }
}