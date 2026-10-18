# ☕ Java Mastery: Complete Modern Java Curriculum & Interactive Handbook

[![Java 21+](https://img.shields.io/badge/Java-21%2B-orange.svg?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Lessons](https://img.shields.io/badge/Lessons-95%20Comprehensive-brightgreen.svg)](#-curriculum-roadmap)
[![Modules](https://img.shields.io/badge/Modules-5%20Complete-blue.svg)](#-curriculum-roadmap)
[![Interactive App](https://img.shields.io/badge/Interactive%20App-Included%20(index.html)-purple.svg)](#-interactive-web-handbook)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A structured, battle-tested curriculum designed to take developers from Java fundamentals to production-grade mastery — covering everything from syntax basics, Object-Oriented Architecture, Collections, Concurrency Internals, and JVM Tuning to Modern Java 21+ (Records, Sealed Classes, Pattern Matching, and Virtual Threads foundations).

---

## 🌟 What's Inside This Repository?

- **95 Comprehensive Lessons:** Each lesson includes an in-depth technical explanation, code walkthroughs, and practical diagrams.
- **🎤 Curated Interview Questions:** Frequently asked interview questions, edge cases, and quick challenges for every lesson.
- **💻 Executable Java Solutions:** Real-world executable reference implementations located in each lesson's `answer/` directory.
- **📖 Standalone Interactive Web Handbook:** An offline, zero-dependency reader application (`index.html`) with instant full-text search, reading progress tracking, dark/light themes, and IDE-grade syntax highlighting with line numbers.

---

## 🚀 Quick Start: Launching the Interactive Handbook

You can read all lessons directly on GitHub or open the included interactive web app on your local machine:

1. Clone or download this repository:
   ```bash
   git clone https://github.com/musab05/Java-Programming-Language.git
   ```
2. Simply double-click **`index.html`** in Windows Explorer (or open it in any modern browser).
   - **No build steps**, **no Node.js**, and **no server required** — works 100% offline!
   - Press `/` anywhere in the app to instantly search topics, keywords, and code patterns across all 95 lessons.

---

## 📚 Curriculum Roadmap

The curriculum is structured into 5 progressive modules:

### 🟢 Module 1: Beginner — Java Fundamentals (1.01 – 1.20)
| ID | Topic | Key Concepts |
| :---: | :--- | :--- |
| **1.01** | [What is Java & JVM Overview](1.01/What%20is%20Java%20&%20JVM%20Overview.md) | Bytecode, WORA philosophy, JVM architecture |
| **1.02** | [Installing JDK & Setting up Environment](1.02/Installing%20JDK%20&%20Setting%20up%20Environment.md) | JDK vs. JRE, JAVA_HOME, PATH configuration |
| **1.03** | [Java Program Structure](1.03/Java%20Program%20Structure.md) | Packages, imports, class declarations |
| **1.04** | [main() Method](1.04/main()%20Method.md) | `public static void main`, execution entry points |
| **1.05** | [Compilation & Execution Process](1.05/Compilation%20&%20Execution%20Process.md) | javac, bytecode, classloader, JIT execution |
| **1.06** | [Variables](1.06/Variables.md) | Local, instance, and static variables |
| **1.07** | [Data Types (Primitive & Non-Primitive)](1.07/Data%20Types%20(Primitive%20&%20Non-Primitive).md) | Primitives, memory sizes, reference types |
| **1.08** | [Type Casting](1.08/Type%20Casting.md) | Widening vs. narrowing casting, overflow |
| **1.09** | [Operators](1.09/Operators.md) | Arithmetic, bitwise, logical, precedence |
| **1.10** | [Control Statements (if, switch)](1.10/Control%20Statements%20(if,%20switch).md) | Branching, traditional switch, fall-through |
| **1.11** | [Loops (for, while, do-while)](1.11/Loops%20(for,%20while,%20do-while).md) | Iteration, enhanced for-each, break/continue |
| **1.12** | [Methods](1.12/Methods.md) | Parameters, return values, pass-by-value |
| **1.13** | [Method Overloading](1.13/Method%20Overloading.md) | Compile-time polymorphism, signature rules |
| **1.14** | [Input-Output (Scanner, BufferedReader)](1.14/Input-Output%20(Scanner,%20BufferedReader).md) | Console I/O, parsing inputs, performance |
| **1.15** | [Comments & Documentation](1.15/Comments%20&%20Documentation.md) | Javadoc tags (`@param`, `@return`, `@throws`) |
| **1.16** | [Java Naming Conventions](1.16/Java%20Naming%20Conventions.md) | CamelCase, PascalCase, SCREAMING_SNAKE |
| **1.17** | [Packages](1.17/Packages.md) | Namespace isolation, package declaration rules |
| **1.18** | [Access Modifiers](1.18/Access%20Modifiers.md) | `public`, `protected`, default, `private` |
| **1.19** | [Command Line Arguments](1.19/Command%20Line%20Arguments.md) | `String[] args`, CLI parameter parsing |
| **1.20** | [Java Memory Basics](1.20/Java%20Memory%20Basics.md) | Stack vs. Heap allocation fundamentals |

---

### 🔵 Module 2: Core Java — Object-Oriented Programming (2.01 – 2.20)
| ID | Topic | Key Concepts |
| :---: | :--- | :--- |
| **2.01** | [Classes & Objects](2.01/Classes%20&%20Objects.md) | Blueprint vs. state, instantiation, heap memory |
| **2.02** | [Constructors](2.02/Constructors.md) | Default, parameterized, copy constructors |
| **2.03** | [this Keyword](2.03/this%20Keyword.md) | Shadowing, constructor chaining (`this()`) |
| **2.04** | [static Keyword](2.04/static%20Keyword.md) | Static variables, methods, blocks, class loading |
| **2.05** | [Encapsulation](2.05/Encapsulation.md) | Data hiding, validation invariants |
| **2.06** | [Inheritance](2.06/Inheritance.md) | IS-A relationships, code reuse, single inheritance |
| **2.07** | [Polymorphism](2.07/Polymorphism.md) | Static vs. dynamic dispatch, upcasting |
| **2.08** | [Method Overriding](2.08/Method%20Overriding.md) | `@Override`, covariant returns, vtables |
| **2.09** | [super Keyword](2.09/super%20Keyword.md) | Calling superclass constructors & overridden methods |
| **2.10** | [Abstraction](2.10/Abstraction.md) | Hiding implementation details, design by contract |
| **2.11** | [Abstract Classes](2.11/Abstract%20Classes.md) | Incomplete hierarchies, template method pattern |
| **2.12** | [Interfaces](2.12/Interfaces.md) | Total abstraction, contracts, constant fields |
| **2.13** | [Multiple Inheritance via Interfaces](2.13/Multiple%20Inheritance%20via%20Interfaces.md) | Diamond problem resolution, default conflict |
| **2.14** | [Object Class Methods](2.14/Object%20Class%20Methods.md) | `equals()`, `hashCode()`, `toString()`, contract rules |
| **2.15** | [final Keyword](2.15/final%20Keyword.md) | Final variables, methods, and classes |
| **2.16** | [Inner Classes](2.16/Inner%20Classes.md) | Member, local, and static nested classes |
| **2.17** | [Anonymous Classes](2.17/Anonymous%20Classes.md) | On-the-fly implementation, event handlers |
| **2.18** | [Enums](2.18/Enums.md) | Type-safe constants, enum methods, singleton pattern |
| **2.19** | [Instanceof Operator](2.19/Instanceof%20Operator.md) | Runtime type queries, casting safety |
| **2.20** | [Wrapper Classes](2.20/Wrapper%20Classes.md) | Autoboxing, unboxing, caching pool gotchas |

---

### 🟡 Module 3: Intermediate Java — Collections, Generics & I/O (3.01 – 3.20)
| ID | Topic | Key Concepts |
| :---: | :--- | :--- |
| **3.01** | [Exception Handling](3.01/Exception%20Handling.md) | Throwable hierarchy, call stack unwinding |
| **3.02** | [Checked vs Unchecked Exceptions](3.02/Checked%20vs%20Unchecked%20Exceptions.md) | Compile-time checking vs. runtime bugs |
| **3.03** | [Custom Exceptions](3.03/Custom%20Exceptions.md) | Business domain errors, error wrapping |
| **3.04** | [try-catch-finally](3.04/try-catch-finally.md) | Resource cleanup, execution order guarantees |
| **3.05** | [throw vs throws](3.05/throw%20vs%20throws.md) | Raising exceptions vs. declaring method signatures |
| **3.06** | [Java Collections Framework](3.06/Java%20Collections%20Framework.md) | Architecture, hierarchy, choosing the right structure |
| **3.07** | [List, Set, Map Interfaces](3.07/List,%20Set,%20Map%20Interfaces.md) | Ordered sequences, unique elements, key-value maps |
| **3.08** | [ArrayList, LinkedList](3.08/ArrayList,%20LinkedList.md) | Contiguous arrays vs. doubly linked nodes |
| **3.09** | [HashSet, TreeSet](3.09/HashSet,%20TreeSet.md) | Hash tables vs. Red-Black binary search trees |
| **3.10** | [HashMap, TreeMap](3.10/HashMap,%20TreeMap.md) | Buckets, collisions, treeification, NavigableMap |
| **3.11** | [Iterators](3.11/Iterators.md) | `Iterator<E>`, `ListIterator<E>`, fail-fast vs. fail-safe |
| **3.12** | [Comparable & Comparator](3.12/Comparable%20&%20Comparator.md) | Natural ordering vs. custom comparator strategies |
| **3.13** | [Generics](3.13/Generics.md) | Type safety, type erasure, parameterized types |
| **3.14** | [Bounded Generics](3.14/Bounded%20Generics.md) | Upper/lower bounds, PECS (`? extends` / `? super`) |
| **3.15** | [Varargs](3.15/Varargs.md) | Variable-length argument lists, array desugaring |
| **3.16** | [Java I-O](3.16/Java%20I-O%20(File,%20FileReader,%20BufferedWriter).md) | Byte vs. character streams, buffering, flush |
| **3.17** | [Serialization & Deserialization](3.17/Serialization%20&%20Deserialization.md) | `Serializable`, `transient`, `serialVersionUID` |
| **3.18** | [Java NIO Basics](3.18/Java%20NIO%20Basics.md) | Channels, ByteBuffers, non-blocking I/O concepts |
| **3.19** | [Optional](3.19/Optional.md) | Eliminating `NullPointerException`, monad basics |
| **3.20** | [Date & Time API (java.time)](3.20/Date%20&%20Time%20API%20(java.time).md) | JSR-310, `LocalDate`, `Instant`, `Duration`, zones |

---

### 🟣 Module 4: Advanced Java — Concurrency, JVM Internals & Memory (4.01 – 4.20)
| ID | Topic | Key Concepts |
| :---: | :--- | :--- |
| **4.01** | [Multithreading Basics](4.01/Multithreading%20Basics.md) | OS processes vs. threads, context switching |
| **4.02** | [Thread Lifecycle](4.02/Thread%20Lifecycle.md) | NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING |
| **4.03** | [Creating Threads](4.03/Creating%20Threads%20(Thread,%20Runnable).md) | Extending `Thread` vs. implementing `Runnable` |
| **4.04** | [Synchronization](4.04/Synchronization.md) | Monitors, intrinsic locks, synchronized methods/blocks |
| **4.05** | [volatile Keyword](4.05/volatile%20Keyword.md) | Memory visibility, CPU cache coherency, instruction reordering |
| **4.06** | [Inter-Thread Communication](4.06/Inter-Thread%20Communication.md) | `wait()`, `notify()`, `notifyAll()`, producer-consumer |
| **4.07** | [Deadlock, Starvation, Livelock](4.07/Deadlock,%20Starvation,%20Livelock.md) | Coffman conditions, thread dumps, prevention |
| **4.08** | [Concurrency Utilities](4.08/Concurrency%20Utilities%20(java.util.concurrent).md) | `ConcurrentHashMap`, `CountDownLatch`, `CyclicBarrier` |
| **4.09** | [Executors Framework](4.09/Executors%20Framework.md) | Thread pools, `ThreadPoolExecutor`, rejection policies |
| **4.10** | [Callable & Future](4.10/Callable%20&%20Future.md) | Asynchronous task results, exceptions, timeouts |
| **4.11** | [Locks (ReentrantLock)](4.11/Locks%20(ReentrantLock).md) | Explicit locks, tryLock, fair locks, Condition variables |
| **4.12** | [Atomic Variables](4.12/Atomic%20Variables.md) | Hardware CAS (Compare-And-Swap), lock-free safety |
| **4.13** | [Fork-Join Framework](4.13/Fork-Join%20Framework.md) | Work-stealing algorithm, recursive tasks |
| **4.14** | [Parallel Streams](4.14/Parallel%20Streams.md) | Spliterators, common ForkJoinPool, thread contention |
| **4.15** | [Memory Management](4.15/Memory%20Management.md) | Eden, Survivor spaces, Tenured Generation, Metaspace |
| **4.16** | [Garbage Collection](4.16/Garbage%20Collection.md) | Mark-Sweep-Compact, GC roots, Stop-The-World pauses |
| **4.17** | [JVM Memory Model](4.17/JVM%20Memory%20Model.md) | JMM specification, happens-before guarantees |
| **4.18** | [JVM Tuning](4.18/JVM%20Tuning.md) | `-Xms`, `-Xmx`, choosing collectors (G1, ZGC, Shenandoah) |
| **4.19** | [Reflection API](4.19/Reflection%20API.md) | Inspecting metadata, dynamic invocation, security |
| **4.20** | [Annotations](4.20/Annotations.md) | Custom annotations, retention policies, processors |

---

### 🔴 Module 5: Modern Java — Functional Programming & Modern Java 21+ (5.01 – 5.15)
| ID | Topic | Key Concepts |
| :---: | :--- | :--- |
| **5.01** | [Lambda Expressions](5.01/Lambda%20Expressions.md) | Functional syntax, closures, variable capture |
| **5.02** | [Functional Interfaces](5.02/Functional%20Interfaces.md) | `Predicate`, `Function`, `Consumer`, `Supplier`, SAM |
| **5.03** | [Stream API](5.03/Stream%20API.md) | Declarative pipelines, lazily evaluated streams |
| **5.04** | [Intermediate vs Terminal Operations](5.04/Intermediate%20vs%20Terminal%20Operations.md) | Stateless vs stateful operations, short-circuiting |
| **5.05** | [Collectors](5.05/Collectors.md) | `groupingBy`, `partitioningBy`, downstream reductions |
| **5.06** | [Method References](5.06/Method%20References.md) | `Class::staticMethod`, `instance::method`, `Class::new` |
| **5.07** | [Default Methods in Interfaces](5.07/Default%20Methods%20in%20Interfaces.md) | Interface evolution, backward compatibility |
| **5.08** | [Optional Best Practices](5.08/Optional%20Best%20Practices.md) | Anti-patterns, `flatMap`, `ifPresentOrElse` |
| **5.09** | [Parallel Streams Internals](5.09/Parallel%20Streams%20Internals.md) | Spliterator chunking, isolating in custom pools |
| **5.10** | [Immutability](5.10/Immutability.md) | Defensive copying, unmodifiable collections |
| **5.11** | [Records](5.11/Records.md) | Transparent data carriers, compact constructors |
| **5.12** | [Sealed Classes](5.12/Sealed%20Classes.md) | Closed hierarchies, Algebraic Data Types |
| **5.13** | [Pattern Matching](5.13/Pattern%20Matching%20(Modern%20Java).md) | `instanceof` patterns, switch expressions, record deconstruction |
| **5.14** | [Text Blocks](5.14/Text%20Blocks.md) | Multiline strings, incidental whitespace stripping |
| **5.15** | [Modules System (JPMS)](5.15/Modules%20System%20(JPMS).md) | `module-info.java`, strong encapsulation, `jlink` |

---

## 📁 Repository Directory Structure

```text
Java-Programming-Language/
├── index.html                   # The complete interactive web handbook reader
├── book-data.js                 # Unified dataset containing all 95 lessons
├── Programming Concepts(Java).csv# Curriculum checklist & GitHub links
├── README.md                    # Repository documentation (you are here)
│
├── 1.01/                        # Lesson directory
│   ├── What is Java & JVM Overview.md  # Main concept explanation
│   ├── questions.md                    # Frequently asked interview questions
│   └── answer/                         # Executable Java code solutions
│       └── ExampleMain.java
│
├── 1.02/ ... 5.15/              # All 95 lesson directories
```

---

## 🛠️ How to Compile & Run Practice Code

All code samples are designed to run using standard modern Java (Java 17 or Java 21+):

```bash
# 1. Navigate to any lesson answer directory
cd "5.11/answer"

# 2. Compile the Java files
javac StudentRecordMain.java

# 3. Run the compiled application
java StudentRecordMain
```

---

## 🤝 Contributing

Contributions, typo fixes, additional interview questions, and new practice scenarios are always welcome!
1. Fork the Project.
2. Create your Feature Branch (`git checkout -b feature/NewExercise`).
3. Commit your Changes (`git commit -m 'Add new solution for 4.12'`).
4. Push to the Branch (`git push origin feature/NewExercise`).
5. Open a Pull Request.

---

## 📄 License

Distributed under the MIT License. See [LICENSE](LICENSE) for more details.
