| Requirement                                   | Action Plan                                                                    |
| --------------------------------------------- | ------------------------------------------------------------------------------ |
| 🔍 Concepts: endpoints, params, headers, etc. | I can summarize those now, or give you a printable cheat sheet                 |
| 🧩 Implement RESTful endpoints                | Use a lightweight HTTP server (e.g. [Javalin](https://javalin.io)) or Undertow |
| 🚀 Endpoints with conventions                 | Example: `GET /pupils`, `POST /pupils`, `GET /pupils/{id}` etc.                |
| 🧾 Return/consume JSON                        | Jackson or GSON can be used to convert Java <-> JSON                           |
| 📦 Accept headers (`Content-Type`, `Accept`)  | Set in the controller methods (we'll use `application/json`)                   |
| ✅ Use HTTP methods: GET, POST, PUT, DELETE    | Map DAO methods to HTTP via controller                                         |
| ⌨️ Accept parameters & path variables         | E.g. `/pupils/{id}` or `?class_id=3`                                           |
| 📤 Response body                              | Return JSON-serialized objects (POJOs)                                         |
| 🧾 Form-Data upload support                   | Optional unless you're uploading files (we can demo with mock)                 |
| 📡 HTTP status codes (200, 201, 404, etc.)    | Map return values and exceptions to proper status codes                        |
| 🔧 Research Undertow                          | I can summarize Undertow + set up minimal REST server                          |


Client -> GET /pupils
|
↓
PupilController.getAllPupils()
↓
PupilService.getAllPupils()
↓
PupilDAO.getAllPupils()
↓
DBConnection.getConnection()


school-api/
├── pom.xml                          # Maven dependencies
├── application.properties           # DB config file (in resources)
├── src/
│   └── main/
│       ├── java/
│       │   └── org/tonui/
│       │       ├── Main.java                   # Starts the server
│       │
│       │       ├── db/
│       │       │   └── DBConnection.java       # Loads DB config, connects with retry
│       │
│       │       ├── model/
│       │       │   └── Pupil.java              # POJO for pupils
│       │
│       │       ├── dao/
│       │       │   └── PupilDAO.java           # Performs DB CRUD operations
│       │
│       │       ├── service/
│       │       │   └── PupilService.java       # Business logic (validation, orchestration)
│       │
│       │       └── controller/
│       │           └── PupilController.java    # HTTP routes and request handlers
│
│       └── resources/
│           └── application.properties          # DB credentials
└──



| Use case                 | Example                                     |
| ------------------------ | ------------------------------------------- |
| Input validation         | Ensure emails, IDs are valid                |
| Transactions             | Save to multiple tables at once             |
| Rule enforcement         | Prevent pupil from being added to 2 classes |
| Aggregating data         | Fetch scores + calculate averages           |
| Future-proofing your app | Easily upgrade to REST APIs, GraphQL, etc.  |



## RESTful API with Undertow — README

### ✅ Project Goal

Build a RESTful API for managing resources (like pupils, subjects, exams) using **Undertow**, a lightweight embedded Java HTTP server. This project emphasizes low-level understanding of HTTP and REST concepts without using heavy frameworks like Spring Boot.

---

## 📦 RESTful API Concepts Covered

### 1. Resources & Endpoints

* **Resource**: A noun (e.g., `pupils`, `subjects`, `exams`)
* **Endpoint**: A URL to access/manipulate resources

**Example:**

```
GET /api/pupils          --> List all pupils
GET /api/pupils/5        --> Get pupil with ID 5
POST /api/subjects       --> Create new subject
```

---

### 2. Path Variables & Query Parameters

* **Path Variable**: `/pupils/{id}` (e.g., `/pupils/7`)
* **Query Param**: `/pupils?gender=male`

**Undertow Example:**

```java
exchange.getQueryParameters().get("gender")
```

---

### 3. HTTP Headers

| Header          | Purpose                                  |
| --------------- | ---------------------------------------- |
| `Content-Type`  | Format of body sent (`application/json`) |
| `Accept`        | Format client wants (`application/json`) |
| `Authorization` | Security token (`Bearer xyz`)            |

**Access in Undertow:**

```java
exchange.getRequestHeaders().getFirst("Authorization")
```

---

### 4. HTTP Methods

| Method    | Purpose                    |
| --------- | -------------------------- |
| `GET`     | Read / Fetch               |
| `POST`    | Create                     |
| `PUT`     | Replace                    |
| `PATCH`   | Partial update             |
| `DELETE`  | Remove                     |
| `OPTIONS` | Discover available methods |

---

### 5. Request & Response Bodies

* **Format:** JSON (or XML if needed)
* **Request:** data sent to server
* **Response:** data returned by API

```json
// Request body (POST /pupils)
{
  "pupil_firstname": "Jane",
  "pupil_lastname": "Doe"
}
```

---

### 6. Form-Data (Multipart)

* Used for file uploads
* `Content-Type: multipart/form-data`
* Optional in this task; can be demonstrated for file submissions later

---

### 7. Status Codes

| Code | Meaning               | Use Case                  |
| ---- | --------------------- | ------------------------- |
| 200  | OK                    | Success                   |
| 201  | Created               | New resource created      |
| 204  | No Content            | No response body          |
| 400  | Bad Request           | Invalid input             |
| 404  | Not Found             | Resource doesn't exist    |
| 500  | Internal Server Error | Exception in server logic |

**Set in Undertow:**

```java
exchange.setStatusCode(201);
```

---

### 8. Undertow Embedded Server

* Lightweight, high-performance Java HTTP server
* No external server needed (embedded inside your app)
* Used in modern frameworks like Quarkus

---

## 🧩 REST API Design Plan

| Endpoint           | Method | Description     |
| ------------------ | ------ | --------------- |
| `/api/pupils`      | GET    | List all pupils |
| `/api/pupils/{id}` | GET    | Get one pupil   |
| `/api/pupils`      | POST   | Add new pupil   |
| `/api/pupils/{id}` | PUT    | Update pupil    |
| `/api/pupils/{id}` | DELETE | Delete pupil    |

---

## 🧪 HTTP Clients (Task 30)

### A. `HttpURLConnection`

* Classic Java client
* Used to manually construct GET/POST/PUT calls

### B. `HttpClient` (Java 11+)

* Modern API with clean syntax
* Supports async and cleaner JSON integration

---

## ✅ What’s Next

1. Implement Undertow server routing for `/api/pupils`
2. Handle JSON with Jackson
3. Add HTTP client classes to test API from Java
4. Expand to other resources (`subjects`, `exams`, etc.)

---

## 🔥 Why This Approach?

* Learn HTTP the hard way → makes you a better backend engineer
* Frameworks become easier once you’ve done it low-level
* Undertow is used in real-world, high-perf apps (e.g., Quarkus)

---

Ready to implement `/api/pupils` in Undertow and test it with Java clients? Let’s move to coding.
