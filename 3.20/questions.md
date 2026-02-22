## 🎤 Frequently Asked Interview Questions

### 1. Why is the immutability of `java.time` classes such a big deal?

**Answer:** The old `java.util.Date` was mutable, which was a concurrency nightmare. If two threads shared a `Date` object and one modified it, the other thread's data was corrupted without it knowing. Because modern classes like `LocalDate` are immutable, they are inherently **thread-safe**. You can share them across your entire application without worrying about side effects.

### 2. What is the difference between `LocalDateTime` and `ZonedDateTime`?

**Answer:** `LocalDateTime` is "wall-clock time." It represents a date and time as you see it on a calendar or clock, but it has no context of where in the world that time is occurring. `ZonedDateTime` adds a `ZoneId` (like `UTC` or `Europe/Paris`).

- **Rule:** Use `LocalDateTime` for things like "The company holiday is Jan 1st." Use `ZonedDateTime` for "The Zoom call starts at 3 PM EST."

### 3. How do you handle Daylight Saving Time (DST) transitions with this API?

**Answer:** The API handles it automatically. If you use `ZonedDateTime` and add a duration that crosses a DST boundary, the API adjusts the offset correctly. For example, if you add 24 hours to a time just before the "spring forward" transition, the resulting `ZonedDateTime` will reflect the hour gap accurately.

### 4. What is the "Epoch," and which class represents it best?

**Answer:** The Unix Epoch is January 1, 1970, at 00:00:00 UTC. The **`Instant`** class is the best representation of this, as it measures the passing of time as a single continuous line of nanoseconds from that fixed point. It is the standard for logging and database timestamps.

### 5. Explain the difference between `Duration` and `ChronoUnit`?

**Answer:** Both are used to work with time differences, but they serve different purposes:

- **`Duration`** is a **time-based amount** that represents a quantity of time in hours, minutes, seconds, and nanoseconds. It's an object you can store, pass around, and manipulate.
- **`ChronoUnit`** is an **enum** representing a unit of time (like `DAYS`, `HOURS`, `MINUTES`). It's used for calculations and measuring differences between temporal objects.

**Key differences:**
| Feature | `Duration` | `ChronoUnit` |
|---------|------------|--------------|
| Type | Class (object) | Enum (constant) |
| Purpose | Store/represent a time amount | Perform calculations with a time unit |
| Precision | Nanoseconds to hours | Nanoseconds to forever (ERAS) |
| Use case | "Wait for 30 seconds" | "How many days between these dates?" |

**Example:**

```java
// Duration - represents an amount of time
Duration timeout = Duration.ofMinutes(5);
Duration halfHour = Duration.ofSeconds(1800);

// ChronoUnit - used for calculations
long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
LocalDateTime later = dateTime.plus(2, ChronoUnit.HOURS);
```

**Rule of thumb:** Use `Duration` when you need to store or pass around a time quantity. Use `ChronoUnit` when you need to measure the difference between two temporal objects in a specific unit or add/subtract a specific unit of time.

---

## 🛠️ Quick Coding Challenges

### Challenge 1: The Birthday Finder

**Task:** Write a single line of code to create a `LocalDate` representing October 31, 2000, without using any "magic numbers" for the month.
**Solution:**

```java
// Use the Month enum for maximum readability!
LocalDate birthday = LocalDate.of(2000, Month.OCTOBER, 31);

```

### Challenge 2: The Next Business Day

**Task:** You have a `LocalDate today`. How do you get the date exactly 3 months and 2 days from now?
**Solution:**

```java
// Fluent API allows for easy chaining
LocalDate future = today.plusMonths(3).plusDays(2);

```

---

## 📝 Practice Problems

Here are two scenario-based problems to test your ability to handle human time and machine time effectively.

### Problem 1: The Subscription Expiry Tracker (`Period`)

You are building a subscription service. When a user signs up, you need to calculate their expiry date and tell them exactly how long they have left in years, months, and days.

**Requirements:**

1. Create a `Subscription` class.
2. In the constructor, set a `startDate` to `LocalDate.now()`.
3. Calculate an `expiryDate` by adding 1 year, 6 months, and 15 days to the `startDate`.
4. Use `Period.between()` to calculate the remaining time between `LocalDate.now()` and the `expiryDate`.
5. Print: "Your subscription expires in X years, Y months, and Z days."

### Problem 2: The Global Meeting Planner (`ZonedDateTime`)

You are in **New York** (`America/New_York`) and want to schedule a meeting with a colleague in **Tokyo** (`Asia/Tokyo`).

**Requirements:**

1. Create a `LocalDateTime` for your local meeting time: March 15, 2026, at 9:00 AM.
2. Wrap this in a `ZonedDateTime` using your local New York zone ID.
3. Use the `.withZoneSameInstant()` method to convert that exact moment to the Tokyo time zone.
4. Print both the New York time and the Tokyo time to verify when your colleague needs to wake up!
