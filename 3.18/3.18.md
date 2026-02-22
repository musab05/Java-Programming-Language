So far, we’ve covered the "Classic" I/O (often called **OIO** or Old I/O). But if you are building a high-performance system—like a web server or a real-time data processor—you need to understand **Java NIO (New I/O)**.

Introduced in JDK 1.4 and significantly enhanced in JDK 7 (NIO.2), NIO shifted the paradigm from **Stream-oriented** (synchronous/blocking) to **Buffer-oriented** (asynchronous/non-blocking).

---

## 1. The Core Philosophy: Streams vs. Channels

In Classic I/O, you use a `Stream` to read data. It’s a one-way street. If you want to read and write, you need two streams. Furthermore, when a thread calls `read()`, it **blocks** (sits idle) until data is available.

In **NIO**, we use **Channels** and **Buffers**.

* **Channels** are like two-way tracks.
* **Buffers** are the "trains" that carry the data.

Instead of a thread waiting for data, it tells a **Selector** to watch the channel and notify it only when the data is ready. This allows a single thread to manage thousands of simultaneous connections.

---

## 2. The Three Pillars of NIO

### A. Buffers

A Buffer is essentially a block of memory into which you can write data, which you can then later read. It wraps a primitive array and provides structured access to it.

* **Key properties:** `Capacity` (size), `Position` (where you are), and `Limit` (how much you can read/write).
* **Important Method:** `flip()`. You write data into a buffer, then "flip" it to start reading out of it.

### B. Channels

Channels are an abstraction for connections to entities capable of I/O operations, such as files and sockets.

* **FileChannel:** For reading/writing files.
* **SocketChannel:** For TCP network I/O.
* **DatagramChannel:** For UDP network I/O.

### C. Selectors

A Selector is a component that can examine one or more NIO Channels and determine which channels are ready for reading or writing. This is the secret sauce of **multiplexing** (handling multiple connections with one thread).

---

## 3. Practical Example: Reading a File with NIO

Notice how we don't just "read a line." We read into a `ByteBuffer`, flip it, and process the bytes.

```java
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class NIOReadExample {
    public static void main(String[] args) throws Exception {
        // 1. Get a Path and a Channel
        Path path = Paths.get("data.txt");
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            
            // 2. Create a Buffer (Allocate 1KB)
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 3. Read from channel into buffer
            int bytesRead = channel.read(buffer);

            while (bytesRead != -1) {
                // 4. Flip the buffer from 'writing' mode to 'reading' mode
                buffer.flip();

                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }

                // 5. Clear for the next read
                buffer.clear();
                bytesRead = channel.read(buffer);
            }
        }
    }
}

```

---

## 4. NIO.2 (The Path API)

Java 7 introduced the **Path** and **Files** classes, which drastically simplified file manipulation. It’s much more intuitive than the old `java.io.File`.

* **`Path`:** Represents the location (the address).
* **`Files`:** A utility class full of static methods to perform operations on files.

```java
Path path = Paths.get("logs/app.log");

// One-liner to read all lines (Great for small files!)
List<String> lines = Files.readAllLines(path);

// Check if a file exists
if (Files.exists(path)) {
    Files.copy(path, Paths.get("logs/backup.log"), StandardCopyOption.REPLACE_EXISTING);
}

```

---

## 5. When to use NIO vs. Classic I/O?

| Requirement | Use Classic I/O | Use Java NIO |
| --- | --- | --- |
| **Simplicity** | High (Easy to code/read) | Low (Steeper learning curve) |
| **Data Volume** | Small to Medium | Large / High-performance |
| **Concurrency** | Low (One thread per stream) | High (One thread for many channels) |
| **Operation** | Byte/Character oriented | Buffer/Block oriented |

### Tip: Direct Buffers

If you’re doing heavy-duty I/O, look into `ByteBuffer.allocateDirect()`. This allocates memory **outside** the standard JVM Heap. It allows the OS to perform I/O directly on the memory block without copying data back and forth to the JVM, which provides a massive performance boost for network and disk operations.
