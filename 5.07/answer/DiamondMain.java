public class DiamondMain {

    interface Readable {
        default String read() { return "Reading..."; }
    }

    interface Writable {
        default String write() { return "Writing..."; }
    }

    interface ReadWritable extends Readable, Writable {
        default String readWrite() {
            return read() + " then " + write();
        }
    }

    static class SmartFile implements ReadWritable {
        private String filename;

        SmartFile(String filename) {
            this.filename = filename;
        }

        @Override
        public String read() {
            return "Smart-reading file: " + filename; // Custom override
        }
        // write() and readWrite() are inherited for free!
    }

    public static void main(String[] args) {
        SmartFile file = new SmartFile("report.txt");

        System.out.println(file.read());       // Custom: Smart-reading file: report.txt
        System.out.println(file.write());      // Default: Writing...
        System.out.println(file.readWrite());  // Combines custom read() + default write()
    }
}
