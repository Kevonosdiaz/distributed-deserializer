package Main;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.HashMap;

public class Deserializer {
    private static int id = 0;
    private static final HashMap<Integer, Object> objectIDs = new HashMap<>();
    // Make use of a list or array of Documents to deserialize
    public Object deserialize(Document document) {
        Element root = document.getRootElement();
        Element element = root.getChild("object");
        Object object = null;
        if (element.getAttribute("length") != null) {
            // Handle array
        }
        // Dynamically load class of the object using "declaringclass" attr but check HashMap
        String className = element.getAttributeValue("class");
        int id = Integer.parseInt(element.getAttributeValue("id"));
        objectIDs.put(id, className);
        return object;
    }

    
}
