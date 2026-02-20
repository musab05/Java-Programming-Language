public class Vault {
    // 2. Encapsulated secret data
    private int secretPin;

    // 3. Public Setter (Write access)
    public void setSecretPin(int secretPin) {
        this.secretPin = secretPin;
        System.out.println("Secure PIN has been set.");
    }

    // 4. Notice there is NO getSecretPin() method! The data cannot be extracted.

    // 5. Safe behavior method
    public boolean checkPin(int attempt) {
        if (attempt == secretPin) {
            System.out.println("Access Granted.");
            return true;
        } else {
            System.out.println("Access Denied.");
            return false;
        }
    }

    public static void main(String[] args) {
        Vault myVault = new Vault();
        myVault.setSecretPin(1234);

        // myVault.getSecretPin(); // ERROR: method does not exist
        // System.out.println(myVault.secretPin); // ERROR: private access
        
        // We can only interact with the PIN indirectly
        myVault.checkPin(9999); // Fails
        myVault.checkPin(1234); // Succeeds
    }
}