import java.util.function.BinaryOperator;

public class CalculatorMain {

    public static void calculate(int a, int b, BinaryOperator<Integer> operation, String label) {
        int result = operation.apply(a, b);
        System.out.println(label + ": " + a + " op " + b + " = " + result);
    }

    public static void main(String[] args) {
        int x = 20, y = 7;

        // Each operation is just a lambda — no Strategy classes needed
        calculate(x, y, (a, b) -> a + b, "Addition");
        calculate(x, y, (a, b) -> a - b, "Subtraction");
        calculate(x, y, (a, b) -> a * b, "Multiplication");
        calculate(x, y, (a, b) -> Math.max(a, b), "Max");
    }
}
