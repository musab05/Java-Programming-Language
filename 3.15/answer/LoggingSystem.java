class Logger {

    // The Varargs parameter (String...) MUST be the last parameter
    public void logEvents(String logLevel, String... messages) {
        
        // Even if no messages are passed, the array is created with length 0 (it is not null)
        if (messages.length == 0) {
            System.out.println("[" + logLevel + "] No messages to log.");
            return;
        }

        // We can iterate over the Varargs exactly like a standard String[]
        for (String message : messages) {
            System.out.println("[" + logLevel + "] Message: " + message);
        }
    }
}

public class LoggingSystem {
    public static void main(String[] args) {
        Logger logger = new Logger();

        System.out.println("--- Test 1: Only the Log Level ---");
        // The compiler passes an empty array: new String[]{}
        logger.logEvents("INFO"); 

        System.out.println("\n--- Test 2: Log Level + Multiple Messages ---");
        // The compiler passes: new String[]{"Database connected", "User authenticated", "Data loaded"}
        logger.logEvents("SUCCESS", "Database connected", "User authenticated", "Data loaded");
    }
}