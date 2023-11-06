package Main;

import org.jdom2.Document;
import org.jdom2.Element;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.HashMap;

public class Deserializer {
    public static final HashMap<Integer, Object> objectIDs = new HashMap<>();
    // Make use of a list or array of Documents to deserialize
    public static Object deserialize(Document document) {
        Element root = document.getRootElement();
        Element element = root.getChild("object");
        Object object = null;
        List<Element> values = element.getChildren("value");
        try {
            if (element.getAttribute("length") != null) {
                // Handle array by loading it
                int length = Integer.parseInt(element.getAttributeValue("length"));
                Class<?> classObj = Class.forName(element.getAttributeValue("class"));

                object = Array.newInstance(classObj.getComponentType(), length);
                for (int i = 0; i < length; i++) {
                    // Populate object with all values from XML
                    Element value = values.get(i);
                    if (value.getText().equals("null")) {
                        Array.set(object, i, null);
                        continue;
                    }
                    if (classObj.getComponentType().isPrimitive()) {
                        if (classObj.getComponentType().equals(int.class)) {
                            Array.set(object, i, Integer.parseInt(value.getText()));
                        } else if (classObj.getComponentType().equals(double.class)) {
                            Array.set(object, i, Double.parseDouble(value.getText()));
                        } else if (classObj.getComponentType().equals(char.class)) {
                            Array.set(object, i, value.getText().charAt(0));
                        } else if (classObj.getComponentType().equals(boolean.class)) {
                            Array.set(object, i, Boolean.parseBoolean(value.getText()));
                        } else if (classObj.getComponentType().equals(byte.class)) {
                            Array.set(object, i, Byte.parseByte(value.getText()));
                        } else if (classObj.getComponentType().equals(short.class)) {
                            Array.set(object, i, Short.parseShort(value.getText()));
                        } else if (classObj.getComponentType().equals(long.class)) {
                            Array.set(object, i, Long.parseLong(value.getText()));
                        } else if (classObj.getComponentType().equals(float.class)) {
                            Array.set(object, i, Float.parseFloat(value.getText()));
                        }
                    } else {
                        Integer id = Integer.parseInt(value.getText());
                        Array.set(object, i, objectIDs.get(id));
                    }
                }
                return object;
            }

            if (element.getAttribute("set-length") != null) {
                // Handle HashSet by loading it
                int length = Integer.parseInt(element.getAttributeValue("set-length"));
                object = new HashSet<>();
                String componentType = element.getAttributeValue("component-type");
                for (int i = 0; i < length; i++) {
                    // Populate object with all values from XML
                    Element value = values.get(i);
                    if (value.getText().equals("null")) {
                        ((HashSet) object).add(null);
                        continue;
                    }
                    if (componentType.equals("java.lang.Integer")) {
                        ((HashSet) object).add(Integer.parseInt(value.getText()));
                    } else if (componentType.equals("java.lang.Double")) {
                        ((HashSet) object).add(Double.parseDouble(value.getText()));
                    } else if (componentType.equals("java.lang.Character")) {
                        ((HashSet) object).add(value.getText().charAt(0));
                    }
                }
                return object;
            }

            int id = Integer.parseInt(element.getAttributeValue("id"));
            if (objectIDs.containsKey(id)) {
                return objectIDs.get(id);
            }
            // Dynamically load class of the object using "class" attr but check HashMap
            String className = element.getAttributeValue("class");
            // Create an instance of the class
            Class<?> classObj = Class.forName(className);
            Constructor<?> constructor = classObj.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object obj = constructor.newInstance();
            for (Element e : values) {
                Class<?> fieldClass = Class.forName(e.getAttributeValue("declaringclass"));
                String fieldName = e.getAttributeValue("name");
                Field f = classObj.getDeclaredField(fieldName);
                f.setAccessible(true);
                String text = e.getText();
                if (text.equals("null")) {
                    f.set(obj, null);
                    continue;
                }
                if (e.getAttribute("reference") != null) {
                    // Dealing with a reference tag
                    int refID = Integer.parseInt(text);
                    f.set(obj, objectIDs.get(refID));
                }

                // Deal with primitive by creating instance using getText
                f.set(obj, toWrapper(fieldClass).getConstructor(String.class).newInstance(text));
            }

            objectIDs.put(id, obj);
            return object;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | InaccessibleObjectException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    // Code from https://stackoverflow.com/questions/1704634/simple-way-to-get-wrapper-class-type-in-java
    static Class<?> toWrapper(Class<?> clazz) {
        if (!clazz.isPrimitive())
            return clazz;

        if (clazz == Integer.TYPE)
            return Integer.class;
        if (clazz == Long.TYPE)
            return Long.class;
        if (clazz == Boolean.TYPE)
            return Boolean.class;
        if (clazz == Byte.TYPE)
            return Byte.class;
        if (clazz == Character.TYPE)
            return Character.class;
        if (clazz == Float.TYPE)
            return Float.class;
        if (clazz == Double.TYPE)
            return Double.class;
        if (clazz == Short.TYPE)
            return Short.class;
        if (clazz == Void.TYPE)
            return Void.class;

        return clazz;
    }

    
}
