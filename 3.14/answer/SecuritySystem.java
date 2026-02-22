// 1. The base class
abstract class SystemEvent {
    private String timestamp = "2026-02-22 15:00:00"; // Dummy timestamp
    public String getTimestamp() { return timestamp; }
}

// 2. The required capability
interface Encryptable {
    String encryptData();
}

// 3. A concrete class that perfectly fits both bounds
class LoginEvent extends SystemEvent implements Encryptable {
    private String username;

    public LoginEvent(String username) {
        this.username = username;
    }

    @Override
    public String encryptData() {
        return "ENCRYPTED_HASH_FOR_" + username.toUpperCase();
    }
}

// 4 & 5. MULTIPLE BOUNDS: T must be a SystemEvent AND Encryptable
// Note: The Class (SystemEvent) MUST be listed before the Interface (Encryptable)
class SecureLogger<T extends SystemEvent & Encryptable> {
    
    // 6. Processing the bounded type
    public void logEvent(T event) {
        // Because of the bounds, we can call methods from BOTH the class and the interface!
        System.out.println("Time: " + event.getTimestamp());
        System.out.println("Logging: " + event.encryptData());
        System.out.println("-----------------------------------");
    }
}

public class SecuritySystem {
    public static void main(String[] args) {
        SecureLogger<LoginEvent> logger = new SecureLogger<>();
        
        LoginEvent login = new LoginEvent("admin_user");
        logger.logEvent(login);
        
        // If we tried to pass a standard SystemEvent that didn't implement Encryptable,
        // the compiler would immediately block it. Ultimate type safety!
    }
}