public class Thermostat {
    // 2. Encapsulated variable
    private int targetTemperature;

    // 3. Public Getter
    public int getTargetTemperature() {
        return targetTemperature;
    }

    // 4. Public Setter with validation logic
    public void setTargetTemperature(int targetTemperature) {
        if (targetTemperature >= 50 && targetTemperature <= 90) {
            this.targetTemperature = targetTemperature;
            System.out.println("Temperature set to " + this.targetTemperature + " degrees.");
        } else {
            // Blocks invalid data from corrupting the state
            System.out.println("Error: Temperature out of safe bounds. Request blocked.");
        }
    }

    public static void main(String[] args) {
        Thermostat livingRoom = new Thermostat();
        
        // Valid request
        livingRoom.setTargetTemperature(72); 
        
        // Invalid request (will be blocked)
        livingRoom.setTargetTemperature(100); 
        
        // State remains protected
        System.out.println("Current Target: " + livingRoom.getTargetTemperature());
    }
}