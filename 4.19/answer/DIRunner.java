import java.lang.annotation.*;
import java.lang.reflect.*;

// 1. Define our custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface InjectMe {}

// 2. The classes to be injected
class Engine {
    public void start() { System.out.println("Engine VROOM!"); }
}

class Car {
    @InjectMe
    private Engine engine;

    public void drive() {
        if (engine == null) {
            System.out.println("Can't drive - no engine!");
        } else {
            engine.start();
            System.out.println("Driving the car...");
        }
    }
}

// 3. The "Framework" logic
class Starter {
    public static void inject(Object obj) {
        Class<?> clazz = obj.getClass();
        
        // Scan all fields (including private ones)
        for (Field field : clazz.getDeclaredFields()) {
            // Check if the field has our annotation
            if (field.isAnnotationPresent(InjectMe.class)) {
                try {
                    // Create an instance of the field's type
                    Object instance = field.getType().getDeclaredConstructor().newInstance();
                    
                    // Bypass 'private' and set the value
                    field.setAccessible(true);
                    field.set(obj, instance);
                    
                    System.out.println("Successfully injected " + field.getType().getSimpleName() + " into " + clazz.getSimpleName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class DIRunner {
    public static void main(String[] args) {
        Car myCar = new Car();
        Starter.inject(myCar); // Magic happens here
        myCar.drive();
    }
}