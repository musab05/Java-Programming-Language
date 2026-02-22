## 🎤 Frequently Asked Interview Questions

### 1. Why does `compareTo()` or `compare()` return an `int` instead of a `boolean`?

**Answer:** Because sorting requires knowing three distinct states, not just two. A boolean (`true`/`false`) can only tell you if things are equal or not. An `int` allows Java to know if an object is **less than** (negative number), **equal to** (zero), or **greater than** (positive number) the other object. This three-way result dictates exactly how the sorting algorithm shifts the elements in memory.

### 2. Is it safe to use `return this.id - other.id;` inside a `compareTo` method?

**Answer:** It is a common shortcut, but it's actually **unsafe**! It makes you vulnerable to **Integer Overflow**. If `this.id` is a very large positive number (e.g., 2 billion) and `other.id` is a very large negative number (e.g., -2 billion), subtracting them exceeds the 32-bit limit of an `int`. The result wraps around to a negative number, completely breaking the sorting logic.

* *The Senior Fix:* Always use `return Integer.compare(this.id, other.id);`.

### 3. Can a class implement multiple `Comparable` interfaces to have multiple natural orders?

**Answer:** No. A class can only implement `Comparable<T>` once. An object can only have **one** default, natural identity. If you need to sort a `User` by ID, then by Email, and then by Age in different parts of your application, you *must* use external `Comparator` classes (or lambdas) for the alternative sorts.

### 4. What happens if you add an object that does NOT implement `Comparable` into a `TreeSet`?

**Answer:** The code will compile fine, but the moment you run it, the JVM will throw a `ClassCastException`. A `TreeSet` relies on a Red-Black Tree to keep elements sorted. If the elements don't implement `Comparable`, the tree has no idea how to compare them to figure out which branch to put them on. (Alternatively, you must pass a `Comparator` into the `TreeSet` constructor).

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Instant Reverse

**Task:** You have a `List<String> names` sorted alphabetically. Write exactly one line of code to sort them in **reverse** alphabetical order without writing your own custom logic.
**Solution:**

```java
// Java provides a built-in comparator for this exact scenario!
names.sort(Comparator.reverseOrder());

```

### Challenge 2: The Lambda Upgrade

**Task:** Upgrade this old-school Java 7 anonymous inner class into a modern, highly optimized Java 8 method reference.

```java
// Legacy way
Comparator<Student> ageSorter = new Comparator<Student>() {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getAge(), s2.getAge());
    }
};

```

**Solution:**

```java
// Modern Java 8+ way (comparingInt prevents autoboxing overhead!)
Comparator<Student> ageSorter = Comparator.comparingInt(Student::getAge);

```

---

## 📝 Practice Problems

Here are two scenario-based problems. The first tests your ability to modify a core class to give it a natural identity. The second pushes you to use modern Java 8+ features to build a highly complex sorting engine.

### Problem 1: The E-Commerce Product (`Comparable`)

You are building the core `Product` class for a shopping cart. By default, whenever products are displayed, they should always be sorted from cheapest to most expensive.

**Requirements:**

1. Create a `Product` class with `String name` and `double price`.
2. Make the class implement `Comparable<Product>`.
3. Override the `compareTo` method. Do not use subtraction! Use the safe `Double.compare()` method.
4. In `Main`, create a `List<Product>`, add three products with different prices, call `Collections.sort(list)`, and print the list to verify the cheapest is first.

### Problem 2: The HR Dashboard (`Comparator` Chaining)

Your HR department wants to view a list of employees. The sorting rules are complex and specific, meaning `Comparable` isn't enough.

**Requirements:**

1. Create an `Employee` class with `String department`, `double salary`, and `String name`. Add standard getters.
2. In your `Main` class, create a `List<Employee>` with a mix of departments and overlapping salaries.
3. Create a chained `Comparator` that sorts by the following strict hierarchy:
* First, sort alphabetically by **Department**.
* If they are in the same department, sort by **Salary**, but from *Highest to Lowest* (reversed).
* If they have the exact same salary in the same department, sort alphabetically by **Name** as a final tie-breaker.


4. Apply the comparator to your list using `list.sort()` and print the results.
