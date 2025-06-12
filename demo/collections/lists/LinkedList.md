## 📘 LinkedList in Depth

### ✨ Overview

`LinkedList` is a doubly-linked list implementation of the `List` and `Deque` interfaces. It provides efficient insertions and deletions from both ends but has slower random access compared to `ArrayList`.

### ⚙️ Internal Structure

`LinkedList` uses a series of nodes where each node holds data and references to the next and previous nodes. It does not use a backing array, so there’s no need to resize or shift elements.

### 📊 Performance

| Operation           | Time Complexity |
| ------------------- | --------------- |
| Access (get/set)    | O(n)            |
| Add/remove (ends)   | O(1)            |
| Add/remove (middle) | O(n)            |
| Search              | O(n)            |

### 🛋️ Use Cases

* Efficient queue or stack implementations
* When frequent insertions/deletions are needed
* Useful for real-time scheduling, undo functionality, etc.

### ⛔ Pitfalls

* Poor random access performance (no direct indexing)
* Slightly more memory usage due to node pointers
* Not thread-safe; consider `ConcurrentLinkedDeque` for concurrency

### 🔧 Common Methods and Usage Scenarios

| Method          | Purpose                          | Example Usage             |
| --------------- | -------------------------------- | ------------------------- |
| `add(E e)`      | Appends element to end           | `list.add("A");`          |
| `addFirst(E e)` | Inserts element at the beginning | `list.addFirst("Start");` |
| `addLast(E e)`  | Inserts element at the end       | `list.addLast("End");`    |
| `remove()`      | Removes head of the list         | `list.remove();`          |
| `removeFirst()` | Removes first element            | `list.removeFirst();`     |
| `removeLast()`  | Removes last element             | `list.removeLast();`      |
| `getFirst()`    | Retrieves first element          | `list.getFirst();`        |
| `getLast()`     | Retrieves last element           | `list.getLast();`         |
| `peek()`        | Retrieves head without removing  | `list.peek();`            |
| `poll()`        | Retrieves and removes head       | `list.poll();`            |
| `clear()`       | Removes all elements             | `list.clear();`           |

### 🔍 Example: Basic Usage

```java
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> languages = new LinkedList<>();
        languages.add("Java");
        languages.addFirst("Python");
        languages.addLast("Go");

        System.out.println("First: " + languages.getFirst());
        System.out.println("Last: " + languages.getLast());

        languages.remove("Java");

        for (String lang : languages) {
            System.out.println(lang);
        }
    }
}
```

### 🌐 Example: Browser History Navigation

```java
import java.util.LinkedList;

public class BrowserHistory {
    public static void main(String[] args) {
        LinkedList<String> history = new LinkedList<>();

        history.add("Home Page");
        history.add("About Us");
        history.add("Contact");

        System.out.println("Current: " + history.getLast());

        // Simulate going back
        String lastVisited = history.removeLast();
        System.out.println("Going back from: " + lastVisited);
        System.out.println("Now at: " + history.getLast());

        // Simulate going forward again
        history.addLast(lastVisited);
        System.out.println("Going forward to: " + history.getLast());
    }
}
```

### 🔄 When to Prefer Over Other Lists

* Use `LinkedList` when your application needs:

  * Fast insertions/removals from both ends
  * Queue or deque behavior
* Avoid when fast random access is needed — use `ArrayList` instead
* For thread-safe use, prefer `ConcurrentLinkedQueue` or proper synchronization
