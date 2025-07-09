package org.online_exams.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.PathTemplateMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.online_exams.dto.PupilExamRankingDTO;
import org.online_exams.dto.PupilExamReportDTO;
import org.online_exams.dto.TopPupilDTO;
import org.online_exams.service.ReportService;
import org.online_exams.util.ApiResponse;
import org.online_exams.util.JsonResponseUtil;

import java.sql.Connection;
import java.util.List;

/**
 * Handles exam report requests for pupils.
 */
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ReportService reportService;

    public ReportController(Connection conn) {
        this.reportService = new ReportService(conn);
    }

    /**
     * Handles GET /api/reports/pupil/{pupilId}/exam/{examId}
     */
    public void handlePupilExamReport(HttpServerExchange exchange){
        exchange.dispatch(() -> {
            try {
                /**
                 * Undertow uses PathTemplateMatch.ATTACHMENT_KEY to store path parameters matched from the URL (e.g., {pupilId} and {examId}).
                 * getAttachment(...) retrieves that match object for the current request.
                 * From there, you can call getParameters().get("key") to access dynamic path segments.
                 */
                PathTemplateMatch pathParams = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY);
                Long pupilId = Long.parseLong(pathParams.getParameters().get("pupilId"));
                Long examId = Long.parseLong(pathParams.getParameters().get("examId"));

                PupilExamReportDTO report = reportService.getPupilExamReport(pupilId, examId);

                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Report generated successfully", report));

            } catch (NumberFormatException e){
                logger.warn("Invalid pupilId or examId", e);
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid ID parameter"));
            } catch (IllegalArgumentException e){
                logger.warn("Validation failed", e);
                JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>(e.getMessage()));
            } catch (Exception e){
                logger.error("Failed to generate report", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to generate report"));
            }
        });
    }


    /**
     * Handles GET /api/reports/exam/{examId}/top5
     */
    public void handleTop5Pupils(HttpServerExchange exchange){
        exchange.dispatch(() -> {
            try {
                String examIdStr = exchange.getAttachment(PathTemplateMatch.ATTACHMENT_KEY)
                        .getParameters()
                        .get("examId");
                Long examId = Long.parseLong(examIdStr);
                List<TopPupilDTO> topPupils = reportService.getTop5Pupils(examId);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Top 5 pupils retrieved successfully", topPupils));
            }catch (IllegalArgumentException e){
                logger.warn("Invalid examId", e);
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid examId"));
            } catch (Exception e){
                logger.error("Failed to get top 5 pupils", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to get top 5 pupils"));
            }

        });
    }

    /**
     * Handles /api/reports/exams/scores
    */
    public void handleAllExamRankings(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            try {
                List<PupilExamRankingDTO> rankings = reportService.getAllExamRankings();
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Exam rankings generated", rankings));
            } catch (Exception e) {
                logger.error("Failed to fetch rankings", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Failed to fetch exam rankings", null));
            }
        });
    }

}
