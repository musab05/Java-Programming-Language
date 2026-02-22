// 1 & 2. Bounding T to only accept Numbers (Integer, Double, Float, etc.)
class MathUtil<T extends Number> {
    
    // 3. The method uses the bounded generic type
    public double sum(T a, T b) {
        // 4. Safe method calls! The compiler knows these are Numbers.
        return a.doubleValue() + b.doubleValue();
    }
}

public class CalculatorApp {
    public static void main(String[] args) {
        // Safe: Integer extends Number
        MathUtil<Integer> intMath = new MathUtil<>();
        System.out.println("Integer Sum: " + intMath.sum(10, 20));

        // Safe: Double extends Number
        MathUtil<Double> doubleMath = new MathUtil<>();
        System.out.println("Double Sum: " + doubleMath.sum(15.5, 4.5));

        // 5. UNCOMMENTING THE LINE BELOW CAUSES A COMPILE-TIME ERROR:
        // "Type parameter 'java.lang.String' is not within its bound"
        // MathUtil<String> stringMath = new MathUtil<>(); 
    }
}