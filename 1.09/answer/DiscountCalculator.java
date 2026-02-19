public class DiscountCalculator {
    public static void main(String[] args) {
        // 1. Declare the variables
        boolean isVip = true;
        double cartTotal = 85.50;
        boolean isSuspended = false;

        // 2. Write the combined boolean expression
        // They get a discount IF: (VIP OR Spent > 100) AND (NOT Suspended)
        boolean getsDiscount = (isVip || cartTotal > 100.00) && !isSuspended;

        // 3. Print the result
        System.out.println("Eligible for discount: " + getsDiscount);
        // Output will be: true (Because they are VIP, which satisfies the first half, and they are not suspended).
    }
}