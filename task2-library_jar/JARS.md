# 📦 Java Thin & Fat JARs with Manual Compilation

This project demonstrates how to manually compile Java source code, create a reusable library (MathLibrary), and package the application in two ways:

* **Thin JAR** — contains only the app; library is provided externally.
* **Fat JAR** — contains both app and library; self-contained.


## 📁 Project Structure

```
task2-library_jar/
├── app/
│   └── CalculatorApp.java          # Application source code
├── mathlib/
│   └── MathLibrary.java            # Library source code
├── out/                            # Compiled .class output
├── dist/                           # JAR output
│   ├── app-thin.jar                # Thin JAR (app only)
│   ├── app-fat.jar                 # Fat JAR (app + library)
│   └── mathlib.jar                 # Compiled library JAR
└── fatjar/                         # Temporary directory for fat jar
```


## 🔹 Thin JAR using `dist/`

A **thin JAR** only contains your application classes. The external library is passed separately via classpath at runtime.

### 1️⃣ Compile and package MathLibrary into a JAR

```bash
# Compile MathLibrary.java and output to out/
javac -d out mathlib/MathLibrary.java

# Create dist/mathlib.jar containing mathlib/MathLibrary.class
jar cf dist/mathlib.jar -C out mathlib
```

### 2️⃣ Compile CalculatorApp.java using mathlib.jar in classpath

```bash
# Compile with mathlib.jar on classpath, and output to out/
javac -cp dist/mathlib.jar -d out app/CalculatorApp.java
```

### 3️⃣ Package CalculatorApp as a thin JAR

```bash
# Create app-thin.jar with entry point and compiled class
jar cfe dist/app-thin.jar app.CalculatorApp -C out app
```

### ▶️ Run Thin JAR

```bash
# Run the app with both JARs on the classpath
java -cp dist/app-thin.jar:dist/mathlib.jar app.CalculatorApp
```

> 🪟 On Windows, replace `:` with `;`

#### ✅ Summary (Thin JAR)

* `app-thin.jar` contains only app code.
* `mathlib.jar` is required at runtime.
* Suitable for modular applications.


## 🔸 Fat JAR using `dist/`

A **fat JAR** (or uber JAR) bundles everything (app + libraries) into a single executable archive.

### 1️⃣ Prepare a temp directory for fat JAR content

```bash
# Create directory to gather all compiled classes
mkdir -p fatjar

# Copy app and library classes
cp -r out/app fatjar/
cp -r out/mathlib fatjar/
```

### 2️⃣ Package all classes into a single fat JAR

```bash
# Create app-fat.jar from fatjar/, set entry point to CalculatorApp
jar cfe dist/app-fat.jar app.CalculatorApp -C fatjar .
```

### ▶️ Run Fat JAR

```bash
# Run directly, no external classpath needed
java -jar dist/app-fat.jar
```

#### ✅ Summary (Fat JAR)

* `app-fat.jar` is fully self-contained.
* Suitable for standalone execution or distribution.


## 📊 JAR Type Comparison

| Type     | Includes MathLibrary?       | Run Command                                           |
| -------- | --------------------------- | ----------------------------------------------------- |
| Thin JAR | ❌ No (separate .jar needed) | `java -cp app-thin.jar:mathlib.jar app.CalculatorApp` |
| Fat JAR  | ✅ Yes (bundled inside)      | `java -jar app-fat.jar`                               |


## ✅ Best Practices Summary

* Use `out/` for compiled `.class` files.
* Use `dist/` for final production `.jar` files.
* Use `fatjar/` as a temporary build workspace.
* Add comments in build commands to ensure maintainability.

Would you like a shell script to automate the full thin/fat JAR workflow?
