# Task 5: Interfaces - Java Interface Implementation with JSON and XML Processing

## Objective

This task was designed to test the understanding of **Java interfaces**, **object-oriented programming**, and **data parsing** using external libraries for structured formats like JSON and XML.

## Key Concepts Tested

* Interface creation and implementation
* Polymorphism in Java
* Working with third-party libraries (org.json)
* XML parsing using native Java APIs
* Command-line compilation and execution with external libraries

## Task Breakdown

1. **Create an Interface `DataProcessor`**

   * Contains one method: `void process(String data)`

2. **Create Two Implementing Classes:**

   * `JsonProcessor`: Parses a JSON string and prints each key-value pair
   * `XMLProcessor`: Parses an XML string and prints each element and its value

3. **Create a Test Class `ProcessorTest`**

   * Instantiates each processor
   * Provides sample JSON and XML strings
   * Calls the `process()` method to validate implementation

## Sample Output

```
==== JSON Output ====
Processing JSON data:
Key: city, Value: Nairobi
Key: name, Value: Alice
Key: age, Value: 28

==== XML Output ====
Processing XML:
Element: person
  Element: name
    Value: Alice
  Element: age
    Value: 28
  Element: city
    Value: Nairobi
```

## Technical Notes

* JSON parsing used `org.json.JSONObject`, requiring external JAR support
* XML parsing used Java's built-in DOM parser (`javax.xml.parsers.DocumentBuilder`)
* Command-line compilation included classpath reference to the JSON JAR:

  ```
  javac -d out -cp .:lib/json-20240303.jar *.java
  ```
  `-d out`: outputs compiled .class files into the out directory, keeping source and binaries separate

  `-cp .:lib/json-20240303.jar`: sets the classpath to current directory and JSON library JAR (use ; on Windows)

  `*.java` : compiles all Java source files in the directory

  ```
  java -cp out:lib/json-20240303.jar ProcessorTest
  ```
  Runs ``ProcessorTest`` from compiled classes in out directory

  Includes external JSON library in classpath for runtime access
  
## Conclusion

This task demonstrates effective use of interfaces to enforce a contract across multiple implementations, and how Java supports working with structured data formats using both native and third-party tools. It's foundational for modular and extensible application design.
