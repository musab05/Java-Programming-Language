import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterMain {

    // The reusable filter method — behavior is passed in via lambda
    public static List<Integer> filterList(List<Integer> list, Predicate<Integer> condition) {
        List<Integer> result = new ArrayList<>();
        for (Integer num : list) {
            if (condition.test(num)) {
                result.add(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-5, 3, 8, -2, 15, 22, 7, -10, 12, 4);

        // Filter 1: Even numbers
        List<Integer> evens = filterList(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + evens);

        // Filter 2: Numbers greater than 10
        List<Integer> greaterThan10 = filterList(numbers, n -> n > 10);
        System.out.println("Greater than 10: " + greaterThan10);

        // Filter 3: Negative numbers
        List<Integer> negatives = filterList(numbers, n -> n < 0);
        System.out.println("Negative numbers: " + negatives);
    }
}
