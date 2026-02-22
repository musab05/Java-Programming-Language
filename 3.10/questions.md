In Java interviews, `HashMap` is arguably the most scrutinized class in the entire standard library. Interviewers will push past the syntax and grill you on exactly how the hash table resolves collisions and manages memory.

Here are the top interview questions, rapid-fire challenges, and architectural practice problems to cement your mastery of Maps.

---

## 🎤 Frequently Asked Interview Questions

### 1. How exactly does a `HashMap` handle collisions, and what changed in Java 8?

**Answer:** A collision happens when two different keys generate the same hash code and land in the same "bucket."

* **Pre-Java 8:** `HashMap` stored these colliding entries in a simple Linked List. If you had terrible hash codes, your  map could degrade into an  linked list.
* **Java 8+ Update:** To fix this, Java 8 introduced a threshold. If a single bucket gets more than 8 items, the `HashMap` dynamically transforms that bucket's Linked List into a **Red-Black Tree**. This ensures that even in the worst-case scenario of massive collisions, the lookup time degrades to  rather than .

### 2. What is the "Load Factor" in a `HashMap`?

**Answer:** The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased. The default initial capacity is 16, and the default load factor is **0.75**. This means when the map is 75% full (12 items), it will double its capacity to 32 and "rehash" (recalculate the buckets for) all existing items. It's a calculated compromise: a higher load factor saves memory but increases collisions (slowing down searches), while a lower load factor wastes memory but keeps searches lightning fast.

### 3. What happens if you use a custom object as a key in a `HashMap` but forget to override `hashCode()` and `equals()`?

**Answer:** You will lose your data! If you don't override them, Java uses the memory address of the object. If you do `map.put(new User(1), "Admin")` and then later call `map.get(new User(1))`, the map will return `null`. Even though the users have the same ID, they occupy different memory addresses, so the `HashMap` thinks they are completely different keys.

### 4. Can you use a custom object as a key in a `TreeMap`? What are the rules?

**Answer:** Yes, but with a strict requirement. Because `TreeMap` must keep its keys sorted in a Red-Black Tree, it needs to know *how* to compare them. The custom object must either implement the `Comparable` interface (defining a `compareTo()` method), OR you must pass a custom `Comparator` into the `TreeMap`'s constructor. If you do neither, the JVM will throw a `ClassCastException` at runtime.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The One-Line Counter

**Task:** You are looping through a list of words. For each word, you need to increment its count in a `Map<String, Integer> wordCounts`. If it doesn't exist yet, set it to 1. Do this in a single line of code inside the loop.
**Solution:**

```java
// Using the brilliant Map.merge() method introduced in Java 8
wordCounts.merge(word, 1, Integer::sum);

```

### Challenge 2: The Closest Appointment

**Task:** You have a `TreeMap<Integer, String> schedule` where the key is the time (in military hours, e.g., 1400) and the value is the appointment name. A user asks, "What is the next available appointment at or after 1500 hours?" Write the one line of code to find it.
**Solution:**

```java
// ceilingEntry returns the key-value mapping for the least key >= the given key
Map.Entry<Integer, String> nextAppt = schedule.ceilingEntry(1500);

```

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to use `HashMap` for efficient frequency counting, and the second tests your ability to leverage the advanced navigational methods of `TreeMap`.

### Problem 1: The Fraud Detection Analyzer (`HashMap`)

You are analyzing a massive log of bank transactions. You need to flag any account that has made more than 3 transactions in the current batch.

**Requirements:**

1. Create an `Analyzer` class.
2. Write a method `public List<String> findSuspiciousAccounts(List<String> accountIds)`.
3. Inside the method, use a `HashMap` to count how many times each `accountId` appears in the list.
4. Iterate through the map's `entrySet()`. If an account appears more than 3 times, add it to a "suspicious" list.
5. Return the list of suspicious accounts.

### Problem 2: The Flight Radar System (`TreeMap`)

You are building the tracking system for an air traffic controller. You need to know exactly which flights are scheduled to take off around specific times.

**Requirements:**

1. Create a `FlightRadar` class.
2. Initialize a `TreeMap<Integer, String>` where the key is the takeoff time (military time, e.g., `830`, `1415`) and the value is the flight number (e.g., `"DL123"`).
3. Write a method `public void registerFlight(int time, String flightNumber)`.
4. Write a method `public void getNextFlightAfter(int time)`. Use the `higherEntry()` method to find and print the very next flight scheduled *strictly after* the given time.
5. Write a method `public void getFlightsBetween(int startTime, int endTime)`. Use the `subMap()` method to print all flights scheduled within that specific time window (inclusive).
