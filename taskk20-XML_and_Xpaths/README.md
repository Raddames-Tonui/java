# XML and XSD Concepts in Java

## What Are XML and XSD?

### **XML (eXtensible Markup Language)**

Imagine you want to describe your favorite book to a computer. You can say:

```xml
<Book>
  <Title>Harry Potter</Title>
  <Author>J.K. Rowling</Author>
</Book>
```

XML is a way to wrap information in tags, just like labeling boxes. Each tag describes what the content means. The computer can then read these labels to understand what you're talking about.

#### Documentation

* `.xml` files are plain text files with a `.xml` extension.
* Tags must open and close (`<tag>value</tag>`).
* Attributes can be used inside opening tags: `<Book genre="fantasy">`
* Nesting is allowed: elements can have child elements.

#### XML Pros:

* Human and machine readable.
* Platform-independent.
* Works well for data exchange.

### **XSD (XML Schema Definition)**

Now, let’s say you want every book in your library to follow a strict format: it must have a `Title`, `Author`, and an optional `Publisher`. XSD is a rulebook that tells your XML what it should look like.

Think of XSD as a contract or checklist:

```xsd
<xs:element name="Book">
  <xs:complexType>
    <xs:sequence>
      <xs:element name="Title" type="xs:string"/>
      <xs:element name="Author" type="xs:string"/>
    </xs:sequence>
  </xs:complexType>
</xs:element>
```

#### Documentation

* `.xsd` files are XML documents that define other XML documents.
* They use the `xs:` namespace prefix for schema elements.
* Can specify types (`xs:string`, `xs:int`, `xs:boolean`, etc).
* Use `<xs:sequence>` for ordered elements.
* Use `<xs:attribute>` to define required/optional attributes.

#### Benefits of XSD:

* Makes your XML consistent.
* Validates correctness.
* Helps during data transmission (e.g., bank transactions, APIs).

---

## Concept Tree

```
Java Object
├── javax.xml (JAXP)
│   ├── DocumentBuilder
│   ├── Document
│   ├── Node / Element / Attr
│   ├── NodeList
│   └── XPath / XPathExpression
├── org.w3c.dom
│   ├── Element
│   ├── Attr
│   └── Document
└── javax.xml.validation
    ├── Schema
    └── Validator
```

---

## Core Concepts

### DOM (Document Object Model)

DOM is Java’s way of loading XML into memory so it can walk through it like a tree.

Imagine XML is a tree:

* The document is the tree.
* The tags are branches.
* The values are fruits.

You can climb the tree, change fruits, or cut branches (i.e., edit the XML).

### XPath (The GPS for XML)

XPath is a smart search engine inside your XML tree. Instead of checking every branch manually, you ask XPath:

* "Find me all fruits with color='red'."
* "Count how many apples I have."
* "Get me the second banana."

---

## Use Cases

* **APIs:** Payment, banking, insurance forms.
* **Configs:** App settings (e.g., Spring Boot XML beans).
* **Web:** SVG graphics, RSS feeds.
* **Validation:** Making sure XML data is properly structured.

---

## DOM Parsing in Java – Baby Steps

```java
// Step 1: Load XML into Java memory
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
DocumentBuilder builder = factory.newDocumentBuilder();
Document doc = builder.parse(new File("file.xml"));

// Step 2: Get elements by tag name
NodeList list = doc.getElementsByTagName("tag_name");
Element element = (Element) list.item(i);

// Step 3: Read or update attributes
String attr = element.getAttribute("field_type");
element.setAttribute("use", "OPTIONAL");

// Step 4: Remove an element
Node parent = element.getParentNode();
parent.removeChild(element);
```

---

## XPath in Java – Simple Search

```java
XPathFactory xPathFactory = XPathFactory.newInstance();
XPath xpath = xPathFactory.newXPath();
String expression = "//element[@field_type='API_BASED']";
NodeList nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
```

* Just like Google Search, but for your XML.

---

# Task 1: signatories\_model\_info.xml

## Without XPath

### a. Get all elements/attributes with `field_type=API_BASED` and list their `tag_name`

* Loop through elements using DOM.
* If `field_type="API_BASED"`, extract `tag_name` attribute.

### b. Count elements with `field_type=TABLE_BASED`

* Same as above, just increment a counter.

### c. Find elements with `check_duplicate="true"` and extract associated fields.

### d. Remove:

```xml
<RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE>
<MAX_RESTRICTED_ACCESS_NATIONALITIES>
<RESTRICTED_ACCESS_NATIONALITIES>
```

* Use `getElementsByTagName()` and remove them from parent.

### e. Change `use='MANDATORY'` → `use='OPTIONAL'`

* Loop and replace value using `setAttribute()`.

## With XPath

```xpath
//element[@field_type='API_BASED']
count(//element[@field_type='TABLE_BASED'])
//element[@check_duplicate='true']
//RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE | //MAX_RESTRICTED_ACCESS_NATIONALITIES | //RESTRICTED_ACCESS_NATIONALITIES
//element[@use='MANDATORY']
```

---

# Task 2: pac008\_sample.xml

## How to Write the XSD

* Start with `<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">`
* Define `<xs:element>`
* Nest elements using `<xs:sequence>`

### Sample Snippet

```xml
<xs:element name="OrgnlTxRef">
  <xs:complexType>
    <xs:sequence>
      <xs:element name="CdtrAcct">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="Id">
              <xs:complexType>
                <xs:sequence>
                  <xs:element name="Othr">
                    <xs:complexType>
                      <xs:sequence>
                        <xs:element name="Id" type="xs:string" />
                      </xs:sequence>
                    </xs:complexType>
                  </xs:element>
                </xs:sequence>
              </xs:complexType>
            </xs:element>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    </xs:sequence>
  </xs:complexType>
</xs:element>
```

## XPath to Read Values

```xpath
//OrgnlTxRef/CdtrAcct/Id/Othr/Id/text()
```

---

# Summary (As Simple As Possible)

* XML = a smart way to label and store data.
* XSD = the rulebook that tells what the XML should look like.
* Java reads XML using DOM (tree walking) and XPath (smart search).
* XPath makes it easy to find, count, or change anything in XML.
* You can update, delete, or validate your XML to ensure correctness.

If XML is a document, DOM is the editor, XPath is the search tool, and XSD is the grammar guide.

You're ready to parse, query, and validate XML like a pro!
