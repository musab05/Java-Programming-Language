import java.util.concurrent.*;
import java.util.Random;

public class CurrencyConverterApp {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Random random = new Random();

        // 1. Define the work
        Callable<Double> fetchEUR = () -> {
            Thread.sleep(random.nextInt(2000)); // Simulate delay
            return 0.92; 
        };
        Callable<Double> fetchGBP = () -> {
            Thread.sleep(random.nextInt(2000));
            return 0.79;
        };
        Callable<Double> fetchJPY = () -> {
            Thread.sleep(random.nextInt(2000));
            return 150.25;
        };

        System.out.println("[Main] Fetching exchange rates in parallel...");

        // 2. Submit and get "Claim Tickets"
        Future<Double> eurFuture = executor.submit(fetchEUR);
        Future<Double> gbpFuture = executor.submit(fetchGBP);
        Future<Double> jpyFuture = executor.submit(fetchJPY);

        // 3. Retrieve results (This is where the blocking happens)
        System.out.println("EUR Rate: " + eurFuture.get());
        System.out.println("GBP Rate: " + gbpFuture.get());
        System.out.println("JPY Rate: " + jpyFuture.get());

        executor.shutdown();
        System.out.println("[Main] All rates retrieved.");
    }
}