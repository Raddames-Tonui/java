package org.online_exams;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathTemplateHandler;
import io.undertow.util.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.online_exams.controller.ExamController;
import org.online_exams.controller.PupilController;
import org.online_exams.controller.ReportController;
import org.online_exams.util.MethodAwareHandler;

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

    // PathTemplateHandler matches path patterns (e.g., /api/pupils/{id})
    private final PathTemplateHandler pathTemplateHandler;
    private static final Logger logger = LoggerFactory.getLogger(RouterHandler.class);

    /**
     * Constructor sets up all API endpoints and maps them to the appropriate controller methods.
     *
     * @param conn Active database connection passed to controllers
     */
    public RouterHandler(Connection conn) {
        // Instantiate the controller with DB connection
        PupilController pupilController = new PupilController(conn);
        ExamController examController = new ExamController(conn);
        ReportController reportController = new ReportController(conn);

        // Initialize path matcher
        this.pathTemplateHandler = new PathTemplateHandler();
        // Root path handler for base welcome message
        pathTemplateHandler.add("/", this::handleRoot);

        pathTemplateHandler.add("/api/pupils", new MethodAwareHandler()
                .on("GET", pupilController::handleGetAll)
                .on("POST", pupilController::handleCreate));
        // Route: /api/pupils/{id} → Handles GET (by ID), PUT (update), DELETE (remove)
        pathTemplateHandler.add("/api/pupils/{id}", new MethodAwareHandler()
                .on("GET", pupilController::handleGetById)
                .on("PUT", pupilController::handleUpdate)
                .on("DELETE", pupilController::handleDelete));
        pathTemplateHandler.add("/api/teachers/{teacherId}/exams", new MethodAwareHandler()
                .on("GET", examController::handleExamByTeacher));
        pathTemplateHandler.add("/api/reports/pupil/{pupilId}/exam/{examId}", new MethodAwareHandler()
                .on("GET", reportController::handlePupilExamReport));
        pathTemplateHandler.add("/api/reports/exam/{examId}/top5", new MethodAwareHandler()
                .on("GET", reportController::handleTop5Pupils));
        pathTemplateHandler.add("/api/reports/exams/scores", new MethodAwareHandler()
                .on("GET", reportController::handleAllExamRankings));


    }

    /**
     * This method is called for every HTTP request received by the Undertow server.
     * It delegates to PathTemplateHandler which finds the appropriate route.
     * A try-catch is used to ensure graceful error handling for unanticipated exceptions.
     *
     * @param exchange The HTTP request/response context
     */
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
        exchange.getResponseSender().send("{\"message\": \"Welcome to online_exams API\"}");
    }
}
