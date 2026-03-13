import java.lang.annotation.*;
import java.lang.reflect.*;

// 1. Define the Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogMe {}

// 2. The Class using the labels
class BusinessLogic {
    @LogMe
    public void calculateTax() {
        System.out.println("Executing: Tax Math...");
    }

    public void internalHelper() {
        System.out.println("Executing: Internal Cleanup (No Log)...");
    }

    @LogMe
    public void saveToDatabase() {
        System.out.println("Executing: DB Save...");
    }
}

// 3. The Runner (The "Processor")
class AnnotationRunner {
    public static void run(Object obj) throws Exception {
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {
            // Check if the label is present
            if (method.isAnnotationPresent(LogMe.class)) {
                System.out.println(">>> [LOG]: Starting method " + method.getName());
                method.invoke(obj);
            } else {
                // Just run it normally without the log
                method.invoke(obj);
            }
        }
    }
}

public class LoggerMain {
    public static void main(String[] args) throws Exception {
        BusinessLogic logic = new BusinessLogic();
        AnnotationRunner.run(logic);
    }
}