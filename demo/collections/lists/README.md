# 📘 Java Lists: Complete Guide (README)

## 📌 Overview

A `List` in Java is an **ordered collection** that allows duplicate elements. It is part of the Java Collections Framework and is implemented by classes such as `ArrayList`, `LinkedList`, and `Vector`. Lists are **dynamic**, meaning they grow and shrink as needed, unlike arrays which are fixed in size.


## 🚀 Why Use Lists?

* Dynamic resizing
* Index-based access
* Can contain duplicates
* Rich API with search, sort, filter, etc.


## 🧱 Interfaces and Implementations

| Interface | Common Implementations              |
| --------- | ----------------------------------- |
| `List<E>` | `ArrayList`, `LinkedList`, `Vector` |


## 📦 Import Statement

```java
import java.util.List;
import java.util.ArrayList;
```


## 🧪 Creating a List

```java
List<String> names = new ArrayList<>();
names.add("Raddames");
names.add("Ken");
names.add("Asha");
```


## 🛠 Common Operations

### Add Elements

```java
names.add("Liam");
names.add(1, "Noah"); // insert at index 1
```

### Access Elements

```java
String first = names.get(0);
```

### Modify Elements

```java
names.set(2, "Emma");
```

### Remove Elements

```java
names.remove("Ken");
names.remove(0); // removes first element
```

### Check Size

```java
int size = names.size();
```

### Iterate

```java
for (String name : names) {
    System.out.println(name);
}
```

### Sort List

```java
import java.util.Collections;
Collections.sort(names);
```

### Search

```java
boolean hasEmma = names.contains("Emma");
int index = names.indexOf("Emma");
```


## 📚 Types of Lists (Coming Up)

We'll dive deeper into the following:

1. `ArrayList` [demo](./ArrayLists.md)
2. `LinkedList` [demo](./LinkedList.md)
3. `Vector`
4. `Stack` (which extends `Vector`)


## ⚠️ Key Points

* `List` is an **interface**, not a class.
* Use `ArrayList` for fast access.
* Use `LinkedList` for frequent insert/delete.
* Prefer `List` reference type for flexibility.


## ✅ Best Practices

* Always program to the interface (`List`) not implementation.
* Avoid raw types: use generics (`List<String>`).
* Use `Collections.unmodifiableList()` for read-only lists.


