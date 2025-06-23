import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.File;

public class XmlParserWithXPath {

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("signatories_model_info.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        XPath xpath = XPathFactory.newInstance().newXPath();

        // === a. Elements with field_type="API_BASED" ===
        System.out.println("Elements with field_type='API_BASED':");
        XPathExpression apiExpr = xpath.compile("//item[@field_type='API_BASED']/@tag_name");
        NodeList apiBasedNodes = (NodeList) apiExpr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < apiBasedNodes.getLength(); i++) {
            System.out.println(apiBasedNodes.item(i).getNodeValue());
        }

        // === b. Count elements with field_type="TABLE_BASED" ===
        XPathExpression tableExpr = xpath.compile("count(//item[@field_type='TABLE_BASED'])");
        Double tableCount = (Double) tableExpr.evaluate(doc, XPathConstants.NUMBER);
        System.out.println("Total TABLE_BASED elements: " + tableCount.intValue());

        // === c. Elements with check_duplicate and associated_field ===
        System.out.println("Elements to check for duplicates:");
        XPathExpression dupExpr = xpath.compile("//item[check_duplicate]");
        NodeList dupItems = (NodeList) dupExpr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < dupItems.getLength(); i++) {
            Element item = (Element) dupItems.item(i);
            String tagName = item.getAttribute("tag_name");
            NodeList assocList = item.getElementsByTagName("associated_field");
            for (int j = 0; j < assocList.getLength(); j++) {
                Element assoc = (Element) assocList.item(j);
                String value = assoc.getAttribute("value");
                System.out.println(tagName + " → " + value);
            }
        }

        // === d. Remove specific elements ===
        String[] toRemove = {
            "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
            "MAX_RESTRICTED_ACCESS_NATIONALITIES",
            "RESTRICTED_ACCESS_NATIONALITIES"
        };

        for (String tag : toRemove) {
            XPathExpression removeExpr = xpath.compile("//item[@tag_name='" + tag + "']");
            Node node = (Node) removeExpr.evaluate(doc, XPathConstants.NODE);
            if (node != null && node.getParentNode() != null) {
                node.getParentNode().removeChild(node);
                System.out.println("Removed element with tag_name: " + tag);
            }
        }

        // === e. Change all use="MANDATORY" to use="OPTIONAL" ===
        XPathExpression mandatoryExpr = xpath.compile("//item[@use='MANDATORY']");
        NodeList mandatoryItems = (NodeList) mandatoryExpr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < mandatoryItems.getLength(); i++) {
            Element el = (Element) mandatoryItems.item(i);
            el.setAttribute("use", "OPTIONAL");
            System.out.println("Updated use='MANDATORY' to use='OPTIONAL' for tag_name: " + el.getAttribute("tag_name"));
        }
    }
}
