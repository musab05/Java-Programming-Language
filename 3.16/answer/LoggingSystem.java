import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class FileLogger {

    public void logError(String message) {
        // 1. Try-With-Resources automatically handles the .close() method.
        // 2. The 'true' parameter in FileWriter turns ON Append Mode!
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("app_errors.log", true))) {
            
            // 3. Write the data
            writer.write("[ERROR] " + message);
            
            // 4. newLine() is OS-independent. It uses \r\n on Windows and \n on Mac/Linux.
            writer.newLine(); 
            
            System.out.println("Successfully wrote to log: " + message);

        } catch (IOException e) {
            // I/O operations are unpredictable (disk full, permissions changed, etc.)
            System.err.println("CRITICAL: Failed to write to log file! Reason: " + e.getMessage());
        }
    }
}

public class LoggingSystem {
    public static void main(String[] args) {
        FileLogger logger = new FileLogger();

        System.out.println("--- Starting Logging Process ---");
        logger.logError("Database connection timed out.");
        logger.logError("NullPointerException in UserService at line 42.");
        logger.logError("Failed to authenticate user ID: 9948.");
        
        System.out.println("\nCheck your project folder! You should see 'app_errors.log' with 3 lines in it.");
    }
}