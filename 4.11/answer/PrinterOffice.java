import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Printer {
    private final ReentrantLock lock = new ReentrantLock();

    public void print(String document) {
        String threadName = Thread.currentThread().getName();
        
        try {
            // Attempt to get the lock, but only wait for 2 seconds
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(threadName + " acquired the printer. Printing: " + document);
                    Thread.sleep(1000); // Simulate printing time
                    System.out.println(threadName + " finished printing.");
                } finally {
                    // CRITICAL: Always unlock in finally
                    lock.unlock();
                }
            } else {
                System.out.println(threadName + ": Printer busy. I'll come back later.");
            }
        } catch (InterruptedException e) {
            System.err.println(threadName + " was interrupted.");
        }
    }
}

public class PrinterOffice {
    public static void main(String[] args) {
        Printer officePrinter = new Printer();

        // 5 employees trying to print at the same time
        Runnable employeeTask = () -> officePrinter.print("Quarterly Report");

        for (int i = 1; i <= 5; i++) {
            new Thread(employeeTask, "Employee-" + i).start();
        }
    }
}