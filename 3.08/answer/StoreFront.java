import java.util.ArrayList;
import java.util.List;

class ProductCatalog {
    private List<String> catalog;

    public ProductCatalog() {
        // PRE-ALLOCATION: We tell the JVM to create an array of exactly 10,000 slots.
        // This prevents the ArrayList from having to resize itself as we load data!
        this.catalog = new ArrayList<>(10000);
    }

    public void loadProducts(String[] productData) {
        System.out.println("Loading " + productData.length + " products into the catalog...");
        for (String product : productData) {
            catalog.add(product);
        }
        System.out.println("Catalog loaded successfully.");
    }

    // O(1) operation: Instant mathematical lookup in memory
    public String getProductAt(int index) {
        if (index < 0 || index >= catalog.size()) {
            throw new IndexOutOfBoundsException("Invalid catalog index: " + index);
        }
        return catalog.get(index);
    }
}

public class StoreFront {
    public static void main(String[] args) {
        ProductCatalog myCatalog = new ProductCatalog();
        
        // Simulating the 10,000 products
        String[] dummyData = new String[10000];
        for (int i = 0; i < 10000; i++) {
            dummyData[i] = "Product_SKU_" + i;
        }
        
        myCatalog.loadProducts(dummyData);
        
        System.out.println("\n--- Customer Searches ---");
        // These lookups are practically instantaneous
        System.out.println("Item at index 500: " + myCatalog.getProductAt(500));
        System.out.println("Item at index 9999: " + myCatalog.getProductAt(9999));
        
        try {
            myCatalog.getProductAt(15000); // Throws exception
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }
}