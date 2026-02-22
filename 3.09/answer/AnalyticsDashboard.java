import java.util.LinkedHashSet;
import java.util.Set;

class VisitorTracker {
    // LinkedHashSet prevents duplicates AND maintains insertion order
    private Set<String> uniqueVisitors = new LinkedHashSet<>();

    public void logVisit(String ipAddress) {
        // The add() method returns true if the item was added, 
        // and false if it was already in the Set.
        boolean isNewVisitor = uniqueVisitors.add(ipAddress);
        
        if (isNewVisitor) {
            System.out.println("Logged new visitor: " + ipAddress);
        } else {
            System.out.println("Repeat visitor ignored: " + ipAddress);
        }
    }

    public void printChronologicalVisitors() {
        System.out.println("\n--- Daily Visitor Log (Chronological) ---");
        int count = 1;
        for (String ip : uniqueVisitors) {
            System.out.println(count + ". " + ip);
            count++;
        }
    }
}

public class AnalyticsDashboard {
    public static void main(String[] args) {
        VisitorTracker tracker = new VisitorTracker();

        tracker.logVisit("192.168.1.1"); // 1st
        tracker.logVisit("10.0.0.5");    // 2nd
        tracker.logVisit("172.16.0.4");  // 3rd
        
        // This will be ignored, but importantly, it won't change 
        // the fact that "192.168.1.1" is still sitting in the #1 spot!
        tracker.logVisit("192.168.1.1"); 

        tracker.printChronologicalVisitors();
    }
}