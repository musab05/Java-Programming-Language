import java.lang.annotation.*;
import java.lang.reflect.Field;

// 1. Define Annotation with a parameter
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// 2. The Model
class User {
    @MaxLength(10)
    private String bio;

    public User(String bio) {
        this.bio = bio;
    }
}

// 3. The Validator Engine
class Validator {
    public static boolean isValid(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                // Get the annotation instance to read its 'value'
                MaxLength annotation = field.getAnnotation(MaxLength.class);
                int limit = annotation.value();

                field.setAccessible(true);
                String fieldValue = (String) field.get(obj);

                if (fieldValue != null && fieldValue.length() > limit) {
                    System.out.println("Validation Failed: Field [" + field.getName() + 
                                       "] exceeds limit of " + limit);
                    return false;
                }
            }
        }
        return true;
    }
}

public class ValidationMain {
    public static void main(String[] args) throws Exception {
        User goodUser = new User("Hello!");
        User badUser = new User("This bio is way too long for the limit.");

        System.out.println("Good User valid? " + Validator.isValid(goodUser));
        System.out.println("Bad User valid? " + Validator.isValid(badUser));
    }
}