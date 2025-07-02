# 🔬 Research on Undertow Embedded Server in Java

Undertow is a flexible, performant, and embeddable web server written in Java, designed to be used both as a standalone web server or embedded in Java applications. Developed by JBoss (Red Hat), Undertow is known for its high throughput, low latency, and non-blocking I/O capabilities.

---

## 📌 Key Features

* **Lightweight and embeddable**: Can be started and stopped directly from Java code.
* **Non-blocking architecture**: Built on Java NIO for scalable, asynchronous processing.
* **Flexible handlers**: Highly composable request handling pipeline.
* **WebSockets support**: Native support for full-duplex communication.
* **HTTP/2 support**
* **Servlet container support**: Can act as a JSR-340 (Servlet 3.1+) container.
* **Reverse proxy** capabilities.

---

## 🧱 Core Classes and Packages

### 1. `io.undertow.Undertow`

* The entry point class to create an embedded Undertow server.

#### 🔧 Key Methods

* `builder()` → Returns an `Undertow.Builder` object.
* `start()` → Starts the server.
* `stop()` → Stops the server.

### 2. `io.undertow.server.HttpHandler`

* Functional interface to handle HTTP requests.

```java
@FunctionalInterface
public interface HttpHandler {
    void handleRequest(HttpServerExchange exchange) throws Exception;
}
```

### 3. `io.undertow.server.HttpServerExchange`

* Encapsulates HTTP request and response.

#### 🔧 Key Methods

* `getRequestMethod()`
* `getRequestURI()`
* `getResponseSender().send(String)`
* `getQueryParameters()`

### 4. `io.undertow.util.Headers`

* Utility class for standard HTTP headers.

```java
exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
```

---

## 🧪 Example: Basic Embedded Server

```java
public class Server {
    public static void main(String[] args) {
        Undertow server = Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(exchange -> {
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                exchange.getResponseSender().send("Hello from Undertow!");
            })
            .build();
        server.start();
    }
}
```

---

## 🔄 Routing with PathHandler

`PathHandler` allows simple routing without needing a full MVC framework:

```java
PathHandler pathHandler = new PathHandler()
    .addPrefixPath("/hello", exchange -> {
        exchange.getResponseSender().send("Hello World");
    })
    .addPrefixPath("/goodbye", exchange -> {
        exchange.getResponseSender().send("Goodbye World");
    });
```

---

## 🔒 HTTPS Support

Undertow can be configured to serve HTTPS content:

```java
Undertow.builder()
    .addHttpsListener(8443, "localhost", sslContext)
    .setHandler(...);
```

You’ll need an `SSLContext` which is typically built from a keystore.

---

## 🔌 Deployment as a Servlet Container

Undertow can also be used with Servlet API:

### Key Classes:

* `io.undertow.servlet.api.DeploymentInfo`
* `io.undertow.servlet.api.ServletContainer`
* `io.undertow.servlet.Servlets`

### Example:

```java
DeploymentInfo servletBuilder = Servlets.deployment()
    .setClassLoader(Server.class.getClassLoader())
    .setContextPath("/")
    .setDeploymentName("myapp.war")
    .addServlets(
        Servlets.servlet("HelloServlet", HelloServlet.class)
            .addMapping("/hello")
    );
```

---

## 🌐 WebSockets

Undertow has built-in WebSocket support using `WebSocketProtocolHandshakeHandler`.

```java
WebSocketProtocolHandshakeHandler handler = ...;
```

---

## 🔁 Request Lifecycle

```text
Client HTTP Request
    ↓
Undertow (Embedded HTTP Server)
    ↓
HttpHandler chain (PathHandler, RoutingHandler, etc.)
    ↓
Business logic / response generation
    ↓
Response sent via HttpServerExchange
```

---

## 📚 Useful Resources

* Official Docs: [https://undertow.io/](https://undertow.io/)
* GitHub Repo: [https://github.com/undertow-io/undertow](https://github.com/undertow-io/undertow)
* Javadoc API: [https://undertow.io/javadoc/](https://undertow.io/javadoc/)

---

## ✅ Summary

Undertow is ideal for:

* Microservices
* Lightweight REST APIs
* Embedded servers in Spring-free Java apps
* Custom web frameworks

Its non-blocking model and composable handler chain make it one of the most efficient and flexible Java HTTP servers available today.
