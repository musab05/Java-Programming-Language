// 1. The Enum declaration
public enum TrafficLight {
    // 2 & 3. Constants with their specific durations (in seconds)
    RED(30),
    YELLOW(5),
    GREEN(45);

    // Private field to hold the state
    private final int duration;

    // 4. Private constructor (implicitly private in Enums)
    TrafficLight(int duration) {
        this.duration = duration;
    }

    // 5. Getter method for the duration
    public int getDuration() {
        return duration;
    }

    // 6. The State Transition Method
    public TrafficLight nextLight() {
        switch (this) {
            case RED:
                return GREEN; // Red goes to Green
            case GREEN:
                return YELLOW; // Green goes to Yellow
            case YELLOW:
                return RED; // Yellow goes back to Red
            default:
                throw new IllegalStateException("Unknown light state: " + this);
        }
    }
}

// 7. The Main class to run the simulation
class Main {
    public static void main(String[] args) {
        // Start the simulation at RED
        TrafficLight currentLight = TrafficLight.RED;

        System.out.println("Starting Traffic Light Simulation...\n");

        // Loop to transition through the lights 5 times
        for (int i = 1; i <= 5; i++) {
            System.out.println("Cycle " + i + ": The light is " + currentLight.name() + 
                               " for " + currentLight.getDuration() + " seconds.");
            
            // Transition to the next state
            currentLight = currentLight.nextLight();
        }
    }
}