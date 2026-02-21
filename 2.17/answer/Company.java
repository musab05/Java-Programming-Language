// 1. The base class
class Employee {
    // Protected so subclasses (like our anonymous class) can access it
    protected double baseSalary = 50000.00;

    // 2. The method we will eventually override
    public double calculatePay() {
        return baseSalary;
    }
}

public class Company {
    public static void main(String[] args) {
        // 3. A standard employee instantiation
        Employee standardWorker = new Employee();
        System.out.println("Standard Worker Pay: $" + standardWorker.calculatePay());

        // 4. The CEO instantiation using an Anonymous Inner Class
        // Notice the curly braces immediately following the constructor call!
        Employee ceo = new Employee() {
            @Override
            public double calculatePay() {
                // Overriding the behavior just for this specific 'ceo' object
                return baseSalary * 10;
            }
        };

        // 5. Verifying the overridden behavior
        System.out.println("CEO Pay: $" + ceo.calculatePay());
        
        // Proof that they are different types under the hood:
        System.out.println("Worker class: " + standardWorker.getClass().getName());
        System.out.println("CEO class: " + ceo.getClass().getName());
    }
}