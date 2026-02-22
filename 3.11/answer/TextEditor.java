import java.util.LinkedList;
import java.util.ListIterator;

public class TextEditor {
    public static void main(String[] args) {
        // 1. Setup the document
        LinkedList<String> document = new LinkedList<>();
        document.add("The");
        document.add("quick");
        document.add("brown");
        document.add("fox");
        
        System.out.println("Original Text: " + document);

        // 2. Get the ListIterator starting at index 0
        ListIterator<String> cursor = document.listIterator();

        // 3 & 4. Find "quick" and insert "red"
        while (cursor.hasNext()) {
            String word = cursor.next();
            if (word.equals("quick")) {
                // add() inserts the new item immediately BEFORE the cursor's current position
                cursor.add("red");
                System.out.println("Added 'red'.");
                break; // Stop looking, we did our job
            }
        }

        // 5 & 6. Continue forward to find "fox" and replace it with "wolf"
        while (cursor.hasNext()) {
            String word = cursor.next();
            if (word.equals("fox")) {
                // set() replaces the last element returned by next() or previous()
                cursor.set("wolf");
                System.out.println("Replaced 'fox' with 'wolf'.");
                break;
            }
        }
        
        System.out.println("\nModified Text (Forward): " + document);
        System.out.println("\n--- Reading Backwards ---");

        // 7. Rewind! Read the document in reverse using the same iterator
        while (cursor.hasPrevious()) {
            System.out.print(cursor.previous() + " ");
        }
        System.out.println(); // Just for a clean new line
    }
}