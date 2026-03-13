// How to run this:
// java -Xmx128m -XX:+PrintGCDetails GCStressTest

import java.util.ArrayList;
import java.util.List;

public class GCStressTest {
    public static void main(String[] args) {
        List<byte[]> memoryHog = new ArrayList<>();
        System.out.println("Starting memory pressure test...");

        try {
            while (true) {
                // 1. Create a 1MB "chunk" of data
                byte[] chunk = new byte[1024 * 1024]; 
                
                // 2. Keep a reference so the GC CANNOT delete it
                memoryHog.add(chunk);

                // 3. Print status every 10MB
                if (memoryHog.size() % 10 == 0) {
                    System.out.println("Current heap usage: ~" + memoryHog.size() + " MB");
                    Thread.sleep(100); // Slow down so we can see the logs
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("\nSYSTEM CRASHED: OutOfMemoryError!");
            System.err.println("The Old Generation filled up and the GC couldn't find any more treasure.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}