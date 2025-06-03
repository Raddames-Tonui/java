# XML Validator App - Task 3

## ✅ Overview

This Java application demonstrates how to:

* Pass JVM options (timezone, min/max heap size).
* Handle command-line arguments (`-xml` and `-xsd`).
* Validate an XML file against an XSD schema.
* Package and run a Java app from a runnable JAR.

## 📂 Folder Structure

```
task3-validator/
├── XmlValidatorApp.java
├── manifest.txt
└── validator.jar (after build)
```

## 💻 Requirements

* Java 8+
* VS Code or any text editor

## 📄 1. Java Code: `XmlValidatorApp.java`

This app:

* Prints the current time in a specified timezone.
* Parses command-line arguments `-xml` and `-xsd`.
* Validates the XML against the XSD using Java's built-in XML APIs.

## 📋 2. Manifest File: `manifest.txt`

```text
Main-Class: XmlValidatorApp
```

## ⛏️ 3. Compile and Package

```bash
# Compile the Java source
javac XmlValidatorApp.java

# Create the JAR with a manifest
jar cfm validator.jar manifest.txt XmlValidatorApp.class
```

## ▶️ 4. Run with JVM Options & CLI Args

```bash
java -Xms128m -Xmx512m -Duser.timezone=Africa/Nairobi -jar validator.jar -xml test.xml -xsd test.xsd
```

## ℹ️ Notes

* If either argument is missing or incorrect, the app handles errors gracefully.
* You can pass any timezone using the `-Duser.timezone` JVM option.
* The XML and XSD files must exist in the provided paths.

## 🏆 Output

* Shows system timezone and current time.
* Reports if XML is valid or describes errors.

## 🚀 Example Output

```text
Timezone: Africa/Nairobi
Current Date & Time: Tue, 03 Jun 2025 11:00:00 +0300
✅ XML is valid.
```


