The Java Platform Module System (JPMS) introduced in Java 9 redefined the architecture of the Java language and runtime. By moving from a flat, uncontrolled classpath to an explicit, strongly encapsulated module path, JPMS provides reliable configuration, robust encapsulation, and the ability to assemble tailor-made JDK runtimes.

---

## 🎤 Frequently Asked Interview Questions

### 1. What problems does the Java Platform Module System (JPMS) solve?

**Answer:** JPMS addresses two critical shortcomings of the traditional classpath:
1. **Unreliable configuration ("JAR hell"):** Missing or duplicate JARs on the classpath only caused runtime failures (`NoClassDefFoundError`). JPMS checks dependencies at application startup (or compile-time) and refuses to start if any required module is missing or duplicated.
2. **Lack of strong encapsulation:** In standard Java, any `public` class could be accessed by any other class on the classpath. With JPMS, a `public` class is only accessible outside its module if the package containing it is explicitly declared in an `exports` directive.

### 2. What is the difference between `requires` and `requires transitive`?

**Answer:**
- `requires M;` means the current module reads module `M`, but modules that depend on the current module **do not** automatically read `M`.
- `requires transitive M;` (implied readability) means any module that depends on the current module **also implicitly reads module `M`**. This is necessary when public methods in the current module return types defined in module `M`.

### 3. What is the difference between `exports` and `opens` in `module-info.java`?

**Answer:**
- `exports <package>;` makes all `public` and `protected` types in the package accessible at compile-time and runtime via normal imports. Private members remain protected against reflection.
- `opens <package>;` permits **deep reflective access** (e.g. `setAccessible(true)`) to private members at runtime without exporting the package for compile-time usage. This is required for dependency injection and ORM frameworks like Spring, Hibernate, and Jackson.

### 4. What is an "Automatic Module" versus an "Unnamed Module"?

**Answer:**
- An **Unnamed Module** represents all JARs and classes placed on the traditional `--class-path`. It can access all exported packages of all modules, but named modules cannot declare a `requires` dependency on it.
- An **Automatic Module** is created when a traditional non-modular JAR is placed onto the `--module-path`. Its name is derived from the JAR's filename or `Automatic-Module-Name` manifest header. It exports all of its packages and reads all other modules on the module path.

### 5. What is `jlink` and how does it relate to JPMS?

**Answer:** `jlink` (Java Linker) is a tool introduced alongside JPMS that packages an application along with **only the exact subset of the JDK modules** it requires into a custom, standalone runtime image. This eliminates the need to install a full JRE/JDK on the target server and can reduce application distribution sizes down to 30–50MB.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Declaring an Open Module
**Task:** Write a `module-info.java` for `com.acme.store` that requires `java.sql` and `java.net.http`, exports `com.acme.store.api`, and opens `com.acme.store.entity` to `org.hibernate.orm.core`.

**Solution:**
```java
module com.acme.store {
    requires java.sql;
    requires java.net.http;

    exports com.acme.store.api;
    opens com.acme.store.entity to org.hibernate.orm.core;
}
```

### Challenge 2: Inspecting Module Metadata Programmatically
**Task:** How do you check if the current class is running in a named module or the unnamed module at runtime?

**Solution:**
```java
Module currentModule = MyClass.class.getModule();
if (currentModule.isNamed()) {
    System.out.println("Running inside named module: " + currentModule.getName());
} else {
    System.out.println("Running on legacy classpath (unnamed module)");
}
```

---

## 💻 Practice Problems

### Problem 1: Runtime Module Introspection
Use Java's Module Reflection API (`java.lang.Module`, `java.lang.module.ModuleDescriptor`) to inspect the JVM's runtime module graph:
- Query the module of the current executing class.
- List all platform modules loaded into the JVM runtime layer (e.g., `java.base`, `java.sql`).
- Inspect exported packages and module descriptors programmatically.
- File: [ModuleDescriptorMain.java](file:///c:/Java%20Programming%20Language/5.15/answer/ModuleDescriptorMain.java)

### Problem 2: Service Provider Interface (SPI) with `ServiceLoader`
Demonstrate the JPMS `uses` and `provides ... with` architecture pattern using `java.util.ServiceLoader`:
- Define a `PaymentGateway` service interface.
- Implement multiple service providers (e.g., `StripeGatewayProvider`, `PayPalGatewayProvider`).
- Dynamically discover, load, and invoke providers at runtime via `ServiceLoader`.
- File: [ServiceLoaderPluginMain.java](file:///c:/Java%20Programming%20Language/5.15/answer/ServiceLoaderPluginMain.java)
