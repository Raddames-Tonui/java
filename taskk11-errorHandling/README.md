# ☕ Java Error Handling

## 📘 Introduction

Error handling is a critical part of Java programming. It allows developers to anticipate, detect, and respond to runtime issues that could disrupt application execution. Java uses **exceptions** to signal and manage errors.

---

## 🧠 Key Concepts

### 🔶 What is an Exception?

An exception is an event that disrupts the normal flow of a program. It is an object that describes an error condition and is a subclass of the `Throwable` class.

### 🔶 What is Error?

Errors represent serious issues that a program should not try to handle (e.g., `OutOfMemoryError`, `StackOverflowError`). These are generally external or system-level problems.

---

## 🔍 Types of Exceptions

### 1. **Checked Exceptions**

Checked exceptions are enforced by the compiler. They must be handled explicitly using a `try-catch` block or declared using the `throws` keyword. They usually deal with external resources (like files, databases).

**Examples:** `IOException`, `SQLException`

```java
import java.io.*;

public class CheckedExample {
    public static void main(String[] args) {
        try {
            // Attempting to read a non-existent file
            FileReader fr = new FileReader("file.txt");
        } catch (FileNotFoundException e) {
            // This block handles the checked exception
            e.printStackTrace();
        }
    }
}
```

### 2. **Unchecked Exceptions**

Unchecked exceptions occur at runtime. The compiler does not require them to be caught or declared. They are usually due to programming logic errors.

**Examples:** `NullPointerException`, `ArithmeticException`

```java
public class UncheckedExample {
    public static void main(String[] args) {
        // This will throw ArithmeticException at runtime
        int a = 5 / 0; 
    }
}
```

### 3. **Errors**

Errors are serious problems from which recovery is not expected. They are typically thrown by the JVM.

**Example:**

```java
public class ErrorExample {
    public static void main(String[] args) {
        // This recursive call will eventually lead to StackOverflowError
        main(args); 
    }
}
```

---

## 🛠️ Handling Exceptions

### ✅ Try-Catch Block

Wraps risky code and provides a recovery path if something goes wrong.

```java
try {
    // Risky operation: dividing by zero
    int num = 10 / 0;
} catch (ArithmeticException e) {
    // Handle exception here
    System.out.println("Cannot divide by zero");
}
```

### ✅ Try-Catch-Finally Block

`finally` always runs—use it for cleanup like closing files, database connections, etc.

```java
try {
    String data = "123";
    int number = Integer.parseInt(data); // May throw NumberFormatException
} catch (NumberFormatException e) {
    System.out.println("Invalid format");
} finally {
    System.out.println("Cleanup completed.");
}
```

### ✅ Multi-Catch

Catch multiple exception types in a single block to reduce redundancy.

```java
try {
    String data = null;
    System.out.println(data.length()); // May throw NullPointerException
} catch (NullPointerException | NumberFormatException e) {
    System.out.println("Handled multiple exceptions.");
}
```

---

## 🧩 Custom Exceptions

### 🔧 Define Custom Exception

Creating your own exception class helps you represent specific business logic errors.

```java
// Custom exception class extending Exception (checked exception)
class MyException extends Exception {
    public MyException(String message) {
        super(message); // Call the parent class constructor
    }
}
```

### 🔧 Use Custom Exception

Use it where specific domain validation or business rules fail.

```java
public class CustomDemo {

    // Method to validate age - throws custom exception if age is less than 18
    static void validateAge(int age) throws MyException {
        if (age < 18) {
            // Throwing a custom exception with message
            throw new MyException("Age must be 18 or older.");
        }
    }

    public static void main(String[] args) {
        try {
            validateAge(16); // This will cause our custom exception to be thrown
        } catch (MyException e) {
            // Catch block for handling custom exception
            System.out.println("Caught custom exception: " + e.getMessage());
        }
    }
}
```

---

## 🧼 Best Practices

* ✅ Catch specific exceptions first (before general ones).
* ❌ Avoid empty catch blocks—always handle or log.
* ✅ Use `finally` or try-with-resources for cleanup.
* ✅ Create custom exceptions to represent domain-specific errors.
* ✅ Use logging frameworks like Log4j or SLF4J to log exceptions properly.

---

## 🧪 Advanced Tips for Professionals

* 🔁 **Try-with-resources**: Automatically closes resources like streams.

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
} catch (IOException e) {
    e.printStackTrace();
}
```

* 🔗 **Exception Chaining**: Capture the original cause.

```java
catch (IOException e) {
    throw new RuntimeException("Wrapped IOException", e);
}
```

* 🧾 **Error Codes via Enums**
  Create enums for handling various error scenarios cleanly across APIs.

* 🧩 **Monitoring**
  Integrate with logging/monitoring tools like:

  * Sentry
  * ELK Stack
  * New Relic

---

## 🧾 Summary Table

| Type          | Checked   | Unchecked        | Error |
| ------------- | --------- | ---------------- | ----- |
| Compile Time  | ✅         | ❌                | ❌     |
| Handles Logic | ✅         | ✅                | ❌     |
| Extends       | Exception | RuntimeException | Error |

---

## 📚 References

* [Oracle Java Docs - Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
* Effective Java, Joshua Bloch
* Clean Code, Robert C. Martin

---

Happy Coding! 🧑‍💻
