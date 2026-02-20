public class Player {
    // 1 & 2. Fields
    String username;
    int health;

    // 3. Parameterized Constructor (The Master Setup)
    public Player(String username, int health) {
        this.username = username;
        this.health = health;
    }

    // 4. No-Argument Constructor (The Default Setup)
    public Player() {
        // Must be the very first line! Forwards to the constructor above.
        this("Guest", 100); 
    }

    public static void main(String[] args) {
        // 5. Create players using both constructors
        Player guestPlayer = new Player();
        Player customPlayer = new Player("DragonSlayer99", 250);

        System.out.println("Player 1: " + guestPlayer.username + " (HP: " + guestPlayer.health + ")");
        System.out.println("Player 2: " + customPlayer.username + " (HP: " + customPlayer.health + ")");
    }
}