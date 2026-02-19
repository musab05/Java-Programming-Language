package answer;
public class EmployeeProfile {

    // 1. Static Variable: Shared by all instances of EmployeeProfile
    static String companyName = "TechCorp";

    // 2. Instance Variables: Unique to each created EmployeeProfile object
    String fullName;
    int employeeId;
    boolean isFullTime;

    // 3. Local Variable: Only exists inside this method while it runs
    public void calculateBonus() {
        double bonusAmount = 500.00; // Must be initialized before use!
        System.out.println("Bonus Amount: $" + bonusAmount);
    }

    // Optional: A main method to test and prove the code works
    public static void main(String[] args) {
        // Creating an instance of the class
        EmployeeProfile emp1 = new EmployeeProfile();
        
        // Assigning values to instance variables
        emp1.fullName = "Musab";
        emp1.employeeId = 101;
        emp1.isFullTime = true;

        // Printing the results
        System.out.println("Company: " + EmployeeProfile.companyName); // Accessed via Class name
        System.out.println("Employee: " + emp1.fullName);              // Accessed via Object name
        
        // Calling the method to trigger the local variable
        emp1.calculateBonus(); 
    }
}