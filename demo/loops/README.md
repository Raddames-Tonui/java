# Java Loops

This document outlines various loop constructs in Java, including syntax, use cases, and key characteristics. It also includes control flow mechanisms like `break` and `continue`.

---

## Table of Contents

* [1. for Loop](#1-for-loop)
* [2. while Loop](#2-while-loop)
* [3. do-while Loop](#3-do-while-loop)
* [4. Enhanced for Loop (for-each)](#4-enhanced-for-loop-for-each)
* [5. Loop Control Statements](#5-loop-control-statements)

  * [break](#break)
  * [continue](#continue)
* [6. Summary Table](#6-summary-table)

---

## 1. `for` Loop

Executes a block of code a specific number of times.

```java
for (int i = 0; i < 5; i++) {
    System.out.println("i = " + i);
}
```

**Use case:** Known iteration count.

---

## 2. `while` Loop

Executes a block of code while a condition is true.

```java
int i = 0;
while (i < 5) {
    System.out.println("i = " + i);
    i++;
}
```

**Use case:** Unknown iteration count, condition-based.

---

## 3. `do-while` Loop

Executes the block of code **at least once**, and then continues as long as the condition is true.

```java
int i = 0;
do {
    System.out.println("i = " + i);
    i++;
} while (i < 5);
```

**Use case:** Code must execute at least once regardless of condition.

---

## 4. Enhanced `for` Loop (for-each)

Iterates over elements of arrays or collections.

```java
int[] numbers = {1, 2, 3, 4, 5};
for (int number : numbers) {
    System.out.println("Number: " + number);
}
```

**Use case:** Iterating through collections or arrays without modifying indices.

---

## 5. Loop Control Statements

### `break`

Terminates the loop or switch statement immediately.

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // Exit loop when i is 5
    }
    System.out.println(i);
}
```

**Use case:** Early exit from loop when a condition is met.

### `continue`

Skips the current iteration and proceeds with the next iteration of the loop.

```java
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) {
        continue;  // Skip even numbers
    }
    System.out.println(i);
}
```

**Use case:** Skip specific iterations without exiting the loop.

---

## 6. Summary Table

| Loop Type  | Best Use Case                      | Condition Checked | Executes At Least Once | Index Access | Collection Support |
| ---------- | ---------------------------------- | ----------------- | ---------------------- | ------------ | ------------------ |
| `for`      | Fixed number of iterations         | Before            | No                     | Yes          | No                 |
| `while`    | Unknown number of iterations       | Before            | No                     | Yes          | No                 |
| `do-while` | Execute at least once              | After             | Yes                    | Yes          | No                 |
| `for-each` | Iterating through array/collection | Internal          | No                     | No           | Yes                |
| `break`    | Exit loop early                    | Condition-based   | N/A                    | N/A          | N/A                |
| `continue` | Skip specific iterations           | Condition-based   | N/A                    | N/A          | N/A                |

---

Let me know if you'd like examples of nested loops, labels with `break/continue`, or performance considerations.
