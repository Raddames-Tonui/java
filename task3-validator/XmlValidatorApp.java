import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class XmlValidatorApp {
    public static void main(String[] args) {
        System.out.println("Timezone: " + TimeZone.getDefault().getID());
        System.out.println("Current Date & Time: " + ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));

        String xmlPath = null;
        String xsdPath = null;

        for (int i = 0; i < args.length - 1; i++) {
            if ("-xml".equals(args[i])) xmlPath = args[++i];
            else if ("-xsd".equals(args[i])) xsdPath = args[++i];
        }

        if (xmlPath == null || xsdPath == null) {
            System.err.println("Usage: java -jar validator.jar -xml file.xml -xsd file.xsd");
            return;
        }

        File xmlFile = new File(xmlPath);
        File xsdFile = new File(xsdPath);
        if (!xmlFile.exists() || !xsdFile.exists()) {
            System.err.println("Either XML or XSD file does not exist.");
            return;
        }

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            System.out.println("✅ XML is valid.");
        } catch (Exception e) {
            System.err.println("❌ XML validation failed: " + e.getMessage());
        }
    }
}
