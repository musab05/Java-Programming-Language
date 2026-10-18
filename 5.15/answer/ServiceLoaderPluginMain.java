import java.util.*;

public class ServiceLoaderPluginMain {

    // 1. Service Provider Interface (SPI)
    // In a modular setup: 'uses PaymentPlugin;' in module-info.java
    public interface PaymentPlugin {
        String providerName();
        boolean processTransaction(String accountId, double amount);
    }

    // 2. Concrete Provider 1
    public static class StripePaymentPlugin implements PaymentPlugin {
        @Override
        public String providerName() {
            return "Stripe Payment Services";
        }

        @Override
        public boolean processTransaction(String accountId, double amount) {
            System.out.printf("[STRIPE] Charging $%.2f to customer token %s via REST API.%n", amount, accountId);
            return true;
        }
    }

    // 3. Concrete Provider 2
    public static class PayPalPaymentPlugin implements PaymentPlugin {
        @Override
        public String providerName() {
            return "PayPal Express Checkout";
        }

        @Override
        public boolean processTransaction(String accountId, double amount) {
            System.out.printf("[PAYPAL] Authorizing $%.2f for wallet %s.%n", amount, accountId);
            return true;
        }
    }

    // 4. Registry demonstrating ServiceLoader SPI architecture
    public static class PluginManager {
        private final Map<String, PaymentPlugin> registeredPlugins = new HashMap<>();

        public PluginManager() {
            // In a modular environment, ServiceLoader dynamically resolves providers
            // registered with 'provides PaymentPlugin with ...;'
            ServiceLoader<PaymentPlugin> loader = ServiceLoader.load(PaymentPlugin.class);

            for (PaymentPlugin plugin : loader) {
                registeredPlugins.put(plugin.providerName().toLowerCase(), plugin);
            }

            // Fallback for classpath/embedded demo:
            if (registeredPlugins.isEmpty()) {
                registerManual(new StripePaymentPlugin());
                registerManual(new PayPalPaymentPlugin());
            }
        }

        public void registerManual(PaymentPlugin plugin) {
            registeredPlugins.put(plugin.providerName().toLowerCase(), plugin);
        }

        public void executePayment(String providerKey, String account, double amount) {
            PaymentPlugin plugin = registeredPlugins.get(providerKey.toLowerCase());
            if (plugin == null) {
                System.out.println("[ERROR] No payment plugin found for key: " + providerKey);
                return;
            }
            System.out.println("\nRouting payment to provider: " + plugin.providerName());
            boolean ok = plugin.processTransaction(account, amount);
            System.out.println("Result: " + (ok ? "SUCCESS" : "FAILED"));
        }

        public void listAvailablePlugins() {
            System.out.println("Discovered payment providers (" + registeredPlugins.size() + "):");
            registeredPlugins.values().forEach(p -> System.out.println("  * " + p.providerName()));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== JPMS ServiceLoader & SPI Architecture Demonstration ===");

        PluginManager manager = new PluginManager();
        manager.listAvailablePlugins();

        manager.executePayment("stripe payment services", "cus_99812481", 149.99);
        manager.executePayment("paypal express checkout", "user@domain.com", 89.50);
        manager.executePayment("crypto pay", "0xABC", 50.00); // Non-existent provider
    }
}
