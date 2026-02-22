## 🎤 Frequently Asked Interview Questions

### 1. Why is wrapping a `FileReader` in a `BufferedReader` so much faster?

**Answer:** Hitting the hard drive is one of the slowest operations a computer can perform. A raw `FileReader` makes a system call to the OS for *every single character* it reads. If a file has 10,000 characters, that's 10,000 expensive disk trips. A `BufferedReader` makes one system call to grab a huge chunk of data (usually 8KB) and stores it in a "buffer" in RAM. Subsequent reads just pull from the ultra-fast RAM until the buffer is empty, drastically reducing OS overhead.

### 2. How exactly does the "Try-With-Resources" block know how to close a file?

**Answer:** It relies on polymorphism! Try-With-Resources only works with objects whose classes implement the `java.lang.AutoCloseable` (or `java.io.Closeable`) interface. When the `try` block finishes—whether it completes normally or throws an exception—the JVM automatically invokes the `.close()` method guaranteed by that interface.

### 3. What is the difference between Byte Streams (`InputStream`/`OutputStream`) and Character Streams (`Reader`/`Writer`)?

**Answer:** * **Byte Streams** read/write raw 8-bit bytes. They are used for binary data like images, audio files, or compiled PDFs.

* **Character Streams** read/write 16-bit characters. They automatically handle character encoding (like UTF-8) and translate the raw bytes into human-readable text. You should always use Character Streams for text files.

### 4. What happens if you forget to close a `FileWriter`?

**Answer:** Two major issues occur:

1. **Data Loss:** `BufferedWriter` doesn't write to the disk immediately; it holds data in memory until the buffer is full. Calling `.close()` automatically calls `.flush()`, which forces any remaining data in the buffer onto the disk. If you don't close it, the tail end of your data might never be written.
2. **Resource Leaks:** The OS places a "lock" on the file while your app has it open. If you don't close the stream, other applications (or even other parts of your own app) won't be able to modify or delete the file, and your server will eventually run out of "file descriptors," causing the app to crash.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Append Trick

**Task:** When you use `new FileWriter("data.txt")`, it completely wipes the existing file and starts fresh. How do you modify this single line of code so that it *adds* text to the end of the existing file instead?
**Solution:**

```java
// Pass 'true' as the second argument to enable "append mode"!
FileWriter writer = new FileWriter("data.txt", true);

```

### Challenge 2: The Legacy Refactor

**Task:** Here is a piece of legacy Java 6 code. Refactor it using the modern Java 7+ Try-With-Resources syntax to completely eliminate the need for the `finally` block.

```java
// Legacy
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("config.txt"));
    System.out.println(br.readLine());
} finally {
    if (br != null) br.close();
}

```

**Solution:**

```java
// Modern (Clean and Auto-closing)
try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
    System.out.println(br.readLine());
}

```

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to write formatted data safely to a persistent log. The second tests your ability to read structured data (like a CSV) and parse it into usable application data.

### Problem 1: The Application Logger (`BufferedWriter`)

You are building a custom logging utility that writes error messages to a text file so developers can review them later.

**Requirements:**

1. Create a `FileLogger` class.
2. Write a method `public void logError(String message)`.
3. Inside the method, use Try-With-Resources to open a `BufferedWriter` pointing to `"app_errors.log"`.
4. **Crucial:** Ensure the writer is in **append mode** so you don't delete previous errors!
5. Write the message to the file, followed by a new line using `writer.newLine()`.
6. In `Main`, call `logError()` three times with different messages, then open the file on your computer to verify they were all saved.

### Problem 2: The CSV Parser (`BufferedReader`)

You have a file named `"users.csv"` containing data formatted like this: `ID,Name,Department`. You need to read this file and print it cleanly to the console.

**Requirements:**

1. Manually create a `users.csv` file in your project directory with three lines of dummy data (e.g., `101,Alice,Engineering`).
2. Create a `CSVParser` class with a method `public void printUsers()`.
3. Use Try-With-Resources to open a `BufferedReader`.
4. Create a `String line` variable and write a `while` loop that reads the file line-by-line until it returns `null`.
5. Inside the loop, use the `line.split(",")` method to break the comma-separated string into a `String[]` array.
6. Print the parsed data in a readable format, like: `"ID: 101 | Name: Alice | Dept: Engineering"`.
