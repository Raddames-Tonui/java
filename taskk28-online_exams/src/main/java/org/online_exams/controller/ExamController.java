package org.online_exams.controller;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.PathTemplateMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.online_exams.dto.ExamResponseDTO;
import org.online_exams.service.ExamService;
import org.online_exams.util.ApiResponse;
import org.online_exams.util.JsonResponseUtil;

import java.sql.Connection;
import java.util.List;

/**
 * Controller for handling exam-related endpoints.
 */
public class ExamController {
    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);
    private final ExamService examService;

    public ExamController(Connection conn) {
        this.examService = new ExamService(conn);
    }

    /**
     * Handles GET /api/teachers/{teacherId}/exams
     * Fetches all exams set by the given teacher.
     */
    public void handleExamByTeacher(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            try {
                PathTemplateMatch pathParams = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY);
                Long teacherId = Long.parseLong(pathParams.getParameters().get("teacherId"));

                List<ExamResponseDTO> exams = examService.getExamsByTeacherId(teacherId);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Teacher exams retrieved successfully", exams));
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid teacher id", e);
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>(e.getMessage()));
            } catch (Exception e) {
                logger.error("Failed to fetch teacher exams", e);
                JsonResponseUtil.sendJson(exchange, 500, "Failed to fetch teacher exams");
            }
        });
    }

}
