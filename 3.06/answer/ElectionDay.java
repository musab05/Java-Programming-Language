import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class VotingMachine {
    // A Set naturally prevents duplicates and provides lightning-fast lookups
    private Set<String> votedCitizens = new HashSet<>();
    
    // A Map links the candidate (Key) to their total votes (Value)
    private Map<String, Integer> candidateVotes = new HashMap<>();

    public void castVote(String citizenId, String candidate) {
        // 1. Check for fraud using the Set
        if (votedCitizens.contains(citizenId)) {
            throw new IllegalStateException("Fraud Alert: Citizen " + citizenId + " has already voted!");
        }

        // 2. Mark the citizen as having voted
        votedCitizens.add(citizenId);

        // 3. Tally the vote in the Map
        int currentVotes = candidateVotes.getOrDefault(candidate, 0);
        candidateVotes.put(candidate, currentVotes + 1);
        
        System.out.println("Vote successfully cast by " + citizenId + " for " + candidate);
    }

    public void printResults() {
        System.out.println("\n--- Final Election Results ---");
        for (Map.Entry<String, Integer> result : candidateVotes.entrySet()) {
            System.out.println(result.getKey() + ": " + result.getValue() + " votes");
        }
    }
}

public class ElectionDay {
    public static void main(String[] args) {
        VotingMachine machine = new VotingMachine();

        // Legitimate votes
        machine.castVote("ID-123", "Alice");
        machine.castVote("ID-456", "Bob");
        machine.castVote("ID-789", "Alice");

        // Fraudulent vote attempt!
        try {
            machine.castVote("ID-123", "Bob");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Print final tallies
        machine.printResults();
    }
}