# 1.1 Introduction to Java - Interview & Assessment Guide

This guide contains the most frequent interview questions and conceptual checks for the core Java introductory concepts.

## 🎤 Frequent Interview Questions

_Common questions asked during technical interviews regarding Java architecture._

1. **What is the difference between JDK, JRE, and JVM?**

- **JDK (Java Development Kit):** The full software environment used to develop Java applications. It includes the JRE and development tools like `javac`.
- **JRE (Java Runtime Environment):** The environment needed to run Java applications. It includes the JVM and standard libraries.
- **JVM (Java Virtual Machine):** The engine that actually executes the bytecode.

2. **Why is Java called "Platform Independent"?**

- Because of the **Bytecode**. When you compile Java code, it turns into bytecode (.class file), which can run on any device that has a JVM, regardless of the operating system (Windows, Linux, macOS).

3. **Explain the `public static void main(String[] args)` line.**

- **public:** Accessible from anywhere (the JVM needs to access it).
- **static:** Can be called without creating an object of the class.
- **void:** Returns no value.
- **main:** The entry point name recognized by the JVM.
- **String[] args:** Accepts command-line arguments as an array of Strings.

4. **What is the JIT compiler?**

- The **Just-In-Time** compiler is part of the JVM. It improves performance by compiling bytecode into native machine code at runtime for frequently executed sections of code.

---

## 💡 Concept Check Questions

_Answer these questions to verify if truly understands the material._

### Conceptual Check

- "If I give you a `.class` file, do you need the JDK or just the JRE to run it? Why?"
- "Can a single Java file have multiple classes? If so, which one should be the name of the file?"
- "What happens inside the computer the moment you type `java MyProgram` in the terminal?"
