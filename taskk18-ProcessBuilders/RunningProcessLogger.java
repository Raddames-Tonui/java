
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


//  Detects OS and writes currently running processes to a file.

public class RunningProcessLogger {

    public static void main(String[] args) {
        try{
            // Detect the OS name and convert to lowercase
            String os = System.getProperty("os.name").toLowerCase();
            String[] command;

            System.out.println("OS: " + os);

            // Determine appropriate command based on OS
            if (os.contains("win")) {
                command = new String[]{"cmd", "/c", "tasklist"};
            }else{
                command = new String[]{"sh", "-c", "ps -e"};
            }

            // Build the process to run the command
            // ProcessBuilder is initialized with the command array (e.g., {"sh", "-c", "ps -e"} or {"cmd", "/c", "tasklist"}),
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();  // The returned Process object lets you interact with the process (read output, check exit status, etc.).

            // Read process output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> lines = reader.lines().collect(Collectors.toList());

            // Create logs directory if not exists
            new File("logs").mkdirs();

            // Write the output to running_processes.txt
            Files.write(Paths.get("logs/running_processes.txt"), lines);

            System.out.println("Process list saved to logs/running_processes.txt");

        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
