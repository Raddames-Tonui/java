package org.tonui.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Demonstrates making a GET HTTP request using HttpURLConnection
 * to fetch data from your own Undertow API.
 */
public class ClientHttpURLConnection {

    public static void main(String[] args) {
        String endpoint = "http://localhost:8000/api/pupils";

        try {
            // 1. Create URL object
            URL url = new URL(endpoint);

            // 2. Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 3. Set HTTP method to GET
            connection.setRequestMethod("GET");

            // 4. Set headers (optional)
            connection.setRequestProperty("Accept", "application/json");

            // 5. Read the response code
            int status = connection.getResponseCode();
            System.out.println("HTTP Response Code: " + status);

            // 6. Read the response body
            if (status == HttpURLConnection.HTTP_OK) {
                // Read response line-by-line
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Close the stream
                in.close();

                // Print the JSON response
                System.out.println("Response JSON:\n" + response.toString());

            } else {
                System.out.println("Request failed. Server returned code: " + status);
            }

            // 7. Close the connection
            connection.disconnect();

        } catch (Exception e) {
            System.err.println("Error during GET request: " + e.getMessage());
        }
    }
}
