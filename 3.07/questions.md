The "Big Three" (Lists, Sets, and Maps) are the bread and butter of technical interviews. Interviewers don't just want to see that you can write the syntax; they want to see that you understand the underlying data structures and their Big O performance trade-offs.

Here are the top interview questions, quick challenges, and practical problem statements to ensure you can confidently choose the right "tool for the job."

---

## 🎤 Frequently Asked Interview Questions

### 1. When exactly should you choose a `LinkedList` over an `ArrayList`?

**Answer:** In 99% of real-world scenarios, you should use an `ArrayList` because accessing elements by index is O(1) (instant), and adding to the end is generally O(1). However, you should choose a `LinkedList` if your application requires a massive amount of insertions or deletions specifically at the *beginning* or *middle* of the list. An `ArrayList` has to shift every subsequent element over (O(N)), while a `LinkedList` just changes a couple of object pointers (O(1), assuming you already have the reference to the node).

### 2. What happens if you put a custom object into a `HashSet` but forget to override the `equals()` and `hashCode()` methods?

**Answer:** The `HashSet` will fail to recognize logical duplicates. By default, Java uses the memory address of an object for its hash code and equality check. If you create two separate `Person` objects with the exact same name and age, Java will treat them as two completely distinct objects and allow both into the `HashSet`. You *must* override those methods to tell Java what makes two objects "equal."

### 3. How does a `HashMap` handle "collisions" (when two different keys generate the same hash code)?

**Answer:** When a collision occurs, the `HashMap` doesn't overwrite the old value. Instead, it stores both Key-Value pairs in the same "bucket" using a Linked List. When you call `get()`, Java finds the bucket using the hash code, and then traverses the Linked List using the `equals()` method to find your exact key. *(Bonus points: Mention that in Java 8+, if a bucket gets too large—usually 8 items—it transforms from a Linked List into a Red-Black Tree to keep search times fast!)*

### 4. Why doesn't the `Map` interface extend the `Collection` interface?

**Answer:** Because they represent fundamentally different data paradigms. A `Collection` (like a List or Set) is a group of individual elements. A `Map` is a group of Key-Value *pairs*. The methods required for a Collection (like `add(Object)`) don't make sense for a Map, which requires `put(Key, Value)`.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Performance Fix

**Task:** You are given a massive `List<String>` of banned IP addresses, and you need to check if an incoming user's IP is in that list. Calling `bannedList.contains(userIp)` is causing severe lag. Fix it in one line of code.
**Solution:**

```java
// Convert the List to a HashSet. The contains() check goes from O(N) to O(1) instantly.
Set<String> fastBannedSet = new HashSet<>(bannedList);
// Now use fastBannedSet.contains(userIp);

```

### Challenge 2: The Ordered Dictionary

**Task:** You need to store a mapping of Student IDs (Integer) to Names (String). You need the speed of a HashMap, but when you iterate over the map to print the roster, it *must* print in the exact order the students were added. Which Map implementation do you instantiate?
**Solution:**

```java
Map<Integer, String> roster = new LinkedHashMap<>();

```

---

## 📝 Practice Problems

Here are two classic interview problems. The first tests your ability to use Maps to group complex data. The second tests your understanding of how Sets determine uniqueness with custom objects.

### Problem 1: The Anagram Grouper (Using Maps)

This is a highly popular whiteboarding question. An anagram is a word formed by rearranging the letters of a different word (e.g., "eat", "tea", "ate").

**Requirements:**

1. Write a method `public List<List<String>> groupAnagrams(String[] words)`.
2. You need to group the anagrams together. For example, if the input is `["eat", "tea", "tan", "ate", "nat", "bat"]`.
3. The output should be grouped: `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]`.
4. *Hint:* If you sort the characters of "eat", "tea", and "ate" alphabetically, they all become "aet". Use this sorted string as a **Key** in a `Map`, and use a `List<String>` as the **Value** to hold the actual words!

### Problem 2: The VIP Guest List (Using Sets & Custom Objects)

You are building an entry system for an exclusive club. You only want unique people on the guest list.

**Requirements:**

1. Create a `Guest` class with two fields: `String name` and `String passportNumber`.
2. Generate a constructor and getters.
3. **Crucial Step:** Override the `equals()` and `hashCode()` methods. Two guests should be considered exactly the same if their `passportNumber` is identical, regardless of their name.
4. In your `Main` class, instantiate a `HashSet<Guest>`.
5. Add three guests:
* "Alice", "US123"
* "Bob", "UK456"
* "Alice Fraud", "US123" (Someone trying to use Alice's passport!)


6. Print the size of the Set. If you did it right, the size should be `2`, and the fraudster should have been rejected automatically by the Set!
