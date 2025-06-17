# Employee & EmployeeManager - Java File Handling Demo

## Overview

This project demonstrates Java file handling through serialization and deserialization of objects. It includes two classes:

* **Employee**: A simple model representing an employee.
* **EmployeeManager**: A class that manages employee data, reads from/writes to a file, and persists state between executions.

---

## 1. Employee Class

File: `c/Employee.java`

### Key Features:

* Represents employee data.
* Implements `Serializable` to support object serialization.

### Properties:

* `String employeeNumber`
* `String employeeName`
* `double netSalary`

### Constructor:

```java
public Employee(String employeeNumber, String employeeName, double netSalary)
```

### `toString()` Method:

Used to format the employee details when printing.

```java
@Override
public String toString() {
    return employeeNumber + " - " + employeeName + " - KES " + netSalary;
}
```

---

## 2. EmployeeManager Class

File: `c/EmployeeManager.java`

### Purpose:

* Loads existing employees from file (if any).
* Accepts user input to add new employees.
* Saves updated employee list to file.

### Key Features:

* Uses `ObjectInputStream` and `ObjectOutputStream` to persist a `List<Employee>`.
* Ensures the file exists and handles file I/O exceptions gracefully.

### Main Workflow:

1. **Check command-line arguments**

   ```java
   if (args.length < 1)
   ```
2. **Deserialize existing employees (if file exists and has content)**

   ```java
   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
   ```
3. **Prompt user for new employee data** using `Scanner`
4. **Add new `Employee` to list** and **serialize** back to file:

   ```java
   ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
   oos.writeObject(employees);
   ```

---

## Folder Structure

```
project-folder/
└── c/
    ├── Employee.java
    ├── EmployeeManager.java
└── employee.txt (created at runtime)
```

---

## How to Compile and Run

### Step 1: Open Terminal and Navigate

```bash
cd /path/to/project-folder
```

### Step 2: Compile

```bash
javac c/*.java
```

### Step 3: Run

```bash
java c.EmployeeManager employee.txt
```

### Notes:

* `employee.txt` will be created if it doesn't exist.
* Program loads existing employees and lets you add more.
* File path is required as a command-line argument.

---

## Example Session

```
$ java c.EmployeeManager employee.txt
Existing employees:
123 - John Doe - KES 75000.0

Add New Employee:
Employee Number: 456
Employee Name: Jane Smith
Net Salary: 89000
Employee list saved
```

---

## Concepts Covered

* Java File Handling
* Serialization/Deserialization
* Object Streams
* Command-line arguments
* Exception Handling
* Java Packages

---

## What's Next (Beyond Scope)

* Replace serialization with JSON using libraries like Gson or Jackson.
* Build a CLI menu for managing (add, delete, search) employees.
* Connect to a database (e.g., SQLite, PostgreSQL).
* Integrate with JavaFX or Swing for GUI.
* Use unit tests to verify serialization logic.

