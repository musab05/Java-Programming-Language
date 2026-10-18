Records in Java 16+ revolutionize how we create data transfer objects, immutable value objects, and composite keys. By replacing hundreds of lines of boilerplate with a clean, compiler-enforced contract, records bring clarity, safety, and conciseness to modern Java applications.

Understanding their constraints — shallow immutability, implicit finality, compact constructors, and how they interact with collections — is essential for writing robust modern Java code.

---

## 🎤 Frequently Asked Interview Questions

### 1. What is a Record in Java and why was it introduced?

**Answer:** A record is a special kind of class in Java (introduced in Java 14, finalized in Java 16) designed to act as an immutable data carrier. It was introduced to eliminate boilerplate code (constructors, getters, `equals()`, `hashCode()`, and `toString()`) and clearly communicate the design intent: this class is a transparent carrier for a shallowly immutable set of data values.

### 2. Can a record extend another class or be extended by another class?

**Answer:** No. 
- A record **cannot extend any other class** because all records implicitly extend `java.lang.Record`. Because Java does not support multiple class inheritance, extending any other class is forbidden.
- A record **cannot be extended** by any class because all records are implicitly declared `final`.
- However, records **can implement interfaces** (e.g., `implements Serializable, Comparable<T>`).

### 3. What is a "Compact Constructor" in a record?

**Answer:** A compact constructor is a constructor syntax unique to records where the parameter list is omitted (`public RecordName { ... }`). It is used to validate inputs or normalize arguments before fields are assigned. Inside the compact constructor, you reference component names directly without `this.` (e.g., `age` instead of `this.age`), and the compiler automatically injects the field assignments `this.x = x;` at the very end of the compact constructor block.

### 4. Are records deeply immutable or shallowly immutable?

**Answer:** Records are **shallowly immutable**. The fields generated for record components are `final`, meaning the reference cannot be changed once initialized. However, if a component refers to a mutable object (such as an `ArrayList` or `Date`), the internal state of that object can still be modified unless defensive copies are made in the constructor and accessors (e.g. using `List.copyOf()`).

### 5. How do accessor methods in records differ from traditional JavaBean getters?

**Answer:** Record accessors do not use the `get` or `is` prefix. Instead, the accessor method matches the component name directly. For example, for component `name`, the accessor is `user.name()`, not `user.getName()`.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: Compact Constructor with Normalization

**Task:** Create a record `Product(String sku, double price)` where `sku` must be non-null and converted to uppercase, and `price` must be strictly greater than `0.0`.

**Solution:**
```java
public record Product(String sku, double price) {
    public Product {
        Objects.requireNonNull(sku, "SKU cannot be null");
        if (price <= 0.0) {
            throw new IllegalArgumentException("Price must be positive: " + price);
        }
        sku = sku.trim().toUpperCase();
    }
}
```

### Challenge 2: "With" Style Functional Mutation

**Task:** Since record fields are final, how do you update a single field in a record? Write a `withPrice(double newPrice)` method for `Product`.

**Solution:**
```java
public record Product(String sku, double price) {
    public Product withPrice(double newPrice) {
        return new Product(this.sku, newPrice);
    }
}
```

---

## 💻 Practice Problems

### Problem 1: Safe Student Grade Book
Implement an immutable `StudentReport` record that holds a student name, student ID, and a list of grades (`List<Double>`).
- Guarantee immutability by defensively copying the list of grades.
- Implement a method `double calculateGPA()` that computes the average grade.
- Implement a method `boolean isHonors()` that returns true if the average is $\ge 3.8$.
- File: [StudentRecordMain.java](file:///c:/Java%20Programming%20Language/5.11/answer/StudentRecordMain.java)

### Problem 2: E-Commerce Order & Line Items (Nested Records)
Create nested records representing an `OrderItem` (product name, quantity, unit price) and an `Order` (order ID, customer ID, list of `OrderItem`, discount percentage).
- Enforce validation rules: quantities $> 0$, unit prices $> 0$, discount between $0.0$ and $1.0$.
- Provide helper methods `double calculateSubtotal()`, `double calculateTotal()`, and a `withDiscount(double newDiscount)` method.
- Demonstrate local records in a Stream pipeline to summarize top customers.
- File: [EcommerceOrderMain.java](file:///c:/Java%20Programming%20Language/5.11/answer/EcommerceOrderMain.java)
