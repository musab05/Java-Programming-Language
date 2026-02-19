public class AppConfig {
    public static void main(String[] args) {
        // 2. Check if at least one argument exists
        if (args.length == 0) {
            System.out.println("Default Mode: Limited access.");
            return;
        }

        // 3 & 4. Evaluate the first argument
        String mode = args[0].toLowerCase(); // Convert to lowercase for flexibility

        if (mode.equals("test")) {
            System.out.println("Running in Test Mode: Debugging enabled.");
        } else if (mode.equals("prod")) {
            System.out.println("Running in Production Mode: Security protocols active.");
        } else {
            // 5. Handling unknown arguments
            System.out.println("Default Mode: Limited access.");
        }
    }
}

/*
When using Command Line Arguments, you are essentially "injecting" data into the main method from the outside world. This makes your program much more flexible; you could use this to pass a database password or a specific file path without ever hard-coding sensitive information into your .java files.
*/