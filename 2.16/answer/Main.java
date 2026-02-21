public class SmartHome {
    private String address;

    public SmartHome(String address) {
        this.address = address;
    }

    // 1. Private Member Inner Class
    // Tied to a specific instance of a SmartHome
    private class LightBulb {
        void powerOn() {
            // Accesses the private field 'address' of the outer class directly
            System.out.println("The house at " + address + " is now lit.");
        }
    }

    // 2. Static Nested Class
    // Acts independently of any SmartHome instance
    static class SecuritySystem {
        static void alert() {
            System.out.println("Global security alert!");
        }
    }

    // Helper method to give access to the private inner class
    public void activateLights() {
        LightBulb bulb = new LightBulb();
        bulb.powerOn();
    }
}

class Main {
    public static void main(String[] args) {
        // --- Accessing the Static Nested Class ---
        // No instance of SmartHome needed!
        SmartHome.SecuritySystem.alert();

        // --- Accessing the Member Inner Class ---
        // Step 1: Create the Outer class instance
        SmartHome myMansion = new SmartHome("123 Java Lane");

        // Step 2: Use the helper method OR the .new syntax
        // Option A (Cleanest):
        myMansion.activateLights();

        // Option B (Direct instantiation - only if the inner class was public):
        // SmartHome.LightBulb bulb = myMansion.new LightBulb();
        // bulb.powerOn();
    }
}