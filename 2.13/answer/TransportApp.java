interface Boat {
    default void refuel() {
        System.out.println("Filling water tanks.");
    }
}

interface Plane {
    default void refuel() {
        System.out.println("Filling fuel tanks.");
    }
}

class Hovercraft implements Boat, Plane {
    
    // 4. Manual override to resolve ambiguity
    @Override
    public void refuel() {
        // Explicitly calling specific interface versions
        Boat.super.refuel();
        Plane.super.refuel();
        System.out.println("Hovercraft systems fully replenished.");
    }
}

public class TransportApp {
    public static void main(String[] args) {
        Hovercraft hc = new Hovercraft();
        hc.refuel();
    }
}