// 1. Parent Class
class BankAccount {
    // Restricted to this package and subclasses
    protected void calculateInterest() {
        System.out.println("Base interest calculated.");
    }
}

// 2. Child Class
class HighYieldAccount extends BankAccount {
    
    // 3 & 4. Overriding and expanding visibility to 'public'
    @Override
    public void calculateInterest() {
        System.out.println("High yield interest calculated!");
    }
}

public class BankingApp {
    public static void main(String[] args) {
        // 5. Instantiate and prove it works
        HighYieldAccount account = new HighYieldAccount();
        account.calculateInterest(); 
    }
}