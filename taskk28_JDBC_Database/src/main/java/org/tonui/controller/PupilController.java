package org.tonui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.tonui.model.Pupil;
import org.tonui.service.PupilService;

import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.List;

/**
 * Handles routing and HTTP request/response processing for pupil-related endpoints.
 */
public class PupilController {

    private final PupilService pupilService = new PupilService(); // Use service layer
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Central router for all incoming HTTP requests related to pupils.
     */
    public void handleRouting(HttpServerExchange exchange) {
        String path = exchange.getRequestPath();
        String method = exchange.getRequestMethod().toString();

        if (method.equals("GET") && path.equals("/")) {
            // Welcome route
            exchange.setStatusCode(200);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send("🎉 Welcome to the Undertow API Server");

        } else if (method.equals("GET") && path.equals("/api/pupils/by-class")) {
            // Fetch pupils by class
            getPupilsByClassId(exchange);

        } else if (method.equals("GET") && path.equals("/api/pupils")) {
            // Get all pupils
            getAllPupils(exchange);

        } else if (method.equals("POST") && path.equals("/api/pupils")) {
            // Create a new pupil
            createPupil(exchange);

        } else if (path.startsWith("/api/pupils/")) {
            // Handle dynamic endpoints by pupil ID
            if (method.equals("GET")) {
                getPupilById(exchange);

            } else if (method.equals("PUT")) {
                updatePupil(exchange);

            } else if (method.equals("DELETE")) {
                deletePupil(exchange);

            } else {
                exchange.setStatusCode(405);
                exchange.getResponseSender().send("Method not allowed");
            }
        } else {
            exchange.setStatusCode(404);
            exchange.getResponseSender().send("Endpoint not found");
        }
    }

    /**
     * Returns all pupils in the database.
     */
    private void getAllPupils(HttpServerExchange exchange) {
        try {
            List<Pupil> pupils = pupilService.getAll(); // Use service
            String json = objectMapper.writeValueAsString(pupils);

            exchange.setStatusCode(200);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send(json);
        } catch (Exception e) {
            exchange.setStatusCode(500);
            exchange.getResponseSender().send("Failed to fetch pupils: " + e.getMessage());
        }
    }

    /**
     * Returns a specific pupil by ID.
     */
    private void getPupilById(HttpServerExchange exchange) {
        try {
            String[] segments = exchange.getRequestPath().split("/");
            if (segments.length == 4) {
                Long id = Long.parseLong(segments[3]);
                Pupil pupil = pupilService.getById(id); // Use service

                if (pupil != null) {
                    String json = objectMapper.writeValueAsString(pupil);
                    exchange.setStatusCode(200);
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                    exchange.getResponseSender().send(json);
                } else {
                    exchange.setStatusCode(404);
                    exchange.getResponseSender().send("Pupil not found");
                }
            } else {
                exchange.setStatusCode(400);
                exchange.getResponseSender().send("Invalid ID format");
            }
        } catch (Exception e) {
            exchange.setStatusCode(500);
            exchange.getResponseSender().send("Failed to fetch pupil: " + e.getMessage());
        }
    }

    /**
     * Creates a new pupil from JSON input.
     */
    private void createPupil(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullBytes((ex, message) -> {
            try {
                String body = new String(message, StandardCharsets.UTF_8);
                Pupil pupil = objectMapper.readValue(body, Pupil.class);

                boolean success = pupilService.createPupil(pupil); // Use service
                if (success) {
                    exchange.setStatusCode(201);
                    exchange.getResponseSender().send("Pupil created successfully");
                } else {
                    exchange.setStatusCode(500);
                    exchange.getResponseSender().send("Failed to create pupil");
                }
            } catch (Exception e) {
                exchange.setStatusCode(400);
                exchange.getResponseSender().send("Invalid request body: " + e.getMessage());
            }
        });
    }

    /**
     * Updates an existing pupil using ID from URL and JSON body.
     */
    private void updatePupil(HttpServerExchange exchange) {
        String[] segments = exchange.getRequestPath().split("/");
        if (segments.length == 4) {
            Long id = Long.parseLong(segments[3]);

            exchange.getRequestReceiver().receiveFullBytes((ex, message) -> {
                try {
                    String body = new String(message, StandardCharsets.UTF_8);
                    Pupil updatedPupil = objectMapper.readValue(body, Pupil.class);
                    updatedPupil.setPupilId(id);

                    boolean success = pupilService.update(updatedPupil); // Use service
                    if (success) {
                        exchange.setStatusCode(200);
                        exchange.getResponseSender().send("Pupil updated successfully");
                    } else {
                        exchange.setStatusCode(404);
                        exchange.getResponseSender().send("Pupil not found");
                    }
                } catch (Exception e) {
                    exchange.setStatusCode(400);
                    exchange.getResponseSender().send("Invalid request body: " + e.getMessage());
                }
            });
        } else {
            exchange.setStatusCode(400);
            exchange.getResponseSender().send("Invalid ID format");
        }
    }

    /**
     * Deletes a pupil by ID.
     */
    private void deletePupil(HttpServerExchange exchange) {
        String[] segments = exchange.getRequestPath().split("/");
        if (segments.length == 4) {
            Long id = Long.parseLong(segments[3]);
            boolean success = pupilService.delete(id); // Use service

            if (success) {
                exchange.setStatusCode(200);
                exchange.getResponseSender().send("Pupil deleted successfully");
            } else {
                exchange.setStatusCode(404);
                exchange.getResponseSender().send("Pupil not found");
            }
        } else {
            exchange.setStatusCode(400);
            exchange.getResponseSender().send("Invalid ID format");
        }
    }

    /**
     * Fetch pupils by class using query param (e.g., ?classId=1).
     */
    private void getPupilsByClassId(HttpServerExchange exchange) {
        Deque<String> classIdParam = exchange.getQueryParameters().get("classId");
        if (classIdParam == null || classIdParam.isEmpty()) {
            exchange.setStatusCode(400);
            exchange.getResponseSender().send("Missing classId query parameter");
            return;
        }

        try {
            Long classId = Long.parseLong(classIdParam.getFirst());
            List<Pupil> pupils = pupilService.getByClassId(classId); // Use service
            String json = objectMapper.writeValueAsString(pupils);

            exchange.setStatusCode(200);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send(json);
        } catch (NumberFormatException e) {
            exchange.setStatusCode(400);
            exchange.getResponseSender().send("Invalid classId format");
        } catch (Exception e) {
            exchange.setStatusCode(500);
            exchange.getResponseSender().send("Error: " + e.getMessage());
        }
    }
}
