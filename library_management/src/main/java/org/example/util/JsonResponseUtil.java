package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * Utility class for sending JSON responses using Jackson and Undertow.
 * This centralizes JSON serialization and ensures consistent response formatting.
 */
public class JsonResponseUtil {

    // Shared Jackson object mapper used for converting Java objects to JSON
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Sends a JSON response through the given HttpServerExchange.
     *
     * @param exchange   Undertow exchange object representing the HTTP request/response
     * @param statusCode HTTP status code to return (e.g., 200, 404, 500)
     * @param payload    Java object to serialize as JSON and return in the response body
     */
    public static void sendJson(HttpServerExchange exchange, int statusCode, Object payload) {
        try {
            // Serialize payload object to a JSON string
            String json = objectMapper.writeValueAsString(payload);

            // Set response status and content type
            exchange.setStatusCode(statusCode);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");

            // Send the JSON response body
            exchange.getResponseSender().send(json);

        } catch (Exception e) {
            // In case of serialization failure, return a simple JSON error message
            exchange.setStatusCode(500);
            exchange.getResponseSender().send("{\"message\":\"JSON serialization error\"}");
        }
    }
}
