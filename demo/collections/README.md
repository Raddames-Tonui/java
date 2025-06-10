# Java Collections Overview

## 📚 Introduction

The Java Collections Framework provides a set of interfaces and classes to handle groups of objects. The core interface is `Collection`, which is extended by other interfaces like `List`, `Set`, and `Queue`. This README focuses on the `Set`, `List`, and `Queue` hierarchies and their implementations.

---

## 🌳 Collection Hierarchy Tree

```plaintext
java.util.Collection (interface)
│
├── java.util.List (interface)
│   ├── ArrayList
│   ├── LinkedList
│   └── Vector / Stack
│
├── java.util.Queue (interface)
│   ├── PriorityQueue
│   └── ArrayDeque
│
└── java.util.Set (interface)
    ├── HashSet
    │   └── LinkedHashSet
    │
    └── SortedSet (interface)
        └── NavigableSet (interface)
            └── TreeSet
```

---

## 🔹 java.util.List Interface

* **Ordered collection** (elements retain insertion order)
* Allows **duplicates**
* Index-based access to elements

### ArrayList

* Backed by a resizable array
* Fast random access: O(1)
* Slower insert/remove at beginning/middle: O(n)
* Use case: Frequent access by index

```java
List<String> list = new ArrayList<>();
list.add("Apple");
list.add("Banana");
list.get(1); // Banana
```

### LinkedList

* Doubly linked list
* Slower access by index: O(n)
* Fast insert/delete at head or tail: O(1)
* Implements both `List` and `Deque`
* Use case: Frequent insertions/removals

```java
List<String> list = new LinkedList<>();
list.add("Apple");
list.addFirst("Banana");
```

### Vector / Stack

* `Vector`: synchronized, dynamic array
* `Stack`: extends `Vector`, LIFO (Last-In-First-Out)
* Use case: Legacy code, basic stack operations

```java
Stack<String> stack = new Stack<>();
stack.push("Apple");
stack.pop(); // Apple
```

---

## 🔹 java.util.Queue Interface

* Used for **FIFO** (First-In-First-Out) data structures
* Core methods: `offer()`, `poll()`, `peek()`

### PriorityQueue

* Elements ordered using natural order or comparator
* Not thread-safe
* Does not allow nulls
* Use case: Priority-based processing

```java
Queue<Integer> queue = new PriorityQueue<>();
queue.offer(10);
queue.offer(5);
queue.poll(); // 5
```

### ArrayDeque

* Resizable array-based implementation of `Deque`
* Faster than `Stack` or `LinkedList`
* No capacity restrictions, no nulls
* Use case: Efficient double-ended queue operations

```java
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("Apple");
deque.addLast("Banana");
deque.pollFirst(); // Apple
```

---

## 🔹 java.util.Set Interface

* **No duplicates allowed**
* Key methods: `add()`, `remove()`, `contains()`, `iterator()`

### HashSet

* **No order guaranteed**
* Backed by a `HashMap`
* **Null allowed** (one)
* Fast operations: O(1)
* Use case: Speed over order

```java
Set<String> set = new HashSet<>();
set.add("Apple");
set.add("Banana");
```

### LinkedHashSet

* Maintains **insertion order**
* Backed by `LinkedHashMap`
* Slight overhead for ordering
* Use case: Order + fast access

```java
Set<String> set = new LinkedHashSet<>();
set.add("Apple");
set.add("Banana");
```

### TreeSet

* Implements `NavigableSet` and `SortedSet`
* **Sorted order** (natural or custom)
* Backed by **Red-Black tree**
* O(log n) operations
* No nulls (in natural order)
* Use case: Sorted set or range queries

```java
Set<String> set = new TreeSet<>();
set.add("Banana");
set.add("Apple");
```

---

## 🔸 NavigableSet Interface

* Extends `SortedSet`
* Adds methods for navigation:

  * `ceiling()`, `floor()`
  * `higher()`, `lower()`
  * `pollFirst()`, `pollLast()`
  * `descendingSet()`

---

## ✅ Summary Table

| Type               | Ordered?          | Sorted? | Nulls | Thread-Safe | Performance     |
| ------------------ | ----------------- | ------- | ----- | ----------- | --------------- |
| `ArrayList`        | ✅ Insertion       | ❌ No    | ✅     | ❌           | ✅ Random access |
| `LinkedList`       | ✅ Insertion       | ❌ No    | ✅     | ❌           | ⚠️ Index slow   |
| `Vector` / `Stack` | ✅ Insertion       | ❌ No    | ✅     | ✅           | ⚠️ Legacy usage |
| `PriorityQueue`    | ❌ Priority order  | ✅ Yes   | ❌     | ❌           | ✅ O(log n)      |
| `ArrayDeque`       | ✅ Insertion       | ❌ No    | ❌     | ❌           | ✅ Fast deque    |
| `HashSet`          | ❌ No              | ❌ No    | ✅ 1   | ❌           | ✅ O(1)          |
| `LinkedHashSet`    | ✅ Insertion order | ❌ No    | ✅ 1   | ❌           | ✅ O(1)          |
| `TreeSet`          | ❌ Natural order   | ✅ Yes   | ❌     | ❌           | ⚠️ O(log n)     |

---

## MAPS
[Check on maps](./Map.md)

## 📘 Conclusion

The Java Collections Framework is rich in data structures tailored to specific needs. `List`, `Queue`, and `Set` offer different advantages:

* Use `ArrayList` for fast random access.
* Use `LinkedList` when frequent insertions/removals are needed.
* Use `PriorityQueue` for prioritized processing.
* Use `HashSet` or `LinkedHashSet` for fast, unique collections.
* Use `TreeSet` or `NavigableSet` for sorted and range queries.

Choose wisely to optimize performance, memory, and clarity of your code.
