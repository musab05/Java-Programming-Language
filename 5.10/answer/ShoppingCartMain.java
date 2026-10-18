import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShoppingCartMain {

    // Immutable CartItem
    static final class CartItem {
        private final String productName;
        private final double price;

        CartItem(String productName, double price) {
            this.productName = productName;
            this.price = price;
        }

        public String getProductName() { return productName; }
        public double getPrice() { return price; }

        @Override
        public String toString() { return productName + " ($" + price + ")"; }
    }

    // Immutable ShoppingCart
    static final class ShoppingCart {
        private final List<CartItem> items;

        ShoppingCart() {
            this.items = List.of();
        }

        private ShoppingCart(List<CartItem> items) {
            this.items = List.copyOf(items); // Defensive copy
        }

        // Returns a NEW cart with the item added
        public ShoppingCart addItem(CartItem item) {
            List<CartItem> newItems = new ArrayList<>(items);
            newItems.add(item);
            return new ShoppingCart(newItems);
        }

        // Returns a NEW cart without the named item
        public ShoppingCart removeItem(String name) {
            List<CartItem> newItems = items.stream()
                .filter(i -> !i.getProductName().equals(name))
                .collect(Collectors.toList());
            return new ShoppingCart(newItems);
        }

        public double getTotal() {
            return items.stream().mapToDouble(CartItem::getPrice).sum();
        }

        public List<CartItem> getItems() { return items; } // Already unmodifiable

        @Override
        public String toString() {
            return "Cart" + items + " Total: $" + getTotal();
        }
    }

    public static void main(String[] args) {
        ShoppingCart cart1 = new ShoppingCart();
        ShoppingCart cart2 = cart1.addItem(new CartItem("Laptop", 999.99));
        ShoppingCart cart3 = cart2.addItem(new CartItem("Mouse", 29.99));
        ShoppingCart cart4 = cart3.removeItem("Laptop");

        // Each cart is independent — immutable!
        System.out.println("Cart 1: " + cart1); // Empty
        System.out.println("Cart 2: " + cart2); // Laptop
        System.out.println("Cart 3: " + cart3); // Laptop + Mouse
        System.out.println("Cart 4: " + cart4); // Mouse only
    }
}
