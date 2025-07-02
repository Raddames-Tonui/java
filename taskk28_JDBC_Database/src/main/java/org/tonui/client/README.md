# Java HTTP Clients in Backend Applications

This README explains two HTTP clients in Java: `HttpURLConnection` and `HttpClient`. Both are used to send HTTP requests **from a Java backend** to another server. This could be another microservice, an external API (like Stripe or Mpesa), or even another endpoint within the same app.

---

## 1. HttpURLConnection

### 📄 Overview

* A built-in, low-level HTTP client from early Java versions.
* Verbose and boilerplate-heavy, but useful when no external dependencies are desired.
* Suitable for very basic GET/POST use cases.

### ✅ Key Features

* Lightweight
* No dependencies
* Works on all JDK versions

### 🔧 Example: GET Request

```java
URL url = new URL("http://localhost:8000/api/pupils");
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");

int status = connection.getResponseCode();
BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
StringBuilder content = new StringBuilder();
String inputLine;
while ((inputLine = in.readLine()) != null) {
    content.append(inputLine);
}
in.close();
connection.disconnect();

System.out.println("Status: " + status);
System.out.println("Response: " + content);
```

---

## 2. HttpClient (Java 11+)

### 📄 Overview

* Introduced in Java 11 as a modern replacement for `HttpURLConnection`.
* Easier to use, supports asynchronous programming, and handles JSON/text bodies smoothly.

### ✅ Key Features

* Built-in (Java 11+)
* Cleaner syntax
* Supports async requests (`sendAsync`)
* Supports headers, JSON, and more

### 🔧 Example: GET Request

```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8000/api/pupils"))
        .header("Accept", "application/json")
        .GET()
        .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Status Code: " + response.statusCode());
System.out.println("Body: " + response.body());
```

### 🔧 Example: POST Request

```java
String json = "{\"firstName\":\"John\", \"lastName\":\"Doe\"}";
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8000/api/pupils"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Created: " + response.body());
```

### 🔧 Example: PATCH Request

```java
String jsonPatch = "{\"email\":\"newemail@example.com\"}";
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8000/api/pupils/5"))
        .header("Content-Type", "application/json")
        .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonPatch))
        .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Updated: " + response.body());
```

### 🔧 Example: DELETE Request

```java
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8000/api/pupils/5"))
        .DELETE()
        .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Deleted: " + response.body());
```

### 🔧 Example: Multipart Form-Data (File Upload)

Java's standard libraries don’t handle `multipart/form-data` easily. Consider using libraries like Apache HttpClient or OkHttp for real-world uploads.

---

## 🧠 When to Use HTTP Clients in Java

| # | Use Case                        | Description                                                                 |
| - | ------------------------------- | --------------------------------------------------------------------------- |
| 1 | **Microservices Communication** | Service A (e.g. Orders) calls Service B (e.g. Inventory) to get stock info. |
| 2 | **External APIs**               | Your backend calls Stripe, Mpesa, Twilio, OpenAI, Google APIs, etc.         |
| 3 | **Webhook Listeners**           | Your app receives a webhook → then calls a 3rd-party API to sync data.      |
| 4 | **Service-to-Service Auth**     | Fetch access tokens or auth info from an OAuth/OpenID server.               |
| 5 | **Internal Background Jobs**    | One backend task triggers another internal API (e.g. report generator).     |
| 6 | **Data Syncing**                | Regularly fetch data from another system (e.g. syncing with ERP, CRM).      |
| 7 | **Server health checks**        | Admin dashboard pings internal services via HTTP.                           |

---

## 📦 Working with Headers

Example of using Authorization and Accept headers:

```java
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.example.com/data"))
        .header("Authorization", "Bearer your_access_token")
        .header("Accept", "application/json")
        .GET()
        .build();
```

---

## 🧱 Folder Structure Example

```
org/tonui/
├── Server.java
├── controller/
│   └── PupilController.java
├── dao/
│   └── PupilDAO.java
├── model/
│   └── Pupil.java
├── db/
│   └── DBConnection.java
└── client/
    ├── ClientHttpURLConnection.java
    └── ClientHttpClient.java
```

---

## 📌 Summary

| Feature         | HttpURLConnection         | HttpClient (Java 11+)       |
| --------------- | ------------------------- | --------------------------- |
| Simplicity      | ❌ Verbose                 | ✅ Clean and fluent          |
| Async Support   | ❌ No                      | ✅ Yes                       |
| JSON Handling   | ❌ Manual                  | ✅ Built-in support          |
| Recommended Use | Legacy systems, low-level | ✅ Modern apps, all new work |

---

## ✅ Recommendation

Use **`HttpClient`** if you’re on Java 11 or newer. Only fall back to `HttpURLConnection` if:

* You’re on Java 8 or older
* You need ultra-lightweight setups with no additional libraries
