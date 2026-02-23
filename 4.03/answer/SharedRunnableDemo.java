class CounterTask implements Runnable {
    // Shared state across any threads that use this instance
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            count++;
            System.out.println(Thread.currentThread().getName() + 
                               " incremented count to: " + count);
            
            // Short sleep to encourage the threads to overlap
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}

public class SharedRunnableDemo {
    public static void main(String[] args) {
        // ONE task instance
        CounterTask sharedTask = new CounterTask();

        // TWO threads using the SAME task
        Thread worker1 = new Thread(sharedTask, "Worker-Alpha");
        Thread worker2 = new Thread(sharedTask, "Worker-Beta");

        worker1.start();
        worker2.start();
    }
}