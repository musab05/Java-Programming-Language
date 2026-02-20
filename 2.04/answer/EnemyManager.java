public class EnemyManager {
    // 2. Declare static variable
    static int MAX_ENEMIES;

    // 3. Static block for initialization
    static {
        System.out.println("Loading core game configurations...");
        MAX_ENEMIES = 50;
    }

    // 4. Main method (Entry point of the program)
    public static void main(String[] args) {
        System.out.println("Game engine started!");
        System.out.println("Maximum enemies allowed on screen: " + MAX_ENEMIES);
    }
}