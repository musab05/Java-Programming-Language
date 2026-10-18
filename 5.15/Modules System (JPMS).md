# Lesson 5.15: Java Platform Module System (JPMS)

Introduced in Java 9 under the codename **Project Jigsaw**, the **Java Platform Module System (JPMS)** is the most ambitious structural change in Java's history. It redesigned the entire Java runtime (breaking up the monolithic 60MB+ `rt.jar` into discrete modules) and provided a system for modularizing enterprise applications.

---

## 1. Why JPMS? The Problems with the Classpath

Before Java 9, all Java applications ran on the **Classpath**. The classpath had two notorious structural flaws:

1. **"JAR Hell" & Fragile Runtime:**
   - The classpath is just a flat, linear list of JARs.
   - If a required dependency is missing from the classpath, you don't find out at compile-time or JVM startup — the application crashes with a `NoClassDefFoundError` or `ClassNotFoundException` hours later in production when code first tries to call that class.
   - If two versions of the same library are on the classpath, the JVM loads whichever one appears first, leading to silent bugs.

2. **Lack of Strong Encapsulation:**
   - In Java, `public` meant public to the **entire world**.
   - If you wrote an internal helper class in your library (e.g. `com.mycompany.internal.FastEngine`), you had to make it `public` so your other packages in the same library could use it. But that meant *external consumers* could also import and depend on it!
   - Developers constantly used internal JDK APIs (like `sun.misc.Unsafe`), preventing the JVM team from refactoring internal components.

---

## 2. What is a Module?

A **module** is a named, self-describing collection of code and data. It explicitly declares:
- What it **requires** (its dependencies).
- What it **exports** (its public API exposed to outside consumers).
- What it **opens** (packages accessible via reflection).
- What services it **uses** or **provides**.

Every module has a module descriptor named `module-info.java` at the root of its source folder:

```java
// module-info.java
module com.example.ecommerce {
    // 1. Dependencies needed by this module
    requires java.sql;
    requires com.fasterxml.jackson.databind;

    // 2. Packages exported to consumers
    exports com.example.ecommerce.api;
    exports com.example.ecommerce.model;

    // Notice: com.example.ecommerce.internal is NOT exported!
    // External code CANNOT import or use internal classes, even if public!
}
```

---

## 3. Module Directives Reference

### A. `requires`
Specifies a compile-time and runtime dependency on another module.
```java
requires java.net.http;
```

### B. `requires transitive` (Implied Readability)
If Module A requires Module B, and Module A's public API returns types from Module B, consumers of Module A automatically get access to Module B:
```java
module com.company.service {
    requires transitive com.company.model; // Callers can also see 'model'
}
```

### C. `requires static`
Specifies an optional compile-time dependency. At runtime, the module may or may not be present:
```java
requires static lombok;
```

### D. `exports` vs. Qualified `exports ... to`
- `exports com.mycompany.api;`: Exports the package to **all** modules.
- `exports com.mycompany.internal to com.mycompany.test;`: Exports the package **only** to the specified module (qualified export).

### E. `opens` and Deep Reflection
In modern Java, reflection is restricted by default. If a framework like Spring, Hibernate, or Jackson needs to access private fields via reflection, you must open the package:
```java
opens com.mycompany.entity to org.hibernate.orm.core;
```
To open an entire module for reflection:
```java
open module com.mycompany.app {
    // All packages in this module are open to deep reflection
}
```

### F. `uses` and `provides ... with` (Service Provider Interface / SPI)
JPMS integrates natively with `java.util.ServiceLoader`:
```java
// Consumer module:
uses com.example.payment.PaymentGateway;

// Provider module:
provides com.example.payment.PaymentGateway with com.example.payment.StripeGateway;
```

---

## 4. Types of Modules

| Module Type | Location | Name | Encapsulation |
| :--- | :--- | :--- | :--- |
| **Named Module** | Module-path | Explicitly named in `module-info.java` | Strong encapsulation; only exported packages visible. |
| **Automatic Module** | Module-path | Derived from non-modular JAR filename | Exports all packages; reads all other modules. |
| **Unnamed Module** | Classpath | None (all code on the classpath) | Reads all modules; cannot be required by named modules. |
| **Platform Module** | JDK runtime | e.g., `java.base`, `java.desktop`, `java.logging` | Core standard library modules. |

> [!NOTE]
> `java.base` is the fundamental module containing `java.lang`, `java.util`, `java.io`, etc. Every module **implicitly requires `java.base`** without needing to write it.

---

## 5. Command-Line JVM Flags for Escape Hatches

During migration to modern Java, third-party libraries may attempt illegal reflective access. Java provides CLI options to grant access:

- `--add-opens <module>/<package>=<target-module>`: Allows deep reflection on non-exported or private members.
- `--add-exports <module>/<package>=<target-module>`: Allows compile-time and runtime access to internal types.

Example:
```bash
java --add-opens java.base/java.lang=ALL-UNNAMED -jar myapp.jar
```

---

## 6. Creating Custom Minimal Runtimes with `jlink`

One of the greatest benefits of JPMS is `jlink`. Because the JDK is now modular, you can bundle **only the exact modules your app uses** into a tiny, standalone custom runtime (stripping away AWT, Swing, RMI, Corba, etc.):

```bash
jlink \
  --module-path $JAVA_HOME/jmods:mlib \
  --add-modules com.mycompany.app \
  --output custom-runtime \
  --strip-debug \
  --compress 2 \
  --no-header-files \
  --no-man-pages
```

The resulting `custom-runtime` directory can be as small as **30–40 MB** and runs your application without needing Java installed on the target machine!

---

## 7. Summary Checklist

- `module-info.java` defines the boundaries, dependencies, and exports of a module.
- Strong encapsulation protects internal packages even if classes inside them are `public`.
- Use `requires transitive` when your public API exposes types from a dependency.
- Use `opens` for frameworks requiring runtime reflection (Jackson, Hibernate, Spring).
- Use `jlink` to build ultra-lightweight Docker images and self-contained distributions.
