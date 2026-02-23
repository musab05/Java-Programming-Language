class Bakery {
    private boolean hasBread = false;

    public synchronized void bake() {
        // While there is already bread, the producer waits
        while (hasBread) {
            try { wait(); } catch (InterruptedException e) { }
        }
        
        hasBread = true;
        System.out.println("Baker: Fresh bread is ready!");
        notify(); // Wake up the consumer
    }

    public synchronized void eat() {
        // While there is no bread, the consumer waits
        while (!hasBread) {
            try { wait(); } catch (InterruptedException e) { }
        }
        
        hasBread = false;
        System.out.println("Customer: That was a delicious loaf!");
        notify(); // Wake up the baker
    }
}

public class BakerySim {
    public static void main(String[] args) {
        Bakery bakery = new Bakery();

        Thread baker = new Thread(() -> {
            for (int i = 0; i < 5; i++) bakery.bake();
        });

        Thread customer = new Thread(() -> {
            for (int i = 0; i < 5; i++) bakery.eat();
        });

        baker.start();
        customer.start();
    }
}