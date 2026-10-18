import java.util.*;

public class ConfigResolverMain {

    interface ConfigSource {
        Optional<String> get(String key);
        String name();
    }

    static class EnvironmentConfig implements ConfigSource {
        private final Map<String, String> data = Map.of("DB_HOST", "env-db.server.com");
        public Optional<String> get(String key) { return Optional.ofNullable(data.get(key)); }
        public String name() { return "Environment"; }
    }

    static class FileConfig implements ConfigSource {
        private final Map<String, String> data = Map.of(
            "DB_HOST", "file-db.server.com",
            "DB_PORT", "5432"
        );
        public Optional<String> get(String key) { return Optional.ofNullable(data.get(key)); }
        public String name() { return "File"; }
    }

    static class DefaultConfig implements ConfigSource {
        private final Map<String, String> data = Map.of(
            "DB_HOST", "localhost",
            "DB_PORT", "3306",
            "DB_NAME", "myapp"
        );
        public Optional<String> get(String key) { return Optional.ofNullable(data.get(key)); }
        public String name() { return "Default"; }
    }

    static class ConfigResolver {
        private final ConfigSource env = new EnvironmentConfig();
        private final ConfigSource file = new FileConfig();
        private final ConfigSource defaults = new DefaultConfig();

        public String resolve(String key) {
            return env.get(key)
                .or(() -> file.get(key))
                .or(() -> defaults.get(key))
                .orElseThrow(() -> new RuntimeException("Config key not found: " + key));
        }
    }

    public static void main(String[] args) {
        ConfigResolver resolver = new ConfigResolver();

        // DB_HOST found in Environment (highest priority)
        System.out.println("DB_HOST: " + resolver.resolve("DB_HOST"));

        // DB_PORT found in File (Environment doesn't have it)
        System.out.println("DB_PORT: " + resolver.resolve("DB_PORT"));

        // DB_NAME found in Default (only source that has it)
        System.out.println("DB_NAME: " + resolver.resolve("DB_NAME"));
    }
}
