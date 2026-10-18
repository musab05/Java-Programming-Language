public class ExpressionTreeMain {

    // Sealed algebraic expression interface
    public sealed interface Expr permits Constant, Add, Multiply, Negate {}

    public record Constant(double value) implements Expr {}
    public record Add(Expr left, Expr right) implements Expr {}
    public record Multiply(Expr left, Expr right) implements Expr {}
    public record Negate(Expr expr) implements Expr {}

    // Evaluator using Pattern Matching with Record Deconstruction & Guards
    public static double eval(Expr expression) {
        return switch (expression) {
            case Constant(double val) -> val;

            // Guarded optimizations: X + 0 = X, 0 + X = X
            case Add(Expr left, Constant(double val)) when val == 0.0 -> eval(left);
            case Add(Constant(double val), Expr right) when val == 0.0 -> eval(right);
            case Add(Expr left, Expr right) -> eval(left) + eval(right);

            // Guarded optimizations: X * 0 = 0, X * 1 = X
            case Multiply(Expr left, Constant(double val)) when val == 0.0 -> 0.0;
            case Multiply(Constant(double val), Expr right) when val == 0.0 -> 0.0;
            case Multiply(Expr left, Constant(double val)) when val == 1.0 -> eval(left);
            case Multiply(Constant(double val), Expr right) when val == 1.0 -> eval(right);
            case Multiply(Expr left, Expr right) -> eval(left) * eval(right);

            // Double negation cancellation: -(-X) = X
            case Negate(Negate(Expr inner)) -> eval(inner);
            case Negate(Expr inner) -> -eval(inner);

            case null -> throw new IllegalArgumentException("Expression cannot be null");
        };
    }

    // Formatter turning AST back into readable mathematical notation
    public static String toInfixString(Expr expression) {
        return switch (expression) {
            case Constant(double val) -> val == (long) val ? String.valueOf((long) val) : String.valueOf(val);
            case Add(Expr l, Expr r) -> "(" + toInfixString(l) + " + " + toInfixString(r) + ")";
            case Multiply(Expr l, Expr r) -> "(" + toInfixString(l) + " * " + toInfixString(r) + ")";
            case Negate(Expr e) -> "-" + toInfixString(e);
            case null -> "";
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Pattern Matching AST Evaluator ===");

        // Representing: (3 * (4 + 5))
        Expr expr1 = new Multiply(
                new Constant(3),
                new Add(new Constant(4), new Constant(5))
        );

        System.out.println("Expression 1: " + toInfixString(expr1));
        System.out.println("Result: " + eval(expr1)); // Expected 27.0

        // Representing: -(-(42)) -> Should trigger double negation optimization
        Expr expr2 = new Negate(new Negate(new Constant(42)));
        System.out.println("\nExpression 2: " + toInfixString(expr2));
        System.out.println("Result: " + eval(expr2)); // Expected 42.0

        // Representing: (100 * 0) + (5 + 0) -> Should hit guarded identity cases
        Expr expr3 = new Add(
                new Multiply(new Constant(100), new Constant(0)),
                new Add(new Constant(5), new Constant(0))
        );
        System.out.println("\nExpression 3: " + toInfixString(expr3));
        System.out.println("Result: " + eval(expr3)); // Expected 5.0
    }
}
