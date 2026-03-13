// Test A (Standard): java -XX:+UseG1GC -XX:MaxGCPauseMillis=50 -Xlog:gc LatencyTest
// Test B (Ultra-Low Latency): java -XX:+UseZGC -Xlog:gc LatencyTest

import java.util.concurrent.ConcurrentHashMap;

public class LatencyTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, byte[]> cache = new ConcurrentHashMap<>();
        
        System.out.println("Simulating a busy cache...");

        for (int i = 0; i < 1_000_000; i++) {
            // Constant churn of map entries
            cache.put(i % 10000, new byte[1024 * 5]); // 5KB entries

            if (i % 50000 == 0) {
                System.out.println("Processed " + i + " updates...");
            }
        }
        System.out.println("Test Complete.");
    }
}