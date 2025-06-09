import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.ByteArrayInputStream;


public class XMLProcessor implements DataProcessor {

    @Override
    public void process(String data) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(data.getBytes()));

            doc.getDocumentElement().normalize();
            System.out.println("Processing XML:");
            printElements(doc.getDocumentElement(), "");
        } catch (Exception e) {
            System.out.println("Invalid XML data: " + e.getMessage());
        }
    }

    /**
     * Recursive helper method to print all XML elements.
     * 
     * @param element Current XML element
     * @param indent  Indentation for display
     */
    private void printElements(Element element, String indent) {
        NodeList children = element.getChildNodes();
        System.out.println(indent + "Element: " + element.getTagName());

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);

            if (child instanceof Element) {
                printElements((Element) child, indent + "  ");
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String text = child.getTextContent().trim();
                if (!text.isEmpty()) {
                    System.out.println(indent + "  Value: " + text);
                }
            }
        }
    }
}
