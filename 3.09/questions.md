Sets are a fantastic topic, and you've perfectly captured the essence of why we use them: **uniqueness**. In technical interviews, the jump from Lists to Sets is where interviewers really start probing your knowledge of time complexity (Big O) and internal data structures.

Let's dive into the most common interview questions, a few quick challenges, and some practical coding problems to lock in your understanding of `HashSet` and `TreeSet`.

---

## 🎤 Frequently Asked Interview Questions

### 1. How exactly does a `HashSet` prevent duplicates under the hood?

**Answer:** A `HashSet` doesn't actually implement its own data structure; it uses a `HashMap` behind the scenes! When you add an item to a `HashSet`, Java uses your item as the **Key** in the internal `HashMap`, and it maps it to a constant, dummy `Object` as the value. Because a `HashMap` strictly forbids duplicate keys (overwriting the old value if a duplicate key is found), the `HashSet` naturally guarantees uniqueness.

### 2. Why does `HashSet` allow a `null` value, but `TreeSet` throws a `NullPointerException`?

**Answer:** It comes down to how they organize data.

* A `HashSet` uses `hashCode()`. In Java's internal implementation, if a key is `null`, it simply hardcodes its hash to bucket `0`.
* A `TreeSet` uses a Red-Black Tree and must actively sort elements using the `compareTo()` method (or a custom `Comparator`). If it tries to call `compareTo()` on a `null` object, or compare an existing object to `null`, the JVM throws a `NullPointerException`.

### 3. If `HashSet` is O(1) and `TreeSet` is O(log n), why would you ever use a `TreeSet`?

**Answer:** You use `TreeSet` when **sorting is a strict business requirement**. If you use a `HashSet` but later need to print the elements in alphabetical order, you have to dump the `HashSet` into a `List` and call `Collections.sort(list)`, which takes **O(n log n)** time. A `TreeSet` keeps the data perfectly sorted at all times, making it much more efficient for range queries (e.g., "Give me all scores between 50 and 100").

### 4. What happens if you modify an object *after* you've added it to a `HashSet`?

**Answer:** This is a notorious memory leak trap! If you change a field on an object that alters its `hashCode()`, the `HashSet` will no longer be able to find it, because it will be looking in the wrong "bucket." You won't be able to remove it, and adding the "same" object again will create a duplicate. **Rule of thumb:** Only use immutable objects (like `String` or `Integer`) or objects whose hashed fields won't change as elements in a `HashSet`.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Null Trap

**Task:** What is the output of the following code?

```java
Set<String> hashSet = new HashSet<>();
hashSet.add(null);
System.out.println("HashSet size: " + hashSet.size());

Set<String> treeSet = new TreeSet<>();
treeSet.add(null);
System.out.println("TreeSet size: " + treeSet.size());

```

**Solution:** It prints `"HashSet size: 1"`, and then immediately crashes with a `NullPointerException` on the `treeSet.add(null)` line. `TreeSet` cannot compare `null`.

### Challenge 2: The Memory Order

**Task:** You are building a "Recently Played Songs" feature. You want to ensure no duplicate songs are in the list, but you *must* preserve the exact order the user played them in. Which Set implementation do you use?
**Solution:** `LinkedHashSet`. It provides the O(1) uniqueness checks of a `HashSet`, but maintains a doubly-linked list through all its entries to remember the insertion order.

---

## 📝 Practice Problems

Here are two scenario-based problems to test your ability to utilize the specific strengths of these different Sets.

### Problem 1: The Unique Visitor Tracker (`LinkedHashSet`)

You are building an analytics dashboard for a website. You need to track the IP addresses of visitors for the day.

**Requirements:**

1. Create a `VisitorTracker` class.
2. Initialize the correct `Set` implementation that guarantees uniqueness but **remembers the exact chronological order** the IPs visited.
3. Write a method `public void logVisit(String ipAddress)`. It should add the IP to the set.
4. Write a method `public void printChronologicalVisitors()`.
5. In `Main`, simulate visitors: "192.168.1.1", "10.0.0.5", and then "192.168.1.1" again. Print the list to verify the duplicate was ignored but the order was maintained.

### Problem 2: The Auto-Sorted Leaderboard (`TreeSet`)

You are building the backend for an arcade game. You need to store the high scores.

**Requirements:**

1. Create a `Leaderboard` class.
2. Initialize a `TreeSet<Integer>` to hold the scores.
3. Write a method `public void addScore(int score)`.
4. Write a method `public void getTopScores()`. Use the special `NavigableSet` methods (like `.descendingSet()`) to print the scores from highest to lowest.
5. Write a method `public void getScoresAbove(int threshold)`. Use the `.tailSet()` method to return only the scores greater than or equal to the threshold.
6. Test it in `Main` with scores: 500, 1200, 300, 850, 1200.
