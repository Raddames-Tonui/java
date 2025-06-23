public class Runner {

    public static void main(String[] args) {

        // 1. Simple Runnable example
        Runnable logTask = () -> {
            System.out.println("Writing logs to file from thread: " + Thread.currentThread().getName());
        };

        Thread loggerThread = new Thread(logTask); 
        loggerThread.start(); // Save user activity without blocking UI or API.

        // 2. Start cleaner background thread
        startCleaner();
    }

    // Method to start cleaner
    public static void startCleaner() {
        Runnable cleaner = () -> {
            while (true) {
                try {
                    System.out.println("🧹 Cleaning expired files in thread: " + Thread.currentThread().getName());
                    Thread.sleep(60000); // Sleep for 1 minute
                } catch (InterruptedException e) {
                    System.out.println("🛑 Cleanup thread interrupted.");
                    break;
                }
            }
        };

        Thread cleanerThread = new Thread(cleaner);
        cleanerThread.setDaemon(true); // Background thread, won't block JVM shutdown
        cleanerThread.start();

        // Hook for graceful shutdown (e.g., Ctrl+C or system exit)
        // Close DB connections, flush logs, notify services.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("🧹 JVM shutting down. Perform final cleanup.");
        }));
    }
}
