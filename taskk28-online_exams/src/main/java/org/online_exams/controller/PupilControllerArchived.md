
package org.online_exams.controller;


import io.undertow.server.HttpServerExchange;
import io.undertow.util.PathTemplateMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.online_exams.service.PupilService;
import org.online_exams.util.ApiResponse;
import org.online_exams.util.JsonResponseUtil;

import java.sql.Connection;

/**
* Handles pupil-related HTTP requests: list, delete, etc.
  */

public class PupilControllerArchived {
private static final Logger logger = LoggerFactory.getLogger(PupilController.class);

    // SERVICES
    private final PupilService pupilService;

    /**
     * Constructor - accepts a shared DB connection and initializes service.
     */
    public PupilController(Connection conn) {
        this.pupilService = new PupilService(conn);
    }

    public PupilControllerArchived(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    /**
     * Handles GET /api/pupils - Retrieves all pupils.
     */
    public void handleGetAll(HttpServerExchange exchange){
        exchange.dispatch(() -> {
            try{
                var pupils = pupilService.getAllPupils();
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("pupils retrieved successfully." , pupils));
            } catch (Exception e) {
                logger.error("Failed to get pupils: ", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to retrieve pupils", null));
            }
        });

    }

    /**
     * Handles DELETE /api/pupils/{id} - Deletes a pupil by ID.
     */
    public void handleDelete(HttpServerExchange exchange){
        exchange.dispatch(() -> {
            try {
                // Extract path parameter {id} from the route
                String idStr = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY)
                        .getParameters()
                        .get("id");

                long id = Long.parseLong(idStr);

                pupilService.deletePupil(id);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("pupil deleted successfully."));
            }catch (NumberFormatException e){
                logger.error("Invalid pupil ID format", e);
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid pupil ID"));
            }catch (Exception e){
                logger.error("Failed to delete pupil", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to delete pupil"));
            }
        });
    }

}
























//------------------------------------------------------------------------------------------

//package org.online_exams.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.undertow.server.HttpServerExchange;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.online_exams.dto.pupil.PupilDTO;
//import org.online_exams.model.Pupil;
//import org.online_exams.service.PupilService;
//import org.online_exams.util.ApiResponse;
//import org.online_exams.util.JsonResponseUtil;
//
//import java.sql.Connection;
//import java.util.List;
//

// * Controller class responsible for handling all HTTP requests related to pupils.
// * Acts as an entry point from the Undertow router to pupil-specific logic.
// */
// public class PupilController {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private static final Logger logger = LoggerFactory.getLogger(PupilController.class);
//
//    // Service layer responsible for business logic
//    private final PupilService pupilService;
//
//    /**
//     * Initializes the PupilController with a database connection.
//     * The connection is passed down to the service and DAO layers.
//     *
//     * @param conn Active JDBC database connection
//     */
//    public PupilController(Connection conn) {
//        this.pupilService = new PupilService(conn);
//    }
//
//    /**
//     * Entry point for routing all HTTP requests related to pupils.
//     * Routes specific endpoints to their respective handler methods.
//     *
//     * @param exchange The HTTP request/response context from Undertow
//     */
//    public void handleRouting(HttpServerExchange exchange) {
//        String path = exchange.getRequestPath();
//        String method = exchange.getRequestMethod().toString();
//
//        if (method.equals("GET") && path.equals("/api/pupils")) {
//            getAllPupils(exchange);
//        } else if (method.equals("POST") && path.equals("/api/pupils")) {
//            createPupil(exchange);
//        } else if (method.equals("DELETE") && path.matches("/api/pupils/\\d+")) {
//            deletePupil(exchange);
//        } else {
//            exchange.setStatusCode(404);
//            exchange.getResponseSender().send("Endpoint not found.");
//        }
//    }
//
//    /**
//     * Handles the GET request to retrieve all pupils.
//     * If data is present, it responds with a success message and data.
//     * If no data is found, it returns an empty list with a friendly message.
//     * On failure, it sends a 500 error response.
//     *
//     * @param exchange The Undertow HTTP exchange object
//     */
//    private void getAllPupils(HttpServerExchange exchange) {
//        try {
//            // Fetch pupils from the service layer
//            List<PupilDTO> pupils = pupilService.getAllPupils();
//
//            // Create an appropriate response message
//            String msg = pupils.isEmpty()
//                    ? "No pupils found."
//                    : "Pupils retrieved successfully.";
//
//            // Wrap the response using ApiResponse and Send as JSON with 200 OK
//            ApiResponse<List<PupilDTO>> response = new ApiResponse<>(msg, pupils);
//            JsonResponseUtil.sendJson(exchange, 200, response);
//
//        } catch (Exception e) {
//            // Handle any exception and send a generic error message
//            ApiResponse<Object> error = new ApiResponse<>("Server error", null);
//            JsonResponseUtil.sendJson(exchange, 500, error);
//        }
//    }
//

//
//    private void createPupil(HttpServerExchange exchange) {
//        exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
//            try {
//                Pupil pupil = objectMapper.readValue(data, Pupil.class);
//                PupilService.createPupil(pupil);
//
//                ApiResponse<String> response = new ApiResponse<>("Pupil created successfully.", null);
//                JsonResponseUtil.sendJson(exchange, 200, response);
//            } catch (Exception e) {
//                logger.error("Failed to create pupil", e);
//                ApiResponse<String> error = new ApiResponse<>("Failed to create pupil.", null);
//                JsonResponseUtil.sendJson(ex, 500, error);
//            }
//        });
//    }
//
//
//    private void deletePupil(HttpServerExchange exchange) {
//        try {
//            String[] parts = exchange.getRequestPath().split("/");
//            if (parts.length < 3) {
//                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid URL path. Pupil ID missing.", null));
//                return;
//            }
//
//            long pupilId = Long.parseLong(parts[parts.length - 1]);
//            pupilService.deletePupil(pupilId);
//
//            JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Pupil deleted successfully.", null));
//        } catch (NumberFormatException e) {
//            logger.error("Invalid pupil ID format", e);
//            JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid pupil ID format.", null));
//        } catch (Exception e) {
//            logger.error("Failed to delete pupil", e);
//            JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to delete pupil.", null));
//        }
//    }
//}
