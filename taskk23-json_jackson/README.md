# JSON in Java

## 🚀 Overview

JSON (JavaScript Object Notation) is a lightweight, human-readable format for storing and transporting data. In Java, we often use it to send and receive data to/from web APIs or files.

To work with JSON in Java, we typically:

1. Read JSON and convert it into Java objects (POJOs).
2. Write Java objects into JSON.

We use libraries like **Jackson**, **Gson**, or **org.json** to perform these operations.

---

## 🧂 Java JSON Libraries at a Glance

### 1. **Jackson**

* **Best for:** Full-featured, high-performance JSON parsing and binding.
* **Strengths:** Annotation support, stream processing, data-binding, tree-model.
* **Use Cases:** Large systems, APIs, enterprise applications, handling complex/nested JSON.

### 2. **Gson (by Google)**

* **Best for:** Quick and lightweight JSON parsing.
* **Strengths:** Simple API, good for converting between Java objects and JSON.
* **Use Cases:** Android apps, microservices, or small-scale projects.

### 3. **org.json (a.k.a. JSON-java)**

* **Best for:** Minimalistic JSON parsing without external dependencies.
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

## 🤖 As a Beginner: What is JSON?

Think of JSON like a toy box 📦. Inside it, you can have toys (data) like:

* Strings ("name": "James")
* Numbers ("age": 25)
* Booleans ("student": true)
* Lists ("subjects": \["Math", "Science"])
* Other boxes inside (nested objects)

Example JSON:

```json
{
  "name": "James",
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
# 📘 Jackson: Powerful JSON Processor for Java

## Overview

**Jackson** is a high-performance Java library used for processing JSON data. It is widely adopted in the Java ecosystem for serializing Java objects to JSON and deserializing JSON into Java objects.

Jackson is maintained under the FasterXML umbrella and provides extensive capabilities through its core modules and extensions.

Jackson is a powerful library for:

* Deserializing: JSON → Java Object
* Serializing: Java Object → JSON

## 🔧 Core Modules

| Module                | Description                                                        |
| --------------------- | ------------------------------------------------------------------ |
| `jackson-core`        | Low-level streaming API for JSON parsing and generation            |
| `jackson-databind`    | High-level data binding between Java objects and JSON              |
| `jackson-annotations` | Annotations for configuring serialization/deserialization behavior |


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

## ⚙️ Basic Usage

### 1. Maven Dependency

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.17.0</version>
</dependency>
```

### 2. Object to JSON (Serialization)

Converts a Java object into its JSON representation.

```java
ObjectMapper mapper = new ObjectMapper();
User user = new User("Alice", 30);
String json = mapper.writeValueAsString(user);
```

### 3. JSON to Object (Deserialization)

Parses JSON content and maps it to a Java object.

```java
String json = "{\"name\":\"Alice\",\"age\":30}";
User user = mapper.readValue(json, User.class);
```

---

## 🧹 Key Features

### ✔️ Annotations

| Annotation                    | Purpose                                     |
| ----------------------------- | ------------------------------------------- |
| `@JsonProperty`               | Rename JSON property                        |
| `@JsonIgnore`                 | Exclude field from serialization            |
| `@JsonInclude`                | Control null or empty field inclusion       |
| `@JsonFormat`                 | Custom date/time formatting                 |
| `@JsonCreator` / `@JsonValue` | Custom constructors or single-value classes |

### ✔️ Tree Model (Flexible JSON Handling)

Allows working with arbitrary or dynamic JSON structures using `JsonNode`, similar to DOM trees.

```java
JsonNode root = mapper.readTree(json);
String name = root.path("name").asText();
```

### ✔️ Streaming API (Performance)

Useful for parsing large JSON documents efficiently with low memory overhead by reading one token at a time.

```java
JsonFactory factory = new JsonFactory();
JsonParser parser = factory.createParser(json);
while (!parser.isClosed()) {
    JsonToken token = parser.nextToken();
    // Process tokens...
}
```

### ✔️ Custom Serializers/Deserializers

Create fully customized control over how Java types are converted to/from JSON.

```java
public class CustomDateSerializer extends JsonSerializer<LocalDate> {
    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeString(date.format(DateTimeFormatter.ISO_DATE));
    }
}
```

## 💼 Practical Applications

* Web APIs: Send/receive data as JSON
* Configurations: Load app settings from JSON files
* Storage: Persist user data, logs, etc.

## 🧠 Tips & Best Practices

* Always use getters/setters for POJOs.
* Use `@JsonProperty` if JSON field names differ from Java fields.
* Use `@JsonIgnore` for sensitive data.
* Validate JSON input before parsing.
---

## 🗀 Integrations

| Framework/Platform | Support                                                      |
| ------------------ | ------------------------------------------------------------ |
| Spring Boot        | ✅ Built-in support via `MappingJackson2HttpMessageConverter` |
| JAX-RS (REST)      | ✅ Used via providers like Jersey or RESTEasy                 |
| Kotlin             | ✅ Supported via `jackson-module-kotlin`                      |
| Java Records       | ✅ Native support in newer Jackson versions                   |
| Java 8 Date/Time   | ✅ With `jackson-datatype-jsr310` module                      |

---

## ⚠️ Common Pitfalls

* **Missing default constructor**: Jackson requires a no-arg constructor unless using `@JsonCreator`.
* **Unmapped fields**: Use `@JsonIgnoreProperties(ignoreUnknown = true)` to avoid deserialization errors.
* **Date format issues**: Customize with `@JsonFormat` or use modules like `jackson-datatype-jsr310`.


## 📈 Performance

Jackson is considered one of the fastest JSON libraries in the Java ecosystem. It supports:

* Zero-copy binary data streaming
* Lazy evaluation
* Custom buffer reuse

---
## 📚 References

* [Jackson Docs](https://github.com/FasterXML/jackson)
* [Jackson Annotations Guide](https://www.baeldung.com/jackson-annotations)
* [JSON.org](https://www.json.org/)
* [Baeldung - Jackson](https://www.baeldung.com/jackson)
