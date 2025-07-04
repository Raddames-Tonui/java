# Java Interfaces

This document introduces Java Interfaces in a simple-to-advanced manner, breaking down core concepts, use cases, syntax, and special interface types like functional interfaces. Examples and metaphors are used to ensure clarity for both beginners and advanced learners.


## Visual Summary: Java Type Tree

```
Java Types
│
├── Class
│   ├── Concrete Class
│   ├── Abstract Class
│   └── Final Class
│
├── Interface
│   ├── Functional Interface
│   ├── Marker Interface
│   ├── Interface with Default Methods
│   └── Interface with Static Methods
```


## What is an Interface?

**Simple Explanation:**
Think of an interface like a **contract** or **a list of promises**. For example, if someone says "I can sing and dance," you expect them to be able to do both. In Java, an interface tells a class what methods it must include — but **not how to implement them**. It’s like saying, “every class that signs this contract must have these methods.”

**Advanced Explanation:**
An interface in Java is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types. Interfaces cannot have instance fields or constructors. A class implements an interface, thereby inheriting the abstract methods of the interface.


## Real-World Analogy (Tree Analogy)

Imagine Java as a forest, and interfaces as the **roots of the trees**. Each root defines what the tree is supposed to do — like grow leaves or bear fruit — but the actual tree (class) can grow in its own unique way while still obeying the rules of the root.


## Why Use Interfaces?

* To achieve **abstraction**
* To allow **multiple inheritance of type** (Java doesn't support multiple class inheritance, but allows multiple interface implementation)
* To enforce a **contract**
* To support **polymorphism**
* To promote **loose coupling** in large applications
* To enable **dependency injection** and **mocking** in tests


## Interface Syntax

```java
public interface DataProcessor {
    void process(String data); // Abstract method
}
```

A class implements this interface like so:

```java
public class JsonProcessor implements DataProcessor {
    public void process(String data) {
        // JSON processing logic here 
        //Must be instantiated, else also it will become an abstract class instead of Concrete class
        System.out.println("This is instantiated method" + data);
    }
}
```


## Use Case Example: JSON and XML Processor

### Step 1: Define Interface

```java
public interface DataProcessor {
    void process(String data);
}
```

### Step 2: Implement in Two Classes
The difference with the classes that are implementing them is the output data since both Overrides the abstract methods from interface.

```java
public class JsonProcessor implements DataProcessor {
    public void process(String data) {
        System.out.println("Processing JSON: " + data);
    }
}

public class XMLProcessor implements DataProcessor {
    public void process(String data) {
        System.out.println("Processing XML: " + data);
    }
}
```

### Step 3: Test It

```java
public class ProcessorTest {
    public static void main(String[] args) {
        DataProcessor json = new JsonProcessor();
        DataProcessor xml = new XMLProcessor();

        json.process("{\"name\": \"Alice\"}");
        xml.process("<person><name>Alice</name></person>");
    }
}
```

## Types of Interfaces
| Type            | Key Feature                          | Example                     |
| --------------- | ------------------------------------ | --------------------------- |
| Normal          | Abstract + default/static methods    | `Runnable`, `Comparable`    |
| Functional      | Exactly 1 abstract method (lambdas)  | `Consumer`, `Predicate`     |
| Marker          | No methods, used for tagging         | `Serializable`, `Cloneable` |
| Constant Holder | Holds only constants                 | `ActionTypes` (legacy)      |
| Nested          | Inside class/interface for structure | `OnClickListener`           |

## Normal Interface (Regular Interface)
Contains two or more Methods.

To define a contract for classes.
Can contain abstract methods, default, static, and private methods.

```java
public interface Flyable {
    void fly();                     // abstract
    default void land() {}         // optional
    static void calibrate() {}     // utility
}

```
## Functional Interfaces

**Definition:** An interface with exactly one abstract method. Used for lambda expressions and functional programming.

```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}
```

**Use Case:**

```java
MathOperation add = (a, b) -> a + b;
System.out.println(add.operate(5, 3)); // Output: 8
```

**Built-in Functional Interfaces:**

### Runnable

Used for executing code in a thread.

```java
Runnable task = () -> System.out.println("Task running");
new Thread(task).start();
```

### Callable (from `java.util.concurrent`)

Like Runnable, but returns a result and can throw an exception.

```java
Callable<Integer> call = () -> 5 + 5;
ExecutorService service = Executors.newSingleThreadExecutor();
Future<Integer> result = service.submit(call);
System.out.println(result.get());
```

### Comparator<T>

Used to compare two objects.

```java
Comparator<String> byLength = (s1, s2) -> s1.length() - s2.length();
List<String> names = List.of("Tom", "Alexander", "Bob");
names.sort(byLength);
```

### Function\<T, R>

Takes an input of type T and returns a result of type R.

```java
Function<String, Integer> toLength = str -> str.length();
System.out.println(toLength.apply("Hello")); // Output: 5
```


## Marker Interfaces

**Definition:** An interface with no methods or fields.

**Use Case:** Used to signal or tag a class with metadata.

### Serializable

Allows an object to be converted into a byte stream.

```java
public class User implements Serializable {
    String name;
    int age;
}
```

### Cloneable

Indicates that the class allows object cloning.

```java
public class User implements Cloneable {
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```


## Default and Static Methods in Interfaces

From Java 8 onwards, interfaces can have:

### *a) Default Methods*
Provide a default implementation
A method in an interface with a body.
Allows interfaces to evolve without breaking existing code.
```java
interface Animal {
    default void breathe() {
        System.out.println("Breathing...");
    }
}

class Dog implements Animal {
    // Inherits breathe() by default
}

```

### b) *Static Methods*
Belongs to the interface itself, not to instances or implementing classes.



```java
interface Utility {
    static int add(int a, int b) {
        return a + b;
    }
}


int result = Utility.add(5, 10);  // Call directly from the interface

```

| Feature            | `default` method   | `static` method             |
| ------------------ | ------------------ | --------------------------- |
| Can be overridden? | ✅ Yes              | ❌ No                        |
| Needs an instance? | ✅ Yes (via object) | ❌ No (called via interface) |
| Added in           | Java 8             | Java 8                      |
| Accessed via       | Object (of class)  | Interface name              |


```java
interface Logger {
    // Default Method
    default void logInfo(String msg){
        System.out.println("INFO: " + msg);
    }
    // Static Method
    static void logError(String msg){
        System.out.println("ERROR: "+ msg)
    }
}

class MyApp implements Logger{

}

public class Main{
    public static void main(String[] args){
        MyApp app = new MyApp();
        app.logInfo("Starting app...") // Default Method
        Logger.logError("Somethingwent wrong") // Uses static Mehod. Not it has been called via the instance name "Logger"
    }
}
```


## Multiple Interface Implementation

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    public void fly() {
        System.out.println("Flying...");
    }
    public void swim() {
        System.out.println("Swimming...");
    }
}
```


## Interface vs Abstract Class

| Feature               | Interface                          | Abstract Class                      |
| --------------------- | ---------------------------------- | ----------------------------------- |
| Inheritance           | Implements (multiple allowed)      | Extends (single inheritance)        |
| Method Implementation | Can have default methods (Java 8+) | Can have both abstract and concrete |
| Variables             | Public, static, final              | Can have instance variables         |
| Constructors          | No                                 | Yes                                 |


## Summary

* **Interfaces define what to do**, not how to do it.
* Used to achieve abstraction, polymorphism, and multiple inheritance of types.
* Key to designing scalable and testable software.
* Can include default and static methods.
* Functional interfaces are the bridge to functional programming in Java.
* Marker interfaces offer metadata tagging capabilities.

> **Tip for Beginners:** Think of an interface as a list of rules or promises. If a class agrees to follow them, it must implement every method listed.

> **Tip for Advanced Devs:** Favor interface-driven design. It promotes decoupling and testability. Interfaces are contracts — and contracts make systems reliable.
