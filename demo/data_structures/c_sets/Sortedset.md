# 📘 SortedSet in Java

## ✨ Overview

`SortedSet` is an interface in Java that extends `Set` and maintains elements in their **natural order** (or a specified comparator). It ensures all elements are unique and **automatically sorted**.

> Typical implementations: `TreeSet`

### 🧱 Key Characteristics

* **Sorted** in ascending order by default
* **No duplicates**
* Implements **NavigableSet** (which adds navigation methods)

### 🔧 Common Methods (SortedSet)

| Method                 | Description                                          |
| ---------------------- | ---------------------------------------------------- |
| `first()`              | Returns the first (lowest) element                   |
| `last()`               | Returns the last (highest) element                   |
| `headSet(E to)`        | Elements less than `to`                              |
| `tailSet(E from)`      | Elements greater than or equal to `from`             |
| `subSet(E from, E to)` | Elements in range `[from, to)`                       |
| `comparator()`         | Returns comparator used, or null if natural ordering |

### 📚 Example: Natural Sorting

```java
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<Integer> numbers = new TreeSet<>();
        numbers.add(50);
        numbers.add(10);
        numbers.add(30);

        System.out.println("Sorted: " + numbers); // [10, 30, 50]
        System.out.println("First: " + numbers.first());
        System.out.println("Last: " + numbers.last());
    }
}
```

### 📚 Example: Custom Comparator

```java
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;

public class CustomSortDemo {
    public static void main(String[] args) {
        SortedSet<String> names = new TreeSet<>(Comparator.reverseOrder());
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println(names); // [Charlie, Bob, Alice]
    }
}
```

# 🌲 TreeSort with TreeSet

`TreeSort` is a sorting algorithm that involves inserting elements into a `TreeSet`, which automatically sorts them.

```java
import java.util.TreeSet;

public class TreeSortDemo {
    public static void main(String[] args) {
        int[] arr = {5, 1, 8, 3};

        TreeSet<Integer> sortedSet = new TreeSet<>();
        for (int num : arr) {
            sortedSet.add(num);
        }

        System.out.println("TreeSorted output: " + sortedSet); // [1, 3, 5, 8]
    }
}
```

This is useful when you want sorted output with automatic duplicate removal.

### 🛎️ Use Cases

* Sorted unique values (e.g., leaderboards)
* Range views (subsets, headsets)
* Implementing sets with consistent order for UI display
* Lightweight sorting logic with `TreeSet`
* Real-time filtered data (e.g., price filters in e-commerce)
* Time-series data with range querying (e.g., logs within a time range)
* Event scheduling with unique, ordered timestamps
* Maintaining an auto-sorted list of user scores or rankings

### ⚠️ Caveats

* Backed by Red-Black tree (log(n) cost per operation)
* Not synchronized
* Slower than `HashSet` for add/remove if no sorting needed

### ✅ When to Use

* When element order matters
* When natural or custom sorting is required
* When efficient subset/range views are needed
* When implementing TreeSort behavior with automatic uniqueness
