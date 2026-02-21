// 1. The Base Custom Exception for the Auth Module
class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}

// 2. The Specific Sub-Exceptions
class InvalidPasswordException extends AuthenticationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends AuthenticationException {
    public AccountLockedException(String message) {
        super(message);
    }
}

// 3 & 4. The Login Service
class LoginService {
    public void login(String username, String password) {
        if (username.equals("admin") && password.equals("wrong123")) {
            throw new InvalidPasswordException("Incorrect password provided.");
        } else if (username.equals("hacker")) {
            throw new AccountLockedException("Suspicious activity detected. Account locked.");
        } else {
            System.out.println("Login successful for user: " + username);
        }
    }
}

// 5 & 6. The Execution and Catch Hierarchy
public class App {
    public static void main(String[] args) {
        LoginService auth = new LoginService();

        try {
            System.out.println("Attempting login...");
            auth.login("hacker", "any_password");
            
        // Catching the most specific exceptions first!
        } catch (AccountLockedException e) {
            System.out.println("UI Message: Please contact support to unlock your account.");
            
        } catch (InvalidPasswordException e) {
            System.out.println("UI Message: Try again. Password was incorrect.");
            
        // Catching the base exception as a fallback for any other auth issues
        } catch (AuthenticationException e) {
            System.out.println("UI Message: General authentication failure. Please try later.");
        }
    }
}