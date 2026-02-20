public class CartItem {
    // 1 & 2. Fields
    int productId;
    double price;

    // 3. Single Parameterized Constructor
    public CartItem(int productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    public static void main(String[] args) {
        // 5. Creating a valid CartItem
        CartItem validItem = new CartItem(9921, 45.50);
        System.out.println("Item added! ID: " + validItem.productId + " | Price: $" + validItem.price);

        /*
         * EXPLANATION FOR CRASH:
         * If we typed: CartItem invalidItem = new CartItem();
         * The compiler would throw an error. Because we explicitly wrote a 
         * parameterized constructor above, Java did NOT generate a hidden 
         * default no-arg constructor. Therefore, creating a CartItem 
         * without passing the two arguments is completely illegal.
         */
    }
}