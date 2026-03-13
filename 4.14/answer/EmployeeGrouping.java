import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

class Employee {
    String name;
    String department;

    Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getDepartment() { return department; }
}

public class EmployeeGrouping {
    public static void main(String[] args) {
        String[] depts = {"HR", "IT", "Finance", "Sales", "Marketing"};
        Random rand = new Random();

        // Generate 1,000,000 mock employees
        List<Employee> employees = IntStream.range(0, 1_000_000)
                .mapToObj(i -> new Employee("Emp" + i, depts[rand.nextInt(depts.length)]))
                .collect(Collectors.toList());

        System.out.println("Starting grouping...");
        long start = System.currentTimeMillis();

        // Using groupingByConcurrent for maximum parallel performance
        Map<String, List<Employee>> grouped = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Employee::getDepartment));

        long end = System.currentTimeMillis();
        
        grouped.forEach((dept, list) -> 
            System.out.println(dept + ": " + list.size() + " employees"));
            
        System.out.println("Grouping completed in: " + (end - start) + "ms");
    }
}