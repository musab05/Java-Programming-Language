import java.util.*;
import java.util.function.Function;

public class ShapeFactoryMain {

    static class Circle {
        double radius;
        Circle(double radius) { this.radius = radius; }
        @Override public String toString() {
            return String.format("Circle(r=%.1f, area=%.2f)", radius, Math.PI * radius * radius);
        }
    }

    static class Square {
        double side;
        Square(double side) { this.side = side; }
        @Override public String toString() {
            return String.format("Square(s=%.1f, area=%.2f)", side, side * side);
        }
    }

    // Generic factory method — uses constructor references!
    static <T> List<T> createShapes(List<Double> sizes, Function<Double, T> factory) {
        return sizes.stream()
            .map(factory)
            .toList();
    }

    public static void main(String[] args) {
        List<Double> sizes = List.of(3.0, 5.0, 7.0, 10.0);

        // Constructor references as factory functions
        Function<Double, Circle> circleFactory = Circle::new;
        Function<Double, Square> squareFactory = Square::new;

        List<Circle> circles = createShapes(sizes, circleFactory);
        List<Square> squares = createShapes(sizes, squareFactory);

        System.out.println("=== Circles ===");
        circles.forEach(System.out::println);

        System.out.println("\n=== Squares ===");
        squares.forEach(System.out::println);
    }
}
