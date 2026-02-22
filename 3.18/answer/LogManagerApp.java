import java.nio.file.*;
import java.io.IOException;

class LogArchiver {
    public void archiveLogs(String source, String archive) {
        Path sourceDir = Paths.get(source);
        Path archiveDir = Paths.get(archive);

        try {
            // 1. Create the archive directory if it doesn't exist
            if (Files.notExists(archiveDir)) {
                Files.createDirectories(archiveDir);
                System.out.println("Created archive directory: " + archiveDir);
            }

            // 2. Use a DirectoryStream with a glob pattern (*.log)
            // This is memory efficient and filters automatically!
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir, "*.log")) {
                
                int count = 0;
                for (Path entry : stream) {
                    // 3. Resolve the new path: archiveDir + filename
                    Path target = archiveDir.resolve(entry.getFileName());
                    
                    // 4. Perform an atomic move (replaces existing if needed)
                    Files.move(entry, target, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Archived: " + entry.getFileName());
                    count++;
                }
                
                System.out.println("\nSuccessfully moved " + count + " log files.");
            }

        } catch (IOException e) {
            System.err.println("Archiving failed: " + e.getMessage());
        }
    }
}

public class LogManagerApp {
    public static void main(String[] args) {
        // Setup dummy environment
        try {
            Files.createDirectories(Paths.get("logs"));
            Files.write(Paths.get("logs/app1.log"), "Log content".getBytes());
            Files.write(Paths.get("logs/app2.log"), "Log content".getBytes());
            Files.write(Paths.get("logs/readme.txt"), "Don't move me!"); // Should be ignored
            //Files.write(Paths.get("logs/readme.txt"), "Don't move me!".getBytes());

            LogArchiver archiver = new LogArchiver();
            archiver.archiveLogs("logs", "archive");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}