package com.academiq.main;

// Import the specific utility class from the other package
import com.academiq.utils.MathHelper;

public class App {
    public static void main(String[] args) {
        // Now we can use MathHelper because of the import statement above
        int result = MathHelper.multiply(5, 5);
        
        System.out.println("The product is: " + result);
    }
}