import java.io.*;

// 1. Implements Serializable to allow byte stream conversion
class GameCharacter implements Serializable {
    
    // 2. Hardcoded version ID to prevent InvalidClassException upon updates
    private static final long serialVersionUID = 1L;

    // 3. Standard fields to be saved
    private String name;
    private int health;
    
    // 4. SECURITY: transient fields are completely ignored by ObjectOutputStream
    private transient String sessionToken;

    public GameCharacter(String name, int health, String sessionToken) {
        this.name = name;
        this.health = health;
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "Character [Name=" + name + ", Health=" + health + ", Token=" + sessionToken + "]";
    }
}

public class GameEngine {
    public static void main(String[] args) {
        GameCharacter hero = new GameCharacter("Arthur", 100, "TOKEN_9948X_SECURE");
        System.out.println("Original Object: " + hero);

        // 5. Serialization (Saving to Disk)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savegame.ser"))) {
            oos.writeObject(hero);
            System.out.println("Game saved successfully to savegame.ser");
        } catch (IOException e) {
            System.err.println("Failed to save game: " + e.getMessage());
        }

        // 6. Deserialization (Loading from Disk)
        System.out.println("\n--- Reloading Game ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savegame.ser"))) {
            // Must cast the returned Object back to GameCharacter
            GameCharacter loadedHero = (GameCharacter) ois.readObject(); 
            
            // Notice how the Token is now 'null' because it was transient!
            System.out.println("Loaded Object: " + loadedHero);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load game: " + e.getMessage());
        }
    }
}