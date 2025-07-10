# Java HTTP Calls README

This document provides guidance on making HTTP calls in Java using two approaches:

1. `HttpURLConnection` – a low-level, legacy API.
2. `HttpClient` – a high-level, modern API introduced in Java 11.

Rather than focusing on implementation code, this document emphasizes the **purpose**, **capabilities**, and **use-cases** of each approach to inform design and technology choices in Java-based applications.

---

## ✅ Prerequisites

* A functional REST API endpoint (e.g., `http://localhost:8000/api/teachers/1/exams`)
* Java Development Kit (JDK)

    * Java 8+ for `HttpURLConnection`
    * Java 11+ for `HttpClient`

---

## 🏗 HttpURLConnection (Java 8+)

### Overview:

`HttpURLConnection` is part of the core Java standard library since early versions. It provides a basic and low-level mechanism for making HTTP requests.

### When to Use:

* When working on legacy systems or maintaining older applications.
* When dependencies must be kept to a minimum.
* In environments restricted to Java 8 or earlier.

### Key Characteristics:

* Synchronous, blocking I/O.
* Requires verbose handling of streams and connection lifecycle.
* No native support for HTTP/2 or asynchronous execution.

### Trade-Offs:

| Pros                      | Cons                                   |
| ------------------------- | -------------------------------------- |
| Built-in, no dependencies | Verbose and error-prone                |
| Widely compatible         | Lacks support for async or modern HTTP |

---

## 🚀 HttpClient (Java 11+)

### Overview:

`HttpClient` is a modern, fully featured HTTP client API introduced in Java 11. It is designed to replace legacy APIs and provide a more robust, concise way to perform HTTP communications.

### When to Use:

* In modern Java applications targeting Java 11+.
* When asynchronous or reactive programming is needed.
* For HTTP/2 support or improved performance features.

### Key Characteristics:

* Supports both synchronous and asynchronous calls.
* Built-in support for HTTP/2.
* Provides fluent, builder-style API.
* Supports request timeouts, headers, and response types out of the box.

### Trade-Offs:

| Pros                                | Cons                              |
| ----------------------------------- | --------------------------------- |
| Cleaner, concise syntax             | Requires Java 11+                 |
| Asynchronous support (non-blocking) | Limited support in older systems  |
| More extensible and readable        | Slightly larger runtime footprint |

---

## ⚠ Common Pitfalls and Considerations

### Using `HttpClient` in Java 8:

* Not supported. You must upgrade to Java 11 or newer.
* Check your Java version with:

```sh
java -version
```

### Upgrading Java:

* Use official JDK distributions from [Adoptium](https://adoptium.net) or [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).
* Ensure your IDE or build tool (Maven/Gradle) is configured to use the newer JDK.

---

## ✅ Best Practices for HTTP Communication in Java

* Prefer `HttpClient` for new projects.
* Centralize HTTP logic into reusable service classes.
* Handle timeouts, retries, and exception management gracefully.
* Log responses and failures for monitoring and debugging.
* Avoid blocking calls in performance-sensitive or multi-threaded environments.

---

## 📌 Summary Table

| Feature        | HttpURLConnection     | HttpClient (Java 11+) |
| -------------- | --------------------- | --------------------- |
| Availability   | Java 8+               | Java 11+              |
| Async Support  | Manual, thread-based  | Native, non-blocking  |
| HTTP/2 Support | ❌                     | ✅                     |
| Ease of Use    | Moderate to Difficult | Easy and fluent       |
| Recommended    | For legacy apps only  | ✅ Modern applications |

---

## 🔍 Testing Locally

Before invoking the API through Java, validate the endpoint manually:

```sh
curl http://localhost:8000/api/teachers/1/exams
```

You should receive a proper JSON response if the backend server is operational.

---

## 📬 Contact

For design decisions, debugging assistance, or extending these utilities, reach out to your team lead or maintainers.

---

Embrace modern practices. Use the right tool for the job. 🚀
