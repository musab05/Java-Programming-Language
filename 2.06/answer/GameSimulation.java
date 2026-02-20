// 1. Parent Class
class Entity {
    int x;
    int y;

    public void move() {
        System.out.println("Moving entity...");
    }
}

// 2. Child Class 1
class Player extends Entity {
    int mana = 100;

    public void heal() {
        System.out.println("Player healing... Mana remaining: " + (mana - 10));
    }
}

// 3. Child Class 2
class Zombie extends Entity {
    int biteDamage = 15;

    public void attack() {
        System.out.println("Zombie bites for " + biteDamage + " damage!");
    }
}

public class GameSimulation {
    public static void main(String[] args) {
        // 4. Instantiate and test
        Player p1 = new Player();
        Zombie z1 = new Zombie();

        // Both inherit from Entity
        p1.move(); 
        z1.move();

        // Specific to their own classes
        p1.heal();
        z1.attack();
        
        // z1.heal(); // ERROR: Zombies cannot heal!
    }
}