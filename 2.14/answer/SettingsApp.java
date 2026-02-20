// 1. Implement Cloneable to grant permission to clone
class Settings implements Cloneable {
    int volumeLevel;

    public Settings(int volumeLevel) {
        this.volumeLevel = volumeLevel;
    }

    // 3. Override clone and change visibility to public
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Performs a shallow copy
    }
}

public class SettingsApp {
    public static void main(String[] args) {
        try {
            Settings defaultSettings = new Settings(50);
            
            // 4. Create an independent copy
            Settings userSettings = (Settings) defaultSettings.clone();
            userSettings.volumeLevel = 80;

            System.out.println("Default Volume: " + defaultSettings.volumeLevel); // 50
            System.out.println("User Volume: " + userSettings.volumeLevel);       // 80
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}