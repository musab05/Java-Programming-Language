import java.util.*;
import java.util.stream.Collectors;

public class EcommerceOrderMain {

    // Nested record 1: Line Item
    public record OrderItem(String productName, int quantity, double unitPrice) {
        public OrderItem {
            Objects.requireNonNull(productName, "Product name cannot be null");
            if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive: " + quantity);
            if (unitPrice < 0.0) throw new IllegalArgumentException("Unit price cannot be negative: " + unitPrice);
        }

        public double lineTotal() {
            return quantity * unitPrice;
        }
    }

    // Nested record 2: Full Order
    public record Order(String orderId, String customerId, List<OrderItem> items, double discountPercentage) {
        public Order {
            Objects.requireNonNull(orderId, "Order ID cannot be null");
            Objects.requireNonNull(customerId, "Customer ID cannot be null");
            Objects.requireNonNull(items, "Items cannot be null");

            if (discountPercentage < 0.0 || discountPercentage > 1.0) {
                throw new IllegalArgumentException("Discount percentage must be between 0.0 and 1.0: " + discountPercentage);
            }

            // Guarantee defensive immutability for the nested collection
            items = List.copyOf(items);
        }

        public double calculateSubtotal() {
            return items.stream().mapToDouble(OrderItem::lineTotal).sum();
        }

        public double calculateTotal() {
            double subtotal = calculateSubtotal();
            return subtotal * (1.0 - discountPercentage);
        }

        public Order withDiscount(double newDiscount) {
            return new Order(this.orderId, this.customerId, this.items, newDiscount);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing E-Commerce Order Records ===");

        OrderItem item1 = new OrderItem("Mechanical Keyboard", 1, 120.00);
        OrderItem item2 = new OrderItem("Wireless Mouse", 2, 45.00);
        OrderItem item3 = new OrderItem("USB-C Hub", 1, 35.00);

        Order order1 = new Order("ORD-9001", "CUST-001", List.of(item1, item2), 0.10); // 10% discount
        Order order2 = new Order("ORD-9002", "CUST-002", List.of(item3), 0.0);
        Order order3 = new Order("ORD-9003", "CUST-001", List.of(item2, item3), 0.05);

        System.out.println("Order 1 Subtotal: $" + order1.calculateSubtotal());
        System.out.println("Order 1 Total (10% off): $" + order1.calculateTotal());

        // Functional transformation
        Order upgradedOrder = order1.withDiscount(0.20); // Promotional flash sale 20%
        System.out.println("Order 1 Total (20% off): $" + upgradedOrder.calculateTotal());

        // Stream analytics using Local Record
        List<Order> allOrders = List.of(order1, order2, order3);

        // Local record scoped exclusively inside this method for aggregation
        record CustomerAggregation(String customerId, double totalSpent, int totalItems) {}

        Map<String, CustomerAggregation> customerSummary = allOrders.stream()
                .collect(Collectors.groupingBy(
                        Order::customerId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                orders -> {
                                    double totalSpent = orders.stream().mapToDouble(Order::calculateTotal).sum();
                                    int totalItems = orders.stream()
                                            .flatMap(o -> o.items().stream())
                                            .mapToInt(OrderItem::quantity)
                                            .sum();
                                    return new CustomerAggregation(orders.get(0).customerId(), totalSpent, totalItems);
                                }
                        )
                ));

        System.out.println("\nCustomer Aggregation Summary (via Local Record):");
        customerSummary.forEach((custId, agg) -> {
            System.out.printf("Customer: %s | Total Spent: $%.2f | Total Units Purchased: %d%n",
                    custId, agg.totalSpent(), agg.totalItems());
        });
    }
}
