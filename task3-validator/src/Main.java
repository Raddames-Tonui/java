import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        System.out.println("XML Validator started ...");
        
        String timezone = TimeZone.getDefault().getID(); // Display current JVM timezone
        System.out.println("Current JVM Timezone: "+ timezone);
        
        ZonedDateTime now = ZonedDateTime.now();// Display current date and time in ISO format
        System.out.println("Current Date and Time: "+ now.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));


        String xmlPath = null;
        String xsdPath = null;

        // Parse command line arguments
        for (int i = 0; i < args.length; i++){
            switch (args[i]){
                case "-xml":
                    if (i + 1 < args.length) xmlPath = args[++i];
                    break;
                case "-xsd":
                    if (i + 1 < args.length ) xsdPath = args[++i];
                    break;
                default:
                    System.out.println("Unknown argument: " + args[i]);
            }
        }

        if (xmlPath == null || xsdPath == null){
            System.err.println("Usage: java -jar XmlValidatorApp.jar -xml path/to/file.xml -xsd path/to/schema.xsd");
            return;
        }

        File xmlFile = new File(xmlPath);
        File xsdFile = new File(xsdPath);
    
        if (!xmlFile.exists() || !xsdFile.exists()){
            System.err.println("Error: XML or XSD file does not exist.");
            return;
        }

        System.out.println("XML File: "+ xmlFile.getAbsolutePath());
        System.out.println("XSD File: "+ xsdFile.getAbsolutePath());


    // Additional logic for XML validation would go here
   try {
            // 4. Load schema
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);

            // 5. Create Validator
            Validator validator = schema.newValidator();

            // 6. Validate XML
            validator.validate(new StreamSource(xmlFile));

            System.out.println("✅ XML is valid according to the XSD schema.");
        } catch (SAXException e) {
            System.err.println("❌ XML is NOT valid.");
            System.err.println("Reason: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("❌ Error reading files: " + e.getMessage());
        }


    }    
}
