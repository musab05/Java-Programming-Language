import java.util.*;
import java.util.stream.Collectors;

public class WordCounterMain {

    public static void main(String[] args) {
        String text = """
            Java is a powerful programming language. Java is used
            in many applications. Programming in Java is fun and
            Java makes programming easier. The language is popular
            and the community is strong. Many developers love Java
            and use Java for building great applications.
            """;

        // Single pipeline: split → clean → lowercase → count → sort → top 5
        Map<String, Long> wordFrequency = Arrays.stream(text.split("\\s+"))
            .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z]", ""))
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(
                word -> word,
                Collectors.counting()
            ));

        System.out.println("=== Top 5 Most Frequent Words ===");
        wordFrequency.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .forEach(entry ->
                System.out.println(entry.getKey() + " : " + entry.getValue() + " times"));
    }
}
