public class ConflictResolver {
    public static void main(String[] args) {
        
        // Using the absolute path to differentiate between two "User" classes
        com.academiq.admin.User adminUser = new com.academiq.admin.User();
        
        com.academiq.student.User studentUser = new com.academiq.student.User();

        System.out.println("Both Admin and Student user objects created successfully.");
    }
}