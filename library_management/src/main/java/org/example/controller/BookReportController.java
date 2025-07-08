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

    // 1.3 Books by author
    public void getBooksByAuthor(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            Deque<String> authorParam = exchange.getQueryParameters().get("authorId");
            if (authorParam == null || authorParam.isEmpty()) {
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("authorId is required"));
                return;
            }

            try {
                Long authorId = Long.parseLong(authorParam.getFirst());
                List<BookReportDTO> result = reportService.getBooksByAuthor(authorId);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Books retrieved Successfully", result));
            } catch (NotFoundException e) {
                JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>(e.getMessage()));
            } catch (Exception e) {
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Error: " + e.getMessage()));
            }
        });
    }

    // 1.4
    public void getBooksByLibrarian(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            Deque<String> librarianParam = exchange.getQueryParameters().get("librarianId");
            if (librarianParam == null || librarianParam.isEmpty()) {
                JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("librarianId is required"));
                return;
            }

            try {
                Long librarianId = Long.parseLong(librarianParam.getFirst());
                List<BookReportDTO> result = reportService.getBooksByLibrarian(librarianId);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Books retrieved Successfully", result));
            } catch (NotFoundException e) {
                JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>(e.getMessage()));
            } catch (Exception e) {
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Error: " + e.getMessage()));
            }
        });
    }


}
