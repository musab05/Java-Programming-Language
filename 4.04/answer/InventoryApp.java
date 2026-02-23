class Warehouse {
    private int stock = 10;

    public void buy(int quantity) {
        String name = Thread.currentThread().getName();

        // Using a synchronized block for efficiency
        synchronized (this) {
            System.out.println(name + " is checking stock... (Current: " + stock + ")");
            
            if (stock >= quantity) {
                // Simulate processing time
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                stock -= quantity;
                System.out.println("SUCCESS: " + name + " bought " + quantity + 
                                   ". Remaining Stock: " + stock);
            } else {
                System.out.println("DENIED: " + name + " requested " + quantity + 
                                   ", but only " + stock + " left.");
            }
            System.out.println("-----------------------------------");
        }
    }
}

public class InventoryApp {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        // 5 customers trying to buy 3 consoles each (Total requested: 15, Total stock: 10)
        Runnable customerTask = () -> warehouse.buy(3);

        for (int i = 1; i <= 5; i++) {
            new Thread(customerTask, "Customer-" + i).start();
        }
    }
}