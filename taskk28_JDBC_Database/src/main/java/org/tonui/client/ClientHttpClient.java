package org.tonui.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Demonstrates making a GET HTTP request using Java's modern HttpClient (Java 11+)
 * to fetch all pupils from your Undertow API.
 */
public class ClientHttpClient {

    public static void main(String[] args) {
        // 1. Define the API endpoint
        String apiUrl = "http://localhost:8000/api/pupils";

        // 2. Create an HttpClient instance (can be reused across requests)
        HttpClient client = HttpClient.newHttpClient();

        // 3. Build the HTTP GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))                   // target URI
                .header("Accept", "application/json")      // optional headers
                .GET()                                     // request type
                .build();

        try {
            // 4. Send the request and get the response synchronously
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 5. Print status and response
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body:\n" + response.body());

        } catch (IOException | InterruptedException e) {
            System.err.println("Request failed: " + e.getMessage());
        }
    }
}
