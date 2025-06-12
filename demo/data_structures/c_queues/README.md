# 📦 Queues in Java

## 🚀 Overview

A **Queue** in Java is a linear data structure based on the **FIFO (First-In-First-Out)** principle. The element that is added first is the one that gets removed first.

Java provides the `Queue` interface under the `java.util` package, which is part of the **Java Collections Framework**.

---

## 🧱 Characteristics

* **FIFO order**: First element added is the first one removed.
* **Supports null?** Usually not (e.g., `ArrayDeque`, `PriorityQueue` don't allow nulls).
* **Thread-safe?** Only specific queues like `ConcurrentLinkedQueue` are.
* **Generic**: Works with any object type.

---

## 🔧 Core Queue Methods

| Method      | Description                                |
| ----------- | ------------------------------------------ |
| `add(e)`    | Inserts element, throws exception if fails |
| `offer(e)`  | Inserts element, returns false if fails    |
| `remove()`  | Removes head, throws exception if empty    |
| `poll()`    | Removes head, returns null if empty        |
| `element()` | Retrieves head, throws exception if empty  |
| `peek()`    | Retrieves head, returns null if empty      |

---

## 🧪 Example with `LinkedList`

```java
import java.util.Queue;
import java.util.LinkedList;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.offer("Apple");
        queue.offer("Banana");
        queue.offer("Cherry");

        System.out.println(queue);         // [Apple, Banana, Cherry]
        System.out.println(queue.poll());  // Apple
        System.out.println(queue.peek());  // Banana
    }
}
```

---

## ⚙️ Implementations

### ✅ `LinkedList`

* Implements `Queue` and `Deque`
* Good for standard FIFO operations

### ✅ `PriorityQueue`

* Orders elements by **natural ordering** or a **custom comparator**
* Not a FIFO queue

```java
Queue<Integer> pq = new PriorityQueue<>();
pq.offer(30);
pq.offer(10);
pq.offer(20);
System.out.println(pq.poll());  // 10 (smallest element)
```

### ✅ `ArrayDeque`

* Resizable array, supports queue and stack operations
* Faster than `LinkedList`
* Does **not allow nulls**

```java
Deque<String> deque = new ArrayDeque<>();
deque.offerFirst("Start");
deque.offerLast("End");
System.out.println(deque);  // [Start, End]
```

### ✅ `ConcurrentLinkedQueue`

* Thread-safe, lock-free
* Used in concurrent applications

```java
Queue<String> concurrentQueue = new ConcurrentLinkedQueue<>();
concurrentQueue.offer("Thread1");
```

---

## 🔁 Types of Queues

| Queue Type          | Description                                           |
| ------------------- | ----------------------------------------------------- |
| **Simple Queue**    | FIFO queue (e.g., `LinkedList`)                       |
| **Deque**           | Double-ended queue (e.g., `ArrayDeque`)               |
| **PriorityQueue**   | Orders by priority, not insertion order               |
| **BlockingQueue**   | Supports thread-safe operations with blocking methods |
| **ConcurrentQueue** | Thread-safe, lock-free queue                          |

---

## 🧠 Use Cases

* **Task scheduling** (e.g., job queues)
* **Breadth-first search** in trees/graphs
* **Message buffering** in concurrent applications
* **Print job queues**
* **Simulation systems** (e.g., call centers, traffic)

---

## ⚠️ Caveats

* `PriorityQueue` is **not** FIFO.
* `ArrayDeque` is faster than `LinkedList`, but no nulls allowed.
* `ConcurrentLinkedQueue` is thread-safe but doesn't block.

---

## ✅ When to Use

* You need **ordered** processing.
* You want to model **real-world queueing behavior**.
* You need **range-based** or **priority-based** retrieval.
* You require **thread-safe** queuing in multi-threaded environments.

