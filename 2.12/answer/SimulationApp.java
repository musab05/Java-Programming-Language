// 1. Interface purely for constants
interface PhysicsConstants {
    double GRAVITY = 9.8;   // Implicitly public static final
    double FRICTION = 0.4;
}

// 3. Implementing the interface to access constants directly
class PhysicsEngine implements PhysicsConstants {
    
    public void calculateFall(double mass) {
        // 4. Accessing constants directly without 'PhysicsConstants.' prefix
        double speed = mass * GRAVITY;
        System.out.println("Calculated falling speed: " + speed + " m/s");
    }
}

public class SimulationApp {
    public static void main(String[] args) {
        PhysicsEngine engine = new PhysicsEngine();
        engine.calculateFall(10.5);

        // 5. Proving they are static and accessible globally
        System.out.println("Global Gravity Constant: " + PhysicsConstants.GRAVITY);
        
        // PhysicsConstants.GRAVITY = 10.0; // ERROR: Cannot assign a value to final variable
    }
}