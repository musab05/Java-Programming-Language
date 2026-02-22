import java.util.Optional;

// Custom Business Exception
class SecurityException extends RuntimeException {
    public SecurityException(String message) { super(message); }
}

class User {
    private String username;
    public User(String username) { this.username = username; }
    public String getUsername() { return username; }
}

class UserRepository {
    public Optional<User> findById(int id) {
        // Simulating a database: only ID 101 exists
        if (id == 101) {
            return Optional.of(new User("Admin_Alice"));
        }
        return Optional.empty();
    }
}

public class AuthService {
    private static final UserRepository repo = new UserRepository();

    public static User login(int id) {
        return repo.findById(id)
            .orElseThrow(() -> new SecurityException("Access Denied: Invalid User ID " + id));
    }

    public static void main(String[] args) {
        try {
            // Valid login
            User u = login(101);
            System.out.println("Logged in as: " + u.getUsername());

            // Invalid login - this will trigger the exception
            login(999); 
        } catch (SecurityException e) {
            System.err.println("Security Alert: " + e.getMessage());
        }
    }
}