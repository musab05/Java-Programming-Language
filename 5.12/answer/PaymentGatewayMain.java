import java.util.*;

public class PaymentGatewayMain {

    // Sealed interface defining closed set of payment options
    public sealed interface PaymentMethod permits CreditCard, BankTransfer, CryptoWallet {
        String payerIdentifier();
    }

    public record CreditCard(String cardNumber, String expiry, String cvv, String cardHolder) implements PaymentMethod {
        public CreditCard {
            Objects.requireNonNull(cardNumber, "Card number required");
            Objects.requireNonNull(cvv, "CVV required");
            if (cvv.length() != 3 && cvv.length() != 4) {
                throw new IllegalArgumentException("Invalid CVV");
            }
        }

        @Override
        public String payerIdentifier() {
            return "Card ending in " + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        }
    }

    public record BankTransfer(String iban, String routingNumber, String bankName) implements PaymentMethod {
        public BankTransfer {
            Objects.requireNonNull(iban, "IBAN required");
            Objects.requireNonNull(routingNumber, "Routing number required");
        }

        @Override
        public String payerIdentifier() {
            return "Bank " + bankName + " (" + iban + ")";
        }
    }

    public record CryptoWallet(String walletAddress, String cryptocurrency) implements PaymentMethod {
        public CryptoWallet {
            Objects.requireNonNull(walletAddress, "Wallet address required");
            Objects.requireNonNull(cryptocurrency, "Crypto currency symbol required");
        }

        @Override
        public String payerIdentifier() {
            return cryptocurrency + " Wallet " + walletAddress.substring(0, Math.min(8, walletAddress.length())) + "...";
        }
    }

    // Receipt record
    public record PaymentReceipt(String transactionId, double amount, String status, String methodSummary) {}

    // Processor using exhaustive switch pattern matching
    public static class PaymentProcessor {
        public static PaymentReceipt process(PaymentMethod method, double amount) {
            String txId = "TX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // Exhaustive switch over sealed interface — compiler guarantees all cases covered!
            String details = switch (method) {
                case CreditCard cc -> {
                    System.out.println("Authorizing card payment with gateway for " + cc.cardHolder());
                    yield "Charged " + cc.payerIdentifier() + " via Visa/Mastercard network";
                }
                case BankTransfer bt -> {
                    System.out.println("Initiating ACH/SEPA wire transfer through " + bt.bankName());
                    yield "Scheduled wire via " + bt.payerIdentifier();
                }
                case CryptoWallet cw -> {
                    System.out.println("Broadcasting tx to " + cw.cryptocurrency() + " blockchain");
                    yield "Submitted to blockchain from " + cw.payerIdentifier();
                }
            };

            return new PaymentReceipt(txId, amount, "APPROVED", details);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Modern Java Sealed Classes: Payment Gateway ===");

        PaymentMethod cc = new CreditCard("4111222233334444", "12/28", "123", "Alice Smith");
        PaymentMethod bank = new BankTransfer("DE89370400440532013000", "0100440", "Deutsche Bank");
        PaymentMethod crypto = new CryptoWallet("0x71C81323712F21287132F", "ETH");

        List<PaymentMethod> methods = List.of(cc, bank, crypto);

        for (PaymentMethod method : methods) {
            PaymentReceipt receipt = PaymentProcessor.process(method, 249.99);
            System.out.println("Receipt Generated:");
            System.out.println("  ID:     " + receipt.transactionId());
            System.out.println("  Amount: $" + receipt.amount());
            System.out.println("  Status: " + receipt.status());
            System.out.println("  Detail: " + receipt.methodSummary());
            System.out.println();
        }
    }
}
