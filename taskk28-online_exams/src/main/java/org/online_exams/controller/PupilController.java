package org.online_exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.PathTemplateMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.online_exams.dto.pupil.PupilDTO;
import org.online_exams.model.Pupil;
import org.online_exams.service.PupilService;
import org.online_exams.util.ApiResponse;
import org.online_exams.util.JsonResponseUtil;

import java.sql.Connection;
import java.util.List;

/**
 * Handles all HTTP interactions related to pupils.
 * Validates input, delegates to the service layer, and prepares HTTP responses.
 */
public class PupilController {
    private static final Logger logger = LoggerFactory.getLogger(PupilController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PupilService pupilService;

    public PupilController(Connection conn) {
        this.pupilService = new PupilService(conn);
    }

    /**
     * Handles GET /api/pupils → Get all pupils
     */
    public void handleGetAll(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            try {
                List<PupilDTO> pupils = pupilService.getAllPupils();
                String message = pupils.isEmpty() ? "No pupils found." : "Pupils retrieved successfully.";
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>(message, pupils));
            } catch (Exception e) {
                logger.error("Failed to fetch pupils", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to retrieve pupils", null));
            }
        });
    }

    /**
     * Handles GET /api/pupils/{id} → Get single pupil
     */
    public void handleGetById(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            try {
                String idStr = extractPathParam(exchange, "id");
                long id = Long.parseLong(idStr);

                PupilDTO pupil = pupilService.getPupilById(id);
                if (pupil == null) {
                    JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>("Pupil not found", null));
                } else {
                    JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Pupil retrieved", pupil));
                }
            } catch (NumberFormatException e) {
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid pupil ID", null));
            } catch (Exception e) {
                logger.error("Error fetching pupil", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Server error", null));
            }
        });
    }
 
    /**
     * Handles POST /api/pupils → Create a new pupil
     */
    public void handleCreate(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
            try {
                Pupil pupil = objectMapper.readValue(data, Pupil.class);
                PupilDTO created = pupilService.createPupil(pupil);

                if (created == null){
                    logger.warn("Pupil creation failed: repository returned null.");
                    JsonResponseUtil.sendJson(ex, 400, new ApiResponse<>("Failed to create pupil"));
                    return;
                }

                JsonResponseUtil.sendJson(ex, 201, new ApiResponse<>("Pupil created successfully", created));
            }catch (IllegalArgumentException e){
                logger.warn("Validation error: " + e.getMessage());
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Validation error", null));
            }catch (Exception e) {
                logger.error("Failed to create pupil", e);
                JsonResponseUtil.sendJson(ex, 500, new ApiResponse<>("Failed to create pupil", null));
            }
        });
    }

    /**
     * Handles PUT /api/pupils/{id} → Update an existing pupil
     */
    public void handleUpdate(HttpServerExchange exchange) {
        exchange.getRequestReceiver().receiveFullBytes((ex, data) -> {
            try {
                String idStr = extractPathParam(exchange, "id");
                long id = Long.parseLong(idStr);
                Pupil updatedPupil = objectMapper.readValue(data, Pupil.class);

                boolean success = pupilService.updatePupil(id, updatedPupil);
                if (success) {
                    JsonResponseUtil.sendJson(ex, 200, new ApiResponse<>("Pupil updated successfully", null));
                } else {
                    JsonResponseUtil.sendJson(ex, 404, new ApiResponse<>("Pupil not found", null));
                }
            } catch (NumberFormatException e) {
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid pupil ID", null));
            } catch (Exception e) {
                logger.error("Failed to update pupil", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to update pupil", null));
            }
        });
    }

    /**
     * Handles DELETE /api/pupils/{id} → Delete a pupil
     */
    public void handleDelete(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            try {
                String idStr = extractPathParam(exchange, "id");
                long id = Long.parseLong(idStr);

                boolean deleted = pupilService.deletePupil(id);
                if (deleted) {
                    JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Pupil deleted successfully", null));
                } else {
                    JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>("Pupil not found", null));
                }
            } catch (NumberFormatException e) {
                logger.error("Invalid pupil ID", e);
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid pupil ID", null));
            } catch (Exception e) {
                logger.error("Failed to delete pupil", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to delete pupil", null));
            }
        });
    }

    /**
     * Utility method to extract a path parameter from the route
     */
    private String extractPathParam(HttpServerExchange exchange, String param) {
        return exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY).getParameters().get(param);
    }
}
