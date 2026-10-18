import java.util.*;
import java.util.function.Function;

public class FormatterMain {

    // Simple User record
    record User(String name, String email, int age) {}

    public static void formatUser(User user, List<Function<User, String>> formatters) {
        System.out.println("=== Formatting User: " + user.name() + " ===");
        for (Function<User, String> formatter : formatters) {
            System.out.println(formatter.apply(user));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        User alice = new User("alice johnson", "alice.j@email.com", 28);
        User bob = new User("bob smith", "bob.smith@work.org", 35);

        // Define formatters as Functions stored in variables
        Function<User, String> nameFormatter = u -> "Name: " + u.name().toUpperCase();

        Function<User, String> emailMasker = u -> {
            String email = u.email();
            String masked = email.substring(0, 2) + "***" + email.substring(email.indexOf("@"));
            return "Email: " + masked;
        };

        Function<User, String> ageLabel = u -> "Age: " + u.age() + (u.age() >= 30 ? " (Senior)" : " (Junior)");

        // Store them in a list — functions as data!
        List<Function<User, String>> formatters = List.of(nameFormatter, emailMasker, ageLabel);

        formatUser(alice, formatters);
        formatUser(bob, formatters);
    }
}
