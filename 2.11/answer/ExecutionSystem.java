abstract class WorkFlow {
    void start() {
        System.out.println("Step 1: System Booting...");
    }

    void stop() {
        System.out.println("Step 3: Cleaning up and shutting down.");
    }

    // Changing behavior
    abstract void execute();

    // 4. Final method locks the execution sequence
    final void runProcess() {
        start();
        execute();
        stop();
    }
}

class DataCleanWorkflow extends WorkFlow {
    @Override
    void execute() {
        System.out.println("Step 2: Scouring database for corrupted entries...");
    }
}

public class ExecutionSystem {
    public static void main(String[] args) {
        DataCleanWorkflow cleanJob = new DataCleanWorkflow();
        // 6. Triggers the entire sequence
        cleanJob.runProcess();
    }
}