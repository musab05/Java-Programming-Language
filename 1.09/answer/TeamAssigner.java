public class TeamAssigner {
    public static void main(String[] args) {
        // 1. Declare the player ID
        int playerId = 42; 
        
        // 2. Use modulus and ternary operator to evaluate and assign
        // Syntax: (condition) ? trueResult : falseResult;
        String team = (playerId % 2 == 0) ? "Red" : "Blue";
        
        // 3. Print the result
        System.out.println("Player ID " + playerId + " is assigned to the " + team + " team.");
        // Output will be: Player ID 42 is assigned to the Red team.
    }
}