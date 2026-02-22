The `ArrayList` vs. `LinkedList` debate is an absolute classic in technical interviews. It separates the developers who just know how to use an API from the engineers who actually understand how memory and data structures work under the hood.

Here are the top interview questions, quick coding challenges, and architectural practice problems to make sure you absolutely nail this topic.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is the default capacity of an `ArrayList`, and exactly how does it "grow"?

**Answer:** When you create an empty `ArrayList` using the default constructor, it actually starts with a capacity of 0, but expands to a default capacity of **10** the moment you add the first element. When you try to add the 11th element, Java calculates a new capacity by taking the old capacity and adding half of it (using a bitwise right shift: `oldCapacity >> 1`). So, a list of 10 grows to 15, then 22, then 33, etc. It then copies the old array into this new, larger array using `Arrays.copyOf()`.

### 2. You mentioned the "Middle Myth" (that `LinkedList` is faster for middle insertions). Why does `ArrayList` usually win in real-world benchmarks anyway?

**Answer:** Two words: **Cache Locality**. Modern CPUs load data from RAM into ultra-fast CPU caches in "blocks." Because an `ArrayList` uses contiguous memory (all elements are physically next to each other in RAM), the CPU pre-fetches the list into the cache, making searching incredibly fast. A `LinkedList` has nodes scattered randomly all over the heap, meaning the CPU constantly gets "cache misses" and has to slowly fetch each pointer from RAM. This hardware reality makes `ArrayList` faster for almost everything in practice.

### 3. Does Java's `LinkedList` implement a singly or doubly linked list?

**Answer:** It is a **Doubly Linked List**. Every node has a reference to both the `next` node and the `previous` node. This allows for efficient reverse iteration (using `descendingIterator()`) and makes it easier to remove a node if you already hold a reference to it.

### 4. Which one is better if I want to build a Queue or a Stack?

**Answer:** `LinkedList`. In fact, the `LinkedList` class in Java implements the `Deque` (Double Ended Queue) interface, giving you native methods like `addFirst()`, `addLast()`, `removeFirst()`, and `removeLast()`. Because you only manipulate the exact ends of the chain, these operations are a guaranteed O(1) without ever needing to resize an array.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Performance Disaster

**Task:** You are writing a script that needs to process and then remove the *first* item of a list 100,000 times until the list is empty.

```java
// Which implementation prevents your application from freezing?
List<String> tasks = new ArrayList<>(); 
// OR
List<String> tasks = new LinkedList<>();

while(!tasks.isEmpty()) {
    process(tasks.get(0));
    tasks.remove(0); 
}

```

**Solution:** You **must** use a `LinkedList`. Calling `tasks.remove(0)` on an `ArrayList` forces the JVM to shift the remaining 99,999 elements to the left. Doing that 100,000 times will result in roughly 5 billion shift operations! A `LinkedList` just updates the `head` pointer instantly.

### Challenge 2: The Capacity Optimization

**Task:** You are pulling exactly 5,000 user records from a database and putting them into a new `ArrayList`. How do you write the initialization line to prevent the JVM from wastefully resizing the array multiple times as you add the users?
**Solution:** 
```java
// Pre-allocate the exact capacity you need!
List<User> users = new ArrayList<>(5000);
```

---

## 📝 Practice Problems

Here are two scenario-based problems. They test your ability to look at business requirements and choose the correct underlying data structure.

### Problem 1: The Call Center Queue
You are building the routing software for a customer service call center. When a customer calls, they are put at the back of a line. When an agent is free, they take the customer at the front of the line. Sometimes, VIP customers call and must be placed at the absolute *front* of the line immediately.

**Requirements:**
1. Create a `CallCenter` class.
2. Choose the correct `List` implementation for your `callLine` variable to ensure O(1) additions and removals from the very front and the very back.
3. Write three methods:
   * `public void addCaller(String name)` (adds to the back)
   * `public void addVipCaller(String name)` (adds to the very front)
   * `public String answerCall()` (removes and returns the person at the front, or returns "No calls" if empty).
4. *Hint:* To access the special `addFirst()` and `removeFirst()` methods, you will need to declare your variable as a `LinkedList` or `Deque`, not just a `List`.

### Problem 2: The Fast-Read Product Catalog
You are building an e-commerce backend. The catalog contains exactly 10,000 products loaded from a file at startup. Once loaded, the catalog never changes (no additions, no deletions). Users will be searching this catalog millions of times a day by passing in random index numbers.

**Requirements:**
1. Create a `ProductCatalog` class.
2. Initialize the correct `List` implementation that is perfectly optimized for O(1) random access lookups, and ensure you pre-allocate the memory to hold exactly 10,000 items so the list never has to resize.
3. Write a method `public void loadProducts(String[] productData)` that fills the list.
4. Write a method `public String getProductAt(int index)`. If the index is out of bounds, throw an `IndexOutOfBoundsException`.
