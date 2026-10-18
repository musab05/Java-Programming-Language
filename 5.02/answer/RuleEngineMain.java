import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RuleEngineMain {

    static class Product {
        String name;
        double price;
        String category;

        Product(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ", " + category + ")";
        }
    }

    @SafeVarargs
    public static List<Product> applyRules(List<Product> products, Predicate<Product>... rules) {
        // Combine all predicates into one using .and()
        Predicate<Product> combined = p -> true; // Start with "accept all"
        for (Predicate<Product> rule : rules) {
            combined = combined.and(rule);
        }

        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (combined.test(product)) {
                result.add(product);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Product> catalog = List.of(
            new Product("Laptop Pro", 1200.0, "Electronics"),
            new Product("Mouse", 25.0, "Electronics"),
            new Product("Headphones", 80.0, "Electronics"),
            new Product("Desk Chair", 350.0, "Furniture"),
            new Product("USB Cable", 8.0, "Electronics"),
            new Product("Gaming Monitor", 550.0, "Electronics")
        );

        // Define rules as Predicates
        Predicate<Product> expensiveRule = p -> p.price > 50;
        Predicate<Product> electronicsRule = p -> p.category.equals("Electronics");
        Predicate<Product> longNameRule = p -> p.name.length() > 5;

        // Apply all rules
        List<Product> filtered = applyRules(catalog, expensiveRule, electronicsRule, longNameRule);
        System.out.println("Products matching ALL rules:");
        filtered.forEach(System.out::println);
    }
}
