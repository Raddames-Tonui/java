# 📄 Data Sanitization and Excel Export - Java Project

## 🔍 Overview

Process a large, unsanitized CSV dataset (1.1 million+ records) and export valid records into an Excel workbook with gender-based sheet separation. Invalid records are exported into a separate sheet for review.

---

## 🎯 Objective

* **Input**: CSV with fields - `ID Number`, `Name`, `Mobile Number`, `Email Address`, `Gender`
* **Output**: Excel file with:

    * Sheet for **Male**
    * Sheet for **Female**
    * Sheet for **Unknown/Empty Gender**
    * Sheet for **Invalid Records**

---

## 🧰 Java Libraries & Classes You Should Understand

### ✅ 1. Apache POI (Excel Writer)

> **Apache POI** is the de facto Java library to **read/write Microsoft Excel** (XLSX/XLS) files.

#### 🔧 Important Classes:

| Class          | Purpose                                         |
| -------------- | ----------------------------------------------- |
| `Workbook`     | Represents the whole Excel workbook (i.e. file) |
| `Sheet`        | One tab/sheet in the workbook                   |
| `Row`          | A row in a sheet                                |
| `Cell`         | A cell in a row                                 |
| `XSSFWorkbook` | Use this for `.xlsx` files (Excel 2007+)        |

> 📚 [Apache POI Docs](https://poi.apache.org/)

### ✅ 2. BufferedReader / Files API (CSV Reader)

You use `BufferedReader` to **read large text files line-by-line**.

```java
BufferedReader br = Files.newBufferedReader(Paths.get("file.csv"));
```

Efficient and memory-friendly.

Use `.split(",", -1)` for parsing:

```java
String[] fields = line.split(",", -1); // -1 keeps empty values
```

➡️ For advanced use, look into **OpenCSV** or **Apache Commons CSV** for safer parsing.

### ✅ 3. Regular Expressions (Regex)

Used for **validation**:

```java
email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")
```

Learn regex for:

* Email patterns
* Phone number formats
* ID digit patterns

> 📘 You must **master regex** if you're doing any data validation.

---

## 🔎 Field Validation Logic

| Field  | Rule                                                    |
| ------ | ------------------------------------------------------- |
| ID     | Only digits, 6–12 characters                            |
| Mobile | Starts with `07`, `2547`, or valid numeric 10-13 digits |
| Email  | Standard email format with `@` and domain               |

Use `String.matches(...)` for validation.

---

## 🧱 Data Structures

### Member POJO

Encapsulates:

* Fields: `id`, `name`, `mobile`, `email`, `gender`
* Methods: `isValidID()`, `isValidMobile()`, `isValidEmail()`, `isValid()`

### Grouping

You categorize members into:

* `List<Member> maleList`
* `List<Member> femaleList`
* `List<Member> unknownList`
* `List<Member> invalidList`

---

## 🧠 Core Concepts to Master

### 1. Java I/O

* Efficient file handling using `BufferedReader`
* Streaming data line-by-line to handle large files

### 2. Regex

* Field pattern validation
* Dynamic rule enforcement

### 3. Apache POI Hierarchy

* Workbook → Sheet → Row → Cell
* Excel generation and auto-formatting

### 4. Java Collections

* Grouping, sorting, filtering using `List`, `Map`
* Organizing logic with clean code

### 5. POJOs and Clean Design

* Separate model class for validation
* Method encapsulation for reusability

---

## 📌 Advanced Topics

| Topic          | Description                                   |
| -------------- | --------------------------------------------- |
| Spring Batch   | Large-scale batch processing                  |
| OpenCSV        | Safer CSV parsing with escaping, quoting      |
| Streams API    | Functional-style data filtering/mapping       |
| Apache Commons | Handy utilities for validation and string ops |
| Excel Styling  | Add colors, fonts, formatting in POI          |
| Cloud Upload   | Push Excel to S3, GCS, or Azure Blob          |

---

## 🚀 Future Extensions

* Make this a REST API using Spring Boot
* GUI to upload CSV + download Excel (JavaFX/Swing)
* Store sanitized data in DB (MySQL/PostgreSQL)
* Automatically email or archive reports
* Schedule with CRON/Quartz jobs

---

## ✅ Outcome

You will be able to:

* Build data pipelines in pure Java
* Sanitize and categorize massive datasets
* Export professional Excel reports
* Understand and reuse core Java libraries effectively

---
