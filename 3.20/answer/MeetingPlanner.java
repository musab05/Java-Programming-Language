import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingPlanner {
    public static void main(String[] args) {
        // 1. Local meeting time in New York
        LocalDateTime localMeeting = LocalDateTime.of(2026, 3, 15, 9, 0);

        // 2. Attach the New York Zone ID
        ZoneId nyZone = ZoneId.of("America/New_York");
        ZonedDateTime nyMeeting = ZonedDateTime.of(localMeeting, nyZone);

        // 3. Convert to Tokyo time zone
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoMeeting = nyMeeting.withZoneSameInstant(tokyoZone);

        // Formatting for readability
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy @ hh:mm a (z)");

        System.out.println("--- Global Meeting Planner ---");
        System.out.println("New York Time: " + nyMeeting.format(formatter));
        System.out.println("Tokyo Time:    " + tokyoMeeting.format(formatter));
        
        System.out.println("\nNote: Tokyo is 13 hours ahead of New York in March.");
    }
}