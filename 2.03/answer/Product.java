public class Product {
    // 1 & 2. Fields
    String name;
    double price;
    int stock;

    // 3. Parameterized Constructor with exact same names
    public Product(String name, double price, int stock) {
        // 4. Resolving Variable Shadowing
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static void main(String[] args) {
        // 5. Instantiate and verify
        Product laptop = new Product("Gaming Laptop", 1200.50, 10);
        System.out.println("Product: " + laptop.name + " | Price: $" + laptop.price + " | Stock: " + laptop.stock);
    }
}