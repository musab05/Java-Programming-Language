class LegacyDatabase {
    public void connect() {
        // Randomly succeed or fail
        if (Math.random() > 0.5) {
            throw new RuntimeException("Connection timeout to database.");
        }
        System.out.println("Successfully connected to the database!");
    }

    public void closeConnection() {
        System.out.println("Database connection closed safely.");
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Declare the variable OUTSIDE the try block
        LegacyDatabase db = null;

        try {
            System.out.println("Attempting database connection...");
            // 2. Instantiate and use it INSIDE the try block
            db = new LegacyDatabase();
            db.connect();
            
        } catch (RuntimeException e) {
            // 3. Catch the specific failure
            System.out.println("Error: " + e.getMessage());
            
        } finally {
            // 4. The Cleanup Crew
            // We MUST check for null here. If the 'new LegacyDatabase()' instantiation 
            // failed, 'db' would be null, and calling closeConnection() would throw a 
            // NullPointerException, masking the original error!
            if (db != null) {
                db.closeConnection();
            } else {
                System.out.println("Database was never instantiated. Nothing to close.");
            }
        }
    }
}