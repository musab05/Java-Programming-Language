import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class RateLimiter {
    // Start with a quota of 100 requests
    private final AtomicInteger quota = new AtomicInteger(100);

    public boolean allowRequest() {
        // decrementAndGet is atomic. Even if 1000 threads hit this,
        // it will count down accurately.
        int remaining = quota.decrementAndGet();

        if (remaining >= 0) {
            return true;
        } else {
            // Optional: reset to 0 so the number doesn't become -9000
            quota.set(0); 
            return false;
        }
    }
}

public class ThrottlingDemo {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 150 threads trying to get through a limit of 100
        for (int i = 0; i < 150; i++) {
            executor.submit(() -> {
                if (limiter.allowRequest()) {
                    System.out.println("Request ALLOWED");
                } else {
                    System.out.println("Request REJECTED");
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}