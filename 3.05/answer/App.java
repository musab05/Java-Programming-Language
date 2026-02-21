import java.io.IOException;

// 1. The broad, somewhat dangerous legacy interface
interface DataExporter {
    void exportData(String data) throws Exception;
}

// 2 & 3. The File Exporter (Narrowing the exception)
class CSVExporter implements DataExporter {
    
    // We narrowed 'Exception' down to 'IOException'. This is perfectly legal!
    @Override
    public void exportData(String data) throws IOException {
        if (data == null || data.trim().isEmpty()) {
            // 4. Physically triggering the error
            throw new IOException("No data provided to export.");
        }
        System.out.println("Exporting to CSV: " + data);
    }
}

// 5 & 6. The Console Exporter (Removing the exception entirely)
class ConsoleExporter implements DataExporter {
    
    // We removed the 'throws' clause completely. 
    // This is the safest implementation possible, and fully legal!
    @Override
    public void exportData(String data) {
        System.out.println("Printing to console: " + (data == null ? "EMPTY" : data));
    }
}

public class App {
    public static void main(String[] args) {
        DataExporter consoleOut = new ConsoleExporter();
        
        // Notice a cool trick here: If we call consoleOut.exportData() 
        // through the DataExporter interface reference, the compiler STILL 
        // makes us use a try-catch, because the interface promised it might throw!
        try {
            consoleOut.exportData("Hello World!");
        } catch (Exception e) {
            System.out.println("Failed to export.");
        }
    }
}