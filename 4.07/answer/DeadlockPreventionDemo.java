class Account {
    public int id;
    public double balance;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }
}

public class DeadlockPreventionDemo {
    public static void safeTransfer(Account from, Account to, double amount) {
        // Tie-breaking logic: Determine a consistent lock order
        Account firstLock = (from.id < to.id) ? from : to;
        Account secondLock = (from.id < to.id) ? to : from;

        // No matter which thread calls this, they ALWAYS lock lower ID first
        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.balance >= amount) {
                    from.balance -= amount;
                    to.balance += amount;
                    System.out.println("Transfer successful: " + amount);
                }
            }
        }
    }

    public static void main(String[] args) {
        Account accA = new Account(1, 1000);
        Account accB = new Account(2, 1000);

        // Thread 1: A -> B
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) safeTransfer(accA, accB, 1);
        });

        // Thread 2: B -> A (This would usually cause deadlock)
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) safeTransfer(accB, accA, 1);
        });

        t1.start();
        t2.start();
        
        System.out.println("Simulation finished without deadlock!");
    }
}