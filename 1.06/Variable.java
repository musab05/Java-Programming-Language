public class Variable {
    public static void main(String[] args){
        // Integer Types
        byte smallNum = 100;           // 1 byte
        short medNum = 30000;          // 2 bytes
        int age = 50;                  // 4 bytes
        long bigNum = 1500000000L;     // 8 bytes

        // Floating Point Types
        float price = 19.99f;          // 4 bytes
        double pi = 3.14159;           // 8 bytes

        // Character & Boolean
        char grade = 'A';              // 2 bytes
        boolean isReady = true;        // 1 bit (represented as 1 byte in memory)

        // String: A sequence of characters
        String greeting = "Hello";      // Reference (usually 4-8 bytes) -> Heap Object

        // Arrays: Fixed-size collection of the same type
        int[] numbers = {1, 2, 3};      // Reference -> Heap Array Object

        // Classes/Interfaces: Custom types
        User student = new User();      // Reference -> Heap Object Instance
        // Note this code will not compile since User class is not initialise.
    }
}
