# 📦 Maven Concepts 

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

### ✅ Installing Maven in Ubuntu 

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

* `mvn archetype:generate`
This tells Maven to generate a new project based on an archetype (template).

* `-DgroupId=com.example.helloworld`
Sets the group ID for your project — this is like the project's root namespace or package.
In Java terms, it will be your base package: com.example.helloworld.

* `-DartifactId=helloworld`
This is the name of your project directory and also the name of the JAR (or WAR) file that Maven will eventually build.
It becomes the main identifier for your app.

* `-DarchetypeArtifactId=maven-archetype-quickstart`
Specifies the Maven template (archetype) to use.
maven-archetype-quickstart is a commonly used archetype for creating simple Java applications with a sample class and unit test.

* `-DinteractiveMode=false`
Runs the command in non-interactive mode, meaning it won’t prompt you for inputs.
Instead, it uses only the values you provide in the command line.

This creates a structure:

```
helloworld/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               └── helloworld/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── example/
                    └── helloworld/
                        └── AppTest.java

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
Gson is a Java library that facilitates the conversion of Java objects to JSON and vice versa. It is available within the com.google.code.gson namespace and is commonly used in Java projects. You can incorporate Gson into your project by including it as a dependency in your Maven POM file. This allows you to easily serialize Java objects into JSON strings and deserialize JSON strings back into Java objects, simplifying data exchange and manipulation in your applications.
## Running the Project with Maven

This guide explains how to set up and run your Java project using Maven, including project creation, configuration, and execution.

### 1. Create a Maven Project

Use the Maven archetype plugin to generate a new project:

```bash
mvn archetype:generate \
  -DgroupId=com.example.task7gson \
  -DartifactId=task7gson \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

This creates a basic Maven project structure with the specified group ID and artifact ID.

### 2. Configure Java Version in `pom.xml`

Specify the Java version you want to use (e.g., Java 21) in your `pom.xml` file:

```xml
<properties>
  <maven.compiler.source>21</maven.compiler.source>
  <maven.compiler.target>21</maven.compiler.target>
</properties>
```

### 3. Add Dependencies and Plugins

Ensure the `pom.xml` includes necessary dependencies (e.g., Gson for JSON serialization/deserialization) and the `exec-maven-plugin` to allow running the project:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>exec-maven-plugin</artifactId>
      <version>3.1.0</version>
      <configuration>
        <mainClass>com.example.task7gson.App</mainClass>
      </configuration>
    </plugin>
  </plugins>
</build>
```

### 4. Build the Project

Compile your project using:

```bash
mvn clean compile exec:java
```

* This cleans previous build artifacts and compiles the source code.

* Run App with all dependencies like Gson included

### 5. Run the Project

Execute the main class using the Maven Exec Plugin:

```bash
mvn exec:java
```

### 📦 Sample JSON Output:

```json
📦 JSON Output:
[{"bookID":3,"bookName":"Java Concurrency in Practice","bookAuthor":"Brian Goetz","numberOfCopies":4,"datePublished":"May 19, 2006"},{"bookID":4,"bookName":"Spring in Action","bookAuthor":"Craig Walls","numberOfCopies":6,"datePublished":"Feb 15, 2021"},{"bookID":5,"bookName":"Head First Java","bookAuthor":"Kathy Sierra","numberOfCopies":8,"datePublished":"Jun 10, 2005"}]

```

### 📥 Deserialized Books:

```
Book{bookID=3, bookName='Java Concurrency in Practice', bookAuthor='Brian Goetz', numberOfCopies=4, datePublished=2006-05-19}
Book{bookID=4, bookName='Spring in Action', bookAuthor='Craig Walls', numberOfCopies=6, datePublished=2021-02-15}
Book{bookID=5, bookName='Head First Java', bookAuthor='Kathy Sierra', numberOfCopies=8, datePublished=2005-06-10}
```

With these steps, you can easily compile and run your Java Maven project, with Gson handling JSON serialization and deserialization.


## 🆚 Maven vs Gradle vs Ant (Summary)

| Tool   | Language | Dependency Mgmt | Build Speed | Modern?  |
| ------ | -------- | --------------- | ----------- | -------- |
| Maven  | XML      | ✅ Built-in      | ⚡ Moderate  | ✅ Yes    |
| Gradle | Groovy   | ✅ Built-in      | ⚡⚡ Fast     | ✅ Yes    |
| Ant    | XML      | ❌ Manual        | ⚠️ Slow     | ❌ Legacy |

---

