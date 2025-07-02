package org.tonui;

import io.undertow.Undertow;
import org.tonui.controller.PupilController;

/**
 * Main entry point: boots Undertow server on port 8000.
 * Delegates all request handling to RouterHandler.
 */
public class Server {
    public static void main(String[] args) {

        // Set up undertow HTTP server
        Undertow server = Undertow.builder()
                .addHttpListener(8000, "localhost")
                .setHandler(new RouterHandler())
                .build();

        server.start();
        System.out.println("Undertow Server Started on http://localhost:8000");
    }
}
