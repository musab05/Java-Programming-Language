public class TradingAnalyzer {
    public static void main(String[] args) {
        // 1. Declare and initialize the array of stock prices
        double[] stockPrices = {105.50, 89.20, 75.00, 48.90, 55.00, 42.10};
        
        // 2. Iterate using an enhanced for-each loop
        for (double price : stockPrices) {
            
            // 3. Check the condition
            if (price < 50.00) {
                System.out.println("ALERT: Price dropped to $" + price + "!");
                
                // 4. Immediately exit the entire loop
                break; 
            }
            
            // Optional: Show the prices being checked before the drop
            System.out.println("Checked price: $" + price + " - Stable.");
        }
        
        System.out.println("Analysis complete.");
    }
}