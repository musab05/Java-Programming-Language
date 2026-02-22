import java.util.Deque;
import java.util.LinkedList;

class CallCenter {
    // We use the Deque interface to expose the queue-specific methods
    // LinkedList is the perfect implementation for this scenario
    private Deque<String> callLine = new LinkedList<>();

    // O(1) operation: Adjusts the 'tail' pointer
    public void addCaller(String name) {
        callLine.addLast(name);
        System.out.println("Standard caller added to the back: " + name);
    }

    // O(1) operation: Adjusts the 'head' pointer
    public void addVipCaller(String name) {
        callLine.addFirst(name);
        System.out.println("VIP caller jumped to the front: " + name);
    }

    // O(1) operation: Removes the 'head' and updates the pointer
    public String answerCall() {
        // pollFirst() returns null if the queue is empty, preventing an exception
        String nextCaller = callLine.pollFirst();
        
        if (nextCaller == null) {
            return "No calls waiting.";
        }
        return "Answering call from: " + nextCaller;
    }
}

public class Main {
    public static void main(String[] args) {
        CallCenter center = new CallCenter();
        
        center.addCaller("Alice");
        center.addCaller("Bob");
        center.addVipCaller("Mr. CEO"); // Should be answered first!
        
        System.out.println("\n--- Answering Calls ---");
        System.out.println(center.answerCall()); // Answers Mr. CEO
        System.out.println(center.answerCall()); // Answers Alice
        System.out.println(center.answerCall()); // Answers Bob
        System.out.println(center.answerCall()); // No calls waiting
    }
}