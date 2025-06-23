# 📦 Maven Concepts and Task 22 Breakdown

## 🧠 What is Maven?

Maven is a **build automation and dependency management tool** for Java projects. It simplifies how you:

* **Compile** your code
* **Package** it into a `.jar` or `.war`
* **Manage third-party libraries** (like Gson, JUnit, Log4j)

### 🔍 Key Features

* Uses a `pom.xml` to declare project structure and dependencies
* Fetches libraries from Maven Central
* Offers a clean **build lifecycle** (`clean`, `compile`, `test`, `package`...)

---

## 🚀 Setting Up Maven

### ✅ Installing Maven (on Ubuntu container)

```bash
sudo apt update
sudo apt install maven -y
```

Check version:

```bash
mvn -version
```

### ✅ Creating a Simple Maven Project

```bash
mvn archetype:generate -DgroupId=com.example.helloworld \
  -DartifactId=helloworld \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

This creates a structure:

```
helloworld/
├── pom.xml
└── src/
    ├── main/java/com/example/helloworld/App.java
    └── test/java/com/example/helloworld/AppTest.java
```

Edit `App.java`:

```java
package com.example.helloworld;
public class App {
    public static void main(String[] args) {
        System.out.println("Hello, Maven World!");
    }
}
```

---

## ⚙️ Build and Run with Maven

### 🔨 Compile Code

```bash
mvn compile
```

### 📦 Package to JAR

```bash
mvn package
```

Run the jar:

```bash
java -cp target/helloworld-1.0-SNAPSHOT.jar com.example.helloworld.App
```

---

## 📚 Task: Add Gson Library + Convert List to JSON

### ✅ Step 1: Add Gson Dependency in `pom.xml`

Inside `<dependencies>`:

```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.11.0</version>
</dependency>
```

### ✅ Step 2: Convert Java List to JSON and Back

Edit `App.java`:

```java
package com.example.helloworld;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();

        List<String> items = Arrays.asList("apple", "banana", "mango");
        String json = gson.toJson(items);
        System.out.println("JSON: " + json);

        List<?> fromJson = gson.fromJson(json, List.class);
        System.out.println("List: " + fromJson);
    }
}
```

### ✅ Rebuild and Run

```bash
mvn clean compile
mvn package
java -cp target/helloworld-1.0-SNAPSHOT.jar com.example.helloworld.App
```

---

## 🆚 Maven vs Gradle vs Ant (Summary)

| Tool   | Language | Dependency Mgmt | Build Speed | Modern?  |
| ------ | -------- | --------------- | ----------- | -------- |
| Maven  | XML      | ✅ Built-in      | ⚡ Moderate  | ✅ Yes    |
| Gradle | Groovy   | ✅ Built-in      | ⚡⚡ Fast     | ✅ Yes    |
| Ant    | XML      | ❌ Manual        | ⚠️ Slow     | ❌ Legacy |

---

## ✅ Summary for Task 22

* [x] Installed Maven ✅
* [x] Created Hello World Maven Project ✅
* [x] Built runnable JAR ✅
* [x] Added Gson v2.11 ✅
* [x] Converted List ↔ JSON ✅

