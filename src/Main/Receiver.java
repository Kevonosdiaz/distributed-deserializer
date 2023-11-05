package Main;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static Document receive(int port) {
        System.out.println("Receiver: initializing");
        try (ServerSocket server = new ServerSocket(port))
        {

            Socket socket = server.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder receivedXMLBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                receivedXMLBuilder.append(line);
            }
            String receivedXML = receivedXMLBuilder.toString();
            System.out.println("Receiver: connected");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document receivedDoc = saxBuilder.build(new StringReader(receivedXML));
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            StringWriter sw = new StringWriter();
            xmlOutputter.output(receivedDoc, sw);
            StringBuffer sb = sw.getBuffer();
            System.out.println(sb.toString());

            return receivedDoc;
        } catch (IOException | JDOMException e) {
            System.out.println("Could not rebuild XML document after receiving!");
            throw new RuntimeException(e);
        }
    }

}
