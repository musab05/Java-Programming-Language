Annotations are the "Post-it notes" of the Java world. They don't change the logic of the code itself, but they give essential instructions to the people (or tools) reading that code. If Reflection is the ability to look inside a box, Annotations are the labels on the outside telling you what’s in the box and how to handle it.

In high-level development, annotations are what enable **Declarative Programming**. Instead of writing 50 lines of code to connect to a database, you just write `@Entity` and `@Table`, and the framework does the rest.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the difference between `@RetentionPolicy.CLASS` and `RUNTIME`?

**Answer:** This is a "gotcha" question. Both appear in the compiled `.class` file. However, `CLASS` is ignored by the JVM at runtime, making it invisible to Reflection. You use `RUNTIME` only when you need your application to "see" the annotation while it's running (e.g., Spring checking for `@Autowired`). `CLASS` is used primarily by bytecode manipulators that process the files before they are even loaded into the JVM.

### 2. Can annotations be inherited by subclasses?

**Answer:** By default, no. If you annotate a parent class, the child class won't have that annotation. However, if you add the meta-annotation **`@Inherited`** to your custom annotation definition, then any subclass will "inherit" that annotation from its parent.

### 3. What are "Meta-Annotations"?

**Answer:** They are annotations that you apply to other annotations. They define the "rules" of the annotation itself. The most common ones are `@Target` (where can it be used?), `@Retention` (how long does it last?), and `@Documented` (should it appear in Javadoc?).

### 4. Can an annotation have a method that returns a custom Object?

**Answer:** No. Annotation "methods" (parameters) are strictly limited to primitives, `String`, `Class`, `enums`, other annotations, or arrays of these types. You cannot return a `User` object or a `List` from an annotation.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Restricted Annotation

**Task:** Create an annotation called `@WebSafe` that can **only** be applied to Classes (Types).
**Solution:**

```java
@Target(ElementType.TYPE)
public @interface WebSafe {}

```

### Challenge 2: The Default Value

**Task:** Create an annotation `@Version` with a mandatory `author` field and a `value` field that defaults to `1.0`.
**Solution:**

```java
public @interface Version {
    String author(); // No default, so it's mandatory
    String value() default "1.0";
}

```

---

## 📝 Practice Problems

### Problem 1: The "Auto-Logger" Framework

Build a system that automatically logs the start of methods marked with a specific label.

**Requirements:**

1. Create an annotation `@LogMe`.
2. Create a class `BusinessLogic` with three methods; mark two of them with `@LogMe`.
3. Write an `AnnotationRunner` class that takes an object, finds all methods with `@LogMe`, and prints: "LOG: Calling method [name]" before invoking it.
4. **The Goal:** Run the runner on `BusinessLogic` and see only the annotated methods being logged.

### Problem 2: The "Validator" Annotation

Create a simple validation system for String lengths.

**Requirements:**

1. Create an annotation `@MaxLength` that takes an `int value`.
2. Create a `User` class with a `private String bio;` field annotated with `@MaxLength(10)`.
3. Write a `Validator` class with a `static boolean isValid(Object obj)` method.
4. It should use Reflection to check if any String fields exceed the length specified in the `@MaxLength` annotation.
5. **The Goal:** Return `false` if the `bio` is "This is a very long bio," and `true` if it is "Hello!".
