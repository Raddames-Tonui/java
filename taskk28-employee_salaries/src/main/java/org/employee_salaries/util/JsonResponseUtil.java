package org.employee_salaries.util;

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
            if (!exchange.isComplete()) {
                String json = objectMapper.writeValueAsString(payload);
                exchange.setStatusCode(statusCode);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                exchange.getResponseSender().send(json);
            }
        } catch (Exception e) {
            if (!exchange.isComplete()) {
                exchange.setStatusCode(500);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                exchange.getResponseSender().send("{\"message\":\"JSON serialization error\"}");
            }
        }
    }
}