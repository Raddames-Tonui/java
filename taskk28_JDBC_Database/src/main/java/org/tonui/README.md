# 🧱 Java Web App Architecture with Undertow, Maven & Clean Structure

This guide documents the architecture, concepts, and project structure of a Java web backend using Undertow, Maven, and Clean Architecture principles.

---

## 📦 Tech Stack Overview

| Layer        | Technology        | Purpose                           |
| ------------ | ----------------- | --------------------------------- |
| Web Server   | Undertow          | Lightweight, fast HTTP server     |
| Build Tool   | Maven             | Dependency management & build     |
| Language     | Java              | Core programming language         |
| Database     | PostgreSQL (JDBC) | Persistent data storage           |
| JSON Binding | Jackson           | Java objects ↔ JSON serialization |

---

## 🌳 Project Structure Tree

```bash
project-root/
├── pom.xml                             # Maven project configuration
├── README.md                           # Project documentation (optional)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── tonui/
│   │   │           ├── controller/     # Handles HTTP routing (Undertow)
│   │   │           │   └── PupilController.java
│   │   │           ├── service/        # Business logic layer
│   │   │           │   └── PupilService.java
│   │   │           ├── dao/            # Database access using JDBC
│   │   │           │   └── PupilDAO.java
│   │   │           ├── model/          # POJO data models
│   │   │           │   └── Pupil.java
│   │   │           ├── db/             # DB connection setup
│   │   │           │   └── DBConnection.java
│   │   │           ├── util/           # Shared utility functions (optional)
│   │   │           └── Server.java     # Undertow server bootstrapper
│   │   └── resources/                  # Configuration files, e.g., application.properties
│   └── test/
│       ├── java/
│       │   └── org/
│       │       └── tonui/
│       │           └── PupilServiceTest.java  # Unit tests (example)
│       └── resources/                  # Test-specific resources
└── target/                             # Maven build output (generated)
```

---

## 🛠️ Maven Configuration (pom.xml)

```xml
<dependencies>
    <!-- Undertow server -->
    <dependency>
        <groupId>io.undertow</groupId>
        <artifactId>undertow-core</artifactId>
        <version>2.3.17.Final</version>
    </dependency>

    <!-- PostgreSQL JDBC Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.7</version>
    </dependency>

    <!-- Jackson for JSON mapping -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.17.1</version>
    </dependency>
</dependencies>
```

---

## 🧠 Key Concepts

### 1. Controller Layer (Routing)

* Maps HTTP routes to methods
* Uses `HttpServerExchange` from Undertow
* Handles request parsing and response sending

### 2. Service Layer

* Encapsulates business logic
* Validates data before calling DAO
* Optional but strongly recommended

### 3. DAO Layer (Database Access)

* Uses JDBC to communicate with PostgreSQL
* Handles SQL queries: `SELECT`, `INSERT`, `UPDATE`, `DELETE`

### 4. Model Layer

* Java POJOs (Plain Old Java Objects)
* Represent database entities
* Used in both input (JSON) and output

### 5. DBConnection Utility

* Centralizes database connection logic using JDBC

---

## 🔁 Execution Flow

```scss
HTTP Request
   ↓
Controller (PupilController)
   ↓
Service (PupilService)
   ↓
DAO (PupilDAO)
   ↓
Database (PostgreSQL)
   ↓
Response (JSON via Jackson)
```

---

## 📤 HTTP Endpoints (Examples)

| Method | Path                           | Description           |
| ------ | ------------------------------ | --------------------- |
| GET    | /api/pupils                    | List all pupils       |
| POST   | /api/pupils                    | Create a new pupil    |
| GET    | /api/pupils/{id}               | Get pupil by ID       |
| PUT    | /api/pupils/{id}               | Update pupil by ID    |
| DELETE | /api/pupils/{id}               | Delete pupil by ID    |
| GET    | /api/pupils/by-class?classId=1 | Get pupils in a class |

---

## ✅ Benefits of this Design

| Feature                | Why it Matters                                   |
| ---------------------- | ------------------------------------------------ |
| Separation of Concerns | Each layer focuses on one responsibility         |
| Maintainability        | Easy to update or refactor specific components   |
| Scalability            | Supports team collaboration and growing codebase |
| Reusability            | Services can be reused across multiple endpoints |
| Testability            | Each layer is easier to test independently       |

---

## 🧠 Summary

This project follows a clean, layered structure:

* Keep controllers lean
* Move logic to services
* Keep data access in DAOs

✅ **“Fat Service, Skinny Controller.”** — industry best practice.

You're now production-ready 🚀
