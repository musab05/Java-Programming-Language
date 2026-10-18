import java.util.*;
import java.util.stream.Collectors;

public class OrderProcessorMain {

    enum Status { PENDING, SHIPPED, DELIVERED }

    static class Order {
        int id;
        double amount;
        Status status;
        String customerName;

        Order(int id, double amount, Status status, String customerName) {
            this.id = id;
            this.amount = amount;
            this.status = status;
            this.customerName = customerName;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order(101, 250.00, Status.PENDING, "Alice"),
            new Order(102, 89.99, Status.SHIPPED, "Bob"),
            new Order(103, 620.00, Status.PENDING, "Charlie"),
            new Order(104, 45.50, Status.DELIVERED, "Diana"),
            new Order(105, 199.99, Status.PENDING, "Eve"),
            new Order(106, 750.00, Status.PENDING, "Frank"),
            new Order(107, 30.00, Status.SHIPPED, "Grace"),
            new Order(108, 410.00, Status.PENDING, "Hank"),
            new Order(109, 55.00, Status.PENDING, "Ivy"),
            new Order(110, 1200.00, Status.PENDING, "Jack")
        );

        // Pipeline: filter → sort → limit → map → collect
        System.out.println("=== Top 5 Pending Orders (by amount) ===");
        List<String> topPending = orders.stream()
            .filter(o -> o.status == Status.PENDING)
            .sorted(Comparator.comparingDouble((Order o) -> o.amount).reversed())
            .limit(5)
            .map(o -> String.format("Order #%d - $%.2f - %s", o.id, o.amount, o.customerName))
            .collect(Collectors.toList());
        topPending.forEach(System.out::println);

        // anyMatch: does any order exceed $500?
        boolean hasLargeOrder = orders.stream()
            .anyMatch(o -> o.amount > 500);
        System.out.println("\nAny order > $500? " + hasLargeOrder);

        // reduce: total of all PENDING orders
        double totalPending = orders.stream()
            .filter(o -> o.status == Status.PENDING)
            .map(o -> o.amount)
            .reduce(0.0, Double::sum);
        System.out.printf("Total pending amount: $%.2f%n", totalPending);
    }
}
