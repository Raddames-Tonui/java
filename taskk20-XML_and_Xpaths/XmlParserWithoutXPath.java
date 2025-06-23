// Import core classes to work with XML DOM (Document Object Model)
import org.w3c.dom.*; // Provides Element, NodeList, Document, etc.
import javax.xml.parsers.*; // Provides DocumentBuilder and Factory classes
import java.io.File; // Used to access the XML file from the local file system

public class XmlParserWithoutXPath {
    public static void main(String[] args) throws Exception {

        // Step 1: Load the XML file from the filesystem
        File xmlFile = new File("signatories_model_info.xml");

        // Step 2: Create a factory that builds XML parsers
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        // Enable namespace awareness (important if XML uses namespaces)
        factory.setNamespaceAware(true);

        // Step 3: Use the factory to create a document builder (parser)
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Step 4: Parse the file into a tree (DOM Document object)
        Document doc = builder.parse(xmlFile);

        // Step 5: Get all elements with tag name "item", regardless of namespace
        NodeList items = doc.getElementsByTagNameNS("*", "item");

        // Counter for TABLE_BASED field types
        int tableBasedCount = 0;

        // Informational print
        System.out.println("Elements with field_type='API_BASED':");

        // Step 6: Loop through each <item> element
        for (int i = 0; i < items.getLength(); i++) {
            Element el = (Element) items.item(i); // Cast Node to Element

            // Get attributes
            String fieldType = el.getAttribute("field_type"); // e.g., API_BASED, TABLE_BASED
            String tagName = el.getAttribute("tag_name");     // custom tag name

            // Print tag_name if field_type is API_BASED
            if ("API_BASED".equals(fieldType)) {
                System.out.println(tagName);
            }

            // Increment counter if field_type is TABLE_BASED
            if ("TABLE_BASED".equals(fieldType)) {
                tableBasedCount++;
            }

            // Step 7: Check for duplicate check instructions inside this item
            NodeList children = el.getElementsByTagNameNS("*", "check_duplicate");

            for (int j = 0; j < children.getLength(); j++) {
                Element checkDup = (Element) children.item(j);

                // Grab the associated field that this item is checked against
                Element assoc = (Element) checkDup.getElementsByTagName("associated_field").item(0);
                String associated = assoc.getAttribute("value");

                // Print the mapping of item → associated field
                System.out.println(tagName + " → " + associated);
            }

            // Step 8: Remove certain restricted tags by tag_name
            if ("RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE".equals(tagName)
                || "MAX_RESTRICTED_ACCESS_NATIONALITIES".equals(tagName)
                || "RESTRICTED_ACCESS_NATIONALITIES".equals(tagName)) {
                
                // Remove the element from its parent
                el.getParentNode().removeChild(el);
                
                // Decrement loop index to avoid skipping next element
                i--;
            }

            // Step 9: Change use='MANDATORY' to use='OPTIONAL'
            if ("MANDATORY".equals(el.getAttribute("use"))) {
                el.setAttribute("use", "OPTIONAL");
            }
        }

        // Final count display
        System.out.println("Total TABLE_BASED elements: " + tableBasedCount);
    }
}
