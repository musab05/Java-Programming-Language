import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CustomPoolMain {

    static String simulateIO(int id) {
        try {
            Thread.sleep(100); // Simulate slow I/O
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Result-" + id + " [" + Thread.currentThread().getName() + "]";
    }

    public static void main(String[] args) throws Exception {
        List<Integer> tasks = new ArrayList<>();
        for (int i = 1; i <= 20; i++) tasks.add(i);

        // Method 1: Using Common Pool (DANGEROUS — blocks other parallel streams)
        System.out.println("=== Using Common Pool ===");
        System.out.println("Pool size: " + ForkJoinPool.commonPool().getParallelism());
        long start = System.nanoTime();
        List<String> results1 = tasks.parallelStream()
            .map(CustomPoolMain::simulateIO)
            .collect(Collectors.toList());
        long commonTime = System.nanoTime() - start;
        System.out.printf("Time: %.2fms%n%n", commonTime / 1e6);

        // Method 2: Using Custom Pool (SAFE — isolated from other operations)
        System.out.println("=== Using Custom Pool (16 threads) ===");
        ForkJoinPool customPool = new ForkJoinPool(16);
        start = System.nanoTime();
        List<String> results2 = customPool.submit(() ->
            tasks.parallelStream()
                .map(CustomPoolMain::simulateIO)
                .collect(Collectors.toList())
        ).get();
        long customTime = System.nanoTime() - start;
        System.out.printf("Time: %.2fms%n", customTime / 1e6);
        System.out.printf("Speedup: %.2fx%n", (double) commonTime / customTime);

        customPool.shutdown();

        // Show thread names to prove different threads were used
        System.out.println("\nSample results:");
        results2.stream().limit(5).forEach(System.out::println);
    }
}
