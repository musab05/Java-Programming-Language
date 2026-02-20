import java.util.Objects;

class Product {
    int productID;
    String name;
    double price;

    public Product(int productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    // 2. Override equals for logical comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if same memory address
        if (obj == null || getClass() != obj.getClass()) return false; // Check type
        Product product = (Product) obj;
        return productID == product.productID; // Logical equality based on ID
    }

    // 3. Override hashCode to match equals
    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }
}

public class StoreApp {
    public static void main(String[] args) {
        Product p1 = new Product(101, "Laptop", 999.99);
        Product p2 = new Product(101, "Laptop", 999.99);

        System.out.println("Are they equal? " + p1.equals(p2)); // true
        System.out.println("P1 Hash: " + p1.hashCode());
        System.out.println("P2 Hash: " + p2.hashCode()); // Must be identical
    }
}