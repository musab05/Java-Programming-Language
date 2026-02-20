// 1 & 2. Define the class and fields
class TradeOrder {
    String tickerSymbol;
    int quantity;
    double pricePerShare;

    // 3. Define the calculation and output method
    void calculateTotal() {
        double totalCost = quantity * pricePerShare;
        System.out.println("Order: " + quantity + " shares of " + tickerSymbol + " at $" + pricePerShare + ". Total: $" + totalCost);
    }
}

public class BrokerageApp {
    public static void main(String[] args) {
        // 4. Create the object, populate data, and execute
        TradeOrder order = new TradeOrder();
        order.tickerSymbol = "AAPL";
        order.quantity = 15;
        order.pricePerShare = 200.50;
        
        order.calculateTotal();
    }
}