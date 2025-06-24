package com.example.payloadJson;

import com.example.payload.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class App {

    public static void main(String[] args) {
        
        try {
            // Create Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Load the JSON file from resources folder
            File file = new File("src/main/resources/Reg-Payload.json");

            // Deserialize JSON to Java object
            RegPayloadWrapper regPayloadWrapper = objectMapper.readValue(file, RegPayloadWrapper.class);

            // Access sample data
            System.out.println("Action: " + regPayloadWrapper.getAction());
            System.out.println("First Name: " + regPayloadWrapper.getPayload());
            System.out.println("City: " + regPayloadWrapper.getPayload().getCity());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
