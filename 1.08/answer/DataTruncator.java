public class DataTruncator {
    public static void main(String[] args) {
        // 1. Declare the exact interest as a double
        double exactInterest = 45.89;
        
        // 2. Explicitly cast it to an int (Narrowing)
        // We must put (int) in front of the value to force the conversion
        int displayDollars = (int) exactInterest;
        
        // 3. Print both variables to observe the truncation
        System.out.println("Exact Interest: $" + exactInterest);
        System.out.println("Display Dollars: $" + displayDollars);
        // The output for displayDollars will be $45 (the .89 is completely chopped off, not rounded)
    }
}