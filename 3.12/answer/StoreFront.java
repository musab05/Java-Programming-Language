import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1 & 2. Implementing Comparable and specifying the generic type <Product>
class Product implements Comparable<Product> {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    // 3. Overriding the natural sort order
    @Override
    public int compareTo(Product other) {
        // Safe comparison: returns -1 if this < other, 0 if equal, 1 if this > other
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class StoreFront {
    public static void main(String[] args) {
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("Laptop", 1200.00));
        catalog.add(new Product("Mouse", 25.50));
        catalog.add(new Product("Monitor", 300.00));

        System.out.println("Before Sorting: " + catalog);

        // 4. Collections.sort() automatically looks for the compareTo() method
        Collections.sort(catalog);

        System.out.println("After Natural Sort (By Price): " + catalog);
    }
}