public class Multiplier {
    public static void main(String[] args) {
        // 2. Check if exactly 2 arguments are provided
        if (args.length != 2) {
            System.out.println("Error: Please provide exactly two numbers.");
            System.out.println("Usage: java Multiplier <num1> <num2>");
            return; // Exit the program
        }

        try {
            // 3. Parse Strings to integers
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);

            // 4. Calculate and print product
            int product = num1 * num2;
            System.out.println("Result: " + product);
            
        } catch (NumberFormatException e) {
            // Handles cases where user inputs text instead of numbers
            System.out.println("Error: Arguments must be valid integers.");
        }
    }
}