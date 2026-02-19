public class TicketPricing {
    public static void main(String[] args) {
        // 1. Declare the age variable
        int age = 70; // Change this value to test different paths
        
        int ticketPrice;

        // 2. Evaluate the age to determine the price
        if (age < 12) {
            ticketPrice = 8;
        } else if (age >= 65) {
            ticketPrice = 10;
        } else {
            // This acts as the catch-all for anyone 12 to 64
            ticketPrice = 15; 
        }

        // 3. Print the final price
        System.out.println("Based on age " + age + ", the ticket price is $" + ticketPrice);
    }
}