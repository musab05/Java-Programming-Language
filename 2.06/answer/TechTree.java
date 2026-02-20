// 1. Grandparent Class
class Vehicle {
    public void startEngine() {
        System.out.println("Engine started. Vroom!");
    }
}

// 2. Parent Class
class Tank extends Vehicle {
    public void fireCannon() {
        System.out.println("BOOM! Cannon fired.");
    }
}

// 3. Child Class
class HoverTank extends Tank {
    public void hover() {
        System.out.println("Anti-gravity thrusters engaged. Hovering!");
    }
}

public class TechTree {
    public static void main(String[] args) {
        // 4. The child gets access to the entire inheritance chain
        HoverTank futureTank = new HoverTank();

        futureTank.startEngine(); // Inherited from Vehicle
        futureTank.fireCannon();  // Inherited from Tank
        futureTank.hover();       // Specific to HoverTank
    }
}