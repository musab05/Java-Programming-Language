import java.util.*;
import java.util.stream.Collectors;

public class FlatMapMain {

    static class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return name + " ($" + salary + ")";
        }
    }

    static class Department {
        String name;
        List<Employee> employees;

        Department(String name, List<Employee> employees) {
            this.name = name;
            this.employees = employees;
        }
    }

    public static void main(String[] args) {
        List<Department> departments = List.of(
            new Department("Engineering", List.of(
                new Employee("Alice", 95000),
                new Employee("Bob", 88000),
                new Employee("Charlie", 102000),
                new Employee("Diana", 78000)
            )),
            new Department("Marketing", List.of(
                new Employee("Eve", 72000),
                new Employee("Frank", 68000),
                new Employee("Grace", 81000)
            )),
            new Department("Finance", List.of(
                new Employee("Hank", 91000),
                new Employee("Ivy", 85000),
                new Employee("Jack", 110000)
            ))
        );

        // flatMap: flatten all employees from all departments into a single stream
        // Highest-paid employee
        Employee topEarner = departments.stream()
            .flatMap(dept -> dept.employees.stream())
            .max(Comparator.comparingDouble(e -> e.salary))
            .orElseThrow();
        System.out.println("Highest paid: " + topEarner);

        // Average salary across all departments
        double avgSalary = departments.stream()
            .flatMap(dept -> dept.employees.stream())
            .mapToDouble(e -> e.salary)
            .average()
            .orElse(0.0);
        System.out.printf("Average salary: $%.2f%n", avgSalary);

        // Employees earning above average
        System.out.println("\nEmployees above average:");
        departments.stream()
            .flatMap(dept -> dept.employees.stream())
            .filter(e -> e.salary > avgSalary)
            .sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed())
            .forEach(System.out::println);
    }
}
