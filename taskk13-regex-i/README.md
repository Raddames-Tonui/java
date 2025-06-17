# Java Regular Expressions (Regex) - README

## Overview

Regular Expressions (Regex) are powerful tools for pattern matching and text processing. In Java, the `java.util.regex` package provides full support for regex-based operations such as validation, searching, extraction, and more.

This README provides:

* A clear explanation of regex concepts in Java
* Detailed use cases
* Code examples with comments
* A practical cheatsheet for quick reference

---

## Core Concepts

### Quantifiers (Repetition)

| Symbol  | Meaning           | Example  | Matches            |
| ------- | ----------------- | -------- | ------------------ |
| `*`     | 0 or more         | `a*`     | `"", "a", "aa"`    |
| `+`     | 1 or more         | `a+`     | `"a", "aa"`        |
| `?`     | 0 or 1 (optional) | `a?`     | `"", "a"`          |
| `{n}`   | Exactly n         | `a{3}`   | `"aaa"`            |
| `{n,}`  | n or more         | `a{2,}`  | `"aa", "aaa"...`   |
| `{n,m}` | Between n and m   | `a{1,3}` | `"a", "aa", "aaa"` |

### Character Classes

| Syntax     | Meaning            | Example  |
| ---------- | ------------------ | -------- |
| `[abc]`    | a, b, or c         | `[cb]at` |
| `[^abc]`   | not a, b, or c     | `[^a-z]` |
| `[a-z]`    | lowercase a to z   |          |
| `[0-9]`    | digits 0 through 9 |          |
| `[a-zA-Z]` | all letters        |          |

### Predefined Classes

| Symbol | Meaning                     |
| ------ | --------------------------- |
| `.`    | Any character (not newline) |
| `\d`   | Digit `[0-9]`               |
| `\D`   | Non-digit                   |
| `\s`   | Whitespace                  |
| `\S`   | Non-whitespace              |
| `\w`   | Word character              |
| `\W`   | Non-word character          |

### Anchors

| Symbol | Meaning           |
| ------ | ----------------- |
| `^`    | Start of string   |
| `$`    | End of string     |
| `\b`   | Word boundary     |
| `\B`   | Non-word boundary |

### Groups and Alternation

| Syntax    | Meaning             |        |
| --------- | ------------------- | ------ |
| `(abc)`   | Capturing group     |        |
| `(?:abc)` | Non-capturing group |        |
| \`(a      | b)\`                | a or b |
| `(?=abc)` | Positive lookahead  |        |
| `(?!abc)` | Negative lookahead  |        |

---

## Use Cases in Java

### 1. Input Validation

```java
String name = "John Doe";
boolean isValid = name.matches("^[A-Za-z ]+$"); // Letters and spaces only
```

### 2. Email Validation

```java
String email = "test@example.com";
boolean isEmail = email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
```

### 3. Phone Number Format

```java
String phone = "+254712345678";
boolean isPhone = phone.matches("^(254|\\+254|07|01)[0-9]{8}$");
```

### 4. File Parsing (e.g., Extract private methods)

```java
Pattern pattern = Pattern.compile("private\\s+\\w+\\s+\\w+\\s*(\\([^)]*\\))?");
```

### 5. Extract Template Variables (e.g., `[USERNAME]`)

```java
Pattern pattern = Pattern.compile("\\[[^\\]]+\\]");
```

---

## Java Regex Cheatsheet

```
Character Classes:
.        Any char except \n
\d       Digit [0-9]
\D       Non-digit
\s       Whitespace
\S       Non-whitespace
\w       Word char (a-zA-Z0-9_)
\W       Non-word char

Quantifiers:
*        0 or more
+        1 or more
?        0 or 1
{n}      Exactly n
{n,}     n or more
{n,m}    Between n and m

Anchors:
^        Start of string
$        End of string
\b       Word boundary
\B       Non-word boundary

Groups:
(...)    Capturing group
(?:...)  Non-capturing group
a|b      a or b
```

---

## Tips

* Use `Pattern.compile()` to reuse patterns efficiently.
* Escape properly in Java strings (`"\\d+"` not `"\d+"`).
* Test your regex on [regex101.com](https://regex101.com) with Java flavor.

---

## Beyond This

* Learn **lookarounds** and **backreferences**
* Explore **Java Pattern flags** like `Pattern.CASE_INSENSITIVE`
* Try using **regex in streams** (`Files.lines().filter(...)`)
* Integrate regex with **form validation libraries** like Apache Commons Validator
