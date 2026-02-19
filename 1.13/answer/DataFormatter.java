public class DataFormatter {

    // 1. Method accepting an int
    public static void format(int value) {
        System.out.println("Integer value: " + value);
    }

    // 2. Overloaded method accepting a double
    public static void format(double value) {
        System.out.println("Decimal value: " + value);
    }

    // 3. Overloaded method accepting a String
    public static void format(String value) {
        System.out.println("Text string: " + value);
    }

    public static void main(String[] args) {
        // 4. The compiler routes these based on the data type passed
        format(42);               // Routes to the int method
        format(3.14159);          // Routes to the double method
        format("Overloading!");   // Routes to the String method
    }
}

/*
Imagine if you were forced to use different names like formatInt(), formatDouble(), and formatString(). The code would become much harder to read and remember! Overloading allows developers to create clean, intuitive APIs (like System.out.println(), which is heavily overloaded under the hood to print almost any data type).
*/