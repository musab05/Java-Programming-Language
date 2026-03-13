import java.lang.reflect.Field;

class User {
    private String username = "agent_007";
    private String password = "SecretPassword123";
    private int accessLevel = 5;
}

public class ObjectInspector {
    public static void inspect(Object obj) {
        System.out.println("--- Inspecting Object: " + obj.getClass().getSimpleName() + " ---");
        
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                // Bypass encapsulation
                field.setAccessible(true);
                
                String name = field.getName();
                Object value = field.get(obj); // Get the value from this specific instance
                
                System.out.println("Field: [" + name + "] | Value: [" + value + "]");
            } catch (IllegalAccessException e) {
                System.err.println("Could not access field: " + field.getName());
            }
        }
        System.out.println("--- Inspection Complete ---");
    }

    public static void main(String[] args) {
        User secretUser = new User();
        inspect(secretUser);
    }
}