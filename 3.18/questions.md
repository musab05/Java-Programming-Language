## 🎤 Frequently Asked Interview Questions

### 1. What exactly does the `flip()` method do to a `ByteBuffer`?

**Answer:** In NIO, a Buffer doesn't have separate read and write pointers; it uses a single `position` pointer. When you write data into a buffer, the `position` moves forward. If you want to read that data out, you can't just start reading, because the pointer is at the *end* of your data! Calling `flip()` does two things: it sets the `limit` to the current `position` (marking the end of the readable data), and it resets the `position` back to `0`. This perfectly primes the buffer for reading.

### 2. What is the difference between a Direct Buffer and a Non-Direct Buffer?

**Answer:** * A **Non-Direct Buffer** (`ByteBuffer.allocate()`) is created on the standard JVM heap. When performing I/O, the JVM must copy data from the OS memory into the JVM heap memory, which costs CPU cycles.

* A **Direct Buffer** (`ByteBuffer.allocateDirect()`) allocates memory directly in the OS (off-heap memory). This allows the operating system to perform native I/O operations directly on that memory block without copying it to the JVM first. It is significantly faster for massive I/O, but allocating and destroying direct buffers is more expensive, so they should be pooled and reused.

### 3. Explain the concept of a "Selector" in NIO network programming.

**Answer:** In Classic I/O, a server needs one dedicated thread for every client connection because `socket.read()` blocks the thread until the client sends data. A **Selector** allows a single thread to monitor thousands of `SocketChannels` simultaneously. The thread asks the Selector, "Are any of these channels ready to read or write?" The Selector returns only the active channels, meaning one thread can handle massive concurrency without sitting idle waiting for network traffic.

### 4. Why should developers use the NIO.2 `Path` and `Files` classes over the legacy `java.io.File`?

**Answer:** The legacy `File` class lacks standard error handling (many methods just return `false` instead of throwing a meaningful exception), doesn't scale well with massive directories, and has limited support for file metadata and symbolic links. `Path` and `Files` provide a highly robust, exception-driven API with built-in support for walking file trees, reading attributes, and executing atomic file operations.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Modern One-Liner

**Task:** You need to write the string `"Hello, NIO.2!"` into a file named `"output.txt"`. Forget `BufferedWriter`. Write the modern Java 7+ one-liner using the `Files` utility class.
**Solution:**

```java
// Files.write takes a Path and a byte array. Fast and clean!
Files.write(Paths.get("output.txt"), "Hello, NIO.2!".getBytes());

```

### Challenge 2: Buffer Math

**Task:** You allocate a `ByteBuffer` with a capacity of 10. You write 4 bytes into it. You call `flip()`. What are the values of `position`, `limit`, and `capacity` right now?
**Solution:**

* `position` = 0 (Ready to read from the start)
* `limit` = 4 (You can only read the 4 bytes you wrote)
* `capacity` = 10 (The overall size never changes)

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to use Channels for raw performance. The second tests your ability to navigate file systems using the powerful NIO.2 API.

### Problem 1: The "Zero-Copy" Fast File Copier

You are building a backup utility that needs to copy massive 50GB video files. Reading into a buffer and writing out of a buffer in a loop is too slow. You need to use OS-level "Zero-Copy" transfer.

**Requirements:**

1. Create a `FastCopier` class.
2. Write a method `public void copyFile(String source, String destination)`.
3. Open a `FileChannel` for the source (READ) and a `FileChannel` for the destination (WRITE, CREATE).
4. Use the `transferTo()` method on the source channel. This instructs the OS to stream the bytes directly from the source to the destination channel without ever passing them through the JVM memory!
5. Wrap the channels in Try-With-Resources to ensure they close safely.

### Problem 2: The Bulk Log Archiver (NIO.2)

You have a `logs/` directory containing hundreds of files. You need to find all `.log` files and move them to an `archive/` directory.

**Requirements:**

1. Create a `LogArchiver` class.
2. Use `Paths.get()` to define a `sourceDir` and `archiveDir`.
3. Ensure the `archiveDir` actually exists using `Files.createDirectories()`.
4. Use `Files.newDirectoryStream(sourceDir, "*.log")`. This powerful NIO.2 feature lets you iterate through a directory using a glob pattern without loading every file into memory at once.
5. Inside the loop, use `Files.move()` to transfer each file to the archive folder.
