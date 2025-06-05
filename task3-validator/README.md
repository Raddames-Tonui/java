# XML Validator Java Application

## Step 1: Setup & Environment Preparation

### 1. Create Project Folder Structure

```
task3-validator/
├── src/
│   └── Main.java
├── out/
│   └── Main.class
├── resources/
│   └── test.xml, test.xsd
└── manifest.txt
```

* `src/` holds the Java source code.
* `resources/` stores sample XML and XSD files.

### 2. Initialize Main.java

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("XmlValidatorApp started...");
    }
}
```

### 3. Compile & Run Basic Setup

```bash
cd task3-validator                    # Navigate to project directory
javac -d out src/Main.java             # Compile Main.java and place class files in 'out/'
java -cp out Main                      # Run the compiled Main class using classpath 'out'
```

Output:

```
XmlValidatorApp started...
```


## Step 2: Handling JVM Options & Printing Date/Time

### Goal:

* Set timezone using `-Duser.timezone`
* Configure JVM memory
* Print date/time based on timezone

### Updated Main.java

```java
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        String timezone = TimeZone.getDefault().getID();
        System.out.println("Current JVM Timezone: " + timezone);

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current Date and Time: " + now.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }
}
```

### Run with JVM Options

```bash
java -Duser.timezone=Asia/Nairobi -Xms128m -Xmx512m -cp out Main
```

* `-Duser.timezone=Asia/Nairobi`: sets JVM timezone
* `-Xms128m`: initial heap memory size
* `-Xmx512m`: maximum heap memory size
* `-cp out`: sets classpath to compiled output folder


## Step 3: Parse Command-Line Arguments (-xml and -xsd)

### Goal:

Accept command-line inputs:

```bash
java -jar XmlValidatorApp.jar -xml /path/to/file.xml -xsd /path/to/schema.xsd
```

### Updated Main.java

```java
import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        // Timezone info
        String timezone = TimeZone.getDefault().getID();
        System.out.println("Current JVM Timezone: " + timezone);
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current Date and Time: " + now.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        // Argument parsing
        String xmlPath = null;
        String xsdPath = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-xml":
                    if (i + 1 < args.length) xmlPath = args[++i];
                    break;
                case "-xsd":
                    if (i + 1 < args.length) xsdPath = args[++i];
                    break;
                default:
                    System.out.println("Unknown argument: " + args[i]);
            }
        }

        // Validation
        if (xmlPath == null || xsdPath == null) {
            System.err.println("Usage: java -jar XmlValidatorApp.jar -xml path/to/file.xml -xsd path/to/schema.xsd");
            return;
        }

        File xmlFile = new File(xmlPath);
        File xsdFile = new File(xsdPath);

        if (!xmlFile.exists() || !xsdFile.exists()) {
            System.err.println("One or both file paths do not exist.");
            return;
        }

        System.out.println("XML File: " + xmlFile.getAbsolutePath());
        System.out.println("XSD File: " + xsdFile.getAbsolutePath());
    }
}
```


## Step 4: Validate XML Using XSD

### Add XML Validation Logic

```java
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.IOException;

...
try {
    // Create SchemaFactory instance for W3C XML Schema
    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    // Load the schema from the XSD file
    Schema schema = factory.newSchema(xsdFile);

    // Create Validator and validate the XML
    Validator validator = schema.newValidator();
    validator.validate(new StreamSource(xmlFile));

    System.out.println("\u2705 XML is valid according to the XSD schema.");
} catch (SAXException e) {
    System.err.println("\u274C XML is NOT valid.");
    System.err.println("Reason: " + e.getMessage());
} catch (IOException e) {
    System.err.println("\u274C Error reading files: " + e.getMessage());
}
```

### Sample Files for Testing

**test.xsd**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="greeting" type="xs:string"/>
</xs:schema>
```

**test.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<greeting>Hello, world!</greeting>
```

### Run the Validator

```bash
java -cp out Main -xml test.xml -xsd test.xsd
```

Output:

```
✅ XML is valid according to the XSD schema.
```


## Step 5: Package into Runnable JAR

### 1. Create manifest.txt

```
Main-Class: Main
```

*Make sure there's a newline at the end of the file.*

### 2. Compile Java File

```bash
javac Main.java                             # Compile the Main.java source file
```

### 3. Package the JAR

```bash
jar cfm validator.jar manifest.txt Main.class   # Create a JAR file with manifest and compiled class
```

### 4. Run the JAR

```bash
java -jar validator.jar -xml test.xml -xsd test.xsd
```

Output:

```
✅ XML is valid according to the XSD schema.
```


## Done!

You're now able to compile, run, validate, and package your XML validation Java program.
