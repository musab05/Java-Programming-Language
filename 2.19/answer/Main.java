import java.util.ArrayList;
import java.util.List;

class DataProcessor {
    public void process(Object data) {
        // Using Java 16+ Pattern Matching
        if (data instanceof String s) {
            // 's' is already cast to a String!
            System.out.println("String uppercase: " + s.toUpperCase());
        } 
        else if (data instanceof Integer i) {
            // 'i' is already cast to an Integer!
            System.out.println("Integer multiplied: " + (i * 10));
        } 
        else if (data instanceof List<?> list) {
            // 'list' is already cast to a List!
            System.out.println("List size: " + list.size());
        } 
        else {
            System.out.println("Unknown data type: " + data.getClass().getSimpleName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        
        processor.process("hello");                   // String
        processor.process(5);                         // Integer
        processor.process(new ArrayList<String>());   // List
        processor.process(99.99);                     // Double (Unknown)
    }
}