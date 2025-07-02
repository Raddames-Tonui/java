package org.tonui.controller;

import io.undertow.server.HttpServerExchange;

public class SubjectController {
    public void handleRouting(HttpServerExchange exchange) {
        String path = exchange.getRequestPath();
        String method = exchange.getRequestMethod().toString();

    }
}
