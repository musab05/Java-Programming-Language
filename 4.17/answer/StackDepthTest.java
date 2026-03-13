public class StackDepthTest {
    private static int depth = 0;

    public static void main(String[] args) {
        try {
            recursiveMethod();
        } catch (StackOverflowError e) {
            System.err.println("!!! STACK OVERFLOW !!!");
            System.err.println("Max depth reached: " + depth);
        }
    }

    public static void recursiveMethod() {
        depth++;
        // We create a few local variables to "fatten" the stack frame
        // This makes the stack fill up faster
        long a = 1L; long b = 2L; long c = 3L;
        
        recursiveMethod();
    }
}