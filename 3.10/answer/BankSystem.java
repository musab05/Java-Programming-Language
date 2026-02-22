import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Analyzer {

    public List<String> findSuspiciousAccounts(List<String> accountIds) {
        // 1. We use a HashMap for lightning-fast frequency counting
        Map<String, Integer> transactionCounts = new HashMap<>();
        List<String> suspiciousAccounts = new ArrayList<>();

        // 2. Count the occurrences of each account ID
        for (String id : accountIds) {
            // getOrDefault handles the very first time we see an ID perfectly
            transactionCounts.put(id, transactionCounts.getOrDefault(id, 0) + 1);
        }

        // 3. Iterate through the map to find the rule-breakers
        // We use entrySet() because it is the most efficient way to access both keys and values
        for (Map.Entry<String, Integer> entry : transactionCounts.entrySet()) {
            if (entry.getValue() > 3) {
                suspiciousAccounts.add(entry.getKey());
            }
        }

        return suspiciousAccounts;
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer();
        
        // Simulating a batch of transactions
        List<String> batch = List.of(
            "ACC-101", "ACC-102", "ACC-101", "ACC-103", 
            "ACC-101", "ACC-104", "ACC-101", "ACC-102"
        );

        System.out.println("Processing transaction batch...");
        List<String> flagged = analyzer.findSuspiciousAccounts(batch);
        
        System.out.println("Suspicious Accounts detected (> 3 transactions): " + flagged);
    }
}