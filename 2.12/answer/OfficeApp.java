// 1. Defining the Capability Interfaces
interface Printable { void printDoc(); }
interface Scannable { void scanDoc(); }
interface Faxable   { void faxDoc(); }

// 2. Implementing all three (Multiple Inheritance)
class SmartDevice implements Printable, Scannable, Faxable {
    
    @Override
    public void printDoc() {
        System.out.println("Printing document in high resolution...");
    }

    @Override
    public void scanDoc() {
        System.out.println("Scanning document to PDF...");
    }

    @Override
    public void faxDoc() {
        System.out.println("Sending fax to recipient...");
    }
}

public class OfficeApp {
    public static void main(String[] args) {
        SmartDevice myPrinter = new SmartDevice();
        myPrinter.printDoc();
        myPrinter.scanDoc();
        myPrinter.faxDoc();
    }
}