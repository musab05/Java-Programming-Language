public class TradeValidator {

    // 3. Static utility method (no instance variables needed)
    public static boolean isValidTrade(double amount) {
        if (amount > 0 && amount < 1000000) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // 4. Calling the method directly using the Class name
        boolean trade1 = TradeValidator.isValidTrade(500.50);
        boolean trade2 = TradeValidator.isValidTrade(-150.00);

        System.out.println("Is $500.50 a valid trade? " + trade1);
        System.out.println("Is -$150.00 a valid trade? " + trade2);
    }
}