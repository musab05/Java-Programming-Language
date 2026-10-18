import java.util.*;
import java.util.stream.Collectors;

public class StudentReportMain {

    static class Student {
        String name;
        int grade;
        String subject;

        Student(String name, int grade, String subject) {
            this.name = name;
            this.grade = grade;
            this.subject = subject;
        }

        @Override
        public String toString() {
            return name + " (" + subject + ": " + grade + ")";
        }
    }

    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Alice", 92, "Math"),
            new Student("Bob", 78, "Science"),
            new Student("Charlie", 88, "Math"),
            new Student("Diana", 95, "English"),
            new Student("Eve", 65, "Science"),
            new Student("Frank", 85, "English"),
            new Student("Grace", 91, "Math"),
            new Student("Hank", 72, "Science")
        );

        // (a) Students who scored above 80
        System.out.println("=== Students scoring above 80 ===");
        students.stream()
            .filter(s -> s.grade > 80)
            .forEach(System.out::println);

        // (b) Average grade per subject
        System.out.println("\n=== Average grade per subject ===");
        Map<String, Double> avgBySubject = students.stream()
            .collect(Collectors.groupingBy(
                s -> s.subject,
                Collectors.averagingInt(s -> s.grade)
            ));
        avgBySubject.forEach((subject, avg) ->
            System.out.printf("%s: %.1f%n", subject, avg));

        // (c) Top 3 students by grade
        System.out.println("\n=== Top 3 Students ===");
        students.stream()
            .sorted(Comparator.comparingInt((Student s) -> s.grade).reversed())
            .limit(3)
            .forEach(System.out::println);
    }
}
