# Java Threads and Concurrency

## Overview

Java provides built-in support for multithreading and concurrent programming through its `java.lang.Thread` class and `java.util.concurrent` package. These enable developers to write efficient, scalable, and high-performance applications that can perform multiple tasks simultaneously.

Concurrency is essential in modern programming, especially when dealing with large-scale systems, parallel computations, I/O operations, and real-time applications. This document serves as a foundational guide to understanding Java's thread model and common concurrency patterns.

---

## The Big Picture: Multithreading and Parallel Computing

**Multithreading** is the ability of a CPU, or a single core in a multi-core processor, to provide multiple threads of execution concurrently. **Parallel computing** involves performing multiple computations simultaneously, typically across multiple cores.

Java supports both paradigms:

* **Multithreading** through the `Thread` class and `Runnable` interface
* **Parallelism** through frameworks like Fork/Join, Streams, and Executors

Benefits include:

* Better CPU utilization
* Faster processing through parallel execution
* Improved user experience via responsive UIs
* Scalable background processing (e.g., data ingestion, analytics)

---

## Class Hierarchy & Thread Architecture

```
java.lang.Object
  └── java.lang.Thread (implements Runnable)
                ├── run() - Contains thread logic
                ├── start() - Launches the thread
                ├── join() - Waits for the thread to finish
                ├── sleep() - Pauses thread temporarily
                └── interrupt() - Signals thread to stop

java.lang.Runnable
  └── run() - Abstract method for task logic

java.util.concurrent
  ├── Executors - Thread pool management
  ├── Future, Callable - Asynchronous task handling
  ├── BlockingQueue - Producer-consumer queues
  └── Locks, Semaphores - Fine-grained control
```

---

## Core Threading Concepts

### 1. **Thread Lifecycle**

* **New**: Thread is created
* **Runnable**: Thread is ready to run
* **Running**: Thread is executing
* **Blocked/Waiting**: Thread is paused
* **Terminated**: Thread has finished or was interrupted

### 2. **Thread Creation**

#### Using `Thread` Class

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running in thread: " + Thread.currentThread().getName());
    }
}
```

#### Using `Runnable` Interface

```java
Runnable task = () -> System.out.println("Running in thread: " + Thread.currentThread().getName());
Thread thread = new Thread(task);
thread.start();
```

---

## Common Concurrency Patterns

### Parallel Computation (Summation Example)

Efficient for splitting large tasks into smaller subtasks.

```java
class SumTask extends Thread {
    private int start, end;
    private long partialSum = 0;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) partialSum += i;
    }

    public long getPartialSum() { return partialSum; }
}
```

**Aggregating Thread Results:**

```java
// Create multiple SumTasks, start all, join all, and sum results
```

### Producer-Consumer (Restaurant Simulation)

```java
class Order { ... }
BlockingQueue<Order> queue = new ArrayBlockingQueue<>(10);

Runnable producer = () -> queue.put(new Order());
Runnable consumer = () -> queue.take();
```

This pattern decouples work producers and processors. Useful for streaming, logging, or job queues.

---

## Key Classes and Interfaces

| Class/Interface       | Purpose                                   |
| --------------------- | ----------------------------------------- |
| `Thread`              | Basic unit of execution                   |
| `Runnable`            | Functional interface for thread logic     |
| `ExecutorService`     | Manages thread pools and task scheduling  |
| `Future` / `Callable` | Represents async result & task            |
| `BlockingQueue`       | Thread-safe queue for producers/consumers |
| `ReentrantLock`       | Fine-grained thread control               |
| `synchronized`        | Built-in monitor-based locking            |

---

## Where to Use Threads and Concurrency

* Web server request handling
* Background job processing
* Real-time data pipelines
* High-performance computation (e.g., matrix multiplication)
* Event-driven systems
* GUI responsiveness (e.g., Swing/JavaFX apps)

---

## Best Practices

* Avoid shared mutable state
* Prefer higher-level abstractions (Executors over Threads)
* Use `synchronized` blocks sparingly and wisely
* Always handle `InterruptedException`
* Design thread-safe classes carefully (immutability helps)

---

## Further Reading

* *Java Concurrency in Practice* by Brian Goetz
* Oracle Docs: [https://docs.oracle.com/javase/tutorial/essential/concurrency/](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
* Java API: `java.util.concurrent`

---

## Conclusion

Mastering threads and concurrency in Java is critical for building scalable and performant applications. Whether for real-time simulations, parallel computation, or background task processing, Java provides a mature and powerful toolset for developers ready to embrace concurrent programming.
