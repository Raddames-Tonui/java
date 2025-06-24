# JSON in Java - From Beginner to Pro

## 🚀 Overview

JSON (JavaScript Object Notation) is a lightweight, human-readable format for storing and transporting data. In Java, we often use it to send and receive data to/from web APIs or files.

To work with JSON in Java, we typically:

1. Read JSON and convert it into Java objects (POJOs).
2. Write Java objects into JSON.

We use libraries like **Jackson**, **Gson**, or **org.json**. For this README, we focus on **Jackson**, but let's also briefly touch on the other two.

---

## 🧢 Java JSON Libraries at a Glance

### 1. **Jackson**

* **Best for:** Full-featured, high-performance JSON parsing and binding.
* **Strengths:** Annotation support, stream processing, data-binding, tree-model.
* **Use Cases:** Large systems, APIs, enterprise applications, handling complex/nested JSON.

### 2. **Gson (by Google)**

* **Best for:** Quick and lightweight JSON parsing.
* **Strengths:** Simple API, good for converting between Java objects and JSON.
* **Use Cases:** Android apps, microservices, or small-scale projects.

### 3. **org.json (a.k.a. JSON-java)**

* **Best for:** Minimalistic JSON parsing without external libraries.
* **Strengths:** Straightforward, zero-annotation, low dependency.
* **Use Cases:** Embedded systems, small utilities, academic work.

| Feature/Library | Jackson    | Gson          | org.json      |
| --------------- | ---------- | ------------- | ------------- |
| Data Binding    | ✅ Strong   | ✅ Good        | ❌ Manual      |
| Tree Model      | ✅ JsonNode | ✅ JsonElement | ✅ JSONObject  |
| Annotations     | ✅ Powerful | ✅ Limited     | ❌ None        |
| Streaming API   | ✅ Yes      | ❌ No          | ❌ No          |
| File Support    | ✅ Yes      | ✅ Yes         | ✅ Yes         |
| Size            | ⚖️ Medium  | ⚡ Light       | 🪶 Very Light |

---

## 🧒 As a Beginner: What is JSON?

Think of JSON like a toy box 📦. Inside it, you can have toys (data) like:

* Strings ("name": "Raddames")
* Numbers ("age": 25)
* Booleans ("student": true)
* Lists ("subjects": \["Math", "Science"])
* Other boxes inside (nested objects)

Example JSON:

```json
{
  "name": "Raddames",
  "age": 25,
  "student": true,
  "subjects": ["Math", "Science"]
}
```

---

## 👨‍🎓 As a Learner: What is a POJO?

A **POJO** (Plain Old Java Object) is a simple Java class with:

* Private fields
* Public getters/setters
* No extra behavior (just holds data)

Example:

```java
public class Student {
    private String name;
    private int age;
    private boolean student;

    // Getters and Setters
}
```

---

## 🧑‍💼 As a Professional: Using Jackson

Jackson is a powerful library for:

* Deserializing: JSON → Java
* Serializing: Java → JSON

### Key Jackson Classes:

| Class           | Description                               |
| --------------- | ----------------------------------------- |
| `ObjectMapper`  | The core class for reading/writing JSON   |
| `JsonNode`      | Tree model for parsing JSON without POJOs |
| `@JsonProperty` | Annotation to map JSON fields to Java     |
| `@JsonIgnore`   | Ignore a field during serialization       |
| `@JsonInclude`  | Include non-null/empty fields             |

### Methods of `ObjectMapper`:

| Method                           | Purpose                            |
| -------------------------------- | ---------------------------------- |
| `readValue(jsonStr, Class<T>)`   | JSON string to POJO                |
| `writeValueAsString(Object obj)` | POJO to JSON string                |
| `readTree(jsonStr)`              | Parses JSON into a tree (JsonNode) |

---

## 🌳 Class & Interface Tree (Jackson)

```
com.fasterxml.jackson
└── databind
    ├── ObjectMapper
    │   ├── readValue()
    │   ├── writeValueAsString()
    │   └── readTree()
    └── JsonNode
        ├── get()
        ├── path()
        ├── asText()
        ├── isObject()
        └── isArray()
```

---

## 📂 Use Case: Reg-Payload.json

Given a file like `Reg-Payload.json`:

```json
{
  "username": "james",
  "email": "james@example.com",
  "age": 25,
  "active": true
}
```

### 1. Create a POJO:

```java
public class User {
    private String username;
    private String email;
    private int age;
    private boolean active;

    // Getters and Setters
}
```

### 2. Deserialize JSON into POJO:

```java
ObjectMapper mapper = new ObjectMapper();
User user = mapper.readValue(new File("Reg-Payload.json"), User.class);
```

### 3. Serialize POJO into JSON:

```java
String json = mapper.writeValueAsString(user);
```

---

## 💼 Practical Applications

* Web APIs: Send/receive data as JSON
* Configurations: Load app settings from JSON files
* Storage: Persist user data, logs, etc.

---

## 🧠 Tips & Best Practices

* Always use getters/setters for POJOs.
* Use `@JsonProperty` if JSON field names differ from Java fields.
* Use `@JsonIgnore` for sensitive data.
* Validate JSON input before parsing.

---

## 🔚 Summary

* JSON is a simple data format.
* Jackson + POJO = Java power for handling JSON.
* Understand both tree model (`JsonNode`) and data-binding (`ObjectMapper`).

> Next: We’ll implement the task using the above principles.

---

## 📘 References

* [Jackson Docs](https://github.com/FasterXML/jackson)
* [JSON.org](https://www.json.org/)
* [Baeldung - Jackson](https://www.baeldung.com/jackson)
