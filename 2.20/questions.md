## 🎤 Frequently Asked Interview Questions

### 1. What is the "Integer Cache" in Java, and how does it affect the `==` operator?

**Answer:** To save memory, Java caches `Integer` objects for values between **-128 and 127**. If you autobox two numbers in this range, Java points them to the exact same object in memory. Therefore, `==` will return `true` for `100 == 100`. However, if you autobox `200`, Java creates a brand new object. So, `200 == 200` using the `==` operator on `Integer` objects will return `false`. Always use `.equals()` to compare Wrapper class values!

### 2. Can unboxing throw an exception?

**Answer:** Yes, it can throw a `NullPointerException`. If a Wrapper class object is `null`, and Java attempts to automatically unbox it into a primitive (for example, in a math operation or assignment), it fails because primitives cannot hold a `null` value.

### 3. If Wrapper classes are required for Collections, how do we efficiently store a massive list of numbers without the memory overhead?

**Answer:** Standard Java Collections (like `ArrayList<Integer>`) will always have the Object memory overhead. For high-performance applications dealing with massive amounts of numbers, you should use standard arrays (e.g., `int[]`) or specialized third-party libraries like Eclipse Collections or Trove, which provide primitive collections like `TIntArrayList`.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The `==` Illusion

**Task:** Without running it, what will this code print?

```java
Integer a = 50;
Integer b = 50;
Integer c = 500;
Integer d = 500;

System.out.println(a == b);
System.out.println(c == d);

```

**Solution:** It prints `true` then `false`. Because `50` is within the cached range (-128 to 127), `a` and `b` point to the same object. `500` is outside the cache, so `c` and `d` are entirely different objects in memory.

### Challenge 2: The Hidden NPE

**Task:** Identify the bug in this code.

```java
public class Cart {
    static Integer discount;

    public static void main(String[] args) {
        int finalPrice = 100 - discount;
        System.out.println(finalPrice);
    }
}

```

**Solution:** `discount` is an Object (`Integer`), and because it is not initialized, its default value is `null`. When Java tries to do `100 - discount`, it attempts to unbox `null` into an `int`, throwing a `NullPointerException`.

---

## 📝 Practice Problems

Here are two practical coding problems. One tests your ability to use Wrapper utility methods, and the other proves the performance impact of autoboxing.

### Problem 1: The API Data Cleaner

When pulling data from a web API or a CSV file, everything comes in as a `String`, and sometimes the data is missing or corrupted.

**Requirements:**

1. Create a method `public Double calculateAverage(List<String> rawScores)`.
2. Iterate through the list. If a string is `"N/A"` or `null`, ignore it.
3. Use the `Double` wrapper class utility methods to parse the valid strings into numbers.
4. Keep a running sum and count to calculate the average.
5. If the parsed string is not a valid number (e.g., "abc"), catch the `NumberFormatException` and ignore that entry.
6. Return the average as a `Double`.

### Problem 2: The Performance Bottleneck

This is a classic experiment to see the hidden cost of Wrapper classes.

**Requirements:**

1. Write a program that calculates the sum of all numbers from 1 to 10,000,000 (10 million).
2. **Test A:** Do this using a `Long` wrapper class for the `sum` variable. Note the start time and end time using `System.currentTimeMillis()`.
3. **Test B:** Do this using a primitive `long` for the `sum` variable. Note the start time and end time.
4. Print the time taken for both tests. You will see a massive difference because Test A is instantiating and garbage collecting millions of unnecessary Objects!
