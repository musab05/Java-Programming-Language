import java.util.ArrayList;
import java.util.List;

public class VisualGC {
    public static void main(String[] args) {
        System.out.println("Starting object creation loop...");
        
        // We create millions of strings that aren't stored anywhere
        // This puts immense pressure on the Young Generation (Eden Space)
        for (int i = 0; i < 10_000_000; i++) {
            String trash = new String("Garbage Data Number: " + i);
            
            // Every 1 million objects, print a heartbeat
            if (i % 1_000_000 == 0) {
                System.out.println("Created " + i + " objects...");
            }
        }
        
        System.out.println("Loop finished. Check your console for [GC (Allocation Failure)...] logs!");
    }
}