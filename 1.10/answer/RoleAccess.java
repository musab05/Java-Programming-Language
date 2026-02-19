public class RoleAccess {
    public static void main(String[] args) {
        // 1. Declare the user role
        String userRole = "ADMIN"; // Try changing this to "EDITOR" or "HACKER"
        
        // 2. Switch statement to grant access based on the exact string
        switch (userRole) {
            case "ADMIN":
                System.out.println("Full System Access Granted.");
                break; // Stops the switch from falling through to the next case
                
            case "EDITOR":
                System.out.println("Content Modification Access Granted.");
                break;
                
            case "GUEST":
                System.out.println("Read-Only Access Granted.");
                break;
                
            default:
                // Acts as a fallback if the role doesn't match any known cases
                System.out.println("Access Denied: Unknown Role.");
                break; 
        }
    }
}