class Worker implements Runnable {
    // volatile ensures that changes made by the 'main' thread 
    // are visible to the 'worker' thread immediately.
    private volatile boolean running = true;

    public void shutdown() {
        System.out.println("\n[Main] Shutdown signal sent...");
        running = false;
    }

    @Override
    public void run() {
        System.out.println("[Worker] Thread started.");
        while (running) {
            System.out.println("[Worker] processing data...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("[Worker] interrupted!");
            }
        }
        System.out.println("[Worker] Thread stopped safely.");
    }
}

public class KillSwitchDemo {
    public static void main(String[] args) throws InterruptedException {
        Worker workerTask = new Worker();
        Thread workerThread = new Thread(workerTask);

        workerThread.start();

        // Let the worker run for 1 second
        Thread.sleep(1000);

        // Tell the worker to stop
        workerTask.shutdown();

        // Wait for the thread to actually finish
        workerThread.join();
        System.out.println("[Main] Program exited.");
    }
}