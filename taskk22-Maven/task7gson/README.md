
# 📚 Task : Add Gson Library + Convert List to JSON
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
```xml
<!-- Root element defining a Maven project -->
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

  <!-- Specifies the POM model version -->
  <modelVersion>4.0.0</modelVersion>

  <!-- Group ID: like a package name, usually in reverse domain style -->
  <groupId>com.example.task7gson</groupId>

  <!-- Artifact ID: the name of the built project -->
  <artifactId>task7gson</artifactId>

  <!-- Type of packaging (jar/war/etc) -->
  <packaging>jar</packaging>

  <!-- Version of your application -->
  <version>1.0-SNAPSHOT</version>

  <!-- Optional: Human-readable project name -->
  <name>task7gson</name>

  <!-- Optional: Project homepage -->
  <url>http://maven.apache.org</url>

  <!-- Set Java source and target compatibility version -->
  <properties>
    <!-- Source code version -->
    <maven.compiler.source>21</maven.compiler.source>
    <!-- Bytecode version -->
    <maven.compiler.target>21</maven.compiler.target>
  </properties>

  <!-- Dependencies block: lists external libraries to include -->
  <dependencies>
    <!-- JUnit: Used for unit testing (only needed in test scope) -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope> <!-- Only included during testing -->
    </dependency>

    <!-- Gson: Google's library for JSON serialization and deserialization -->
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.11.0</version>
    </dependency>
  </dependencies>

  <!-- Build section for custom build behavior -->
  <build>
    <!-- Plugins used during build phase -->
    <plugins>
      <plugin>
        <!-- Plugin to run Java applications via Maven -->
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>3.1.0</version>
        <configuration>
          <!-- The fully-qualified name of the class with the main() method -->
          <mainClass>com.example.task7gson.App</mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
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



