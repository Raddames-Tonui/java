## 📘 ArrayList in Depth

### ✨ Overview

`ArrayList` is the most commonly used implementation of the `List` interface. It uses a dynamic array to store elements and offers fast read operations.

### ⚙️ Internal Structure

Internally, `ArrayList` uses an array to hold elements. When the array is full, it grows by 50% (Java 8+). Resizing is an expensive operation, so avoid adding elements one-by-one in tight loops when possible.

### 📊 Performance

| Operation        | Time Complexity |
| ---------------- | --------------- |
| Access (get/set) | O(1)            |
| Add (end)        | O(1) amortized  |
| Insert/remove    | O(n)            |
| Search           | O(n)            |

### 🛌️ Use Cases

* Best for quick access (random reads)
* Suitable when insert/delete operations are minimal
* Ideal for list-backed UI models (e.g., dropdown options)
* Useful when order matters and frequent traversal is required

### ⛔ Pitfalls

* Poor performance when frequently inserting/removing in the middle
* Not thread-safe; use `Collections.synchronizedList()` or `CopyOnWriteArrayList` for concurrency

### 🔧 Common Methods and Usage Scenarios

| Method                | Purpose                                           | Example Usage                             |
| --------------------- | ------------------------------------------------- | ----------------------------------------- |
| `add(E e)`            | Appends an element to the end                     | `list.add("Apple");`                      |
| `add(int index, E e)` | Inserts an element at the specified index         | `list.add(1, "Banana");`                  |
| `get(int index)`      | Retrieves the element at the specified index      | `list.get(0);`                            |
| `set(int index, E e)` | Replaces the element at the specified index       | `list.set(0, "Orange");`                  |
| `remove(int index)`   | Removes the element at the specified index        | `list.remove(2);`                         |
| `contains(Object o)`  | Checks if the list contains the specified element | `list.contains("Apple");`                 |
| `indexOf(Object o)`   | Returns the index of the first occurrence         | `list.indexOf("Banana");`                 |
| `clear()`             | Removes all elements                              | `list.clear();`                           |
| `size()`              | Returns the number of elements                    | `list.size();`                            |
| `isEmpty()`           | Checks if the list is empty                       | `list.isEmpty();`                         |
| `sort(Comparator)`    | Sorts elements with a custom comparator           | `list.sort(String::compareToIgnoreCase);` |

### 🔧 Example

```java
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");

        Collections.sort(fruits);

        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
```

### 🔄 When to Prefer Over Other Lists

* Use `ArrayList` when frequent random access is needed.
* Avoid when inserting/removing elements in the middle is common — consider `LinkedList` instead.
* For thread-safe scenarios, wrap with synchronization or use concurrent alternatives.
