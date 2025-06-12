# 🧠 LRU (Least Recently Used) Cache in Java

## 📘 Overview

An **LRU Cache** is a special kind of data structure that stores a fixed number of items. When the cache is full and a new item is inserted, it removes the **least recently accessed** item.

> It is commonly used in applications where memory is limited and recent data is more likely to be reused.


## 🔧 Key Characteristics

* **Fixed size**: Defined at initialization.
* **Eviction policy**: Discards the **least recently used** item.
* **Fast operations**: Efficient `get` and `put`.


## ⚙️ Java Implementation using `LinkedHashMap`

Java’s `LinkedHashMap` makes LRU implementation straightforward:

```java
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // true = access order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

### ✅ Example Usage

```java
public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.get(1); // Access item 1
        cache.put(4, "Four"); // Evicts item 2

        System.out.println(cache); // Output: {3=Three, 1=One, 4=Four}
    }
}
```


## 🛠️ When to Use

* Caching database queries
* Managing session tokens
* Web browser history
* Image or file caching


## ⚠️ Considerations

* `LinkedHashMap` is **not thread-safe**. Use `Collections.synchronizedMap()` or `ConcurrentHashMap` for concurrency.
* For very large caches or high concurrency, consider using **Caffeine** or **Guava Cache**.


## 📚 Alternatives

* Custom Doubly Linked List + HashMap implementation (for full control)
* `Guava Cache`
* `Caffeine Cache`


