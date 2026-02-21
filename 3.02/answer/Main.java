// 1. The Legacy Checked Exception (forces try-catch or throws)
class LegacyDatabaseException extends Exception {
    public LegacyDatabaseException(String message) {
        super(message);
    }
}

// 2. The Modern Unchecked Exception (compiler ignores it)
class DatabaseFetchException extends RuntimeException {
    public DatabaseFetchException(String message, Throwable cause) {
        super(message, cause); // Passing the original cause is a best practice!
    }
}

// 3. The annoying legacy code we can't change
class LegacyRepository {
    public String fetchData() throws LegacyDatabaseException {
        // Simulating a database failure
        throw new LegacyDatabaseException("Connection timeout to legacy DB");
    }
}

// 4 & 5. Our modern service layer
class ModernService {
    LegacyRepository repo = new LegacyRepository();

    // Notice how clean this signature is! No 'throws' required.
    public String getUserData() {
        try {
            return repo.fetchData();
        } catch (LegacyDatabaseException e) {
            // 6. Exception Translation (Wrapping)
            // We catch the checked exception and throw an unchecked one.
            throw new DatabaseFetchException("Failed to retrieve user data", e);
        }
    }
}

// 7. The execution
public class Main {
    public static void main(String[] args) {
        ModernService service = new ModernService();
        
        System.out.println("Requesting user data...");
        
        // We don't need a try-catch here to compile, but the program 
        // will crash with our clean, custom DatabaseFetchException.
        service.getUserData(); 
    }
}