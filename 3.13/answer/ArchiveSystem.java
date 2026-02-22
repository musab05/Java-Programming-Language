import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataMigrator {
    
    // 2, 3 & 4. The PECS Method Signature
    // Source: "Producer Extends" - We are READING from it, so it can be Number or any child (Integer, Double).
    // Destination: "Consumer Super" - We are WRITING to it, so it must be Number or any parent (Object).
    public static void migrateData(List<? extends Number> source, List<? super Number> destination) {
        
        System.out.println("Migrating " + source.size() + " records...");
        
        // 5. Iterating through the source and adding to the destination
        for (Number record : source) {
            destination.add(record);
        }
    }
}

public class ArchiveSystem {
    public static void main(String[] args) {
        // 6. Setup the distinct lists
        // A specific list of Integers (Child of Number)
        List<Integer> oldDatabaseIds = Arrays.asList(101, 102, 103); 
        
        // A specific list of Doubles (Child of Number)
        List<Double> oldBalances = Arrays.asList(45.50, 100.25);
        
        // A generalized master archive (Exact match of Number, or could be Object!)
        List<Number> masterArchive = new ArrayList<>();

        // 7. Execute the migration. 
        // The wildcards in our method signature are what make these lines compile legally!
        DataMigrator.migrateData(oldDatabaseIds, masterArchive);
        DataMigrator.migrateData(oldBalances, masterArchive);

        System.out.println("\nMaster Archive Contents: " + masterArchive);
    }
}