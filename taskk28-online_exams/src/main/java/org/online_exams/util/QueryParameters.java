package org.online_exams.util;

import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

/**
 * Utility class to simplify reading query parameters from Undertow's HttpServerExchange.
 * Provides a convenient and safe way to get values with null checks.
 */
public class QueryParameters {
    private final Map<String, Deque<String>> params;

    /**
     * Constructor takes in the HTTP exchange and extracts query parameters.
     * @param exchange the Undertow HTTP server exchange
     */
    public QueryParameters(HttpServerExchange exchange) {
        this.params = exchange.getQueryParameters();
    }

    /**
     * Returns the first value of a given query parameter key, or null if missing.
     *
     * @param key the query parameter name (e.g. "teacherId")
     * @return the first value, or null
     */
    public String get(String key) {
        Deque<String> values = params.get(key);
        return (values != null && !values.isEmpty()) ? values.getFirst() : null;
    }

    /**
     * Returns the value as a Long if possible, or null if missing or invalid.
     */
    public Long getAsLong(String key) {
        try {
            String value = get(key);
            return (value != null) ? Long.parseLong(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns the value as an Integer if possible, or null if missing or invalid.
     */
    public Integer getAsInt(String key) {
        try {
            String value = get(key);
            return (value != null) ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Returns the value or a default if the parameter is missing.
     *
     * @param key          the query parameter key
     * @param defaultValue fallback value if not present
     */
    public String getOrDefault(String key, String defaultValue) {
        String value = get(key);
        return (value != null) ? value : defaultValue;
    }
}
