import java.util.*;
import java.util.stream.Collectors;

public class GradePartitionMain {

    static class Student {
        String name;
        int score;

        Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Alice", 92),
            new Student("Bob", 55),
            new Student("Charlie", 78),
            new Student("Diana", 45),
            new Student("Eve", 88),
            new Student("Frank", 60),
            new Student("Grace", 38),
            new Student("Hank", 71)
        );

        // Partition into passing (>= 60) and failing
        Map<Boolean, List<Student>> partitioned = students.stream()
            .collect(Collectors.partitioningBy(s -> s.score >= 60));

        // Report for each group
        for (Map.Entry<Boolean, List<Student>> entry : partitioned.entrySet()) {
            String label = entry.getKey() ? "PASSING" : "FAILING";
            List<Student> group = entry.getValue();

            long count = group.size();

            double avgScore = group.stream()
                .collect(Collectors.averagingInt(s -> s.score));

            String names = group.stream()
                .map(s -> s.name)
                .collect(Collectors.joining(", "));

            System.out.println("=== " + label + " ===");
            System.out.println("  Count: " + count);
            System.out.printf("  Average Score: %.1f%n", avgScore);
            System.out.println("  Students: " + names);
            System.out.println();
        }
    }
}
