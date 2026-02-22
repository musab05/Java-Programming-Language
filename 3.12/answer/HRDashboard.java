import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 1. A standard POJO (Plain Old Java Object) - No Comparable interface needed!
class Employee {
    private String department;
    private double salary;
    private String name;

    public Employee(String department, double salary, String name) {
        this.department = department;
        this.salary = salary;
        this.name = name;
    }

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%,.2f", department, name, salary);
    }
}

public class HRDashboard {
    public static void main(String[] args) {
        // 2. Setup the overlapping data
        List<Employee> staff = new ArrayList<>();
        staff.add(new Employee("Engineering", 90000, "Zack"));
        staff.add(new Employee("Engineering", 120000, "Alice"));
        staff.add(new Employee("Engineering", 90000, "Bob"));
        staff.add(new Employee("Sales", 60000, "Charlie"));
        staff.add(new Employee("Sales", 85000, "Diana"));

        // 3. The Chained Comparator (The magic of Java 8)
        Comparator<Employee> complexSorter = Comparator
            .comparing(Employee::getDepartment) // Level 1: A-Z by Dept
            .thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed()) // Level 2: High to Low Salary
            .thenComparing(Employee::getName); // Level 3: A-Z by Name (Tie-breaker)

        // 4. Apply the sort
        staff.sort(complexSorter);

        System.out.println("--- HR Roster (Sorted by Dept -> Salary(Desc) -> Name) ---");
        for (Employee emp : staff) {
            System.out.println(emp);
        }
    }
}