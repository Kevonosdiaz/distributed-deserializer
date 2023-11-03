import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {
    public Receiver(int port) {
        System.out.println("Receiver: initializing");
        try (ServerSocket server = new ServerSocket(port);
             Socket socket = server.accept();
             Scanner in = new Scanner(socket.getInputStream());)
        {
            System.out.println("Receiver: connected");
            String next;
            while (in.hasNext()) {
                next = in.nextLine();
                System.out.println("Receiver: data");
                System.out.println(next);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
