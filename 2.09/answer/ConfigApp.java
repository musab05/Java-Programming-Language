class SystemConfig {
    int timeout = 30; // Parent variable
}

class UserProfile extends SystemConfig {
    int timeout = 60; // Child variable shadows parent

    public void printTimeouts() {
        // Accessing the local child variable
        System.out.println("Custom Timeout: " + timeout);
        
        // Accessing the parent's shadowed variable
        System.out.println("System Default Timeout: " + super.timeout);
    }
}

public class ConfigApp {
    public static void main(String[] args) {
        UserProfile user = new UserProfile();
        user.printTimeouts();
    }
}