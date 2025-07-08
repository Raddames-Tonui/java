package org.example;


import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathTemplateHandler;
import io.undertow.util.Headers;
import org.example.controller.BookReportController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;

/**
 * RouterHandler acts as the central route manager for the entire application.
 * It uses PathTemplateHandler to match URL paths and delegates to MethodAwareHandler
 * for HTTP method-specific dispatching.
 *
 * This design gives us clean separation between:
 *  - Path-based routing (e.g. /api/pupils/{id})
 *  - Method-based dispatching (e.g. GET vs DELETE vs PUT)
 */
public class RouterHandler implements HttpHandler {

    // PathTemplateHandler matches path patterns
    private final PathTemplateHandler pathTemplateHandler;
    private static final Logger logger = LoggerFactory.getLogger(org.example.RouterHandler.class);

    public RouterHandler(Connection conn) {
        this.pathTemplateHandler = new PathTemplateHandler();

        // Controllers
        BookReportController reportController = new BookReportController(conn);

        // Welcome route
        pathTemplateHandler.add("/", this::handleRoot);

        // Reports
        pathTemplateHandler.add("/api/reports/category", exchange ->
                exchange.dispatch(() -> reportController.getBooksByCategory(exchange))
        );
        pathTemplateHandler.add("/api/reports/subcategory", exchange ->
                exchange.dispatch(() -> reportController.getBooksBySubcategory(exchange))
        );
        pathTemplateHandler.add("/api/reports/author", exchange ->
                exchange.dispatch(() -> reportController.getBooksByAuthor(exchange))
        );
        pathTemplateHandler.add("/api/reports/librarian", exchange ->
                exchange.dispatch(() -> reportController.getBooksByLibrarian(exchange))
        );


    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        try {
            pathTemplateHandler.handleRequest(exchange);
        } catch (Exception e) {
            logger.error("Unhandled exception in RouterHandler", e);
            exchange.setStatusCode(500);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send("{\"message\": \"Internal server error.\"}");
        }
    }

    /**
     * Default handler for the root path ("/").
     * Returns a simple JSON welcome message.
     *
     * @param exchange The HTTP request/response context
     */
    private void handleRoot(HttpServerExchange exchange) {
        exchange.setStatusCode(200);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send("{\"message\": \"Welcome to McMillan Library.\"}");
    }
}
