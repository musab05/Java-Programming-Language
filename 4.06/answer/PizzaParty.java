class Kitchen {
    private boolean pizzaReady = false;

    public synchronized void cookPizza() {
        while (pizzaReady) {
            try { wait(); } catch (InterruptedException e) {}
        }
        pizzaReady = true;
        System.out.println("\nChef: Pizza is out of the oven!");
        // Notify ALL customers; one will win the lock, others will re-check 'while'
        notifyAll(); 
    }

    public synchronized void eatPizza(String customerName) {
        while (!pizzaReady) {
            try {
                System.out.println(customerName + ": Waiting for pizza...");
                wait();
            } catch (InterruptedException e) {}
        }
        
        pizzaReady = false;
        System.out.println(customerName + ": NOM NOM NOM! I got the pizza!");
        notifyAll(); // Tell the chef the tray is empty
    }
}

public class PizzaParty {
    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();

        // 1 Chef
        Thread chef = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                kitchen.cookPizza();
            }
        });

        // 3 Hungry Customers
        Runnable customerAction = () -> kitchen.eatPizza(Thread.currentThread().getName());
        Thread c1 = new Thread(customerAction, "Alice");
        Thread c2 = new Thread(customerAction, "Bob");
        Thread c3 = new Thread(customerAction, "Charlie");

        c1.start(); c2.start(); c3.start();
        chef.start();
    }
}