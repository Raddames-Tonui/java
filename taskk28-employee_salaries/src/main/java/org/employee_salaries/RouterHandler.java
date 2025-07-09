package org.employee_salaries;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathTemplateHandler;
import io.undertow.util.Headers;
import org.employee_salaries.controller.EmployeeController;
import org.employee_salaries.util.MethodAwareHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class RouterHandler implements HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(RouterHandler.class);
    private final PathTemplateHandler pathTemplateHandler;

    public RouterHandler(Connection conn) {
        this.pathTemplateHandler = new PathTemplateHandler();

        // Controllers
        EmployeeController employeeController = new EmployeeController(conn);

        // Root test
        pathTemplateHandler.add("/", this::handleRoot);

        // API Routes
        pathTemplateHandler.add("/api/employees/grouped-by-department", new MethodAwareHandler()
                .on("GET", employeeController::handleGroupedEmployeesByStatus));


    }

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        try {
            pathTemplateHandler.handleRequest(exchange);
        } catch (Exception e) {
            logger.error("RouterHandler exception", e);
            exchange.setStatusCode(500);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send("{\"message\": \"Internal server error.\"}");
        }
    }

    private void handleRoot(HttpServerExchange exchange) {
        exchange.setStatusCode(200);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send("{\"message\": \"Welcome to employee_salaries API\"}");
    }
}
