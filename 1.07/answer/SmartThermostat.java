package answer;
public class SmartThermostat {
    public static void main(String[] args) {
        
        // 1. Temperature (-20 to 120)
        // Why byte? A byte takes up only 1 byte of memory and can hold values 
        // from -128 to 127. This perfectly covers our temperature range, saving 
        // memory compared to an int (4 bytes) or short (2 bytes).
        byte currentTemp = 72; 

        // 2. Exact price (e.g., 149.99)
        // Why float? A float takes up 4 bytes of memory, which is half the size 
        // of a double (8 bytes). We must remember to append the 'f' at the end!
        float unitPrice = 149.99f; 

        // 3. Wi-Fi connection status
        // Why boolean? It is the absolute smallest data type, taking up only 
        // 1 bit of memory to represent true or false.
        boolean isConnected = true; 

        // 4. Manufacturer's initial
        // Why char? A char takes up 2 bytes and is specifically designed to 
        // hold a single character surrounded by single quotes.
        char manufacturerInitial = 'N'; 

        // --- Printing the results to verify ---
        System.out.println("Temp: " + currentTemp + " degrees");
        System.out.println("Price: $" + unitPrice);
        System.out.println("Wi-Fi Connected: " + isConnected);
        System.out.println("Brand Initial: " + manufacturerInitial);
    }
}