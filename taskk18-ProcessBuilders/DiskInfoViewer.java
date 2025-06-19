import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DiskInfoViewer {

    public static void main(String[] args) {
        try {
            // Identify OS to choose correct command
            String os = System.getProperty("os.name").toLowerCase();
            String[] command;

            if (os.contains("win")) {
                command = new String[]{"cmd", "/c", "wmic logicaldisk get size,freespace,caption"};
            } else {
                command = new String[]{"sh", "-c", "df -h"};
            }

            // Execute the disk info command
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            // Capture and print output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            System.out.println("Disk Information:\n------------------");
            reader.lines().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error fetching disk info: " + e.getMessage());
        }
    }
}
