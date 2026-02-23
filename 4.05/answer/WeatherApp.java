import java.util.Random;

class WeatherStation {
    // Volatile is sufficient because we only have ONE thread writing to this value.
    private volatile double temperature = 25.0;

    public void startSensor() {
        new Thread(() -> {
            Random rand = new Random();
            while (true) {
                // The Sensor thread updates the value
                temperature = 20 + (rand.nextDouble() * 15); 
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        }).start();
    }

    public void startDisplay() {
        new Thread(() -> {
            while (true) {
                // The Display thread is guaranteed to see the latest update from RAM
                System.out.printf("[Display] Current Temperature: %.2f°C%n", temperature);
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
        }).start();
    }
}

public class WeatherApp {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        station.startSensor();
        station.startDisplay();
    }
}