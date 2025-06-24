# 📦 Maven Concepts and Build Lifecycle — Beginner to Pro

This guide simplifies Maven from scratch. If you're starting out, you'll understand every bit. If you're already building projects, you'll gain depth.

---

## 🧠 What is Maven?

Maven is a **build automation and dependency management tool** for Java. It makes it easy to:

* 🧱 Compile your code
* 📦 Package it into a `.jar` or `.war`
* 📚 Download and manage third-party libraries like Jackson, JUnit, Gson

### 🔍 Key Features

* Uses a `pom.xml` to define your project
* Downloads dependencies from Maven Central automatically
* Provides a standard build lifecycle (`compile`, `test`, `package`, etc.)

---

## 🚀 Setting Up Maven

### ✅ Installing Maven on Ubuntu

```bash
sudo apt update
sudo apt install maven -y
```

Check it's working:

```bash
mvn -version
```

### ✅ Creating a Maven Project

```bash
mvn archetype:generate -DgroupId=com.example.helloworld \
  -DartifactId=helloworld \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

What each part means:

* `groupId`: Like your package (e.g. `com.example.app`)
* `artifactId`: The name of the project folder + built file
* `archetypeArtifactId`: Template for creating a Java starter
* `interactiveMode=false`: Skips interactive questions

Creates this structure:

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

Sample `App.java`:

```java
package com.example.helloworld;
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

---

## 📄 Example POM (pom.xml)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example.helloworld</groupId>
  <artifactId>helloworld</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>helloworld</name>
  <url>http://maven.apache.org</url>

  <properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
  </properties>

<!-- Dependencies  -->
  <dependencies>

        <!-- JUnit (optional if no tests) -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
<!-- Run Java App with dependencies 	-->
<!-- exec-maven-plugin:  Lets you run your Java app with Maven easily, using a defined main class -->
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.10.1</version>
        <configuration>
          <source>21</source>
          <target>21</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
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

Run:

```bash
java -cp target/helloworld-1.0-SNAPSHOT.jar com.example.helloworld.App
```

---

## 🆚 Maven vs Gradle vs Ant

| Tool   | Language | Dependency Mgmt | Build Speed | Modern?  |
| ------ | -------- | --------------- | ----------- | -------- |
| Maven  | XML      | ✅ Built-in      | ⚡ Moderate  | ✅ Yes    |
| Gradle | Groovy   | ✅ Built-in      | ⚡⚡ Fast     | ✅ Yes    |
| Ant    | XML      | ❌ Manual        | ⚠️ Slow     | ❌ Legacy |

---

# 📦 Maven Build Lifecycle Deep Dive

### 🔧 `mvn clean install` Explained

* `mvn clean`: Deletes previous build files in `target/`
* `mvn install`: Compiles, tests, packages, and adds to your local Maven repo

Used when you want to:

* Test full build lifecycle
* Share libraries between projects on the same machine

### 💡 When to Use What

| Use Case                     | Command               |
| ---------------------------- | --------------------- |
| Quick compile only           | `mvn compile`         |
| Build and package            | `mvn package`         |
| Full clean and local install | `mvn clean install`   |
| Debug dependency issues      | `mvn dependency:tree` |
| Build with test reports/docs | `mvn site`            |

### ▶️ Running the App

```bash
java -cp target/yourapp.jar:target/dependency/* com.example.MainClass
```

---

## 🚀 `install` vs `copy-dependencies`

| Command                            | Purpose                                            |
| ---------------------------------- | -------------------------------------------------- |
| `mvn install`                      | Installs your JAR into local Maven repo (\~/.m2)   |
| `mvn dependency:copy-dependencies` | Copies dependencies to `target/dependency/` folder |

Use `install` when sharing code between modules. Use `copy-dependencies` when running manually with `java -cp`.

---

## 🏠 Maven Local Repository

Local repo path:

```bash
~/.m2/repository/com/example/yourapp
```

---

## ✨ Maven Shade Plugin (Uber JAR)

To bundle all dependencies into one JAR:

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

Run with:

```bash
java -jar target/your-fat-jar.jar
```

---

## 🌟 Project Lombok

Lombok removes boilerplate:

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.30</version>
  <scope>provided</scope>
</dependency>
```

Annotate your class:

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  private String name;
  private int age;
}
```

Make sure annotation processing is enabled in your IDE.

---

## 🏁 Pro Tips

* `mvn clean verify`: Build and test without installing
* `mvn install -DskipTests`: Build without running tests
* `mvn dependency:tree`: View all nested dependencies
* `mvn dependency:copy-dependencies`: Useful for manual classpaths
* `mvn exec:java -Dexec.mainClass=com.example.Main`: Run app via Maven

---

Now you’re ready to go from a blank folder to production-ready Java builds. Keep this as your cheat sheet and build with confidence! 🚀
