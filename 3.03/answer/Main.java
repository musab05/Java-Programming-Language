// 1 & 2 & 3. The Stateful Custom Exception
class ItemOutOfStockException extends RuntimeException {
    private final String itemId;
    private final int availableQuantity;

    public ItemOutOfStockException(String message, String itemId, int availableQuantity) {
        super(message); // Passes the text message up to RuntimeException
        this.itemId = itemId;
        this.availableQuantity = availableQuantity;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}

// 4 & 5. The Business Logic
class ShoppingCart {
    public void addItem(String itemId, int requestedQuantity) {
        // Hardcoded inventory check
        int stockForItem99 = 2;

        if (itemId.equals("ITEM-99") && requestedQuantity > stockForItem99) {
            // Throwing the exception and packing it with useful state!
            throw new ItemOutOfStockException(
                "Not enough stock for item.", 
                itemId, 
                stockForItem99
            );
        }
        
        System.out.println("Successfully added " + requestedQuantity + " of " + itemId + " to cart.");
    }
}

// 6. The Execution
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        try {
            System.out.println("Attempting to add 5 units of ITEM-99...");
            cart.addItem("ITEM-99", 5);
        } catch (ItemOutOfStockException e) {
            // Because we added custom fields, we can pull exact data out of the error!
            System.out.println("Cart Error: Sorry, you wanted 5 but we only have " 
                               + e.getAvailableQuantity() + " of " 
                               + e.getItemId() + " left.");
        }
    }
}