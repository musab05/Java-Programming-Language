// How to run it with logging:
// java -Xmx512m -Xms512m -Xlog:gc*:file=gc.log:time,uptime,level,tags -XX:+UseG1GC GCChurnGenerator

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GCChurnGenerator {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> survivorList = new ArrayList<>();
        Random random = new Random();

        System.out.println("Generating churn. Open gc.log to see the results...");

        for (int i = 0; i < 1_000_000; i++) {
            // 1. High Churn: Create 100KB trash that dies immediately
            byte[] trash = new byte[1024 * 100]; 

            // 2. Promotion: Every 50 iterations, keep an object alive
            if (i % 50 == 0) {
                survivorList.add(new byte[1024 * 256]); // 256KB survivor
            }

            // 3. Prevent OutOfMemory: Clear survivors periodically to see "Mixed GC"
            if (survivorList.size() > 500) {
                survivorList.clear();
                System.out.println("Survivor list cleared to trigger heap reclamation.");
            }

            // Small throttle to avoid crashing too fast
            if (i % 1000 == 0) Thread.sleep(10);
        }
    }
}