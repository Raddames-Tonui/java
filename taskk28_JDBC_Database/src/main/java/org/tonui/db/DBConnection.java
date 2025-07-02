package org.tonui.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBConnection handles loading database configuration from
 * a properties file and provides a reusable connection method.
 */
public class DBConnection {
    // A Properties object to store key-value pairs from application.properties
    private static final Properties props = new Properties();

    // Static initializer: runs once when the class is loaded
    static {
        try (
                // Load the properties file from the classpath (src/main/resources)
                InputStream input = DBConnection.class
                        .getClassLoader()
                        .getResourceAsStream("application.properties")
        ) {
            if (input == null) {
                throw new RuntimeException("application.properties not found in resources folder");
            }

            // Load property key-value pairs into the 'props' object
            props.load(input);

            // Register PostgreSQL JDBC driver (optional in newer Java, safe for older versions)
            Class.forName("org.postgresql.Driver");

        } catch (IOException e) {
            // Handles error if the properties file can't be read
            throw new RuntimeException("I/O error reading DB config: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handles all other errors (e.g. class not found)
            throw new RuntimeException("Failed to load DB config: " + e.getMessage(), e);
        }
    }

    /**
     * This method provides a JDBC Connection object using the config from application.properties.
     * Includes retry logic in case the DB is not immediately available (e.g., Docker container).
     */
    public static Connection getConnection() throws SQLException {
        // Get values from properties
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        // Retry logic: up to 3 attempts with 1-second delay between
        int attempts = 0;
        int maxRetries = 3;

        while (attempts < maxRetries) {
            try {
                // Try to establish a JDBC connection
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                attempts++;
                System.err.println("Connection attempt " + attempts + " failed: " + e.getMessage());

                // If max attempts reached, rethrow the exception
                if (attempts >= maxRetries) throw e;

                // Wait for 1 second before retrying
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }

        // This line should not be reached due to the throw above
        throw new SQLException("Failed to connect after " + maxRetries + " attempts");
    }
}
