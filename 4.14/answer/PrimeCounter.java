import java.util.*;
import java.util.stream.*;

public class PrimeCounter {
    public static void main(String[] args) {
        // Generate 100,000 random numbers
        List<Integer> numbers = new Random()
                .ints(100_000, 2, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        // 1. Sequential Test
        long start = System.currentTimeMillis();
        long countSeq = numbers.stream()
                .filter(PrimeCounter::isPrime)
                .count();
        long end = System.currentTimeMillis();
        System.out.println("Sequential Count: " + countSeq + " | Time: " + (end - start) + "ms");

        // 2. Parallel Test
        start = System.currentTimeMillis();
        long countPar = numbers.parallelStream()
                .filter(PrimeCounter::isPrime)
                .count();
        end = System.currentTimeMillis();
        System.out.println("Parallel Count:   " + countPar + " | Time: " + (end - start) + "ms");
    }

    // A intentionally "slow" prime check to simulate heavy CPU work
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}