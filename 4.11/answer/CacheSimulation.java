import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SimpleCache {
    private final Map<String, String> data = new HashMap<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public String get(String key) {
        rwLock.readLock().lock(); // Multiple threads can hold this simultaneously
        try {
            System.out.println(Thread.currentThread().getName() + " is READING " + key);
            Thread.sleep(100); // Simulate read delay
            return data.get(key);
        } catch (InterruptedException e) {
            return null;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void put(String key, String value) {
        rwLock.writeLock().lock(); // Only ONE thread can hold this; blocks all readers
        try {
            System.out.println("\n--- " + Thread.currentThread().getName() + " is WRITING " + key + " ---");
            Thread.sleep(500); // Writing usually takes longer
            data.put(key, value);
        } catch (InterruptedException e) {
            // Handle error
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}

public class CacheSimulation {
    public static void main(String[] args) {
        SimpleCache cache = new SimpleCache();
        cache.put("API_KEY", "ABC-123");

        // Start 5 Reader threads
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                while(true) { cache.get("API_KEY"); }
            }, "Reader-" + i).start();
        }

        // Start 1 Writer thread that updates every few seconds
        new Thread(() -> {
            while(true) {
                try { Thread.sleep(2000); } catch (InterruptedException e) {}
                cache.put("API_KEY", "UPDATED-" + System.currentTimeMillis());
            }
        }, "Writer-Admin").start();
    }
}