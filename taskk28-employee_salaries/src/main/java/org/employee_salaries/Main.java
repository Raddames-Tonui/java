package org.employee_salaries;

import io.undertow.Undertow;
import org.employee_salaries.db.DBConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Main entry point: boots Undertow server on port from .properties
 * Before starting, tests DB connection.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        Properties config = new Properties();

        try (InputStream input = Main.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                System.err.println("config.properties not found in resources");
                return;
            }

            config.load(input);
        } catch (IOException e) {
            logger.error("Failed to load config: ", e);
            throw new RuntimeException(e);
        }

        int port = Integer.parseInt(config.getProperty("server.port"));
        String host = config.getProperty("server.host");


        /**
         * DB Connection is created once
         * then passed to all handlers via the RouterHandler
         */
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Database connection successful");

            Undertow server = Undertow.builder()
                    .addHttpListener(port, host)
                    .setHandler(new RouterHandler(conn))
                    .build();
            server.start();
            System.out.println("""
                                           .---.           .---.
                                          /     \\\\  __   //     \\\\
                                         / /     \\\\(o o)//     \\ \\\\
                                        //////   '\\\\ ^ //'      \\\\\\\\
                                       //// / // :     :   \\\\  \\ \\\\\\\\
                                      // /   /  /`----'\\      \\   \\ \\\\
                                                \\\\..////
                                =================UU====UU====================
                                                 '//||||\\\\`
                                                   ''''
                                           UNDERTOW PHOENIX RISES
                                           EMPLOYEE SALARIES API
                            """ +
                    "\n      Undertow server started at " +
                    "\n      Database connected Successfully." +
                    "\n      Host   : " + host +
                    "\n      port   : " + port

            );
        } catch (SQLException e) {
            System.err.println("DB connection failed" + e.getMessage());
        }

        // Register a shutdown hook to clean up resources
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("""
                           
                                           UNDERTOW PHOENIX RISES
                                           EMPLOYEE SALARIES API
                                       Shutting down... Closing DB pool.
                               """
            );
            DBConnection.closePool();
        }));
    }
}
