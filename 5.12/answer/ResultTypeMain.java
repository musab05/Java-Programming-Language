import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ResultTypeMain {

    // Sealed interface modeling Either / Result monad
    public sealed interface Result<T> permits Result.Success, Result.Failure {

        record Success<T>(T value) implements Result<T> {}
        record Failure<T>(String errorMessage, int errorCode) implements Result<T> {}

        default boolean isSuccess() {
            return this instanceof Success<T>;
        }

        default boolean isFailure() {
            return this instanceof Failure<T>;
        }

        default T getOrElse(T fallback) {
            return switch (this) {
                case Success<T> s -> s.value();
                case Failure<T> f -> fallback;
            };
        }

        default Optional<T> toOptional() {
            return switch (this) {
                case Success<T> s -> Optional.ofNullable(s.value());
                case Failure<T> f -> Optional.empty();
            };
        }

        // Functional monadic map
        @SuppressWarnings("unchecked")
        default <R> Result<R> map(Function<? super T, ? extends R> mapper) {
            return switch (this) {
                case Success<T> s -> new Success<>(mapper.apply(s.value()));
                case Failure<T> f -> (Result<R>) f; // Propagate failure
            };
        }
    }

    // Bank Account Service using Result pattern instead of throwing exceptions
    static class BankAccount {
        private final String accountNumber;
        private double balance;

        public BankAccount(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public Result<Double> withdraw(double amount) {
            if (amount <= 0) {
                return new Result.Failure<>("Withdrawal amount must be positive", 400);
            }
            if (amount > balance) {
                return new Result.Failure<>(
                    String.format("Insufficient funds. Requested: $%.2f, Available: $%.2f", amount, balance),
                    402
                );
            }

            this.balance -= amount;
            return new Result.Success<>(this.balance);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Monadic Sealed Result Pattern in Action ===");

        BankAccount account = new BankAccount("ACC-44910", 500.00);

        double[] withdrawalRequests = { 200.00, -50.00, 450.00, 100.00 };

        for (double requested : withdrawalRequests) {
            System.out.println("\nAttempting to withdraw: $" + requested);
            Result<Double> result = account.withdraw(requested);

            // Exhaustive pattern matching on sealed Result
            switch (result) {
                case Result.Success<Double> s -> {
                    System.out.println("  [SUCCESS] Transaction approved!");
                    System.out.printf("  New Balance: $%.2f%n", s.value());
                }
                case Result.Failure<Double> f -> {
                    System.out.printf("  [FAILURE] Error Code %d: %s%n", f.errorCode(), f.errorMessage());
                }
            }

            // Using functional getOrElse
            Double currentSafeBalance = result.getOrElse(account.getBalance());
            System.out.printf("  Current Available Balance: $%.2f%n", currentSafeBalance);
        }
    }
}
