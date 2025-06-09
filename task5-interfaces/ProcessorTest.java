public class ProcessorTest {

    public static void main(String[] args) {
        // Sample JSON and XML strings
        String jsonData = "{\"name\":\"Alice\",\"age\":28,\"city\":\"Nairobi\"}";
        String xmlData = "<person><name>Alice</name><age>28</age><city>Nairobi</city></person>";

        // Instantiate processors
        DataProcessor jsonProcessor = new JsonProcessor();
        DataProcessor xmlProcessor = new XMLProcessor();

        // Process JSON
        System.out.println("==== JSON Output ====");
        jsonProcessor.process(jsonData);

        // Process XML
        System.out.println("\n==== XML Output ====");
        xmlProcessor.process(xmlData);
    }
}
