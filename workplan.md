# Java JAR Tasks - README & 4-Day Workplan

## Overview

This document outlines the knowledge and step-by-step plan required to complete the given Java tasks related to creating runnable and library JAR files, handling JVM options, command-line arguments, and abstract classes.

---

## Prerequisites

Before starting the tasks, ensure the following are installed:

* Java JDK (version 11+ recommended)
* IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code)
* Terminal or command prompt access

---

## General Knowledge Required

1. **Basic Java syntax** – classes, methods, data types, I/O, inheritance.
2. **JAR files** – creation, running, manifest file.
3. **Abstract classes and inheritance** in Java.
4. **Command-line arguments** parsing in Java.
5. **Using JVM options** (`-Xms`, `-Xmx`, `-Duser.timezone`).
6. **XML and XSD validation** using Java libraries (e.g., `javax.xml.validation`).

---

## 4-Day Java Learning Work Plan

Here’s a 4-day Java learning work plan using Canva-style formatting — with clear daily goals that build up your foundation to complete all 4 tasks efficiently. The plan is designed for practical learners who prefer to apply knowledge immediately.

### 🗓️ Day 1: Java Basics & CLI Apps

**🎯 Goal:** Be comfortable with core syntax and building simple Java programs
**Topics to Cover:**

* Java installation & setup (JDK, JRE, PATH, IDE or terminal usage)
* `main()` method and structure of a Java program
* Variables, Data types, and Operators
* User input via `Scanner`
* Conditionals (`if`, `switch`) and Loops (`for`, `while`)
* Compile and run using `javac` and `java`

**Mini Task:**

* Create a CLI app that adds two numbers from user input

➡️ You’ll be ready to start Task 1 tomorrow.

### 🗓️ Day 2: Methods, Classes & Runnable JAR

**🎯 Goal:** Understand OOP basics and build your first executable JAR
**Topics to Cover:**

* Creating classes and methods
* Access modifiers (`public`, `private`, `protected`)
* `static` vs instance methods
* Compile and build JARs using `jar` CLI tool
* Understanding Manifest file and `Main-Class`

**Mini Tasks:**

* Turn your add-numbers app into a JAR (✅ Task 1)
* Create `MathLibrary` class with static methods for math operations (start of Task 2)

➡️ You’ll be ready to use and import external JARs tomorrow.

### 🗓️ Day 3: JAR as Libraries & Thin/Fat JARs

**🎯 Goal:** Master inter-JAR usage and building app packages manually
**Topics to Cover:**

* How to use `.jar` files as libraries (`-cp`, `classpath`)
* Build thin vs fat JARs manually (include/exclude dependencies)
* Packaging structure of JARs
* `manifest.mf` with multiple entries

**Mini Tasks:**

* Complete `MathLibrary.jar` (✅ Task 2 Part 1)
* Create another app that consumes `MathLibrary` (✅ Task 2 Part 2)
* Build both Thin & Fat runnable JARs

### 🗓️ Day 4: JVM Options, Arguments & OOP Deep Dive

**🎯 Goal:** Master running apps with JVM configs and OOP with inheritance
**Topics to Cover:**

* JVM Options: `-Xms`, `-Xmx`, `-Duser.timezone`
* Parsing command-line arguments
* XML/XSD Validation in Java using `javax.xml.validation`
* Exception Handling
* Abstract classes, inheritance, method overriding

**Mini Tasks:**

* Create the date+XML validator CLI app (✅ Task 3)
* Build `Employees`, `FullTimeEmployee`, and `PartTimeEmployee` classes (✅ Task 4)
* Compile and test all with clear outputs

---

## ✅ Final Output Checklist

* ✅ Runnable JAR for Add App
* ✅ Library JAR for MathLibrary
* ✅ Fat and Thin JARs for consumer app
* ✅ XML Validator app runnable with JVM & CLI arguments
* ✅ Abstract/OOP-based employee salary app

---

## Tools to Use

* Java compiler (`javac`)
* JAR tool (`jar`)
* Terminal/Command Prompt
* XML/XSD test files (for validation)

---

## Canva README Design Suggestions

Create a visual Canva design that includes:

1. **Title:** Java JAR Tasks Execution Guide
2. **Sections:** Each Task with Icons (e.g., 📦 for JARs, 🧮 for Math, 🗂 for XML)
3. **Timeline Chart:** Showing 4-day breakdown
4. **Icons:** JVM, JAR, Terminal, File symbols
5. **Colors:** Java-themed (e.g., orange, blue)

Let me know if you'd like the Canva design exported or a template mockup shared!
