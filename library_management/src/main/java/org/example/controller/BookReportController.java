package org.example.controller;

import io.undertow.server.HttpServerExchange;
import org.example.dto.BookReportDTO;
import org.example.service.BookReportService;
import org.example.util.ApiResponse;
import org.example.util.JsonResponseUtil;
import org.example.util.NotFoundException;

import java.sql.Connection;
import java.util.Deque;
import java.util.List;

/**
 * Controller class handling HTTP requests for book reports
 */
public class BookReportController {
    private final BookReportService reportService;

    public BookReportController(Connection conn) {
        this.reportService = new BookReportService(conn);
    }

    public void getBooksByCategory(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            Deque<String> categoryParam = exchange.getQueryParameters().get("categoryId");
            if (categoryParam == null || categoryParam.isEmpty()) {
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("categoryId is required"));
                return;
            }

            try {
                Long categoryId = Long.parseLong(categoryParam.getFirst());
                List<BookReportDTO> result = reportService.getBooksByCategory(categoryId);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Success", result));
            } catch (NotFoundException e) {
                JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>(e.getMessage()));
            } catch (Exception e) {
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Error: " + e.getMessage()));
            }
        });
    }

    public void getBooksBySubcategory(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            Deque<String> subcategoryParam = exchange.getQueryParameters().get("subcategoryId");
            if (subcategoryParam == null || subcategoryParam.isEmpty()) {
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("subcategoryId is required"));
                return;
            }

            try {
                Long subcategoryId = Long.parseLong(subcategoryParam.getFirst());
                List<BookReportDTO> result = reportService.getBooksBySubcategory(subcategoryId);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Success", result));
            } catch (NotFoundException e) {
                JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>(e.getMessage()));
            } catch (Exception e) {
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Error: " + e.getMessage()));
            }
        });
    }
}
