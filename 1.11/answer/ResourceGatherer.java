public class ResourceGatherer {
    public static void main(String[] args) {
        // 1. Declare the starting inventory space
        int inventorySpace = 0;
        
        // 2. The loop condition checks if the inventory is NOT full
        while (inventorySpace < 5) {
            
            // 3. Execute the action
            System.out.println("Chopping wood...");
            
            // 4. Increment the inventory space
            inventorySpace++; // This is exactly the same as inventorySpace = inventorySpace + 1;
            
            System.out.println("Current space filled: " + inventorySpace + "/5");
        }
        
        // 5. This code only runs after the loop has completely finished
        System.out.println("Inventory full! Return to base.");
    }
}