import java.util.*;

class AnagramGrouper {
    
    public List<List<String>> groupAnagrams(String[] words) {
        // The Key is the sorted string (e.g., "aet")
        // The Value is the list of original words (e.g., ["eat", "tea", "ate"])
        Map<String, List<String>> groupedMap = new HashMap<>();
        
        for (String currentWord : words) {
            // 1. Convert the word to a char array and sort it
            char[] characters = currentWord.toCharArray();
            Arrays.sort(characters);
            String sortedKey = new String(characters); // "eat" becomes "aet"
            
            // 2. Put it in the map
            // computeIfAbsent is a brilliant modern Java method. 
            // It creates a new ArrayList if the key doesn't exist yet!
            groupedMap.computeIfAbsent(sortedKey, k -> new ArrayList<>()).add(currentWord);
        }
        
        // 3. We only care about the grouped lists, not the sorted keys anymore
        return new ArrayList<>(groupedMap.values());
    }
}

public class Main {
    public static void main(String[] args) {
        AnagramGrouper grouper = new AnagramGrouper();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> result = grouper.groupAnagrams(input);
        
        System.out.println("Grouped Anagrams:");
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}