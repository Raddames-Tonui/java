# XML & XPath for Beginners to Professionals – The Complete Guide

## 🌳 What Is XML? (Explained Like You're 5)

XML is like LEGO for data.
You use **tags** to wrap information and build meaningful structures.

### Example:

```xml
<person>
  <name>James</name>
  <age>25</age>
</person>
```

Here:

* `<person>` is a **parent** tag.
* `<name>` and `<age>` are **child** tags.
* Values like `James` are the **data**.

XML is **self-descriptive**. It tells you what the data means.

---

## 🌴 What Is the DOM (Document Object Model)?

Think of XML as a **tree**.

* The root tag is the **trunk**.
* Nested tags are **branches**.
* Data is the **leaves**.

Java loads this tree using the DOM so you can:

* Walk through every branch.
* Inspect attributes and values.
* Add/remove/update branches.

### Visual Structure

```
<root>
 └── <person>
      ├── <name>James</name>
      └── <age>25</age>
```

### Java DOM Class Tree

```
javax.xml.parsers.DocumentBuilderFactory
├── DocumentBuilder
    └── org.w3c.dom.Document (represents the XML tree)
        ├── Element (represents tags like <person>)
        │   ├── getAttribute(), setAttribute(), getTagName()
        ├── NodeList (list of child nodes)
        └── Node (base class of everything)
```

---

## 🔍 What Is XPath?

XPath is your **GPS** inside the XML tree.
Instead of walking every branch, XPath lets you search directly:

* "Find all `<person>` tags."
* "Get the `<name>` where age is 25."
* "Count how many `<element>` have `field_type='API_BASED'`."

### Common XPath Examples:

| XPath                                | Meaning                                                         |
| ------------------------------------ | --------------------------------------------------------------- |
| `//person`                           | Find all `<person>` tags anywhere in the document               |
| `/root/person/name`                  | Select `<name>` directly under `<person>` under `<root>`        |
| `//element[@field_type='API_BASED']` | Find all `<element>` tags with attribute `field_type=API_BASED` |
| `//element[@use='MANDATORY']`        | Find all `<element>` tags where `use=MANDATORY`                 |
| `count(//element)`                   | Count how many `<element>` tags exist                           |
| `//element[@check_duplicate='true']` | Find all duplicate-check-enabled fields                         |
| `//element[@*]`                      | Find all elements that have at least one attribute              |
| `//*`                                | Select every node in the document                               |
| `//element[position()=1]`            | Select the first `<element>` in the document                    |
| `//element[last()]`                  | Select the last `<element>` in the document                     |
| `//element[@name='username']`        | Get the element with attribute `name='username'`                |

---

## 🧠 How Java Loads and Reads XML

### Step-by-Step DOM Setup:

**Step 1**: Load the XML document

```java
// Create a factory that produces document builders
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

// Use the factory to create a DocumentBuilder object
DocumentBuilder builder = factory.newDocumentBuilder();

// Parse the XML file and store it as a DOM Document object
Document doc = builder.parse(new File("file.xml"));
```

* `DocumentBuilderFactory`: Abstract factory class to create a document builder instance.
* `DocumentBuilder`: Parses XML and creates a DOM `Document` tree.
* `Document`: The in-memory representation of the whole XML structure.

**Step 2**: Get all elements by tag name

```java
// Get a list of all elements in the document with the tag name "element"
NodeList list = doc.getElementsByTagName("element");
```

* `NodeList`: A collection of nodes returned by methods like `getElementsByTagName()`.

**Step 3**: Access or update attributes

```java
// Get the first element from the list and cast it to Element
Element element = (Element) list.item(0);

// Read an attribute named "field_type"
String value = element.getAttribute("field_type");

// Set or change the attribute "use" to "OPTIONAL"
element.setAttribute("use", "OPTIONAL");
```

* `Element`: Represents an XML tag. You can read/set its attributes.

**Step 4**: Remove an element

```java
// Get the parent node of the element
Node parent = element.getParentNode();

// Remove the child element from its parent
parent.removeChild(element);
```

* This physically deletes a tag from the XML tree in memory.

---

## 🌐 How XPath Works in Java

**Step 1**: Create XPath engine

```java
// Create an XPath engine to evaluate expressions
XPath xpath = XPathFactory.newInstance().newXPath();
```

* `XPathFactory`: Used to create an XPath object.
* `XPath`: Executes expressions like search queries on XML.

**Step 2**: Compile and evaluate XPath expressions

```java
// Define your XPath expression
String expression = "//element[@field_type='API_BASED']";

// Evaluate the expression and return a NodeList result
NodeList results = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
```

* `XPathConstants.NODESET`: Return type that tells XPath to return a list of matching nodes.

---

## 🎯 Tasks Using DOM (No XPath)

### a. Print `tag_name` for All `API_BASED` Elements

```java
NodeList list = doc.getElementsByTagName("element");
for (int i = 0; i < list.getLength(); i++) {
    Element el = (Element) list.item(i);
    if ("API_BASED".equals(el.getAttribute("field_type"))) {
        System.out.println(el.getAttribute("tag_name"));
    }
}
```

### b. Count `TABLE_BASED` Elements

```java
int count = 0;
for (int i = 0; i < list.getLength(); i++) {
    Element el = (Element) list.item(i);
    if ("TABLE_BASED".equals(el.getAttribute("field_type"))) {
        count++;
    }
}
System.out.println("Count: " + count);
```

### c. Print Fields to Check for Duplicates

```java
for (int i = 0; i < list.getLength(); i++) {
    Element el = (Element) list.item(i);
    if ("true".equals(el.getAttribute("check_duplicate"))) {
        System.out.println("Duplicate Field: " + el.getAttribute("tag_name"));
    }
}
```

### d. Remove Specific Tags

```java
String[] tagsToRemove = {
    "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
    "MAX_RESTRICTED_ACCESS_NATIONALITIES",
    "RESTRICTED_ACCESS_NATIONALITIES"
};

for (String tag : tagsToRemove) {
    NodeList nodes = doc.getElementsByTagName(tag);
    for (int i = 0; i < nodes.getLength(); i++) {
        Node node = nodes.item(i);
        node.getParentNode().removeChild(node);
    }
}
```

### e. Change All `use=MANDATORY` to `OPTIONAL`

```java
for (int i = 0; i < list.getLength(); i++) {
    Element el = (Element) list.item(i);
    if ("MANDATORY".equals(el.getAttribute("use"))) {
        el.setAttribute("use", "OPTIONAL");
    }
}
```

---

## 🎯 Tasks Using XPath

### a. Get API\_BASED Elements

```xpath
//element[@field_type='API_BASED']
```

### b. Count TABLE\_BASED Elements

```xpath
count(//element[@field_type='TABLE_BASED'])
```

### c. Check Duplicates

```xpath
//element[@check_duplicate='true']
```

### d. Remove 3 Tags

```xpath
//RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE | //MAX_RESTRICTED_ACCESS_NATIONALITIES | //RESTRICTED_ACCESS_NATIONALITIES
```

### e. Change All Mandatory Fields

```xpath
//element[@use='MANDATORY']
```

---

## 🏁 Final Summary Table

| Task               | DOM (Manual Walk)             | XPath Shortcut                                |        |       |
| ------------------ | ----------------------------- | --------------------------------------------- | ------ | ----- |
| Get API Fields     | `.getAttribute("field_type")` | `//element[@field_type='API_BASED']`          |        |       |
| Count Table Fields | Counter + Loop                | `count(//element[@field_type='TABLE_BASED'])` |        |       |
| Find Duplicates    | Check `check_duplicate`       | `//element[@check_duplicate='true']`          |        |       |
| Remove Tags        | Use `getElementsByTagName`    | \`//TAG1                                      | //TAG2 | ...\` |
| Change use         | `.setAttribute("use")`        | `//element[@use='MANDATORY']`                 |        |       |

You're now fully ready for any XML parsing challenge in Java—from baby steps to pro tools like XPath. 🎓
