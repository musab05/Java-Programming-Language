public class AreaCalculator {

    // 1. Method with ONE parameter (Square)
    public static int calculateArea(int side) {
        return side * side;
    }

    // 2. Overloaded method with TWO parameters (Rectangle)
    public static int calculateArea(int length, int width) {
        return length * width;
    }

    public static void main(String[] args) {
        // 3. Calling the overloaded methods
        int squareArea = calculateArea(5);        // Routes to the 1-parameter method
        int rectangleArea = calculateArea(5, 10); // Routes to the 2-parameter method

        System.out.println("Area of the square is: " + squareArea);
        System.out.println("Area of the rectangle is: " + rectangleArea);
    }
}