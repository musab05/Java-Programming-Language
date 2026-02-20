// 1. Parent Class
class Particle {
    public void spawn() {
        System.out.println("Spawning a generic white dot.");
    }
}

// 2. Child Class 1
class Spark extends Particle {
    @Override
    public void spawn() {
        System.out.println("Spawning a bright yellow spark!");
    }
}

// 3. Child Class 2
class Smoke extends Particle {
    @Override
    public void spawn() {
        System.out.println("Spawning a dark grey cloud.");
    }
}

public class ParticleSystem {
    public static void main(String[] args) {
        // 4. Upcasting: Parent reference holding Child objects
        Particle p1 = new Spark();
        Particle p2 = new Smoke();

        // Dynamic Binding in action
        p1.spawn(); // Output: Spawning a bright yellow spark!
        p2.spawn(); // Output: Spawning a dark grey cloud.
    }
}

// Note remove @Override and add typo to Spark and Smoke spawn method like make it spaw or spawnn and see what it prints and then keep that typo as it is and add @Override once again and see what it shows. Give it a try.