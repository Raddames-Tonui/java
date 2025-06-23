# Java Advanced Concepts Documentation

This documentation deeply covers several key Java concepts: Lambdas, Parametric Polymorphism, Comparators, Streams, Annotations, and Functional Interfaces. Each is situated in its context within the Java ecosystem and includes a tree of related ideas, use cases, and methods where applicable.

---

## 1. Lambdas

### Context:

Lambdas were introduced in Java 8 under the **java.util.function** package to support **functional programming**.

### Tree:

```
Lambda Expressions
├── Functional Interfaces
│   ├── Predicate
│   ├── Consumer
│   ├── Function
│   └── Supplier
├── Streams API
│   ├── map()
│   ├── filter()
│   └── forEach()
└── Event Listeners
```

### Syntax:

```java
(parameters) -> expression
(parameters) -> { statements }
```

### Example:

```java
List<String> names = List.of("Alice", "Bob", "Charlie");
names.forEach(name -> System.out.println(name));
```

### Use Cases:

* Shortening anonymous classes
* Simplifying collection operations
* Functional-style data transformations

---

## 2. Parametric Polymorphism (Generics)

### Context:

Part of Java since version 5, allows for **type-safe code** via **generics**.

### Tree:

```
Parametric Polymorphism
├── Generic Classes
├── Generic Methods
├── Bounded Types
│   ├── extends
│   └── super
└── Wildcards
    ├── ?
    ├── ? extends Type
    └── ? super Type
```

### Example:

```java
public class Box<T> {
    private T value;
    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
```

### Use Cases:

* Collections (e.g., `List<T>`)
* Type-safe APIs
* Avoiding explicit casting

---

## 3. Comparators

### Context:

Part of `java.util`, used for **custom sorting logic**.

### Tree:

```
Comparator Interface
├── compare(T o1, T o2)
├── reversed()
├── thenComparing()
└── comparing(Function)
```

### Example:

```java
List<String> names = Arrays.asList("Zoe", "Alice", "Bob");
Collections.sort(names, (a, b) -> a.compareTo(b));
```

### Use Cases:

* Sorting collections
* Multi-level sorting with `thenComparing`
* Building complex comparison chains

---

## 4. Streams

### Context:

Introduced in Java 8 under `java.util.stream`, promotes **declarative processing** of collections.

### Tree:

```
Streams API
├── Intermediate Operations
│   ├── map()
│   ├── filter()
│   ├── sorted()
│   └── distinct()
└── Terminal Operations
    ├── forEach()
    ├── collect()
    ├── reduce()
    └── count()
```

### Example:

```java
List<String> names = List.of("Alice", "Bob", "Charlie");
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("A"))
    .collect(Collectors.toList());
```

### Use Cases:

* Data transformation
* Filtering large datasets
* Aggregations and statistics

---

## 5. Annotations

### Context:

Metadata used by the compiler or runtime. Found in `java.lang.annotation`.
Similar to **decorators in Python**, but annotations do not wrap behavior directly; they attach metadata used by frameworks or tools.

### Tree:

```
Annotations
├── Built-in
│   ├── @Override
│   ├── @Deprecated
│   └── @SuppressWarnings
├── Meta-Annotations
│   ├── @Retention
│   ├── @Target
│   ├── @Documented
│   └── @Inherited
└── Custom Annotations
```

### Example:

```java
@interface MyAnnotation {
    String value();
}

@MyAnnotation(value = "example")
public class MyClass {}
```

### Use Cases:

* Dependency injection (e.g., `@Autowired` in Spring)
* ORM mapping (`@Entity`, `@Id`)
* Unit testing (`@Test`)

### Difference with Python Decorators:

* **Java**: Metadata, compile/runtime processed
* **Python**: Code wrappers that modify function/class behavior

---

## 6. Functional Interfaces

### Context:

Single Abstract Method (SAM) interfaces enabling functional programming.

### Tree:

```
Functional Interfaces (java.util.function)
├── Predicate<T>        // boolean test(T t)
├── Consumer<T>         // void accept(T t)
├── Function<T, R>      // R apply(T t)
├── Supplier<T>         // T get()
└── UnaryOperator<T>, BinaryOperator<T>
```

### Example:

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}

Printer p = msg -> System.out.println(msg);
p.print("Hello Lambda");
```

### Use Cases:

* Lambdas
* Streams operations
* Callbacks

---

## Summary Table

| Concept                 | Java Package         | Introduced In | Key Usage                     |
| ----------------------- | -------------------- | ------------- | ----------------------------- |
| Lambdas                 | java.util.function   | Java 8        | Inline function-like behavior |
| Parametric Polymorphism | java.lang            | Java 5        | Type-safe generics            |
| Comparator              | java.util            | Java 1.2      | Custom sorting                |
| Streams                 | java.util.stream     | Java 8        | Functional data processing    |
| Annotations             | java.lang.annotation | Java 5        | Metadata for tools/frameworks |
| Functional Interfaces   | java.util.function   | Java 8        | Enable Lambdas, callbacks     |

---

Would you like me to export this to a Canva-ready `README.md` or PDF?
