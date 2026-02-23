import java.util.concurrent.*;
import java.util.Map;

public class WarehouseInventory {
    public static void main(String[] args) throws InterruptedException {
        // 1. ConcurrentHashMap handles thread-safe access to individual buckets
        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();
        inventory.put("Apples", 100);
        inventory.put("Oranges", 100);
        inventory.put("Bananas", 100);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("Initial Inventory: " + inventory);

        // 2. Submit 100 tasks to update inventory simultaneously
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                // Randomly pick an item to decrement
                String item = (System.nanoTime() % 2 == 0) ? "Apples" : "Oranges";
                
                // 3. 'merge' is an atomic operation: 
                // key, value to merge, and a remapping function (oldValue, newValue)
                inventory.merge(item, 1, (oldValue, one) -> oldValue - one);
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Final Inventory:   " + inventory);
        System.out.println("Total decrements should be 100.");
    }
}