// 1. Class name becomes PascalCase
class PaymentProcessor {
    
    // 2. Constants become SCREAMING_SNAKE_CASE
    static final double PROCESSING_FEE_PERCENTAGE = 0.03;
    
    // 3. Method name becomes lowerCamelCase (verb)
    // 4. Parameters become lowerCamelCase
    void processPayment(double paymentAmount, boolean isValid) {
        
        // 5. Local variable becomes lowerCamelCase
        double finalTotal = paymentAmount + (paymentAmount * PROCESSING_FEE_PERCENTAGE);
    }
}