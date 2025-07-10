package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {
    public static void main(String[] args) throws IOException {
        // URL of the API endpoint
        URL url = new URL("http://localhost:8000/api/teachers/1/exams");

        // Open connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set HTTP method to GET
        connection.setRequestMethod("GET");

        // Set request headers if needed
        connection.setRequestProperty("Accept", "application/json");

        // Check response code
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // Read response if successful
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String inputLine;
            StringBuilder response = new StringBuilder();

            // Read line by line
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close(); // Close the stream
            System.out.println("Response:\n" + response.toString());
        } else {
            System.out.println("GET request failed");
        }

        connection.disconnect(); // Always disconnect
    }
}
