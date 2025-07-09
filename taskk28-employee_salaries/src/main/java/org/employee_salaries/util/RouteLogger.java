package org.employee_salaries.util;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RouteLogger wraps an HttpHandler and logs the request method, path, and optional performance info.
 */
public class RouteLogger implements HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(RouteLogger.class);

    private final HttpHandler next; // The actual handler being wrapped

    public RouteLogger(HttpHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String method = exchange.getRequestMethod().toString();
        String path = exchange.getRequestPath();

        // Log the incoming request
        logger.info("Incoming Request: {} {}", method, path);

        try {
            next.handleRequest(exchange); // Continue to the real handler
        } catch (Exception e) {
            logger.error("Exception handling {} {}: {}", method, path, e.getMessage());
            throw e; // Let the upstream error handler handle it
        }
    }
}
