import java.time.LocalDate;
import java.time.Period;

class Subscription {
    private LocalDate startDate;
    private LocalDate expiryDate;

    public Subscription() {
        // 2. Set start date to today
        this.startDate = LocalDate.now();
        
        // 3. Add 1 year, 6 months, and 15 days
        this.expiryDate = startDate.plusYears(1)
                                   .plusMonths(6)
                                   .plusDays(15);
    }

    public void printRemainingTime() {
        LocalDate today = LocalDate.now();
        
        // 4. Calculate the difference using Period
        Period remaining = Period.between(today, expiryDate);

        // 5. Output the result
        System.out.println("Start Date: " + startDate);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("Your subscription expires in: " 
            + remaining.getYears() + " years, " 
            + remaining.getMonths() + " months, and " 
            + remaining.getDays() + " days.");
    }
}

public class SubscriptionApp {
    public static void main(String[] args) {
        Subscription sub = new Subscription();
        sub.printRemainingTime();
    }
}