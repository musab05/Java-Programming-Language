import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class CSVParser {

    public void printUsers() {
        System.out.println("\n--- Parsing User Data ---");
        
        // 1. Try-With-Resources wrapping a BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
            
            String line;
            
            // 2. Read lines until we hit the End of File (EOF), which returns null
            while ((line = reader.readLine()) != null) {
                
                // 3. Split the comma-separated string into an array
                String[] data = line.split(",");
                
                // 4. Basic validation to ensure the row isn't corrupted
                if (data.length == 3) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    String dept = data[2].trim();
                    
                    System.out.println("ID: " + id + " | Name: " + name + " | Dept: " + dept);
                } else {
                    System.out.println("Skipping malformed row: " + line);
                }
            }
            
        } catch (IOException e) {
            System.err.println("Failed to read the CSV file: " + e.getMessage());
        }
    }
}

public class DataImporter {
    public static void main(String[] args) {
        // --- Setup Step: Generate the dummy CSV so this code runs perfectly ---
        createDummyCSV();
        
        // --- Actual Parsing Step ---
        CSVParser parser = new CSVParser();
        parser.printUsers();
    }
    
    // A quick helper method to create our test file
    private static void createDummyCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.csv"))) {
            writer.write("101,Alice,Engineering"); writer.newLine();
            writer.write("102,Bob,Human Resources"); writer.newLine();
            writer.write("103,Charlie,Sales"); writer.newLine();
            writer.write("104,MalformedDataOops"); writer.newLine(); // Testing our validation!
        } catch (IOException e) {
            System.out.println("Failed to setup dummy data.");
        }
    }
}