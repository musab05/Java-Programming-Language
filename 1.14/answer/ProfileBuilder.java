import java.util.Scanner; // 1. Import the Scanner class

public class ProfileBuilder {
    public static void main(String[] args) {
        // Initialize the Scanner to read from the console
        Scanner sc = new Scanner(System.in);
        
        // 2. Prompt and read the username
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        
        // 3. Prompt and read the account balance
        System.out.print("Enter your account balance: ");
        double balance = sc.nextDouble();
        
        // 4. Print the formatted result
        // %s acts as a placeholder for the String (username)
        // %.2f acts as a placeholder for the double (balance), rounded to 2 decimal places
        System.out.printf("User: %s | Balance: $%.2f\n", username, balance);
        
        // 5. Close the scanner to prevent memory leaks
        sc.close(); 
    }
}