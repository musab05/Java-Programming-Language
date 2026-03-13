Welcome back! To round out our exploration of advanced Java, we have to talk about **Annotations**. If Reflection is the "engine" that allows frameworks to look inside your code, Annotations are the "labels" that tell that engine what to do.

Introduced in Java 5, annotations are a form of **metadata**. They provide data about a program that is not part of the program itself. They have no direct effect on the operation of the code they annotate, but they are used by compilers, tools, and runtime frameworks to change how your code is treated.

---

## 1. The Three Main Uses of Annotations

As a developer, you will encounter annotations in three distinct phases of the software lifecycle:

1. **Information for the Compiler:** Used by the compiler to detect errors or suppress warnings (e.g., `@Override`, `@Deprecated`).
2. **Compile-time Processing:** Software tools can process annotations to generate code, XML files, or documentation (e.g., Lombok's `@Data`).
3. **Runtime Processing:** Some annotations can be examined at runtime using **Reflection** to change the behavior of the application (e.g., Spring's `@Service` or Hibernate's `@Entity`).

---

## 2. Built-in Java Annotations

Before you start making your own, you must master the ones Java gives you for free:

* **`@Override`**: Tells the compiler that this method is intended to override a method in a superclass. If you make a typo in the method name, the compiler will flag it.
* **`@Deprecated`**: Marks a method/class as "old" and discouraged. The compiler will show a warning if someone tries to use it.
* **`@SuppressWarnings`**: Tells the compiler, "I know what I'm doing, stop showing me warnings for this specific block."
* **`@FunctionalInterface`**: Ensures that an interface has exactly one abstract method (critical for Lambdas).

---

## 3. Anatomy of a Custom Annotation

Creating your own annotation looks a bit like creating an interface, but with an `@` symbol. You also use **Meta-Annotations** to define how your annotation behaves.

### The Meta-Annotations:

* **`@Retention`**: How long the annotation lives. (`SOURCE`, `CLASS`, or `RUNTIME`).
* **`@Target`**: Where can you put it? (`METHOD`, `FIELD`, `TYPE`, etc.).

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // We want to see this at runtime!
@Target(ElementType.METHOD)         // Can only be put on methods
public @interface LogExecutionTime {
    String priority() default "LOW"; // You can add parameters with defaults
}

```

---

## 4. Processing Annotations with Reflection

An annotation does nothing by itself. You need code to "read" it. This is where the **Reflection API** we just studied comes back into play.

```java
public class AnalyticsProcessor {
    public void process(Object obj) throws Exception {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                LogExecutionTime annotation = method.getAnnotation(LogExecutionTime.class);
                System.out.println("Processing method: " + method.getName());
                System.out.println("Priority level: " + annotation.priority());
                
                // You could start a timer here, invoke the method, and stop the timer!
                method.invoke(obj);
            }
        }
    }
}

```

---

## 5. Tip: Retention Policies

Choosing the right `@Retention` is the most common mistake.

* Use **`SOURCE`** if you are writing a tool that generates code (like MapStruct or Lombok).
* Use **`RUNTIME`** if you want your code to act on the annotation while the app is running (like Spring or JUnit).
* If you choose `CLASS` (the default), the annotation is kept in the `.class` file but is **not** available via Reflection at runtime.

---

## Summary: Standard vs. Custom Annotations

| Feature | Standard (e.g., `@Override`) | Custom (e.g., `@MyAnnotation`) |
| --- | --- | --- |
| **Purpose** | Safety & Documentation | Business Logic & Framework Automation |
| **Consumer** | Java Compiler | Your code / External Frameworks |
| **Visibility** | Usually Source only | Usually Runtime (via Reflection) |
| **Complexity** | Very low | Moderate (requires a processor) |

---

**Congratulations!** You've completed the "Advanced Java" modules. You now understand how the heavyweights like Spring and Hibernate actually work under the hood using Reflection and Annotations.
