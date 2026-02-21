import java.io.FileNotFoundException;
import java.io.FileReader;

class ConfigLoader {

    // Notice there is NO 'throws FileNotFoundException' here. 
    // We are handling the problem internally.
    public void loadConfig(String filePath) {
        try {
            System.out.println("Attempting to load config from: " + filePath);
            
            // This line triggers the checked exception if the file is missing
            FileReader reader = new FileReader(filePath); 
            
            System.out.println("Configuration loaded successfully!");
            
        } catch (FileNotFoundException e) {
            // Recovering gracefully instead of crashing
            System.out.println("Warning: Config file not found (" + e.getMessage() + ").");
            System.out.println("Loading default configuration instead.");
        }
    }
}

public class Config {
    public static void main(String[] args) {
        ConfigLoader loader = new ConfigLoader();
        
        // Passing a dummy path to force the exception
        loader.loadConfig("missing_settings.json"); 
    }
}