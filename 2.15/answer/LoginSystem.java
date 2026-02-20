class BaseUser {
    // 2. Locked method - Security check
    public final void verifyPassword(String input) {
        System.out.println("Verifying password against encrypted database...");
    }

    public void greet() {
        System.out.println("Welcome, User.");
    }
}

class AdminUser extends BaseUser {
    // 4. Attempting to override verifyPassword will cause a COMPILE ERROR
    /*
    @Override
    public void verifyPassword(String input) {
        System.out.println("Admin bypass!");
    }
    */

    // 5. This is allowed because greet() is not final
    @Override
    public void greet() {
        System.out.println("Welcome, Administrator. Access level: 10.");
    }
}

public class LoginSystem {
    public static void main(String[] args) {
        AdminUser admin = new AdminUser();
        admin.greet();
        admin.verifyPassword("secret123");
    }
}