import java.util.*;

public class StudentRecordMain {

    public record StudentReport(String id, String name, List<Double> grades) implements Comparable<StudentReport> {
        // Compact constructor for validation and defensive copying
        public StudentReport {
            Objects.requireNonNull(id, "ID cannot be null");
            Objects.requireNonNull(name, "Name cannot be null");
            Objects.requireNonNull(grades, "Grades cannot be null");

            if (id.isBlank()) throw new IllegalArgumentException("ID cannot be blank");
            if (name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");

            // Make defensive immutable copy
            grades = List.copyOf(grades);
        }

        // Convenience secondary constructor
        public StudentReport(String id, String name) {
            this(id, name, List.of());
        }

        // Domain methods
        public double calculateGPA() {
            if (grades.isEmpty()) return 0.0;
            return grades.stream()
                         .mapToDouble(Double::doubleValue)
                         .average()
                         .orElse(0.0);
        }

        public boolean isHonors() {
            return calculateGPA() >= 3.8;
        }

        public StudentReport withGrade(double grade) {
            if (grade < 0.0 || grade > 4.0) {
                throw new IllegalArgumentException("Grade must be between 0.0 and 4.0: " + grade);
            }
            List<Double> updated = new ArrayList<>(this.grades);
            updated.add(grade);
            return new StudentReport(this.id, this.name, updated);
        }

        @Override
        public int compareTo(StudentReport other) {
            return Double.compare(other.calculateGPA(), this.calculateGPA()); // Highest GPA first
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Java Records: StudentReport ===");

        List<Double> initialGrades = new ArrayList<>(Arrays.asList(3.9, 4.0, 3.8));
        StudentReport alice = new StudentReport("S101", "Alice Johnson", initialGrades);

        // Demonstrating shallow immutability protection
        initialGrades.add(2.0); // Modifying original list outside the record
        System.out.println("Alice's grades unchanged: " + alice.grades());
        System.out.println("Alice GPA: " + String.format("%.2f", alice.calculateGPA()));
        System.out.println("Is Alice Honors? " + alice.isHonors());

        // Functional modification via 'withGrade'
        StudentReport bob = new StudentReport("S102", "Bob Smith")
                .withGrade(3.5)
                .withGrade(3.7)
                .withGrade(3.9);

        System.out.println("\nBob: " + bob);
        System.out.println("Bob GPA: " + String.format("%.2f", bob.calculateGPA()));
        System.out.println("Is Bob Honors? " + bob.isHonors());

        // Sorting records using Comparable
        List<StudentReport> students = new ArrayList<>(List.of(alice, bob));
        Collections.sort(students);

        System.out.println("\nRanked Students by GPA:");
        for (int i = 0; i < students.size(); i++) {
            StudentReport s = students.get(i);
            System.out.printf("%d. %s (%s) - GPA: %.2f%n", (i + 1), s.name(), s.id(), s.calculateGPA());
        }
    }
}
