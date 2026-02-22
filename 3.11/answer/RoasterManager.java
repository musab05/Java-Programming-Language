import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RosterManager {
    public static void main(String[] args) {
        // 1. Setup the Set
        Set<Integer> employeeIds = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            employeeIds.add(i);
        }
        
        System.out.println("Original Roster: " + employeeIds);

        // 2. Instantiate the Iterator
        Iterator<Integer> iterator = employeeIds.iterator();

        // 3. Traverse the Set
        while (iterator.hasNext()) {
            // 4. Move the cursor forward and get the value
            int id = iterator.next();
            
            // 5. Check the business logic
            if (id % 2 == 0) {
                // Safely remove the item the cursor just passed!
                // NEVER use employeeIds.remove(id) here!
                iterator.remove(); 
            }
        }

        // 6. Verify the results (Only odd numbers should remain)
        System.out.println("Purged Roster (Odds only): " + employeeIds);
    }
}