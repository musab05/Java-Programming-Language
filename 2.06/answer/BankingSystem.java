// 1. Parent Class with Encapsulated Data
class BankAccount {
    private double balance = 0.0;

    public double getBalance() {
        return balance;
    }

    // protected allows subclasses to use this method safely
    protected void addFunds(double amount) {
        balance += amount;
    }
}

// 2. Child Class
class PremiumAccount extends BankAccount {
    
    // 3. Behavior relying on inherited methods
    public void applyBonus() {
        System.out.println("Applying premium bonus...");
        
        // balance += 100.0; // ERROR: balance has private access in BankAccount
        
        addFunds(100.0); // CORRECT: Using the inherited protected method
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        // 4. Test the restricted flow
        PremiumAccount vip = new PremiumAccount();
        
        vip.applyBonus();
        System.out.println("Final Balance: $" + vip.getBalance());
    }
}