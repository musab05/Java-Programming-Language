public class PerformanceTest {
    public static void main(String[] args) {
        int maxIterations = 10_000_000;
        
        System.out.println("Starting Test A (Wrapper Class)...");
        long startTime = System.currentTimeMillis();
        
        // Using the Wrapper Class 'Long'
        Long wrapperSum = 0L; 
        for (int i = 0; i < maxIterations; i++) {
            /* * HIDDEN DISASTER: 
             * Java is unboxing wrapperSum to add 'i', then autoboxing 
             * the result into a BRAND NEW Long object. 
             * It does this 10 million times!
             */
            wrapperSum += i; 
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Test A (Wrapper) took: " + (endTime - startTime) + " milliseconds.");
        

        System.out.println("\nStarting Test B (Primitive)...");
        startTime = System.currentTimeMillis();
        
        // Using the primitive 'long'
        long primitiveSum = 0L; 
        for (int i = 0; i < maxIterations; i++) {
            // Just raw math. No objects created.
            primitiveSum += i; 
        }
        
        endTime = System.currentTimeMillis();
        System.out.println("Test B (Primitive) took: " + (endTime - startTime) + " milliseconds.");
    }
}