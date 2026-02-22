class StatsEngine {

    public int findMax(int... numbers) {
        // Crucial Edge Case Guard
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Cannot find max of an empty set.");
        }

        // Initialize max with the first element, then compare against the rest
        int max = numbers[0];
        
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        
        return max;
    }
}

public class MathApp {
    public static void main(String[] args) {
        StatsEngine engine = new StatsEngine();

        System.out.println("--- Calculating Max ---");
        // Passing 5 individual integers
        int result = engine.findMax(10, 50, 20, 99, 5);
        System.out.println("The maximum value is: " + result);

        System.out.println("\n--- Testing Empty Set ---");
        try {
            // Calling with zero arguments triggers our safety check
            engine.findMax(); 
        } catch (IllegalArgumentException e) {
            System.out.println("Error caught: " + e.getMessage());
        }
    }
}