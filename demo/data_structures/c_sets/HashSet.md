# 📘 HashSet and LinkedHashSet in Java

Java provides two commonly used implementations of the `Set` interface that are ideal for enforcing uniqueness: `HashSet` and `LinkedHashSet`. This README focuses exclusively on their features, methods, performance characteristics, and use cases.

---

## a) HashSet

#### ✅ Overview

* Implements the `Set` interface
* Backed by a **hash table** (actually a `HashMap`)
* **Does not maintain order** of elements
* Allows one `null` element

#### ⚙️ Common Methods

| Method               | Description                         |
| -------------------- | ----------------------------------- |
| `add(E e)`           | Adds element if not already present |
| `remove(Object o)`   | Removes the specified element       |
| `contains(Object o)` | Checks if the element exists        |
| `size()`             | Returns the number of elements      |
| `clear()`            | Removes all elements                |
| `isEmpty()`          | Checks if the set is empty          |

#### 🧪 Example

```java
Set<String> hashSet = new HashSet<>();
hashSet.add("Apple");
hashSet.add("Banana");
hashSet.add("Apple"); // Duplicate
System.out.println(hashSet); // Order is not guaranteed
```

#### 🧠 Use Cases

* When you don’t care about the order
* For **fast lookups**, additions, deletions (O(1) average time complexity)
* Removing duplicates from collections

---

## b) LinkedHashSet

#### ✅ Overview

* Extends `HashSet`
* Maintains **insertion order** of elements
* Also allows one `null` element

#### ⚙️ Common Methods

| Method               | Description                         |
| -------------------- | ----------------------------------- |
| `add(E e)`           | Adds element if not already present |
| `remove(Object o)`   | Removes the specified element       |
| `contains(Object o)` | Checks if the element exists        |
| `size()`             | Returns the number of elements      |
| `clear()`            | Removes all elements                |
| `isEmpty()`          | Checks if the set is empty          |

#### 🧪 Example

```java
Set<String> linkedSet = new LinkedHashSet<>();
linkedSet.add("Dog");
linkedSet.add("Cat");
linkedSet.add("Bird");
System.out.println(linkedSet); // Maintains insertion order
```

#### 🧠 Use Cases

* When you need uniqueness **and** predictable iteration order
* Caching scenarios (e.g., LRU Cache base layer)
* Creating ordered sets for display/UI purposes

---

### 🆚 HashSet vs LinkedHashSet

| Feature                   | HashSet         | LinkedHashSet      |
| ------------------------- | --------------- | ------------------ |
| Maintains insertion order | ❌               | ✅                  |
| Performance               | Slightly faster | Slightly slower    |
| Use case                  | Fast, unordered | Ordered, UI output |



### ✅ When to Use

* **Use `HashSet`** when you need high-performance set operations and order does not matter.
* **Use `LinkedHashSet`** when you need insertion order with set behavior.

---

Next up: Want to maintain sorting order instead? Look into `TreeSet` and `SortedSet`.
