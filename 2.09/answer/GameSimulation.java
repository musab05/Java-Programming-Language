class BasicEnemy {
    public void attack() {
        System.out.println("Enemy throws a punch!");
    }
}

class NinjaEnemy extends BasicEnemy {
    @Override
    public void attack() {
        // Trigger the parent's logic first
        super.attack(); 
        
        // Add the specific child behavior
        System.out.println("Ninja follows up with a roundhouse kick!");
    }
}

public class GameSimulation {
    public static void main(String[] args) {
        NinjaEnemy ninja = new NinjaEnemy();
        ninja.attack(); // Runs both the parent and child logic
    }
}