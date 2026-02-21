import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileParser {

    public void readFirstLineAsNumber(String filePath) {
        // 1 & 2. Try-With-Resources syntax
        // The BufferedReader is declared inside the parenthesis.
        // It will AUTOMATICALLY close when this block finishes, even if an error occurs.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            // 3. Read and parse
            String firstLine = br.readLine();
            
            if (firstLine != null) {
                int number = Integer.parseInt(firstLine.trim());
                System.out.println("Successfully parsed number: " + number);
            } else {
                System.out.println("The file is empty.");
            }

        // 4 & 5. The Multi-Catch Block (Java 7+)
        // Catching both a checked exception (IOException) and an 
        // unchecked exception (NumberFormatException) in one clean line.
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to process the file.");
            System.out.println("Error details: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        // No 'finally' block needed! The 'br.close()' happens invisibly.
    }
}

public class File {
    public static void main(String[] args) {
        FileParser parser = new FileParser();
        
        // Test 1: File doesn't exist (Throws IOException)
        System.out.println("--- Test 1 ---");
        parser.readFirstLineAsNumber("missing_file.txt");
        
        // Note: To test the NumberFormatException, you would create a real file 
        // called "data.txt" and put the word "hello" on the first line.
    }
}