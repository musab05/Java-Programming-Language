import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InventoryManager {
    // Programming to the interface (Map) rather than the implementation (HashMap)
    private Map<String, Integer> inventory = new HashMap<>();

    public void addStock(String product, int quantity) {
        // getOrDefault is a highly efficient way to handle items that might not exist yet
        int currentStock = inventory.getOrDefault(product, 0);
        inventory.put(product, currentStock + quantity);
        System.out.println("Added " + quantity + " " + product + "(s). Total: " + inventory.get(product));
    }

    public void sellProduct(String product, int quantity) {
        int currentStock = inventory.getOrDefault(product, 0);
        
        if (currentStock < quantity) {
            throw new IllegalArgumentException("Not enough stock for " + product + ". Only " + currentStock + " left.");
        }
        
        inventory.put(product, currentStock - quantity);
        System.out.println("Sold " + quantity + " " + product + "(s). Remaining: " + inventory.get(product));
    }

    public List<String> getOutOfStockItems() {
        List<String> outOfStock = new ArrayList<>();
        
        // Iterating over the Map's entrySet is the most efficient way to read both keys and values
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() == 0) {
                outOfStock.add(entry.getKey());
            }
        }
        return outOfStock;
    }
}

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        
        manager.addStock("Laptop", 5);
        manager.addStock("Mouse", 10);
        
        manager.sellProduct("Laptop", 5); // Drops to 0
        
        try {
            manager.sellProduct("Mouse", 20); // Throws exception
        } catch (IllegalArgumentException e) {
            System.out.println("Sale Failed: " + e.getMessage());
        }
        
        System.out.println("Out of stock items: " + manager.getOutOfStockItems());
    }
}