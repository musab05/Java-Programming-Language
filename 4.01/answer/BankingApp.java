class BankAccount {
    private int balance = 100;

    // The 'synchronized' keyword ensures atomicity: 
    // The check and the subtraction happen as one uninterrupted unit.
    public synchronized void withdraw(int amount) {
        String name = Thread.currentThread().getName();
        
        System.out.println(name + " is checking balance... (Current: $" + balance + ")");
        
        if (balance >= amount) {
            System.out.println(name + " is proceeding with withdrawal of $" + amount);
            balance -= amount;
            System.out.println(name + " finished. Remaining Balance: $" + balance);
        } else {
            System.out.println(name + " sorry, insufficient funds for $" + amount);
        }
        System.out.println("-----------------------------------");
    }
}

public class BankingApp {
    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount();

        // Two people trying to withdraw $75 from the same $100 account
        Runnable task = () -> sharedAccount.withdraw(75);

        Thread thread1 = new Thread(task, "Alice");
        Thread thread2 = new Thread(task, "Bob");

        thread1.start();
        thread2.start();
    }
}