#!/bin/bash

# Task 2: Java Library + App (Fat & Thin JARs)
# This script compiles a Java math library and a separate app that uses it.
# It creates two runnable JARs: a Thin JAR (requires external library) and a Fat JAR (self-contained).

# Directory structure assumption:
# task2/
# ├── MathLibrary/MathOperations.java     # Contains static math functions (no main method)
# ├── App/CalculatorApp.java              # Main app that uses MathLibrary
# ├── lib/                                # Output JARs directory
# ├── build/                              # Temporary files for fat jar

mkdir -p lib build

# Step 1: Compile the MathLibrary (no main method, only utility class)
javac MathLibrary/MathOperations.java

# Step 2: Package MathLibrary into a JAR
jar cf lib/mathlib.jar -C . MathLibrary

# Step 3: Compile the CalculatorApp, referencing mathlib.jar
javac -cp lib/mathlib.jar App/CalculatorApp.java

# Step 4: Create Thin JAR (only includes main app, must be run with mathlib.jar on classpath)
jar cfe lib/thin-app.jar App.CalculatorApp -C . App

echo "To run Thin JAR:"
echo "java -cp lib/mathlib.jar:lib/thin-app.jar App.CalculatorApp"

# Step 5: Create Fat JAR (includes both main app and library for standalone execution)
cp -r App build/
cp -r MathLibrary build/
jar cfe lib/fat-app.jar App.CalculatorApp -C build .

echo "To run Fat JAR:"
echo "java -jar lib/fat-app.jar"

# Cleanup build directory (optional)
# rm -r build
