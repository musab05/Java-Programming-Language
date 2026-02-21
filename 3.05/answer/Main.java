// 1. A Custom Checked Exception (because it extends Exception, not RuntimeException)
class DatabaseTimeoutException extends Exception {
    public DatabaseTimeoutException(String message) {
        super(message);
    }
}

// 2. The Service Class
class RegistrationService {
    
    // 3. The 'throws' keyword warns the caller about the Checked Exception
    public void register(String username, int age) throws DatabaseTimeoutException {
        
        // 4a. Throwing an Unchecked Exception (No signature warning needed!)
        if (age < 18) {
            throw new IllegalArgumentException("User must be 18 or older to register.");
        }
        
        // 4b. Throwing a Checked Exception (This requires the 'throws' above)
        if ("admin".equalsIgnoreCase(username)) {
            throw new DatabaseTimeoutException("Connection lost while verifying 'admin' username.");
        }
        
        System.out.println("User '" + username + "' successfully registered!");
    }
}

// 5. The Execution
public class Main {
    public static void main(String[] args) {
        RegistrationService service = new RegistrationService();
        
        try {
            System.out.println("Attempting to register user...");
            
            // This method call is forced into a try-catch by the compiler
            service.register("admin", 20);
            
        } catch (DatabaseTimeoutException e) {
            // Recovering from the Checked Exception
            System.out.println("Registration failed: " + e.getMessage());
            System.out.println("Please try again in a few minutes.");
            
        } catch (IllegalArgumentException e) {
            // Catching the Unchecked Exception (optional, but good practice here)
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}