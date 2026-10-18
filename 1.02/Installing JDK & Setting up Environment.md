
# JDK Installation & Environment Setup

To begin developing in Java, you must install the **Java Development Kit (JDK)** and configure your system's environment variables.

## 1. Official Installation Guide

For the most up-to-date, platform-specific instructions (Windows, macOS, and Linux), refer to the official Oracle documentation:

👉 **[JDK 21 Installation Guide](https://www.oracle.com/in/java/technologies/downloads/)**

---

## 2. Key Setup Steps

1. **Download:** Choose the installer for your OS (x64 or Arm64).
2. **Install:** Run the executable or extract the archive to your preferred directory.
3. **Set Environment Variables:**
* **JAVA_HOME:** Point this to your JDK installation directory.
* **Path:** Add the `bin` folder (inside the JDK directory) to your system's PATH variable.



---

## 3. Verification

Once installed, open your terminal or command prompt and run:

```bash
java -version
javac -version

```

If both commands return a version number, your environment is successfully configured.
