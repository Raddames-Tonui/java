package org.tonui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.tonui.payload.RegPayloadWrapper;

import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try{
            // Create Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Load JSON file from the resources folder
            File file = new File("taskk23-json_jackson/src/main/resources/Reg-Payload.json");

            // Deserialize JSON to java Object
            RegPayloadWrapper regPayloadWrapper = objectMapper.readValue(file, RegPayloadWrapper.class );

            // Access Sample data
            System.out.println("---------------------Java POJO -------------------------");
            System.out.println("Action: " + regPayloadWrapper.getAction());
            System.out.println("Payload: "+ regPayloadWrapper.getPayload());


        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}