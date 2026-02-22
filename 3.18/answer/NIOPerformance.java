package answer;

import java.nio.channels.FileChannel;
import java.nio.file.*;

class FastCopier {
    public void copyFile(String source, String destination) {
        Path srcPath = Paths.get(source);
        Path destPath = Paths.get(destination);

        // 1. Open channels using StandardOpenOption
        try (FileChannel srcChannel = FileChannel.open(srcPath, StandardOpenOption.READ);
             FileChannel destChannel = FileChannel.open(destPath, 
                 StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            long size = srcChannel.size();
            long position = 0;

            System.out.println("Starting Zero-Copy transfer of " + size + " bytes...");

            // 2. transferTo() is the key. It tells the OS to handle the move.
            // We loop because transferTo might not move all bytes in one go 
            // for very large files.
            while (position < size) {
                position += srcChannel.transferTo(position, size - position, destChannel);
            }

            System.out.println("Transfer complete!");

        } catch (Exception e) {
            System.err.println("Fast copy failed: " + e.getMessage());
        }
    }
}

public class NIOPerformance {
    public static void main(String[] args) {
        // Create a dummy file for testing
        try {
            Files.write(Paths.get("source_big.txt"), "Large data simulation...".getBytes());
            
            FastCopier copier = new FastCopier();
            copier.copyFile("source_big.txt", "destination_big.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}