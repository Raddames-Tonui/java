import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PostgresBackupScheduler {

    public static void main(String[] args) {
        // Single-threaded scheduler for periodic task execution
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Generate backup filename with timestamp
                String fileName = "backup_" + System.currentTimeMillis() + ".sql";
                File backupDir = new File("backups");
                backupDir.mkdirs(); // Create directory if not exists

                // Construct pg_dump command using ProcessBuilder
                ProcessBuilder pb = new ProcessBuilder("pg_dump", "-U", "postgres", "Exams");
                pb.redirectOutput(new File(backupDir, fileName)); // Set output destination

                // Set environment password securely (not hardcoded in real apps)
                pb.environment().put("PGPASSWORD", "yourpassword");

                // Start and wait for process to complete
                Process process = pb.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Backup successful: " + fileName);
                } else {
                    System.err.println("Backup failed with exit code: " + exitCode);
                }

                // Perform rotation: Keep only 10 latest files
                File[] files = backupDir.listFiles((dir, name) -> name.endsWith(".sql"));
                if (files != null && files.length > 10) {
                    Arrays.sort(files, Comparator.comparingLong(File::lastModified));
                    files[0].delete(); // Delete oldest
                    System.out.println("Oldest backup deleted: " + files[0].getName());
                }

            } catch (Exception e) {
                e.printStackTrace(); // Log exception for debugging
            }
        }, 0, 1, TimeUnit.MINUTES); // Initial delay = 0, interval = 1 min
    }
}
