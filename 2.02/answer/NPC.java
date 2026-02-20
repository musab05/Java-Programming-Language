public class NPC {
    // 1 & 2. Fields
    String name;
    int level;

    // 3. No-Argument Constructor (The Default)
    public NPC() {
        this.name = "Villager";
        this.level = 1;
    }

    // 4. Parameterized Constructor (The Custom Setup)
    public NPC(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public static void main(String[] args) {
        // 5. Instantiate both types of NPCs
        NPC genericNpc = new NPC(); // Calls the no-arg constructor
        NPC bossNpc = new NPC("Blacksmith", 10); // Calls the parameterized constructor

        // Print details to verify state
        System.out.println("Generic NPC: " + genericNpc.name + " (Level " + genericNpc.level + ")");
        System.out.println("Boss NPC: " + bossNpc.name + " (Level " + bossNpc.level + ")");
    }
}