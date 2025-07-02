package org.tonui;


import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.tonui.controller.PupilController;
import org.tonui.controller.SubjectController;


/**
 * Acts as a central dispatcher: handles all incoming routes.
 * Delegates to relevant controllers based on path.
 */
public class RouterHandler implements HttpHandler {
    private final PupilController pupilController = new PupilController();
    private final SubjectController subjectController = new SubjectController();

    @Override
    public void handleRequest(HttpServerExchange exchange){
        String path = exchange.getRequestPath();

        if (path.equals("/")){
            exchange.setStatusCode(200);
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            exchange.getResponseSender().send(" Welcome to the Undertow API Server");

    //-------------- Route: /api/pupils (delegated to PupilController)-------------
        } else if (path.startsWith("/api/pupils")){
            pupilController.handleRouting(exchange);

    // --------------Route: /api/subjects (delegated to PupilController)--------------
        } else if (path.startsWith("/api/subjects")){
            subjectController.handleRouting(exchange);


        } else{
            exchange.setStatusCode(404);
            exchange.getResponseSender().send("404 Not Found" + path);
        }
    }

}
