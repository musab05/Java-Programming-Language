import java.util.*;

public class PluginSystemMain {

    interface Plugin {
        String getName();

        default void initialize() {
            System.out.println("[" + getName() + "] initialized.");
        }

        default void shutdown() {
            System.out.println("[" + getName() + "] shut down.");
        }

        default int getPriority() {
            return 5; // Default priority
        }
    }

    static class DatabasePlugin implements Plugin {
        @Override public String getName() { return "Database"; }

        @Override
        public int getPriority() { return 1; } // Highest priority
    }

    static class CachePlugin implements Plugin {
        @Override public String getName() { return "Cache"; }

        @Override
        public void initialize() {
            Plugin.super.initialize(); // Call the default first
            System.out.println("[Cache] Warming up cache entries...");
        }
    }

    static class LogPlugin implements Plugin {
        @Override public String getName() { return "Logger"; }
        // Uses ALL defaults — no overrides needed!
    }

    public static void main(String[] args) {
        List<Plugin> plugins = List.of(
            new LogPlugin(),
            new CachePlugin(),
            new DatabasePlugin()
        );

        // Sort by priority (ascending) and run lifecycle
        System.out.println("=== Starting System ===");
        plugins.stream()
            .sorted(Comparator.comparingInt(Plugin::getPriority))
            .forEach(p -> {
                System.out.println("--- Priority: " + p.getPriority() + " ---");
                p.initialize();
            });

        System.out.println("\n=== Shutting Down ===");
        plugins.stream()
            .sorted(Comparator.comparingInt(Plugin::getPriority))
            .forEach(Plugin::shutdown);
    }
}
