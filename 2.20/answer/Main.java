import java.util.Arrays;
import java.util.List;

class DataCleaner {
    
    public Double calculateAverage(List<String> rawScores) {
        // Return null if there's no data (advantage of returning a Wrapper Object!)
        if (rawScores == null || rawScores.isEmpty()) {
            return null; 
        }

        double sum = 0.0;
        int count = 0;

        for (String score : rawScores) {
            // 1. Check for null or "N/A"
            if (score == null || score.trim().equalsIgnoreCase("N/A")) {
                continue; // Skip to the next iteration
            }

            // 2. Try parsing the valid strings
            try {
                // Double.parseDouble() is a highly useful wrapper utility
                sum += Double.parseDouble(score.trim());
                count++;
            } catch (NumberFormatException e) {
                // 3. Ignore corrupted data (e.g., "abc")
                System.out.println("Skipping invalid number format: " + score);
            }
        }

        // Avoid division by zero if no valid numbers were found
        return count == 0 ? null : (sum / count);
    }
}

public class Main {
    public static void main(String[] args) {
        DataCleaner cleaner = new DataCleaner();
        
        List<String> incomingApiData = Arrays.asList(
            "95.5", "N/A", null, "80.0", "corrupted_data", "100"
        );
        
        Double average = cleaner.calculateAverage(incomingApiData);
        System.out.println("\nFinal Average: " + average);
    }
}