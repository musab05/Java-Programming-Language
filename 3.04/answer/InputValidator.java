import java.util.Scanner;

public class InputValidator {
    public static void main(String[] args) {
        // We declare the scanner outside the loop so we can close it in a final cleanup
        Scanner scanner = new Scanner(System.in);
        int attemptsLeft = 3;
        boolean success = false;

        System.out.println("Welcome to the system.");

        try {
            // 1. The Retry Loop
            while (attemptsLeft > 0 && !success) {
                System.out.print("Please enter your age: ");
                
                try {
                    // 2. The Risky Code
                    // NextLine() reads the raw string, parseInt tries to convert it
                    int age = Integer.parseInt(scanner.nextLine());
                    
                    // If parseInt fails, the code jumps to catch. 
                    // This success line only runs if the parse works!
                    System.out.println("Age accepted: " + age);
                    success = true; // Breaks the loop naturally
                    
                } catch (NumberFormatException e) {
                    // 3. The Rescue Operation
                    attemptsLeft--;
                    System.out.println("Invalid input. That is not a number.");
                    
                    if (attemptsLeft > 0) {
                        System.out.println("You have " + attemptsLeft + " attempts left.\n");
                    } else {
                        System.out.println("Account locked. Too many invalid attempts.");
                    }
                }
            }
        } finally {
            // 4. The Ultimate Cleanup
            // Whether they succeeded on attempt 1, or failed all 3, we close the scanner.
            System.out.println("Cleaning up resources...");
            scanner.close();
        }
    }
}