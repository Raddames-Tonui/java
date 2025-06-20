# 📄 FileReadingDemo.java - Java File Reading Showcase

This Java program demonstrates **three different approaches** to read the contents of a file from a provided file path. It's a simple yet powerful utility for learning or reference when dealing with file I/O operations in Java.


## 🧠 Concepts Covered

* File I/O in Java
* Exception Handling
* CLI argument handling
* BufferedReader
* Files API
* Scanner
* Reading JSON, XML, SQL, and text-based file formats


## 📂 File Structure

```
FileReadingDemo.java
```


## 🚀 How to Run

```bash
javac FileReadingDemo.java
java FileReadingDemo <path_to_your_file>
```

> Replace `<path_to_your_file>` with the actual path of the file you want to read.


## 🔍 Techniques Used

### 1. `BufferedReader`

Efficient for **large files**; reads line-by-line to conserve memory.

```java
BufferedReader br = new BufferedReader(new FileReader(filePath));
```

### 2. `Files.readAllLines`

Great for **small or moderately-sized** files; reads all lines into a list.

```java
List<String> lines = Files.readAllLines(Paths.get(filePath));
```

### 3. `Scanner`

Flexible tool for **tokenized reading** or line-based parsing.

```java
Scanner sc = new Scanner(new File(filePath));
```


## 📃 Supported File Types

This program works best with **plain text-based formats**. Here's how it can handle each:

* `.txt`: Fully supported
* `.json`: Can be read as plain text; structure needs to be parsed separately
* `.xml`: Same as JSON; readable but needs XML parsers for structure
* `.sql`: Works well for reading and displaying SQL scripts
* `.pdf`: **Not supported directly**; requires external libraries like Apache PDFBox or iText

To work with `.json` or `.xml`, consider integrating libraries like:

```xml
<!-- JSON -->
com.fasterxml.jackson.core / gson

<!-- XML -->
javax.xml.parsers / org.w3c.dom
```


## 🛠️ Error Handling

Each approach uses `try-catch` to gracefully handle:

* `IOException` (I/O errors)
* `FileNotFoundException` (missing files)


## 📌 Example Output

```
----------Using BufferedReader----------
Line 1 content
Line 2 content
...

 ---------Using Files.readAllLines ------
 Line 1 content
Line 2 content
...

----------- Using Scanner -------
Line 1 content
Line 2 content
...
```


## 📚 Java Version

Tested on **Java 17+**, but compatible with **Java 8+**


## ✨ Use Cases

* Log readers
* Config file processors
* Lightweight data ingestion tools
* JSON/XML readers with extended support
* Learning tool for Java newcomers

