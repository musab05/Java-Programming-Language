public class User {
    // 2. Private variable - hidden from the outside world
    private String password;

    // 3. Public method to set the password
    public void setPassword(String newPassword) {
        // 4. Validation logic
        if (newPassword.length() > 6) {
            this.password = newPassword;
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Error: Password must be longer than 6 characters.");
        }
    }

    public static void main(String[] args) {
        User user = new User();

        // 5. Direct access test:
        // user.password = "123"; // UNCOMMENT THIS TO SEE COMPILER ERROR: "password has private access"

        // Using the public method correctly
        user.setPassword("secret123");
    }
}