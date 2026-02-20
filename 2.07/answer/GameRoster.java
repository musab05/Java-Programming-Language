// 1. Parent Class
class Entity {
    public void move() {
        System.out.println("Entity moves.");
    }
}

// 2. Child Classes
class Player extends Entity {
    @Override
    public void move() {
        System.out.println("Player runs");
    }
}

class NPC extends Entity {
    @Override
    public void move() {
        System.out.println("NPC walks slowly");
    }
}

class Monster extends Entity {
    @Override
    public void move() {
        System.out.println("Monster prowls");
    }
}

public class GameRoster {
    public static void main(String[] args) {
        // 3. Array of the Parent type holding Child objects
        Entity[] roster = { new Player(), new NPC(), new Monster() };

        // 4. Looping through the array
        for (Entity e : roster) {
            e.move(); // Dynamically executes the correct version for each object
        }
    }
}