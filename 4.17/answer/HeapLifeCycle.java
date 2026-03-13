// Recommended JVM Flags to see the action:
// java -XX:+PrintGCDetails -Xmx64m HeapLifeCycle

import java.util.ArrayList;
import java.util.List;

public class HeapLifeCycle {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> longLivedObjects = new ArrayList<>();
        System.out.println("--- Starting Heap Pressure Test ---");

        for (int i = 0; i < 1000; i++) {
            // 1. Create short-lived "trash" (Should die in Young Gen/Eden)
            byte[] trash = new byte[1024 * 10]; // 10KB
            
            // 2. Occasionally create "survivors" (Promoted to Old Gen)
            if (i % 10 == 0) {
                longLivedObjects.add(new byte[1024 * 100]); // 100KB
                System.out.println("Promoting object #" + i + " to survivor list.");
            }

            // Small sleep so you can watch VisualVM or JConsole in real-time
            if (i % 100 == 0) Thread.sleep(200);
        }

        System.out.println("Test complete. Survivor list size: " + longLivedObjects.size());
    }
}