Welcome back to the final act of our core Collections journey! We’ve handled linear lists and unique sets; now we enter the powerhouse of Java data management: the **Map Interface**.

As a developer, you will likely use `HashMap` more than almost any other collection. It’s the "Swiss Army Knife" of data structures. But when the requirements change—when you need your keys sorted or navigable—`TreeMap` is the specialized tool you reach for.

---

## 1. HashMap: The High-Speed Warehouse

`HashMap` is an implementation based on a **Hash Table**. It stores data in "buckets" using a technique called **hashing**.

### How it Works: The "Key" to Success

When you call `map.put(key, value)`, Java runs the `key.hashCode()` method. This returns an integer that determines which "bucket" the value goes into. If two different keys end up in the same bucket (a **collision**), Java handles it using a linked list or a balanced tree within that bucket.

### Key Characteristics:

* **Performance:** The "Gold Standard." It offers **** (constant time) for `get()` and `put()` operations.
* **Ordering:** **None.** The order of elements can change over time as the map grows.
* **Nulls:** Allows one `null` key and multiple `null` values.
* **Thread Safety:** Not synchronized (use `ConcurrentHashMap` for multi-threaded apps).

---

## 2. TreeMap: The Sorted Archive

While `HashMap` is built on a Hash Table, `TreeMap` is built on a **Red-Black Tree**. It implements the `NavigableMap` interface.

### How it Works: Natural Ordering

Instead of hashing, `TreeMap` compares keys against each other using `compareTo()` or a custom `Comparator`. It keeps the keys in a strictly sorted order at all times.

### Key Characteristics:

* **Performance:** ****. It’s slower than `HashMap` because every time you add or find a key, the map has to navigate the tree branches.
* **Ordering:** Always **sorted** by the keys (either natural order or your custom logic).
* **Nulls:** Does **not** allow `null` keys (because it needs to compare them to other keys), but allows multiple `null` values.
* **Navigation:** Provides powerful methods like `firstKey()`, `lastKey()`, `headMap()`, and `tailMap()`.

---

## 3. HashMap vs. TreeMap: The Professional Comparison

| Feature | HashMap | TreeMap |
| --- | --- | --- |
| **Data Structure** | Hash Table | Red-Black Tree |
| **Search/Insert Time** | **** (Constant) | **** (Logarithmic) |
| **Iteration Order** | Random / Unpredictable | **Sorted** (Natural or Comparator) |
| **Null Keys** | Allowed (One) | **Not Allowed** |
| **Best Use Case** | General purpose caching, fast lookups. | Creating a dictionary or a sorted report. |

---

## 4. Practical Implementation: The "Inventory System"

Let’s look at a scenario where we manage a warehouse. We use a `HashMap` for lightning-fast lookups by ID, but we use a `TreeMap` when we need to print an alphabetical product catalog.

```java
import java.util.*;

public class WarehouseManager {
    public static void main(String[] args) {
        // 1. HashMap: Fast lookup of price by Product ID
        Map<Integer, Double> priceLookup = new HashMap<>();
        priceLookup.put(105, 49.99);
        priceLookup.put(101, 1200.00);
        priceLookup.put(103, 15.50);

        // Fetching price for ID 101 is instant O(1)
        System.out.println("Price of 101: $" + priceLookup.get(101));

        // 2. TreeMap: Product Catalog sorted by Name (Key)
        Map<String, Integer> inventory = new TreeMap<>();
        inventory.put("Zebra Scanner", 10);
        inventory.put("Apple MacBook", 50);
        inventory.put("Dell Monitor", 30);

        // Printing results: Notice it's automatically sorted alphabetically
        System.out.println("\nSorted Inventory Catalog:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " | Stock: " + entry.getValue());
        }

        // 3. Navigation with TreeMap
        TreeMap<String, Integer> navMap = (TreeMap<String, Integer>) inventory;
        System.out.println("\nFirst Product: " + navMap.firstKey());
        System.out.println("Products after 'D': " + navMap.tailMap("D"));
    }
}

```

---

## Tip: The LinkedHashMap "Middle Ground"

Just like `LinkedHashSet`, if you want the ** speed** of a `HashMap` but you need to maintain the **insertion order** (e.g., for a "Last Viewed Items" feature), use `LinkedHashMap`. It’s essentially a `HashMap` with a hidden linked list connecting the entries in the order they were added.
