## 🎤 Frequently Asked Interview Questions

### 1. What exactly causes a `ConcurrentModificationException`?

**Answer:** Java collections maintain an internal counter called `modCount` that tracks how many times the collection has been modified (items added or removed). When you create an `Iterator` (which a `for-each` loop does implicitly), it records the current `modCount`. Every time it moves to the `next()` item, it checks if the collection's `modCount` still matches its recorded version. If you modify the collection directly (e.g., `list.remove()`) instead of using the iterator's own `.remove()` method, the counts fall out of sync. The iterator detects this and immediately throws the exception to prevent unpredictable behavior. This is known as a **fail-fast** mechanism.

### 2. Can you use an `Iterator` on a `Map`?

**Answer:** Not directly! The `Map` interface does not extend `Collection` or `Iterable`, so it doesn't have an `iterator()` method. To iterate over a map, you must first extract a Collection "view" from it, such as `map.keySet()`, `map.values()`, or the most efficient option, `map.entrySet()`. Once you have that Set or Collection, you can iterate through it.

### 3. What is the difference between `Iterator` and `ListIterator`?

**Answer:** * **Scope:** `Iterator` works on any `Collection` (Set, List, Queue). `ListIterator` *only* works on `List` implementations.

* **Direction:** `Iterator` only moves forward (`next()`). `ListIterator` is bidirectional (`next()` and `previous()`).
* **Modification:** `Iterator` can only `remove()` the current element. `ListIterator` can `remove()`, `add()` new elements at the cursor's current position, and `set()` (replace) the current element.

### 4. If `removeIf()` is available in Java 8+, why do we still need to know how `Iterator.remove()` works?

**Answer:** Under the hood, `removeIf()` is actually just an elegant wrapper that uses an `Iterator` to do the exact same thing! Understanding the Iterator ensures you know *why* `removeIf()` is safe to use, and it prepares you for maintaining legacy codebases written before Java 8. Plus, if your removal logic is highly complex and requires maintaining external state, a manual `Iterator` block is sometimes easier to debug than a massive Lambda expression.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Modern Fix

**Task:** Here is a junior developer's broken code. Fix it using the modern Java 8+ `removeIf()` approach in just one line.

```java
List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Amanda", "Charlie"));
for (String name : names) {
    if (name.startsWith("A")) {
        names.remove(name); // CRASH!
    }
}

```

**Solution:**

```java
names.removeIf(name -> name.startsWith("A"));

```

### Challenge 2: The Rewind

**Task:** You have a `List<Integer> numbers`. Write the code to instantiate a `ListIterator` that starts at the very **end** of the list and prints the numbers backwards.
**Solution:**

```java
// Pass the size of the list into the listIterator() method to start at the end!
ListIterator<Integer> iterator = numbers.listIterator(numbers.size());
while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}

```

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to manually manage an Iterator on a Set. The second pushes you to use the advanced bidirectional capabilities of a `ListIterator`.

### Problem 1: The Roster Purge (Standard Iterator)

You are managing a `Set` of employee IDs, and you need to purge anyone whose ID is an even number. Because it's a `Set`, you cannot use a `ListIterator` or an index-based loop.

**Requirements:**

1. Create a `Set<Integer> employeeIds` using a `HashSet` and populate it with numbers 1 through 10.
2. Instantiate a standard `Iterator<Integer>`.
3. Write a `while` loop that traverses the set.
4. Inside the loop, check if the ID is even (`id % 2 == 0`).
5. If it is even, safely remove it using the iterator.
6. Print the final set to verify only odd IDs remain. *(Do not use `removeIf()` for this exercise—practice the raw Iterator!)*

### Problem 2: The Text Editor Cursor (`ListIterator`)

Imagine you are building the backend for a simple text editor. You need to simulate a blinking cursor moving back and forth through a sequence of words, making edits.

**Requirements:**

1. Create a `LinkedList<String> document` and add: `"The"`, `"quick"`, `"brown"`, `"fox"`.
2. Get a `ListIterator<String>` starting at index 0.
3. Move the cursor forward until it passes the word `"quick"`.
4. Once it passes `"quick"`, use the iterator to **add** the word `"red"` immediately after it.
5. Move the cursor forward again until it passes `"fox"`.
6. Use the iterator to **set** (replace) `"fox"` with `"wolf"`.
7. Move the cursor **backward** to the beginning, printing each word as you pass it to read the final sentence in reverse.
