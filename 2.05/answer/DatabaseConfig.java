public class DatabaseConfig {
    // 2. Strictly hidden data
    private String dbUrl;

    // 3. Set exactly once during instantiation
    public DatabaseConfig(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    // 4. Public Getter (Read access only)
    public String getDbUrl() {
        return dbUrl;
    }

    // 5. Notice there is NO setDbUrl() method!
    
    public static void main(String[] args) {
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/prod_db");
        System.out.println("Connected to: " + config.getDbUrl());
        
        // config.dbUrl = "new_url"; // ERROR: dbUrl has private access
        // config.setDbUrl("new_url"); // ERROR: method does not exist
    }
}