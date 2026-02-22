import java.util.Map;
import java.util.TreeMap;

class FlightRadar {
    // 1. Initializing a TreeMap to keep the schedule strictly sorted by time
    private TreeMap<Integer, String> schedule = new TreeMap<>();

    public void registerFlight(int time, String flightNumber) {
        schedule.put(time, flightNumber);
        System.out.println("Registered " + flightNumber + " at " + time + " hours.");
    }

    public void getNextFlightAfter(int time) {
        System.out.println("\n--- Searching for next flight after " + time + " ---");
        
        // 2. higherEntry() finds the strictly next mapping > the given key
        Map.Entry<Integer, String> nextFlight = schedule.higherEntry(time);
        
        if (nextFlight != null) {
            System.out.println("Next flight is " + nextFlight.getValue() + " at " + nextFlight.getKey());
        } else {
            System.out.println("No flights scheduled after " + time + ".");
        }
    }

    public void getFlightsBetween(int startTime, int endTime) {
        System.out.println("\n--- Flights between " + startTime + " and " + endTime + " ---");
        
        // 3. subMap(fromKey, fromInclusive, toKey, toInclusive)
        // This creates a highly efficient "view" of just the flights in that window!
        Map<Integer, String> window = schedule.subMap(startTime, true, endTime, true);
        
        if (window.isEmpty()) {
            System.out.println("No flights in this time window.");
        } else {
            for (Map.Entry<Integer, String> flight : window.entrySet()) {
                System.out.println("Time: " + flight.getKey() + " | Flight: " + flight.getValue());
            }
        }
    }
}

public class AirTrafficControl {
    public static void main(String[] args) {
        FlightRadar radar = new FlightRadar();

        radar.registerFlight(830, "DL123");
        radar.registerFlight(915, "UA456");
        radar.registerFlight(1400, "AA789");
        radar.registerFlight(1645, "SW101");
        radar.registerFlight(2030, "JB202");

        radar.getNextFlightAfter(1200); // Should find AA789 at 1400
        radar.getFlightsBetween(900, 1700); // Should find UA456, AA789, SW101
    }
}