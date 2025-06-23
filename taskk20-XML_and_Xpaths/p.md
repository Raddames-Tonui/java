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

Using DOM in Java lets you say: “Go to `person`, get their `name`, or delete their `age`.”

---

## 🔍 What Is XPath?

XPath is your **GPS** inside the XML tree.
Instead of walking every branch, XPath lets you search directly:

* "Find all `<person>` tags."
* "Get the `<name>` where age is 25."
* "Count how many `<element>` have `field_type='API_BASED'`."

### Common XPath Examples:

| XPath                                | Meaning                                       |
| ------------------------------------ | --------------------------------------------- |
| `//person`                           | Find all `<person>` tags anywhere             |
| `//element[@field_type='API_BASED']` | Find all elements with `field_type=API_BASED` |
| `//element[@use='MANDATORY']`        | Find elements with `use=MANDATORY`            |
| `count(//element)`                   | Count how many `<element>` tags exist         |

---

## 🧠 How Java Loads and Reads XML

### Step-by-Step DOM Setup:

```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new File("file.xml"));
```

### Get Tags:

```java
NodeList list = doc.getElementsByTagName("element");
```

### Read Attributes:

```java
String value = element.getAttribute("field_type");
```

### Update or Remove:

```java
element.setAttribute("use", "OPTIONAL");
parent.removeChild(element);
```

---

## 🌐 How XPath Works in Java

### Setup:

```java
XPath xpath = XPathFactory.newInstance().newXPath();
```

### Run a Query:

```java
String expression = "//element[@field_type='API_BASED']";
NodeList results = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
```

---

## 🎯 Now We’re Ready for the Tasks (No XPath First)

### XML Sample:

```xml
<root>
  <element tag_name="name" field_type="API_BASED" use="MANDATORY" check_duplicate="true" />
  <element tag_name="email" field_type="TABLE_BASED" />
  <element tag_name="phone" use="MANDATORY" />
  <RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE/>
  <MAX_RESTRICTED_ACCESS_NATIONALITIES/>
  <RESTRICTED_ACCESS_NATIONALITIES/>
</root>
```

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

## 🛰️ Same Tasks Using XPath

### a. Get API\_BASED Elements

```xpath
//element[@field_type='API_BASED']
```

```java
NodeList nodes = (NodeList) xpath.evaluate("//element[@field_type='API_BASED']", doc, XPathConstants.NODESET);
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
