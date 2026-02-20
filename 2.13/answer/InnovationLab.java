// 1. Base Identity
class Vehicle {
    void drive() { System.out.println("Vehicle moves on wheels."); }
}

// 2. Capabilities
interface Rechargeable { void plugIn(); }
interface Autonomous  { void selfDrive(); }

// 3. Mixing Extends and Implements
class ElectricCar extends Vehicle implements Rechargeable, Autonomous {
    @Override
    public void plugIn() { System.out.println("Charging battery..."); }
    
    @Override
    public void selfDrive() { System.out.println("AI is navigating..."); }
}

public class InnovationLab {
    public static void main(String[] args) {
        ElectricCar tesla = new ElectricCar();

        // 4. Upcasting to prove multiple identities
        Vehicle v = tesla;
        Rechargeable r = tesla;
        Autonomous a = tesla;

        v.drive();
        r.plugIn();
        a.selfDrive();
    }
}