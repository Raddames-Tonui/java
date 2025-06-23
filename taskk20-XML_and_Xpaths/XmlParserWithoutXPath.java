import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class XmlParserWithoutXPath {
    public static void main(String[] args) throws Exception {
        File xmlFile = new File("signatories_model_info.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().parse(xmlFile);

        NodeList items = doc.getElementsByTagNameNS("*", "item");

        int tableBasedCount = 0;
        System.out.println("Elements with field_type='API_BASED':");
        for (int i = 0; i < items.getLength(); i++) {
            Element el = (Element) items.item(i);
            String fieldType = el.getAttribute("field_type");
            String tagName = el.getAttribute("tag_name");

            if ("API_BASED".equals(fieldType)) {
                System.out.println(tagName);
            }

            if ("TABLE_BASED".equals(fieldType)) {
                tableBasedCount++;
            }

            // Find duplicates
            NodeList children = el.getElementsByTagNameNS("*", "check_duplicate");
            for (int j = 0; j < children.getLength(); j++) {
                Element checkDup = (Element) children.item(j);
                String associated = ((Element) checkDup.getElementsByTagName("associated_field").item(0)).getAttribute("value");
                System.out.println(tagName + " → " + associated);
            }

            // Remove unwanted elements
            if ("RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE".equals(tagName)
                || "MAX_RESTRICTED_ACCESS_NATIONALITIES".equals(tagName)
                || "RESTRICTED_ACCESS_NATIONALITIES".equals(tagName)) {
                el.getParentNode().removeChild(el);
                i--; // Adjust index after removal
            }

            // Update MANDATORY → OPTIONAL
            if ("MANDATORY".equals(el.getAttribute("use"))) {
                el.setAttribute("use", "OPTIONAL");
            }
        }

        System.out.println("Total TABLE_BASED elements: " + tableBasedCount);
    }
}
