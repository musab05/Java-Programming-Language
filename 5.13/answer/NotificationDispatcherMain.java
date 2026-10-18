import java.util.List;

public class NotificationDispatcherMain {

    public enum Priority { NORMAL, HIGH, URGENT }

    // Sealed notification events
    public sealed interface Notification permits EmailAlert, SmsAlert, PushAlert {}

    public record EmailAlert(String recipientEmail, String subject, String body, Priority priority) implements Notification {}
    public record SmsAlert(String phoneNumber, String message, Priority priority) implements Notification {}
    public record PushAlert(String deviceToken, String title, String payload, boolean isSilent) implements Notification {}

    // Dispatcher service using Pattern Matching for switch with guards
    public static class Dispatcher {
        private final boolean doNotDisturbMode;

        public Dispatcher(boolean doNotDisturbMode) {
            this.doNotDisturbMode = doNotDisturbMode;
        }

        public String dispatch(Notification notification) {
            return switch (notification) {
                // Guard: If DND is enabled, suppress normal SMS/Push unless URGENT
                case SmsAlert s when doNotDisturbMode && s.priority() != Priority.URGENT ->
                    "[SUPPRESSED] SMS to " + s.phoneNumber() + " blocked by Do-Not-Disturb mode.";

                case SmsAlert(String phone, String msg, Priority prio) ->
                    "[SMS SENT] To: " + phone + " | Priority: " + prio + " | Body: " + msg;

                case PushAlert p when p.isSilent() ->
                    "[BACKGROUND PUSH] Delivered silent payload to device " + p.deviceToken();

                case PushAlert(String token, String title, String payload, boolean silent) when doNotDisturbMode ->
                    "[SUPPRESSED] Push alert '" + title + "' blocked by Do-Not-Disturb.";

                case PushAlert(String token, String title, String payload, boolean silent) ->
                    "[PUSH SENT] '" + title + "' to " + token + " -> " + payload;

                case EmailAlert(String email, String subject, String body, Priority prio) when prio == Priority.URGENT ->
                    "[URGENT EMAIL SENT] Priority: URGENT -> To: " + email + " | Subject: " + subject;

                case EmailAlert(String email, String subject, String body, Priority prio) ->
                    "[STANDARD EMAIL] To: " + email + " | Subject: " + subject;

                case null ->
                    "[ERROR] Null notification dropped safely.";
            };
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Notification Dispatcher: Guarded Pattern Matching ===");

        Dispatcher normalDispatcher = new Dispatcher(false);
        Dispatcher dndDispatcher = new Dispatcher(true);

        List<Notification> testEvents = List.of(
                new SmsAlert("+1-555-0199", "Your one-time passcode is 492019", Priority.URGENT),
                new SmsAlert("+1-555-0144", "Flash sale! 20% off all shoes today!", Priority.NORMAL),
                new PushAlert("TOKEN_ABC_99", "Daily Reminder", "Don't forget to stretch", false),
                new PushAlert("TOKEN_XYZ_11", "Sync", "sync_contacts_data", true),
                new EmailAlert("security@acme.corp", "Critical Breach Detected", "Root login failure", Priority.URGENT),
                new EmailAlert("user@acme.corp", "Monthly Newsletter", "Check out new features", Priority.NORMAL)
        );

        System.out.println("--- Dispatching under Normal Mode ---");
        for (Notification n : testEvents) {
            System.out.println(normalDispatcher.dispatch(n));
        }

        System.out.println("\n--- Dispatching under Do-Not-Disturb Mode ---");
        for (Notification n : testEvents) {
            System.out.println(dndDispatcher.dispatch(n));
        }

        System.out.println("\n--- Testing Null Safety ---");
        System.out.println(dndDispatcher.dispatch(null));
    }
}
