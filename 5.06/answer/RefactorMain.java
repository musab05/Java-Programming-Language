import java.util.*;
import java.util.stream.Collectors;

public class RefactorMain {

    // Utility class with static helper methods
    static class StringUtils {
        static boolean isNotEmpty(String s) {
            return s != null && !s.isEmpty();
        }

        static String trimAndUpper(String s) {
            return s.trim().toUpperCase();
        }
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Charlie", "", "Alice", " ", "Bob", null, "Diana", "");

        System.out.println("=== Before: Using Lambdas ===");
        names.stream()
            .filter(s -> s != null)
            .filter(s -> !s.trim().isEmpty())
            .map(s -> s.toUpperCase())
            .sorted((a, b) -> a.compareTo(b))
            .forEach(s -> System.out.println(s));

        System.out.println("\n=== After: Using Method References ===");
        names.stream()
            .filter(Objects::nonNull)               // Static method ref
            .filter(StringUtils::isNotEmpty)         // Custom static method ref
            .map(String::toUpperCase)                // Arbitrary instance method ref
            .sorted(String::compareTo)               // Arbitrary instance method ref
            .forEach(System.out::println);           // Specific instance method ref
    }
}
