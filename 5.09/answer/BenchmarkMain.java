import java.util.stream.*;

public class BenchmarkMain {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int size = 1_000_000;

        // Test 1: CPU-intensive operation (prime check)
        System.out.println("=== CPU-Intensive: Prime Counting ===");

        long start = System.nanoTime();
        long seqCount = IntStream.range(2, size).filter(BenchmarkMain::isPrime).count();
        long seqTime = System.nanoTime() - start;

        start = System.nanoTime();
        long parCount = IntStream.range(2, size).parallel().filter(BenchmarkMain::isPrime).count();
        long parTime = System.nanoTime() - start;

        System.out.printf("Sequential: %d primes in %.2fms%n", seqCount, seqTime / 1e6);
        System.out.printf("Parallel:   %d primes in %.2fms%n", parCount, parTime / 1e6);
        System.out.printf("Speedup:    %.2fx%n%n", (double) seqTime / parTime);

        // Test 2: Trivial operation (multiply by 2)
        System.out.println("=== Trivial: Multiply by 2 ===");

        start = System.nanoTime();
        IntStream.range(0, size).map(n -> n * 2).sum();
        seqTime = System.nanoTime() - start;

        start = System.nanoTime();
        IntStream.range(0, size).parallel().map(n -> n * 2).sum();
        parTime = System.nanoTime() - start;

        System.out.printf("Sequential: %.2fms%n", seqTime / 1e6);
        System.out.printf("Parallel:   %.2fms%n", parTime / 1e6);
        System.out.printf("Speedup:    %.2fx%n", (double) seqTime / parTime);
        System.out.println("(A speedup < 1.0 means parallel was SLOWER!)");
    }
}
