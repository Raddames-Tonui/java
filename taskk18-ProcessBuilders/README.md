# 🧠 Java ProcessBuilder Deep Dive — Task 18

## 🧭 Where Does ProcessBuilder Fit in Java?

To fully grasp `ProcessBuilder`, you must understand its place in Java's system interaction capabilities:

### 🧱 Core Java Concept: **System-Level Interaction**

Java runs in the JVM, abstracted from the OS. But sometimes we need to interact directly with the underlying operating system — to run shell commands, spawn system processes, or manage system resources.

### 🧩 Relevant Java Concepts

| Concept              | Description                                                              |
| -------------------- | ------------------------------------------------------------------------ |
| **`System` class**   | Basic console I/O, properties, `System.exit`, `System.getenv()`          |
| **`Runtime` class**  | Legacy approach to launching external processes using `Runtime.exec()`   |
| **`Process` class**  | Represents a native system process                                       |
| **`ProcessBuilder`** | A flexible and modern API to create and manage native system processes   |
| **`Thread/Timer`**   | Used to schedule or repeat OS-level commands (e.g., backups every 1 min) |


## ⚔️ ProcessBuilder vs Runtime.exec vs Others

### 1. `Runtime.exec()` (Legacy)

* Quick and dirty command execution
* Harder to handle complex arguments
* Limited environment customization

### 2. `ProcessBuilder` (Modern)

* Cleaner, more flexible
* Easier I/O redirection, directory setup, and environment variable control

### 3. `JNI` (Java Native Interface)

* Low-level native code integration (C/C++)
* Heavyweight, complex for simple tasks
* Not used unless absolutely necessary

### 4. `JNA` / `JavaCPP`

* Libraries for calling native libraries without JNI boilerplate
* Used in advanced or performance-critical native interactions

### ✅ When to Use What?

| Use Case              | Preferred Tool                       |
| --------------------- | ------------------------------------ |
| Simple shell command  | `ProcessBuilder`                     |
| Native C/C++ library  | `JNI` or `JNA`                       |
| Forking many OS tasks | `ProcessBuilder` + `ExecutorService` |
| JVM-exit, sys info    | `System` class                       |


Now that you understand where `ProcessBuilder` fits in and what it's designed for, let's deep dive into its capabilities.


## 📌 Objective

This task demonstrates how to use **Java’s `ProcessBuilder`** class to execute system-level commands programmatically. It covers:

1. Taking scheduled PostgreSQL backups using `pg_dump`
2. OS detection and logging of running processes
3. Viewing disk/partition information via commands

This approach **does not involve JDBC/database drivers** — only shell-level interaction via **process spawning**.


## 🎧 What is ProcessBuilder?

`ProcessBuilder` is a Java class in the `java.lang` package used to **create and manage operating system processes**. It lets you run native system commands (like `pg_dump`, `mkdir`, `echo`, etc.) from within your Java application.


## 🌳 Class Hierarchy Tree

```
Object
└── ProcessBuilder
    ├── List<String> command
    ├── Map<String, String> environment
    ├── File directory
    ├── boolean redirectErrorStream
    └── Process start() 
          ├── getInputStream()
          ├── getOutputStream()
          ├── getErrorStream()
          └── waitFor()
```


## 🧠 Key Concepts

### ✅ ProcessBuilder vs Runtime.exec()

| Feature               | ProcessBuilder                 | `Runtime.exec()`           |
| --------------------- | ------------------------------ | -------------------------- |
| Flexible command args | Yes (via `List<String>`)       | Limited (manual splitting) |
| Output/Error Handling | Easier with stream redirection | Manual streams required    |
| Reusability           | Yes (can set env, dir)         | No                         |


## 🧪 Core Methods in ProcessBuilder

| Method                            | Description                       |
| --------------------------------- | --------------------------------- |
| `command(String...)`              | Sets the command and arguments    |
| `start()`                         | Launches the process              |
| `redirectErrorStream(true/false)` | Merge stderr with stdout          |
| `directory(File)`                 | Working directory for the command |
| `environment()`                   | Modify the environment variables  |


## 🧰 Sample Code Snippets

### 📌 A. PostgreSQL Backup with Rotation (Every 1 Minute)

This snippet schedules PostgreSQL backups every minute and retains only the last 10.

```java
// Schedule a backup task to run every minute
ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
scheduler.scheduleAtFixedRate(() -> {
    try {
        // Generate unique filename based on timestamp
        String fileName = "backup_" + System.currentTimeMillis() + ".sql";
        File backupDir = new File("backups");
        backupDir.mkdirs();

        // Create ProcessBuilder with pg_dump command
        ProcessBuilder pb = new ProcessBuilder("pg_dump", "-U", "postgres", "Exams");
        pb.redirectOutput(new File(backupDir, fileName));
        pb.environment().put("PGPASSWORD", "yourpassword"); // set DB password

        pb.start().waitFor(); // execute and wait for process

        // Rotate if more than 10 backups exist
        File[] files = backupDir.listFiles();
        if (files != null && files.length > 10) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
            files[0].delete(); // remove oldest
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}, 0, 1, TimeUnit.MINUTES);
```

### 📌 B. Detect OS and List Running Processes

Detects your OS and logs currently running processes to a file.

```java
// Get current OS name
String os = System.getProperty("os.name").toLowerCase();

// Choose command depending on OS
String[] command = os.contains("win") ? new String[]{"cmd", "/c", "tasklist"} : new String[]{"sh", "-c", "ps -e"};

// Run the command
ProcessBuilder pb = new ProcessBuilder(command);
Process process = pb.start();

// Read output
BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

// Create logs directory and write output to file
new File("logs").mkdir();
Files.write(Paths.get("logs/running_processes.txt"), reader.lines().collect(Collectors.toList()));
```

### 📌 C. Display Disk Information

Displays storage partition info based on your OS.

```java
// Define command for disk info
String[] command = os.contains("win")
    ? new String[]{"cmd", "/c", "wmic logicaldisk get size,freespace,caption"}
    : new String[]{"sh", "-c", "df -h"};

// Execute command and print output
ProcessBuilder pb = new ProcessBuilder(command);
Process process = pb.start();
BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
reader.lines().forEach(System.out::println);
```


## 💬 Real-World Use Cases

| Use Case             | Example                            |
| -------------------- | ---------------------------------- |
| DevOps Scripting     | Auto-backup, deploy scripts        |
| Cross-platform tools | System diagnostics                 |
| Monitoring           | Uptime, disk usage logs            |
| Security             | Track processes, detect intrusions |


## 🔍 Advanced Enhancements (Future Scope)

* Add timestamp to backup file names
* Compress backups (`pg_dump | gzip`)
* Send backup status emails
* Encrypt backups using GPG
* Real-time process monitoring via `top` or `htop`


## 📁 Sample Output Files

### running\_processes.txt

```
  PID TTY          TIME CMD
  101 ?        00:00:00 systemd
  102 ?        00:00:00 kthreadd
  ...
```


## 🛑 Errors to Watch For

* `IOException` if command fails or not found
* `AccessDeniedException` if permissions block file ops
* `Process.waitFor()` blocking the thread indefinitely
* Always check `exitValue()` for success (0 = OK)


## 🧩 Summary Diagram

```plaintext
Java App
  ├── Uses ProcessBuilder
       ├── Spawns pg_dump
       ├── Spawns ps/tasklist
       ├── Spawns mkdir & echo
       ├── Spawns df/wmic
       └── Handles output streams
  └── Manages files and backup rotation
```


## ✅ Key Takeaways

* `ProcessBuilder` is your gateway to OS-level scripting in Java
* Ideal for hybrid automation (Java + shell)
* Crucial for DevOps, backup systems, and admin utilities
* Write clean logic for platform detection and fallback commands
