public class DamageCalculator {
    
    // 1 & 2. Create the static method with parameters
    public static double calculateCrit(int baseDamage, double multiplier) {
        // 3. Calculate and return the result
        return baseDamage * multiplier;
    }

    public static void main(String[] args) {
        // 4. Call the method, pass the arguments, and store the returned value
        double finalDamage = calculateCrit(50, 1.5);
        
        // Print the result
        System.out.println("Critical Hit! Damage dealt: " + finalDamage);
        // Output: Critical Hit! Damage dealt: 75.0
    }
}