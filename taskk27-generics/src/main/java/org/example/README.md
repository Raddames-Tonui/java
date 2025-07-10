# Generics in Java - Comprehensive README

## 🌳 Generics in Java - Conceptual Tree

```
Object
│
├── Class<T>
│   ├── List<T>
│   │   ├── ArrayList<T>
│   │   └── LinkedList<T>
│   ├── Set<T>
│   │   ├── HashSet<T>
│   │   └── TreeSet<T>
│   ├── Map<K, V>
│   │   ├── HashMap<K, V>
│   │   └── TreeMap<K, V>
│   └── Queue<T>
│       └── PriorityQueue<T>
│
├── Interface<T>
│   ├── Comparable<T>
│   ├── Comparator<T>
│   ├── Iterable<T>
│   └── Function<T, R>
│
└── Utility Classes
    ├── Collections
    ├── Arrays
    └── Optional<T>
```

---

## 📘 What Are Generics?

Generics enable types (classes and interfaces) to be parameters when defining classes, interfaces, and methods. They allow code to be more flexible, reusable, and type-safe.

**Main Objective:** Type safety and code reusability without the need for typecasting. 

**Example:**

```java
List<String> list = new ArrayList<>();
list.add("Hello");
String s = list.get(0); // No cast needed
```

---

## ⚙️ Use Cases of Generics

### 1. **Generic Classes**

A class that operates on a parameterized type.

```java
class Box<T> {
    private T value;
    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
```

* Reusable containers for any object type.

### 2. **Generic Interfaces**

An interface with type parameters.

```java
interface Pair<K, V> {
    K getKey();
    V getValue();
}
```

* Useful in data structures like maps, key-value pairs, etc.

### 3. **Generic Methods**

A method with its own type parameters.

```java
public <T> void printArray(T[] array) {
    for (T item : array) {
        System.out.println(item);
    }
}
```

* Create reusable logic applicable to multiple types.

### 4. **Bounded Type Parameters**

Restrict the type parameter to a certain type or its subclass.

```java
public <T extends Number> void printDoubleValue(T number) {
    System.out.println(number.doubleValue());
}
```

* Limit acceptable types to ensure specific operations (like numeric operations) are valid.

### 5. **Wildcards**

Use wildcards to allow flexibility with unknown or bounded types.

* `<?>` – Unknown type
* `<? extends T>` – Upper bounded wildcard (covariance)
* `<? super T>` – Lower bounded wildcard (contravariance)

```java
List<? extends Number> numbers = new ArrayList<Integer>();
```

---

## 📌 Key Concepts You Should Know

### 1. **Type Erasure**

Java removes all generic type information during compilation—a process called *type erasure*. This means that the compiled bytecode contains only raw types (e.g., `List` instead of `List<String>`). Despite this, the compiler uses the generic type information at compile-time for type checking and inserts necessary casts where appropriate. At runtime, the JVM works with raw types, but type safety is already enforced by the compiler.

### 2. **Raw Types**

Using generics without a type parameter (discouraged practice):

```java
List rawList = new ArrayList();
```

* Loses type safety and requires casting.

### 3. **Generic Constructor**

Constructors can also define type parameters independently of their class.

```java
class Demo {
    <T> Demo(T t) {
        System.out.println(t);
    }
}
```

* Allows instantiation of a class with a type-parameterized constructor without making the whole class generic.

### 4. **Generic Inheritance**

A subclass can specify or retain type parameters from a superclass.

```java
class IntegerBox extends Box<Integer> {}
class CustomBox<T> extends Box<T> {}
```

* Inheritance allows specialization or preservation of the generic type.

### 5. **Generic Restrictions**

* Cannot instantiate generic types with primitives: `Box<int>` ❌
* Cannot create instances of type parameters: `new T()` ❌
* Cannot create generic arrays: `new T[10]` ❌

---

## 🔁 Advanced Use

### Generic Method Chaining

A pattern where generic methods return `this` to allow chaining calls.
`Method chaining` is a pattern where each method returns the current object (this) to allow a sequence of method calls on a single line.
This is common in:

* Builder patterns

* Fluent interfaces

* Config objects

```java
public class Fluent<T> {
    T value;

    // Fluent setter
    public Fluent<T> set(T val) {
        this.value = val;
        return this;
    }

    // Getter
    public T get() {
        return value;
    }

    // Entry point for testing
    public static void main(String[] args) {
        // Fluent with String
        Fluent<String> stringFluent = new Fluent<>();
        stringFluent.set("Hello, Fluent!");
        System.out.println("String value: " + stringFluent.get());
        
        

        // Fluent with Integer and method chaining
        Fluent<Integer> intFluent = new Fluent<>();
        intFluent.set(42).set(100);
        System.out.println("Integer value: " + intFluent.get());

        // Fluent with custom object
        Fluent<Person> personFluent = new Fluent<>();
        personFluent.set(new Person("Alice", 30));
        System.out.println("Person: " + personFluent.get());
    }
}
// Output:
// String value: Hello, Fluent!
// Integer value: 100

// Custom class for demonstration
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // toString override for pretty printing
    public String toString() {
        return name + " (" + age + ")";
    }
}
// Output:
// Person: Alice (30)

```

* Supports fluent APIs and builder patterns by returning the current object.

### Recursive Bounds

Allow a type parameter to reference itself as a bound.

```java
class EnumType<T extends Enum<T>> {}
```

* Useful when a type parameter must be a subtype of a class that is parameterized by the same type.

---

## ✅ Benefits of Generics

* **Type Safety**: Prevents runtime type errors by enforcing compile-time checks.
* **Code Reusability**: Write one method/class and apply it to many types.
* **Cleaner Code**: No need for explicit casting.
* **Performance**: Reduces boxing/unboxing overhead.

---

## 🧠 Summary

| Feature         | Syntax                          | Example                  | Use Case                                 |
| --------------- | ------------------------------- | ------------------------ | ---------------------------------------- |
| Class           | `<T>`                           | `Box<T>`                 | Create reusable container classes        |
| Method          | `<T>`                           | `<T> void print(T t)`    | Generalize logic across data types       |
| Interface       | `<T>`                           | `Comparator<T>`          | Define contracts for operations on types |
| Bounded         | `<T extends X>`                 | `<T extends Number>`     | Restrict generics to subtype/supertype   |
| Wildcard        | `?`, `? extends T`, `? super T` | `List<? extends Number>` | Accept multiple generic types safely     |
| Constructor     | `<T> Constructor(T arg)`        | `<T> Demo(T t)`          | One-off parameterized object creation    |
| Inheritance     | `class C<T> extends G<T>`       | `CustomBox<T>`           | Extend and specialize generic classes    |
| Recursive Bound | `<T extends Enum<T>>`           | `EnumType<T>`            | Self-referencing types for enums, etc.   |
| Chaining        | `return this` in generics       | `Fluent<T>`              | Fluent/builder-style APIs                |

---

## 📚 Further Reading

* Java Language Specification, Chapter 4 & 8
* Effective Java, Joshua Bloch (Generics chapter)
* Oracle Java Generics Tutorial: [https://docs.oracle.com/javase/tutorial/java/generics/](https://docs.oracle.com/javase/tutorial/java/generics/)

---

## 💡 Tip

Always prefer using generics when designing collections or utility methods. It makes your code cleaner, safer, and easier to extend.

> "Generics make your APIs more powerful, your code safer, and your IDE smarter. Embrace them fully."
