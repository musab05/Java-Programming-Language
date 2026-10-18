In Java, a **Constructor** is a special block of code used to initialize an object immediately upon its creation. Every time you use the `new` keyword to create an object, at least one constructor is called.

It acts as the "setup phase" for your object, allocating memory in the Heap and setting the initial values for the object's attributes.

---

## 1. Rules for Creating a Constructor

To be recognized as a constructor by the Java compiler, the block of code must strictly follow these rules:

1. **Name Matching:** The constructor name must exactly match the Class name.
2. **No Return Type:** It cannot have an explicit return type (not even `void`).
3. **No Special Modifiers:** A constructor cannot be marked as `abstract`, `static`, `final`, or `synchronized`.

---

## 2. Types of Constructors

### **A. Default Constructor (No-Args)**

If you do not write a constructor in your class, the Java compiler automatically inserts a hidden, empty one called the **Default Constructor**. It initializes any unassigned reference variables to `null` and primitive variables to `0` or `false`.

You can also explicitly write a no-argument constructor if you want to set specific default values.

### **B. Parameterized Constructor**

A parameterized constructor accepts arguments. This allows you to pass specific data into the object the exact moment it is created, giving each instance a unique starting state.

---

## 3. Code Example: Constructor Overloading

Just like standard methods, constructors can be overloaded. You can have multiple constructors in a single class, as long as their parameter lists are different.

```java
public class ServerConfig {
    String ipAddress;
    int port;

    // 1. No-Argument Constructor
    public ServerConfig() {
        this.ipAddress = "127.0.0.1"; // Default localhost
        this.port = 8080;             // Default port
    }

    // 2. Parameterized Constructor
    public ServerConfig(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public static void main(String[] args) {
        // Calls the No-Arg constructor
        ServerConfig defaultServer = new ServerConfig(); 
        
        // Calls the Parameterized constructor
        ServerConfig customServer = new ServerConfig("192.168.1.5", 3000); 
    }
}

```

---

## 4. Constructor vs. Method

Because they look similar, it is easy to confuse constructors with standard methods. Here is how they differ:

| Feature | Constructor | Method |
| --- | --- | --- |
| **Purpose** | To initialize an object's state. | To define an object's behavior. |
| **Name** | **Must** be the same as the class name. | Can be anything (usually a verb). |
| **Return Type** | Cannot have a return type. | **Must** have a return type (or `void`). |
| **Invocation** | Called implicitly by the `new` keyword. | Called explicitly using the object reference (e.g., `obj.doSomething()`). |
| **Compiler Action** | Automatically provides a default if none exists. | Never provides a default method. |
