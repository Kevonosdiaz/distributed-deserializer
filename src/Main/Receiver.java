package Main;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {
    public Document receiver(int port) {
        System.out.println("Receiver: initializing");
        try (ServerSocket server = new ServerSocket(port);
             Socket socket = server.accept();
             Scanner in = new Scanner(socket.getInputStream());)
        {
            System.out.println("Receiver: connected");
            String xmlData = in.next();
            return new SAXBuilder().build(new StringReader(xmlData));
        } catch (IOException | JDOMException e) {
            System.out.println("Could not rebuild XML document after receiving!");
            throw new RuntimeException(e);
        }
    }

}
