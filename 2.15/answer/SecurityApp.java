class ApiService {
    // 2. Blank final variable
    private final String apiKey;

    // 3. Initialized via constructor
    public ApiService(String key) {
        this.apiKey = key;
    }

    public void showKey() {
        System.out.println("Active API Key: " + apiKey);
    }

    // 4. Attempting to change it
    public void updateKey(String newKey) {
        // apiKey = newKey; // ERROR: Cannot assign a value to final variable 'apiKey'
    }
}

public class SecurityApp {
    public static void main(String[] args) {
        ApiService service = new ApiService("PROD-9988-X1");
        service.showKey();
    }
}