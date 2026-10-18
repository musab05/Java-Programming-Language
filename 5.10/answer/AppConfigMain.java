import java.util.*;

public class AppConfigMain {

    static final class AppConfig {
        private final String host;
        private final int port;
        private final int maxRetries;
        private final int timeout;
        private final List<String> features;

        private AppConfig(Builder builder) {
            this.host = builder.host;
            this.port = builder.port;
            this.maxRetries = builder.maxRetries;
            this.timeout = builder.timeout;
            this.features = List.copyOf(builder.features); // Defensive copy!
        }

        public String getHost() { return host; }
        public int getPort() { return port; }
        public int getMaxRetries() { return maxRetries; }
        public int getTimeout() { return timeout; }
        public List<String> getFeatures() { return features; } // Already unmodifiable

        @Override
        public String toString() {
            return String.format("AppConfig{host='%s', port=%d, retries=%d, timeout=%d, features=%s}",
                host, port, maxRetries, timeout, features);
        }

        // The Builder
        static class Builder {
            private String host = "localhost";
            private int port = 8080;
            private int maxRetries = 3;
            private int timeout = 5000;
            private List<String> features = new ArrayList<>();

            public Builder host(String host) { this.host = host; return this; }
            public Builder port(int port) { this.port = port; return this; }
            public Builder maxRetries(int retries) { this.maxRetries = retries; return this; }
            public Builder timeout(int timeout) { this.timeout = timeout; return this; }
            public Builder addFeature(String feature) { this.features.add(feature); return this; }

            public AppConfig build() {
                return new AppConfig(this);
            }
        }
    }

    public static void main(String[] args) {
        AppConfig config = new AppConfig.Builder()
            .host("api.production.com")
            .port(443)
            .maxRetries(5)
            .timeout(3000)
            .addFeature("caching")
            .addFeature("rate-limiting")
            .addFeature("compression")
            .build();

        System.out.println(config);

        // Prove immutability — this will throw UnsupportedOperationException
        try {
            config.getFeatures().add("hacking");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nCannot modify features list — immutability works!");
        }
    }
}
