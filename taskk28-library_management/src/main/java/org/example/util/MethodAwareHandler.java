package org.example.util;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * MethodAwareHandler is a utility wrapper that allows method-specific routing for a given path.
 *
 * In Undertow, PathTemplateHandler matches only the path (e.g., "/api/pupils/{id}").
 * This class adds an extra layer to dispatch based on HTTP method (GET, POST, PUT, DELETE, etc.)
 * for that matched path.
 *
 * Usage:
 * new MethodAwareHandler()
 *     .on("GET",    getHandler)
 *     .on("POST",   postHandler)
 *     .on("DELETE", deleteHandler);
 */
public class MethodAwareHandler implements HttpHandler {

    // Maps HTTP methods (e.g., "GET", "POST") to their corresponding handler functions
    private final Map<String, HttpHandler> handlers = new HashMap<>();

    /**
     * Registers a handler for a specific HTTP method.
     * @param method HTTP method as a string (e.g., "GET", "POST")
     * @param handler The logic to execute when the method is matched
     * @return the current instance for chaining
     */
    public MethodAwareHandler on(String method, HttpHandler handler) {
        handlers.put(method.toUpperCase(), handler); // Normalize method to uppercase
        return this;
    }

    /**
     * Core method from Undertow's HttpHandler interface.
     * Delegates the request to the handler mapped for the request's HTTP method.
     * If no handler is defined for the method, returns 405 Method Not Allowed.
     */
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String method = exchange.getRequestMethod().toString().toUpperCase();
        HttpHandler httpHandler = handlers.get(method);

        if (httpHandler != null) {
            httpHandler.handleRequest(exchange); // Dispatch to method-specific logic
        } else {
            // Return 405 when method is not supported on this route
            exchange.setStatusCode(405);
            exchange.getResponseSender().send("{\"message\":\"Method not allowed.\"}");
        }
    }
}
