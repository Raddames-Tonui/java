# XML Schema Definition (XSD) - Full Documentation

## Overview

**XML Schema Definition (XSD)** is a formal language used to define the structure, content, and data types of XML documents. It acts like a blueprint that ensures an XML document adheres to specific rules. It is used extensively in enterprise systems, system integrations, document validations, and data interchange processes.

---

## Core Concepts in XSD (with Practical Use Cases)

### 1. Namespaces

```xml
xmlns:xs="http://www.w3.org/2001/XMLSchema"
```

* Declares the XML Schema namespace.
* Required for XML parsers to interpret the XSD correctly.
* `xs` is a prefix used to qualify schema elements (like `xs:string`).

### 2. Root Element Definition

```xml
<xs:element name="people">
```

* This defines the root element that your XML must start with.
* Root elements can contain complex or simple types.

### 3. Complex Types

```xml
<xs:complexType>
```

* Used when an element has nested children or attributes.
* Useful for modeling records or structured content.

### 4. Sequences

```xml
<xs:sequence>
```

* Specifies that child elements must appear in the specified order.
* Use `<xs:all>` if order doesn’t matter.

### 5. Element Definitions

```xml
<xs:element name="name" type="xs:string"/>
```

* Defines the tag name and expected data type.
* Built-in types include `xs:string`, `xs:integer`, `xs:boolean`, `xs:date`, etc.

### 6. Occurrence Constraints

```xml
minOccurs="0" maxOccurs="unbounded"
```

* `minOccurs="0"`: Optional element.
* `maxOccurs="unbounded"`: Element can appear multiple times.
* Default values: `minOccurs=1`, `maxOccurs=1`.

### 7. Attributes

```xml
<xs:attribute name="id" type="xs:string" use="required"/>
```

* Adds metadata or identifiers inside an element.
* `use="optional" | "required" | "prohibited"`.

### 8. Simple Type Restrictions

```xml
<xs:restriction base="xs:integer">
  <xs:minInclusive value="0"/>
  <xs:maxInclusive value="120"/>
</xs:restriction>
```

* Constrains scalar values.
* Can be used inside elements or to define custom types.

### 9. Enumerations

```xml
<xs:enumeration value="doctor"/>
```

* Limits content to specific allowed values.
* Useful for role, status, or type fields.

### 10. Default and Fixed Values

```xml
<xs:element name="country" default="Kenya"/>
```

* `default`: Used if the element is missing.
* `fixed`: Must always match the value if provided.

### 11. Choice

```xml
<xs:choice>
  <xs:element name="email" type="xs:string"/>
  <xs:element name="phone" type="xs:string"/>
</xs:choice>
```

* Only one of the elements in the choice may appear.
* Used for either-or logic.

### 12. Named Types

```xml
<xs:complexType name="ContactType">
```

* Reusable type definitions.
* Improves maintainability and reusability across schemas.

### 13. Annotations

```xml
<xs:annotation>
  <xs:documentation>This field stores user email</xs:documentation>
</xs:annotation>
```

* Allows schema documentation.
* Useful for developers, auto-generated docs, or validation tooling.

---

## Decision Reference: When to Use `simpleType`, `complexType`, and Model Groups

| Situation                                        | Use This Element                      | Notes                                                              |
| ------------------------------------------------ | ------------------------------------- | ------------------------------------------------------------------ |
| Element has only text (e.g. `<name>John</name>`) | `xs:simpleType` or `type="xs:string"` | No children or attributes allowed                                  |
| Element has child elements                       | `xs:complexType`                      | Must wrap children in `xs:sequence`, `xs:all`, or `xs:choice`      |
| Element has attributes                           | `xs:complexType`                      | Use `xs:attribute` inside it                                       |
| Child elements must appear in order              | `xs:sequence`                         | Typical structure for records/lists                                |
| Only one of many children allowed                | `xs:choice`                           | Used for alternative content (e.g. phone OR email)                 |
| Children can appear in any order                 | `xs:all`                              | All must appear once or not at all                                 |
| Reuse a type across elements                     | Named `xs:complexType`                | Define separately and reference by name                            |
| Restrict allowed text values                     | `xs:restriction`                      | Often used inside simple types for validation                      |
| Optional elements                                | Add `minOccurs="0"`                   | Allows omission in the XML                                         |
| Repeating elements                               | Add `maxOccurs="unbounded"`           | Allows multiple values (e.g. multiple `<person>` under `<people>`) |



## Enhanced XML Example (`people.xml`)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Root element with reference to the external XSD -->
<people xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="people.xsd">

    <!-- First person record -->
    <person id="P001">
        <name>Raddames</name> <!-- Name element (string) -->
        <age>28</age> <!-- Age element (integer) -->
        <salary>salary: &lt; 20000</salary> <!-- Salary element (optional string) -->
        <career>S/w dev</career> <!-- Career with restricted values -->
        <contact>
            <email>raddames@example.com</email> <!-- Email choice -->
        </contact>
    </person>

    <!-- Second person record -->
    <person id="P002">
        <name>Isaac</name>
        <age>16</age>
        <career>doctor</career>
        <contact>
            <phone>0712345678</phone> <!-- Phone choice -->
        </contact>
    </person>

    <!-- Third person record -->
    <person id="P003">
        <name>Jane</name>
        <age>33</age>
        <career>teacher</career>
        <country /> <!-- Country uses default value "Kenya" -->
    </person>
</people>
```


## Enhanced XSD Example (`people.xsd`)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Schema declaration with default namespace -->
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">

  <!-- Reusable contact type allowing either email or phone -->
  <xs:complexType name="ContactType">
    <xs:choice>
      <xs:element name="email" type="xs:string"/>
      <xs:element name="phone" type="xs:string"/>
    </xs:choice>
  </xs:complexType>

  <!-- Root people element -->
  <xs:element name="people">
    <xs:complexType>
      <xs:sequence>

        <!-- Repeating person element -->
        <xs:element name="person" maxOccurs="unbounded">
          <xs:complexType>
            <xs:sequence>

              <!-- Name element -->
              <xs:element name="name" type="xs:string"/>

              <!-- Age with numeric restrictions -->
              <xs:element name="age">
                <xs:simpleType>
                  <xs:restriction base="xs:integer">
                    <xs:minInclusive value="0"/>
                    <xs:maxInclusive value="120"/>
                  </xs:restriction>
                </xs:simpleType>
              </xs:element>

              <!-- Optional salary -->
              <xs:element name="salary" type="xs:string" minOccurs="0"/>

              <!-- Career with limited enumerated values -->
              <xs:element name="career">
                <xs:simpleType>
                  <xs:restriction base="xs:string">
                    <xs:enumeration value="S/w dev"/>
                    <xs:enumeration value="doctor"/>
                    <xs:enumeration value="teacher"/>
                  </xs:restriction>
                </xs:simpleType>
              </xs:element>

              <!-- Optional contact (choice between email or phone) -->
              <xs:element name="contact" type="ContactType" minOccurs="0"/>

              <!-- Optional country with default value -->
              <xs:element name="country" type="xs:string" minOccurs="0" default="Kenya"/>

            </xs:sequence>
            <!-- Required ID attribute -->
            <xs:attribute name="id" type="xs:string" use="required"/>
          </xs:complexType>
        </xs:element>

      </xs:sequence>
    </xs:complexType>
  </xs:element>

</xs:schema>
```


## Common Validation Error: `cvc-elt.1.a`

**Error:** `Cannot find the declaration of element 'people'`

### Root Causes:

* Missing or incorrect `xsi:noNamespaceSchemaLocation`.
* File name mismatch: `people.xsd` must match exactly.
* XSD not in the same folder as XML.
* The root element `people` is not defined in XSD.
* XSD contains an unexpected namespace (like `targetNamespace`).

### How to Fix:

1. Verify `people.xsd` exists in the same directory.
2. Double-check XML root element is `<people>`.
3. Ensure XSD starts with:

```xml
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
```

4. Do not use `targetNamespace` unless using XML namespaces explicitly.
5. Use an XML-aware IDE (VS Code with XML extension, IntelliJ, Eclipse).


## Summary

XSD is a powerful and essential schema language that lets you define precise rules for XML documents. It enables type safety, structured content, optional and repeatable elements, enumerations, and validations. Understanding XSD is crucial for backend developers, integration engineers, data modelers, and anyone working with XML-based systems.
