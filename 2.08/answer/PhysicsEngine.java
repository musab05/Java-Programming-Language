// 1. Parent Class
class PhysicsObject {
    public void applyGravity(float gravityForce) {
        System.out.println("Applying standard gravity: " + gravityForce);
    }
}

// 2. Child Class
class FallingCrate extends PhysicsObject {
    
    // 3 & 4. The Safety Net
    // If you type 'applygravity' here, the compiler throws an error because 
    // it doesn't match the parent's exact signature. 
    @Override
    public void applyGravity(float gravityForce) { 
        // 5. Fixed typo
        System.out.println("Crate falls heavily!");
    }
}

public class PhysicsEngine {
    public static void main(String[] args) {
        FallingCrate crate = new FallingCrate();
        crate.applyGravity(9.8f);
    }
}