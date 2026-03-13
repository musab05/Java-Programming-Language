import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class FibonacciTask extends RecursiveTask<Integer> {
    private final int n;
    // Small threshold because Fibonacci overhead is high
    private static final int THRESHOLD = 10; 

    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= THRESHOLD) {
            return computeSequentially(n);
        }

        FibonacciTask f1 = new FibonacciTask(n - 1);
        FibonacciTask f2 = new FibonacciTask(n - 2);

        // Fork the first task (runs in a different thread if available)
        f1.fork();

        // Compute the second task in the CURRENT thread, then join results
        return f2.compute() + f1.join();
    }

    private int computeSequentially(int n) {
        if (n <= 1) return n;
        return computeSequentially(n - 1) + computeSequentially(n - 2);
    }
}

public class FibonacciApp {
    public static void main(String[] args) {
        int n = 30;
        ForkJoinPool pool = new ForkJoinPool();
        
        System.out.println("Calculating Fibonacci(" + n + ") in parallel...");
        long start = System.currentTimeMillis();
        
        int result = pool.invoke(new FibonacciTask(n));
        
        long end = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (end - start) + "ms");
    }
}