import java.lang.ref.WeakReference;

public class WeakRefDemo {
    public static void main(String[] args) {
        // 1. Create a "Strong Reference" to a large object
        byte[] bigData = new byte[1024 * 1024 * 10]; // 10 MB
        System.out.println("1. 10MB Object created and held by 'bigData'.");

        // 2. Wrap it in a WeakReference
        WeakReference<byte[]> ghostRef = new WeakReference<>(bigData);
        System.out.println("2. WeakReference created.");

        // 3. Clear the strong reference
        // Now the ONLY thing pointing to the 10MB is the WeakReference
        bigData = null; 
        System.out.println("3. Strong reference 'bigData' set to null.");

        // 4. At this point, the object might still be in memory
        System.out.println("4. Checking ghostRef before GC: " + (ghostRef.get() != null ? "Still there" : "Gone"));

        // 5. Explicitly suggest Garbage Collection
        System.out.println("5. Suggesting Garbage Collection...");
        System.gc();

        // 6. Check again
        // Because it was only WEAKLY reachable, the GC is allowed to delete it.
        if (ghostRef.get() == null) {
            System.out.println("6. Success! The GC reclaimed the memory because it was only weakly referenced.");
        } else {
            System.out.println("6. The GC chose not to run yet. Try running with -Xmx20m to force pressure.");
        }
    }
}