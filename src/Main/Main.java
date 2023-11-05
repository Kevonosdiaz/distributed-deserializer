package Main;

import org.jdom2.Document;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get user input to determine when to stop receiving documents
        String input;
        final int port = 8765;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter 'y' to receive a document, or 'n' to stop receiving documents");

            input = scanner.next();
            // Call receiver() to receive a document
            Document doc = Receiver.receive(port);
            if (doc == null) {
                System.out.println("Could not receive document! Try again after waiting for the sender.");
                continue;
            }

            // Call deserialize() to deserialize it
            Object obj = Deserializer.deserialize(doc);

            // Use Inspector to display object reflectively
            Inspector.inspect(obj, false);
        } while (input.equals("y"));
        System.out.println("Exiting...");
    }
}