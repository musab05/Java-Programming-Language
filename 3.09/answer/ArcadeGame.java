import java.util.NavigableSet;
import java.util.TreeSet;

class Leaderboard {
    // We declare it as a TreeSet (or NavigableSet) specifically so we 
    // can access the special sorting methods like descendingSet().
    private TreeSet<Integer> scores = new TreeSet<>();

    public void addScore(int score) {
        scores.add(score);
        System.out.println("Score added: " + score);
    }

    public void getTopScores() {
        System.out.println("\n--- Leaderboard (Highest to Lowest) ---");
        
        // By default, TreeSet sorts natural order (lowest to highest).
        // descendingSet() reverses this instantly without re-sorting the data!
        NavigableSet<Integer> topDown = scores.descendingSet();
        
        int rank = 1;
        for (Integer score : topDown) {
            System.out.println("Rank " + rank + ": " + score);
            rank++;
        }
    }

    public void getScoresAbove(int threshold) {
        System.out.println("\n--- Scores qualifying for a prize (>= " + threshold + ") ---");
        
        // tailSet(value, inclusive) returns a view of the set from that value to the end.
        // It's incredibly efficient because it uses the underlying Red-Black tree.
        NavigableSet<Integer> qualifiers = scores.tailSet(threshold, true);
        
        for (Integer score : qualifiers) {
            System.out.println("Qualifier: " + score);
        }
    }
}

public class ArcadeGame {
    public static void main(String[] args) {
        Leaderboard board = new Leaderboard();

        board.addScore(500);
        board.addScore(1200);
        board.addScore(300);
        board.addScore(850);
        board.addScore(1200); // Ignored by the Set automatically

        board.getTopScores();
        board.getScoresAbove(800);
    }
}