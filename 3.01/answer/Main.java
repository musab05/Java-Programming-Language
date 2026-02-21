// 1. The Custom Exception
// Extending RuntimeException makes it unchecked. 
// Extending Exception would make it checked.
class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// 2 & 3. The Business Logic Class
class BankAccount {
    private double balance;

    public BankAccount(double startingBalance) {
        this.balance = startingBalance;
    }

    public void withdraw(double amount) {
        // 4. Actively throwing the exception if the rule is violated
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Attempted to withdraw $" + amount + " but only have $" + balance
            );
        }
        balance -= amount;
        System.out.println("Successfully withdrew $" + amount + ". Remaining balance: $" + balance);
    }
}

// 5. The Main Execution
public class Main {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(100.00);

        try {
            System.out.println("Attempting to withdraw $500...");
            myAccount.withdraw(500.00);
            
            // This line will NEVER print because the exception interrupts the flow
            System.out.println("Take your cash!"); 
            
        } catch (InsufficientFundsException e) {
            // Catching our specific custom exception
            System.out.println("Transaction Failed: " + e.getMessage());
        }
    }
}