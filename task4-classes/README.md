# Java Class

This document provides a comprehensive overview of the different types of classes in Java, including their definitions, use cases, and code examples.


## What is a Class?

**Simple Explanation:**
Imagine a class like a blueprint for building something — like a blueprint for a car. It tells you what parts the car has (properties) and what it can do (methods), but it's not the actual car. You can use the blueprint to build as many real cars (objects) as you want.

**Advanced Explanation:**
In Java, a class is a fundamental building block of object-oriented programming. It defines the structure and behavior (state and functionality) of objects. A class encapsulates data for the object and methods to manipulate that data. You can think of a class as a user-defined data type.

Syntax Example:

```java
public class Car {
    // Fields (attributes)
    String model;
    int year;

    // Methods (behavior)
    void drive() {
        System.out.println("Driving...");
    }
}
```

## Types of classes

## 1. Concrete Class

**Definition:** A standard class that provides implementations for all its methods and can be instantiated directly.

**Use Case:** Used when you want to create objects that perform specific behaviors and hold state.

**Example:**

```java
public class Car {
    void drive() {
        System.out.println("Driving...");
    }
}
```


## 2. Abstract Class

**Definition:** A class that cannot be instantiated and may contain abstract methods (without a body) and concrete methods (with a body).

**Use Case:** Used as a base class when you want to define a common template for all its subclasses.

**Example:**

```java
abstract class Animal {
    abstract void makeSound();

    void breathe() {
        System.out.println("Breathing...");
    }
}
```


## 3. Final Class

**Definition:** A class that cannot be extended (i.e., no subclassing allowed).

**Use Case:** Used when you want to prevent inheritance for reasons like security or immutability.

**Example:**

```java
public final class Constants {
    public static final String APP_NAME = "MyApp";
}
```


## 4. Static Nested Class

**Definition:** A class defined within another class with the `static` keyword. It cannot access instance variables or methods of the outer class directly.

**Use Case:** Used to logically group classes that are only used in one place or utility/helper classes.

**Example:**

```java
class Outer {
    static class StaticNested {
        void hello() {
            System.out.println("Hello from static nested class");
        }
    }
}
```


## 5. Inner Class (Non-Static Nested Class)

**Definition:** A class defined inside another class without the `static` keyword. It has access to all members of the outer class.

**Use Case:** Used when you want a close relationship between the inner and outer class, such as a component of the outer class.

**Example:**

```java
class Outer {
    class Inner {
        void display() {
            System.out.println("Displaying inner class");
        }
    }
}
```


## 6. Local Inner Class

**Definition:** A class defined within a method or block, scoped to that method.

**Use Case:** Used for encapsulating helper logic that is only relevant within a method.

**Example:**

```java
void method() {
    class Local {
        void show() {
            System.out.println("Local class");
        }
    }
    Local local = new Local();
    local.show();
}
```


## 7. Anonymous Inner Class

**Definition:** A class with no name, used to instantiate classes with slight modifications or to implement interfaces/abstract classes.

**Use Case:** Commonly used in GUI event handling and thread creation.

**Example:**

```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running...");
    }
};
r.run();
```


## 8. Enum Class

**Definition:** A special class used to define a collection of constants.

**Use Case:** Used for fixed sets of constants such as days of the week, directions, or states.

**Example:**

```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY
}
```


## 9. Record Class (Java 14+)

**Definition:** A concise way to create data-carrying classes. Automatically generates constructors, getters, `equals()`, `hashCode()`, and `toString()`.

**Use Case:** Ideal for immutable data transfer objects (DTOs).

**Example:**

```java
record Person(String name, int age) {}
```


### Summary Table

| Class Type          | Instantiable | Inheritable | Common Use Case                          |
| ------------------- | ------------ | ----------- | ---------------------------------------- |
| Concrete Class      | Yes          | Yes         | Regular object creation                  |
| Abstract Class      | No           | Yes         | Base class with shared logic             |
| Final Class         | Yes          | No          | Constants, security-sensitive components |
| Static Nested Class | Yes          | Yes         | Utility/helper class                     |
| Inner Class         | Yes          | Yes         | Close logical grouping                   |
| Local Inner Class   | Yes          | No          | Temporary or one-time-use logic          |
| Anonymous Class     | Yes          | No          | Short, one-off implementations           |
| Enum Class          | Yes          | No          | Collections of constants                 |
| Record Class        | Yes          | No          | Immutable data structures                |


> **Tip:** Choosing the right type of class depends on your design goals — encapsulation, simplicity, reusability, or immutability.
