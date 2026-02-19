// 1. Import the required java.io classes
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class FastDoubler {
    public static void main(String[] args) {
        // 2. Initialize the BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // 3. Wrap the reading logic in a try-catch block. We will talk about this later in exceptions
        try {
            System.out.print("Enter a number to double: ");
            
            // 4. Read the input as a raw String
            String rawInput = reader.readLine();
            
            // 5. Manually parse the String into an integer
            int number = Integer.parseInt(rawInput);
            
            // 6. Multiply and print using println
            int doubledNumber = number * 2;
            System.out.println("Your doubled number is: " + doubledNumber);
            
        } catch (IOException e) {
            // This block handles the exception if the stream fails
            System.out.println("An error occurred while reading input.");
            e.printStackTrace();
        }
    }
}