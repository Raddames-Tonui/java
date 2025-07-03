# Task 23: JSON to POJO Conversion using Jackson

This project demonstrates how to parse a JSON file into a Java object (POJO) using the Jackson library in a Maven-based Java application.

## ✨ Overview

We are provided with a `Reg-Payload.json` file containing user registration information. The objective is to:

1. Create Java classes (POJOs) matching the structure of the JSON.
2. Use Jackson's `ObjectMapper` to convert JSON into these Java objects.
3. Run the project using Maven, ensuring all dependencies are available at runtime.

---

## 📂 Project Structure

```
payloadJson/
├── pom.xml                             # Maven config file with dependencies and project info
├── README.md                           # Documentation file
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           ├── payload/
│       │           │   ├── Account.java
│       │           │   ├── NokBeneficiaryNomineeInfo.java
│       │           │   ├── Payload.java
│       │           │   └── RegPayloadWrapper.java
│       │           └── payloadJson/
│       │               └── App.java   # Main application entry point
│       └── resources/
│           └── Reg-Payload.json       # The input JSON file to be parsed
├── target/
│   ├── classes/                        # Compiled .class files
│   ├── dependency/                     # External dependencies copied here
│   ├── generated-sources/             # Auto-generated sources (if any)
│   ├── maven-archiver/                # Maven metadata
│   ├── maven-status/                  # Maven build tracking
│   └── payloadJson-1.0-SNAPSHOT.jar   # Packaged JAR file
```

---

## 📖 Step-by-Step Guide

### 1. ✍️ Define POJO Classes

Each Java class matches a portion of the JSON structure. Use `@JsonProperty` to map JSON fields to Java fields using camelCase.

* `RegPayloadWrapper.java`: contains `action` and `payload` fields.
* `Payload.java`: contains fields like `firstName`, `emailAddress`, etc., and lists for `nokBeneficiaryNomineeInfo` and `accounts`.
* `NokBeneficiaryNomineeInfo.java`: matches the inner beneficiary object.
* `Account.java`: matches account object inside `accounts`.

### 2. ✉️ Include Jackson in pom.xml

```xml
<dependencies>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.16.1</version>
  </dependency>
</dependencies>
```

### 3. ⚙️ Build & Prepare the Project

```bash
mvn clean compile
```

> Deletes `target/` and compiles source files.

```bash
mvn package
```

> Packages the `.class` files into a `.jar` file in `target/`.

### 4. 💾 Copy Dependencies

```bash
mvn dependency:copy-dependencies
```

> Downloads and copies all required JARs (e.g., Jackson) to `target/dependency/`.

### 5. ▶️ Run the Application

#### On Linux/macOS:

```bash
java -cp target/payloadJson-1.0-SNAPSHOT.jar:target/dependency/* com.example.payloadJson.App
```

* `target/payloadJson-1.0-SNAPSHOT.jar` Your main application JAR (contains your compiled code).

* `target/dependency/*` Wildcard to include all external JAR dependencies in that folder
* `com.example.payloadJson.App` 	The fully qualified name of the class with the main method to run.

#### On Windows:

```cmd
java -cp target\payloadJson-1.0-SNAPSHOT.jar;target\dependency\* com.example.payloadJson.App
```

> Ensures both the app JAR and dependencies are available at runtime.

---

## 🧩 Optional Tools and Enhancements

### 🔹 Lombok

Lombok is a Java library that reduces boilerplate code. Instead of manually writing getters, setters, and constructors, you can annotate your class:

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payload { ... }
```

> ⚠️ You must add the Lombok dependency and enable annotation processing in your IDE.

### 🔹 Maven Shade Plugin (Fat JAR)

Instead of using `copy-dependencies` and classpath manually, use the Shade plugin to bundle everything into one executable JAR:

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-shade-plugin</artifactId>
  <version>3.4.1</version>
  <executions>
    <execution>
      <phase>package</phase>
      <goals><goal>shade</goal></goals>
    </execution>
  </executions>
</plugin>
```

Then run with:

```bash
java -jar target/payloadJson-1.0-SNAPSHOT-shaded.jar
```

> ✅ No need for `-cp` or managing external libraries separately.

---

## 📊 Example Output

If your JSON is correct and Jackson is properly configured, you should see values printed from the parsed object, such as:

```
Action: MEMBER_REGISTRATION
First Name: John
City: Nairobi
```

---

## 🧠 Why Use Jackson?

* Converts JSON ↔ Java objects easily
* Supports complex structures: nested objects, arrays, generics
* Highly configurable using annotations like `@JsonProperty`, `@JsonIgnore`, etc.
* Integrates well with REST APIs and Spring Boot

### Use Cases

* Reading config files
* Consuming REST APIs
* Logging structured data
* Storing structured records locally

---

## 💡 Tips

* Always match your Java field types to the JSON value types (e.g., `String`, `int`, `double`, `List<>`).
* Use `@JsonProperty` for any field where the JSON name does not match your Java naming convention.
* Structure your project modularly for clarity and maintainability.

---

## 🎉 You're Done!

You now have a complete working Java application that parses a complex JSON structure using Jackson, Maven, and POJO mapping.

Feel free to extend this to support serialization, REST APIs, or validation logic.

Also consider using Lombok for boilerplate elimination or Maven Shade for simplified runtime execution.
