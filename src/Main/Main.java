package Main;

import org.jdom2.Document;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get user input to determine when to stop receiving documents
        String input;
        final int port = 8765;
        while (true) {
            // Call receiver() to receive a document
            Document doc = Receiver.receive(port);

            // Call deserialize() to deserialize it
            Object obj = Deserializer.deserialize(doc);

            System.out.println("Should be inspecting now!");
            // Use Inspector to display object reflectively
            Inspector.inspect(obj, false);
        }
    }
}