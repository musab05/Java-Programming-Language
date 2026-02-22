import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Guest {
    private String name;
    private String passportNumber;

    public Guest(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() { return name; }
    public String getPassportNumber() { return passportNumber; }

    // --- The Magic Happens Here ---
    
    @Override
    public boolean equals(Object o) {
        // 1. If it's the exact same object in memory, it's equal.
        if (this == o) return true;
        
        // 2. If it's null or a completely different class, it's not equal.
        if (o == null || getClass() != o.getClass()) return false;
        
        // 3. Cast the object to a Guest and compare the passport numbers.
        // Notice we completely ignore the 'name' field!
        Guest guest = (Guest) o;
        return Objects.equals(passportNumber, guest.passportNumber);
    }

    @Override
    public int hashCode() {
        // The hashCode MUST use the exact same fields as the equals() method.
        return Objects.hash(passportNumber);
    }
    
    @Override
    public String toString() {
        return name + " (" + passportNumber + ")";
    }
}

public class ClubEntry {
    public static void main(String[] args) {
        Set<Guest> vipList = new HashSet<>();

        Guest alice = new Guest("Alice", "US123");
        Guest bob = new Guest("Bob", "UK456");
        
        // Someone trying to use Alice's passport!
        Guest fraud = new Guest("Alice Fraud", "US123"); 

        System.out.println("Adding Alice: " + vipList.add(alice)); // Prints true
        System.out.println("Adding Bob: " + vipList.add(bob));     // Prints true
        
        // The HashSet uses hashCode() and equals() to realize "US123" is already inside!
        System.out.println("Adding Fraud: " + vipList.add(fraud)); // Prints false!

        System.out.println("\nTotal VIPs approved: " + vipList.size()); // Size is 2
        System.out.println("Final Guest List: " + vipList);
    }
}