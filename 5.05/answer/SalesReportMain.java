import java.util.*;
import java.util.stream.Collectors;

public class SalesReportMain {

    static class Sale {
        String product;
        String category;
        double amount;
        String region;

        Sale(String product, String category, double amount, String region) {
            this.product = product;
            this.category = category;
            this.amount = amount;
            this.region = region;
        }
    }

    public static void main(String[] args) {
        List<Sale> sales = List.of(
            new Sale("Laptop", "Electronics", 1200.0, "North"),
            new Sale("Phone", "Electronics", 800.0, "South"),
            new Sale("TV", "Electronics", 1500.0, "East"),
            new Sale("Shirt", "Clothing", 45.0, "West"),
            new Sale("Jacket", "Clothing", 120.0, "North"),
            new Sale("Jeans", "Clothing", 65.0, "South"),
            new Sale("Dress", "Clothing", 90.0, "East"),
            new Sale("Bread", "Food", 5.0, "West"),
            new Sale("Milk", "Food", 4.0, "North"),
            new Sale("Cheese", "Food", 8.0, "South"),
            new Sale("Headphones", "Electronics", 250.0, "West"),
            new Sale("Rice", "Food", 12.0, "East")
        );

        // (a) Total sales per category
        System.out.println("=== Total Sales per Category ===");
        Map<String, Double> totalByCategory = sales.stream()
            .collect(Collectors.groupingBy(s -> s.category, Collectors.summingDouble(s -> s.amount)));
        totalByCategory.forEach((cat, total) -> System.out.printf("  %s: $%.2f%n", cat, total));

        // (b) Average sale amount per region
        System.out.println("\n=== Average Sale per Region ===");
        Map<String, Double> avgByRegion = sales.stream()
            .collect(Collectors.groupingBy(s -> s.region, Collectors.averagingDouble(s -> s.amount)));
        avgByRegion.forEach((region, avg) -> System.out.printf("  %s: $%.2f%n", region, avg));

        // (c) Highest single sale per category
        System.out.println("\n=== Highest Sale per Category ===");
        Map<String, Optional<Sale>> maxByCategory = sales.stream()
            .collect(Collectors.groupingBy(s -> s.category,
                Collectors.maxBy(Comparator.comparingDouble(s -> s.amount))));
        maxByCategory.forEach((cat, sale) ->
            sale.ifPresent(s -> System.out.printf("  %s: %s ($%.2f)%n", cat, s.product, s.amount)));

        // (d) Comma-separated products per category
        System.out.println("\n=== Products per Category ===");
        Map<String, String> productsByCategory = sales.stream()
            .collect(Collectors.groupingBy(s -> s.category,
                Collectors.mapping(s -> s.product, Collectors.joining(", "))));
        productsByCategory.forEach((cat, products) ->
            System.out.printf("  %s: %s%n", cat, products));
    }
}
