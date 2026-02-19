public class AverageScore {
    public static void main(String[] args) {
        // 1. Calculate the total sum of the scores
        int score1 = 88;
        int score2 = 92;
        int score3 = 89;
        int totalSum = score1 + score2 + score3; // 269
        
        // 2. Declare the number of exams
        int numExams = 3;

        // 3. Calculate the exact average using explicit casting
        // We cast totalSum to a double FIRST, making the equation: 269.0 / 3
        double exactAverage = (double) totalSum / numExams;

        // Print the result
        System.out.println("Total Sum: " + totalSum);
        System.out.println("Exact Average: " + exactAverage); 
        // Output will be exactly 89.66666666666667
    }
}