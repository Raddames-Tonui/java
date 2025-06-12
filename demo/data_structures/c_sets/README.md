## 📘 Set in Java

### ✨ Overview

A `Set` is a collection that **does not allow duplicate elements**. It models the mathematical set abstraction. Sets are part of the Java Collections Framework and are ideal when you want to ensure **uniqueness** of stored values.

### 🌳 Interface Hierarchy Diagram

```
              ┌────────────┐
              │   Set<E>   │
              └────┬───────┘
                   │
     ┌─────────────┼────────────┐
     │                          │
┌────▼────┐              ┌──────▼──────┐
│HashSet  │              │SortedSet<E> │
│         │              └──────┬──────┘
│        ┌▼──────────┐          │
│        │LinkedHashSet│        │
│        └────────────┘    ┌────▼────────┐
│                          │NavigableSet<E>│
└──────────────────────────┴──────┬────────┘
                                  │
                              ┌───▼─────┐
                              │ TreeSet │
                              └─────────┘
```

### 🧬 Interfaces

Java provides the following main `Set` interfaces:

* `Set<E>` – Base interface
* `HashSet<E>` [HashSet](./HashSet.md) – Extends Set with no order, quick access.
* `SortedSet<E>`[Sorted set](./Sortedset.md) – Extends Set to maintain order


### 🧱 Implementations

| Implementation  | Characteristics           | Ordered? | Thread-Safe? |
| --------------- | ------------------------- | -------- | ------------ |
| `HashSet`       | Backed by a hash table    | ❌        | ❌            |
| `LinkedHashSet` | Maintains insertion order | ✅        | ❌            |
| `TreeSet`       | Sorted (Red-Black tree)   | ✅        | ❌            |

### 🔧 Common Methods

| Method               | Description                         |
| -------------------- | ----------------------------------- |
| `add(E e)`           | Adds element if not already present |
| `remove(Object o)`   | Removes specified element           |
| `contains(Object o)` | Checks if element exists            |
| `isEmpty()`          | Checks if set is empty              |
| `clear()`            | Removes all elements                |
| `size()`             | Returns number of elements          |

### 🛋️ Use Cases

* Enforcing uniqueness (e.g., usernames, IDs)
* Filtering duplicates
* Set operations: union, intersection, difference
* Maintaining order (with LinkedHashSet or TreeSet)

### ⛔ Pitfalls

* No access by index (unlike List)
* Element equality is based on `equals()` and `hashCode()`
* Not synchronized (use `Collections.synchronizedSet()` if needed)

### 🔍 Example

```java
import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        Set<String> emails = new HashSet<>();
        emails.add("john@example.com");
        emails.add("jane@example.com");
        emails.add("john@example.com"); // Duplicate, will not be added

        for (String email : emails) {
            System.out.println(email);
        }

        System.out.println("Total unique emails: " + emails.size());
    }
}
```

### ✅ When to Use

* When duplicates are unacceptable
* When set operations (like union/intersection) are needed
* When order isn't required (use `HashSet`), or specific order is needed (use `LinkedHashSet`/`TreeSet`)

---

Next: Check detailed README sections for [HashSet](./HashSet.md),  [Sorted Set](./Sortedset.md). 
