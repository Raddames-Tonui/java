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
`pomxml`


 `App.java`: This is the entry to the system.

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

## 🆚 Maven vs Gradle vs Ant (Summary)

| Tool   | Language | Dependency Mgmt | Build Speed | Modern?  |
| ------ | -------- | --------------- | ----------- | -------- |
| Maven  | XML      | ✅ Built-in      | ⚡ Moderate  | ✅ Yes    |
| Gradle | Groovy   | ✅ Built-in      | ⚡⚡ Fast     | ✅ Yes    |
| Ant    | XML      | ❌ Manual        | ⚠️ Slow     | ❌ Legacy |



