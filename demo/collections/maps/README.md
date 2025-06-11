# Java Collections & Map Overview

## 📚 Introduction

This README covers Java Collections and Maps in detail, focusing on `List`, `Set`, `Queue`, and `Map` interfaces along with their major implementations. It includes explanations, use cases, code samples, and a hierarchy tree.

---

## 🌳 Collection & Map Hierarchy Tree

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

java.util.Map (interface)
├── HashMap
│   └── LinkedHashMap
├── TreeMap
└── Hashtable
    └── Properties
```

---

## 🔹 java.util.Map Interface

* Represents a **mapping of keys to values**
* **Not** part of the `Collection` interface
* Keys must be unique
**Syntax:**

```java
Map<String, String> map = new HashMap<>();
```


### Common Implementations:

* `HashMap`: Unordered, fast performance
* `TreeMap`: Sorted by key
* `LinkedHashMap`: Maintains insertion order


### 🔸 HashMap

* No ordering of keys
* Allows one null key and multiple null values
* Backed by hash table
* Average time complexity: O(1)

```java
Map<String, Integer> map = new HashMap<>();
map.put("Math", 95);
map.get("Math"); // 95
```

### 🔸 LinkedHashMap

* Maintains insertion order
* Slightly slower than `HashMap`

```java
Map<String, String> map = new LinkedHashMap<>();
map.put("first", "Alpha");
map.put("second", "Beta");
```

### 🔸 TreeMap

* Sorted by natural order or a comparator
* Does not allow null keys
* Backed by Red-Black tree
* Time complexity: O(log n)

```java
Map<String, Integer> map = new TreeMap<>();
map.put("C", 3);
map.put("A", 1);
```

### 🔸 Hashtable

* Legacy synchronized map
* Slower due to thread safety
* No null keys or values
* Avoid in new code — use `ConcurrentHashMap` instead

```java
Map<String, String> table = new Hashtable<>();
table.put("user", "admin");
```

### 🔸 Properties

* A subclass of `Hashtable`
* Used for config `.properties` files

```java
Properties props = new Properties();
props.setProperty("app.name", "MyApp");
props.getProperty("app.name");
```

### Key Methods

| Method              | Description                           |
| ------------------- | ------------------------------------- |
| `put(K key, V val)` | Adds or updates a key-value pair      |
| `get(Object key)`   | Returns value mapped to the key       |
| `containsKey(key)`  | Checks if the key exists              |
| `containsValue(v)`  | Checks if the value exists            |
| `remove(key)`       | Removes the mapping for the key       |
| `keySet()`          | Returns a `Set` view of the keys      |
| `values()`          | Returns a `Collection` view of values |
| `entrySet()`        | Returns a `Set` of key-value entries  |

---

## 🧠 Map vs Collection

| Feature        | Collection       | Map                 |
| -------------- | ---------------- | ------------------- |
| Element Access | Single elements  | Key-value pairs     |
| Duplicates     | Allowed (List)   | Keys must be unique |
| Ordering       | Depends on impl. | Depends on impl.    |
| Nulls          | Varies           | Varies by map type  |

---

## ✅ Summary Table

| Type            | Ordered?          | Sorted? | Nulls | Thread-Safe | Notes                           |
| --------------- | ----------------- | ------- | ----- | ----------- | ------------------------------- |
| `HashMap`       | ❌ No              | ❌ No    | ✅     | ❌           | Fast, most used general purpose |
| `LinkedHashMap` | ✅ Insertion order | ❌ No    | ✅     | ❌           | Predictable iteration           |
| `TreeMap`       | ❌ Sorted          | ✅ Yes   | ❌     | ❌           | Use for sorted keys             |
| `Hashtable`     | ❌ No              | ❌ No    | ❌     | ✅           | Legacy, avoid in new code       |
| `Properties`    | ✅ (as text file)  | ❌ No    | ❌     | ✅           | Used for configuration          |

---

## 📘 Final Tips

* Use `HashMap` when you need a fast, general-purpose key-value store.
* Use `LinkedHashMap` if insertion order matters.
* Use `TreeMap` when you need keys sorted.
* Avoid `Hashtable` in modern Java — prefer `ConcurrentHashMap` for thread-safe needs.

Would you like to generate icons, export this for Canva, or add code samples for each method?



