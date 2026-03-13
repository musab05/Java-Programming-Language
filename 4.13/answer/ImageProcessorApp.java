import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class ImageBlurAction extends RecursiveAction {
    private final int startRow;
    private final int endRow;
    private static final int THRESHOLD = 1000;

    public ImageBlurAction(int startRow, int endRow) {
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    protected void compute() {
        int range = endRow - startRow;

        if (range <= THRESHOLD) {
            // Task is small enough to handle
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is processing rows: " + startRow + " to " + endRow);
            // (Simulated blur math here)
        } else {
            // Split the work in half
            int middle = startRow + (range / 2);
            ImageBlurAction left = new ImageBlurAction(startRow, middle);
            ImageBlurAction right = new ImageBlurAction(middle, endRow);

            // invokeAll is a shortcut that forks 'left' and computes 'right' for you
            invokeAll(left, right);
        }
    }
}

public class ImageProcessorApp {
    public static void main(String[] args) {
        // Simulating an image with 5000 rows
        int totalRows = 5000;
        ForkJoinPool pool = new ForkJoinPool();

        System.out.println("Starting Parallel Image Blur...");
        pool.invoke(new ImageBlurAction(0, totalRows));
        
        System.out.println("Processing complete.");
    }
}