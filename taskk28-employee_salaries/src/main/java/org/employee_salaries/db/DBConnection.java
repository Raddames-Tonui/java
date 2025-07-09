package org.online_exams.db;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBConnection class using HikariCP for efficient JDBC connection pooling.
 * This setup improves performance, connection reuse, and production reliability.
 */

public class DBConnection {
    // A static HikariCP DataSource shared across the application
    private static final HikariDataSource dataSource;

    // Static initializer block runs once when class is loaded
    static {
        try {
            // Load configuration directly from a properties file in classpath
            // config.properties should be located inside src/main/resources/
            HikariConfig config = new HikariConfig("/db.properties");

            // Create a new datasource from the configuration (Shared pool across app)
            dataSource = new HikariDataSource(config);

        }catch ( Exception e){
            throw new RuntimeException("Failed to initialize HikariDataSource: "+ e.getMessage());
        }
    }

    /**
     * Provides a pooled JDBC connection from HikariCP.
     * Hikari handles the lifecycle, so no manual retry or driver management is needed.
     *
     * @return Active, pooled JDBC Connection
     * @throws SQLException if connection acquisition fails
     */

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    /**
     * Closes the connection pool gracefully.
     * Recommended to be called during application shutdown.
     */
    public static void closePool(){
        if(dataSource != null && !dataSource.isClosed()){
            dataSource.close();
        }
    }
}