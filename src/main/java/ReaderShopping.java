import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReaderShopping {
    boolean enabled;
    String fileName;
    String format;

    public ReaderShopping(Node node) {
        NodeList listNode = node.getChildNodes();

        for (int i = 0; i < listNode.getLength(); i++) {
            Node currentNode = listNode.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                if (currentNode.getNodeName().equals("enabled")) {
                    enabled = Boolean.parseBoolean(currentNode.getTextContent());
                }
                if (currentNode.getNodeName().equals("fileName")) {
                    fileName = currentNode.getTextContent();
                } else if (currentNode.getNodeName().equals("format")) {//условие поска файла по имени "format"
                    format = currentNode.getTextContent();
                }
            }
        }
    }
}
