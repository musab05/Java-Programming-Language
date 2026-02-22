// 1 & 2. The Generic Class definition using standard K (Key) and V (Value) placeholders
class Pair<K, V> {
    private K key;
    private V value;

    // 3. The constructor uses the generic placeholders
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Standard getters returning the generic types
    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override
    public String toString() {
        return "[" + key.getClass().getSimpleName() + ": " + key + 
               " | " + value.getClass().getSimpleName() + ": " + value + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        // 4. Instantiating with completely different type profiles
        Pair<Integer, String> userProfile = new Pair<>(101, "Alice");
        Pair<String, Double> stockPrice = new Pair<>("AAPL", 150.75);

        // 5. Printing the results. Notice how the compiler inherently knows 
        // that userProfile.getKey() is an Integer, not just an Object!
        System.out.println("User Profile -> " + userProfile);
        System.out.println("Stock Price  -> " + stockPrice);
    }
}