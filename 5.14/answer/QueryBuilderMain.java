public class QueryBuilderMain {

    // Method returning formatted JSON configuration using modern text blocks
    public static String buildDatabaseConfig(String host, int port, String database, int poolSize) {
        return """
                {
                  "database": {
                    "connection": {
                      "host": "%s",
                      "port": %d,
                      "schema": "%s"
                    },
                    "pooling": {
                      "maxPoolSize": %d,
                      "idleTimeoutMs": 30000
                    }
                  }
                }
                """.formatted(host, port, database, poolSize);
    }

    // Method demonstrating SQL query with line continuations (\) and trailing spaces (\s)
    public static String buildUserSearchQuery(String role, double minSalary, int limit) {
        return """
                SELECT \
                u.id, \
                u.username, \
                u.email, \
                p.salary \
                FROM app_users u \
                INNER JOIN payroll p ON u.id = p.user_id \
                WHERE u.role = '%s' \
                AND p.salary >= %.2f \
                ORDER BY p.salary DESC \
                LIMIT %d;\
                """.formatted(role, minSalary, limit);
    }

    public static void main(String[] args) {
        System.out.println("=== Modern Java Text Blocks Demonstration ===");

        // 1. JSON Configuration
        String configJson = buildDatabaseConfig("db-primary.internal.net", 5432, "analytics_prod", 20);
        System.out.println("Generated JSON Config:");
        System.out.println(configJson);

        // 2. Continuous SQL Query
        String sql = buildUserSearchQuery("DEVELOPER", 85000.0, 10);
        System.out.println("Generated Single-line SQL Query (via \\ continuation):");
        System.out.println(sql);
        System.out.println("Does SQL contain newlines? " + sql.contains("\n"));

        // 3. Demonstrating string methods: indent and stripIndent
        String simpleText = """
                Item A
                Item B
                Item C
                """;

        System.out.println("\nOriginal indented (4 spaces):");
        System.out.print(simpleText.indent(4));

        // 4. Demonstrating \\s preserving trailing whitespace
        String fixedTable = """
                ID   \s
                1001 \s
                1002 \s
                """;
        System.out.println("Fixed Table Length: " + fixedTable.length());
    }
}
