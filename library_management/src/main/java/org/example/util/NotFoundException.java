package org.example.util;


/**
 * Custom exception to represent 404 Not Found situations.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
