public class GCPerformance {
    public static void main(String[] args) {
        int iterations = 50_000;

        // TEST 1: The "GC Nightmare" (String concatenation)
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "data"; // Creates a NEW String object every single time!
        }
        long end = System.currentTimeMillis();
        System.out.println("String Concatenation took: " + (end - start) + "ms");

        // TEST 2: The "GC Friendly" (StringBuilder)
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("data"); // Modifies a single buffer; almost zero garbage
        }
        String finalResult = sb.toString();
        end = System.currentTimeMillis();
        System.out.println("StringBuilder took: " + (end - start) + "ms");
    }
}