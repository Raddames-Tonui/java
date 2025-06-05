# MathLibrary and MainApp Project

This project demonstrates how to create a reusable Java library (`MathLibrary`) and use it in a separate main application (`MainApp`). It also explains how to package the application as both a **Thin JAR** and a **Fat JAR** without using any dependency managers.

## 📁 Project Structure

```

task2-library_jar/
├── app/
│   └── CalculatorApp.java                    # Application source code
├── mathlib/
│   └── MathLibrary.java                      # Library source code
├── out/
│   ├── app/CalculatorApp.class               # Compiled application
│   └── mathlib/MathLibrary.class             # Compiled library 
├── dist/
│   ├── mathlib.jar       ← thin JAR of mathlib
│   ├── app.jar           ← thin JAR of app (depends on mathlib.jar)
│   └── app-fat.jar       ← fat JAR with everything


```

## 📆 Java Package Structure

In Java, packaging and directory structure follow a clear convention:

* If a file declares a package (e.g. `package mathlib;`), it must be placed in a folder with the same name (`mathlib/`).
* Subpackages require subdirectories (e.g. `package mathlib.utils;` → `mathlib/utils/`).
* When compiling, the `-d` flag tells `javac` where to place the compiled `.class` files following the package structure.

Example:

```bash
javac -d out mathlib/MathLibrary.java
```

> This puts `MathLibrary.class` in `out/mathlib/`

When bundling into JARs, use `-C` to specify the base directory:

```bash
jar cf mathlib.jar -C out .
```

> This packages the files from `out/` with correct internal structure.

## 🧬 MathLibrary

* A Java class with **no ****************************************************`main`**************************************************** method**.
* Contains static methods: `add`, `subtract`, `multiply`, and `divide`.
* Declared in a package:

```java
package mathlib;

public class MathLibrary {
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
}
```

### 🔨 Compile and package into a JAR:

```bash
# Compile MathLibrary.java and place compiled class in the 'out' directory
javac -d out mathlib/MathLibrary.java

# Package the compiled class into a JAR file
jar cf mathlib.jar -C out .
```

Explanation:

* `javac -d out` tells the compiler to place output files into `out/`, respecting package structure.
* `jar cf mathlib.jar -C out .` creates the JAR from files in `out`, preserving directory structure.

## 💻 MainApp

* Console app that:

  1. Prompts user to select an operation.
  2. Accepts two numbers.
  3. Calls the corresponding method from `MathLibrary`.

```java
package app;

import java.util.Scanner;
import mathlib.MathLibrary;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        // Validate operation choice
        while (choice < 1 || choice > 4) {
            System.out.println("Choose operation:\n 1-Add\n 2-Subtract\n 3-Multiply\n 4-Divide");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please choose a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                input.next(); // Clear invalid input
            }
        }

        // Prompt and validate first number
        Double a = null;
        while (a == null) {
            System.out.print("Enter first number: ");
            if (input.hasNextDouble()) {
                a = input.nextDouble();
            } else {
                System.out.println("Invalid number. Try again.");
                input.next(); // Clear invalid input
            }
        }

        // Prompt and validate second number
        Double b = null;
        while (b == null) {
            System.out.print("Enter second number: ");
            if (input.hasNextDouble()) {
                b = input.nextDouble();
            } else {
                System.out.println("Invalid number. Try again.");
                input.next(); // Clear invalid input
            }
        }

        input.close();

        // Perform operation
        double result;
        try {
            result = switch (choice) {
                case 1 -> MathLibrary.add(a, b);
                case 2 -> MathLibrary.subtract(a, b);
                case 3 -> MathLibrary.multiply(a, b);
                case 4 -> MathLibrary.divide(a, b);
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            };
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error during calculation: " + e.getMessage());
        }
    }
}
```

## JARS
Find Fat and Thin Jars Documentation in below link.
[Jar Documentation](./JARS.md)

## 📝 Notes

* Ensure the `package` declaration in `MathLibrary.java` matches its folder structure.
* Avoid Maven or Gradle — build manually using `javac` and `jar` tools.
* Always test both JARs after building.
* You can place `mathlib.jar` in the same directory as `MainApp.java`, or reference it using a relative path like `../mathlib/mathlib.jar`.
* A Java source file only becomes part of a package when:

  * The file contains a `package` declaration.
  * It's placed in a directory structure matching that package name.

**Author:** *Raddames Tonui*
**Date:** *2025-06-05*
