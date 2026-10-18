import java.util.List;
import java.util.stream.Collectors;

public class HtmlReportMain {

    public record SaleItem(String sku, String description, int quantity, double price) {
        public double total() {
            return quantity * price;
        }
    }

    public static String generateHtmlReport(String companyName, String reportDate, List<SaleItem> items) {
        // Generate rows dynamically
        String rowsHtml = items.stream()
                .map(item -> """
                            <tr>
                                <td>%s</td>
                                <td>%s</td>
                                <td style="text-align: right;">%d</td>
                                <td style="text-align: right;">$%.2f</td>
                                <td style="text-align: right;"><strong>$%.2f</strong></td>
                            </tr>
                        """.formatted(item.sku(), item.description(), item.quantity(), item.price(), item.total()))
                .collect(Collectors.joining());

        double grandTotal = items.stream().mapToDouble(SaleItem::total).sum();

        // Assemble full document with text block
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>%s - Sales Summary</title>
                    <style>
                        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; margin: 2rem; }
                        table { width: 100%%; border-collapse: collapse; margin-top: 1rem; }
                        th, td { border: 1px solid #ddd; padding: 10px; }
                        th { background-color: #f4f6f8; text-align: left; }
                        .total-row { background-color: #eef2f7; font-weight: bold; }
                    </style>
                </head>
                <body>
                    <h1>%s</h1>
                    <p>Report Date: <em>%s</em></p>
                    <table>
                        <thead>
                            <tr>
                                <th>SKU</th>
                                <th>Description</th>
                                <th style="text-align: right;">Quantity</th>
                                <th style="text-align: right;">Unit Price</th>
                                <th style="text-align: right;">Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                %s        <tr class="total-row">
                                <td colspan="4" style="text-align: right;">Grand Total:</td>
                                <td style="text-align: right;">$%.2f</td>
                            </tr>
                        </tbody>
                    </table>
                </body>
                </html>
                """.formatted(companyName, companyName, reportDate, rowsHtml, grandTotal);
    }

    public static void main(String[] args) {
        System.out.println("=== HTML Email Report Generator using Text Blocks ===");

        List<SaleItem> items = List.of(
                new SaleItem("LAP-001", "MacBook Pro 16", 2, 2499.00),
                new SaleItem("MON-099", "4K Ultra-Wide Monitor", 3, 599.99),
                new SaleItem("KEY-772", "Custom Mechanical Keyboard", 5, 149.50)
        );

        String html = generateHtmlReport("Acme Enterprise Solutions", "2026-10-18", items);
        System.out.println(html);
    }
}
