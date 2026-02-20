// 1. Final class - Cannot be extended
final class MathUtils {
    // 2. Static final constant
    public static final double PI = 3.14159;

    // 3. Static utility method
    public static double square(double n) {
        return n * n;
    }
}

// 4. Attempting to inherit
/*
class AttemptedMath extends MathUtils { 
    // ERROR: Cannot inherit from final 'MathUtils'
}
*/

public class MathApp {
    public static void main(String[] args) {
        System.out.println("Value of PI: " + MathUtils.PI);
        System.out.println("Square of 5: " + MathUtils.square(5));
    }
}