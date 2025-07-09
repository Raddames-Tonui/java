package org.employee_salaries.controller;

import io.undertow.server.HttpServerExchange;
import org.employee_salaries.dto.GroupedEmployeeResponse;
import org.employee_salaries.service.EmployeeService;
import org.employee_salaries.util.ApiResponse;
import org.employee_salaries.util.JsonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Set;

public class EmployeeController {
    private final EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(Connection conn) {
        this.employeeService = new EmployeeService(conn);
    }

    /**
     * Endpoint: GET /api/employees/grouped-by-department?status=new
     * Groups employees by department based on status code
     */
    public void handleGroupedEmployeesByStatus(HttpServerExchange exchange) {
        exchange.dispatch(() -> {
            try {
                String status = exchange.getQueryParameters().getOrDefault("status", null) != null
                        ? exchange.getQueryParameters().get("status").getFirst()
                        : null;

                // Validate query param
                Set<String> allowedStatuses = Set.of("new", "active", "onleave", "leaving", "terminated");

                if (status == null || status.isBlank() || !allowedStatuses.contains(status.toLowerCase())) {
                    JsonResponseUtil.sendJson(exchange, 400, new ApiResponse<>("Invalid or missing 'status' query parameter"));
                    return;
                }

                var result = employeeService.getGroupedEmployeesByStatus(status.toLowerCase());

                if (result.isEmpty()) {
                    JsonResponseUtil.sendJson(exchange, 404, new ApiResponse<>("No employees found for status: " + status));
                    return;
                }

                // Wrap in serializable DTO
                GroupedEmployeeResponse groupedResponse = new GroupedEmployeeResponse(result);
                JsonResponseUtil.sendJson(exchange, 200, new ApiResponse<>("Grouped employees fetched", groupedResponse));

            } catch (Exception e) {
                logger.error("Failed to fetch grouped employees", e);
                JsonResponseUtil.sendJson(exchange, 500, new ApiResponse<>("Server error occurred"));
            }
        });
    }
}
