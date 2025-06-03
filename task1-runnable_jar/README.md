## **Java Runnable JAR Documentation**



### ✅ Step 1: Compile the Java File

```bash
javac AddNumbers.java
```

* This command compiles the `AddNumbers.java` file into `AddNumbers.class` bytecode.



### ✅ Step 2: Create a Runnable JAR

```bash
jar cfe AddNumbers.jar AddNumbers AddNumbers.class
```

#### Breakdown:

* `c` → **create** a new JAR file
* `f` → **specify the filename** of the output JAR
* `e` → **set the entry point** (Main-Class) to `AddNumbers`
* `AddNumbers.jar` → name of the output JAR file
* `AddNumbers.class` → compiled class to be included



### ✅ Step 3: Run the Runnable JAR

```bash
java -jar AddNumbers.jar
```

* Executes the JAR by invoking the `main()` method in the `AddNumbers` class specified in the manifest.



### 📑 Summary of `jar` flags

| Flag | Meaning                      |
| ---- | ---------------------------- |
| `c`  | Create a new JAR file        |
| `f`  | Specify the filename         |
| `e`  | Define the entry point class |

This approach ensures your application is packaged cleanly and can be run independently using a single command.
