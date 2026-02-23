public class ParallelStartup {
    public static void main(String[] args) {
        System.out.println("System boot initiated...");

        // 1. Define services using Lambdas
        Thread emailSvc = new Thread(() -> {
            try {
                System.out.println("Email Service: Starting...");
                Thread.sleep(1000);
                System.out.println("Email Service: [OK]");
            } catch (InterruptedException e) {}
        });

        Thread dbSvc = new Thread(() -> {
            try {
                System.out.println("Database Service: Starting...");
                Thread.sleep(1500);
                System.out.println("Database Service: [OK]");
            } catch (InterruptedException e) {}
        });

        Thread logSvc = new Thread(() -> {
            try {
                System.out.println("Logging Service: Starting...");
                Thread.sleep(500);
                System.out.println("Logging Service: [OK]");
            } catch (InterruptedException e) {}
        });

        // 2. Start all services in parallel
        emailSvc.start();
        dbSvc.start();
        logSvc.start();

        try {
            // 3. Wait for all to finish before proceeding
            emailSvc.join();
            dbSvc.join();
            logSvc.join();
            
            System.out.println("\nSUCCESS: All systems online. Ready for traffic.");
        } catch (InterruptedException e) {
            System.err.println("Startup interrupted!");
        }
    }
}