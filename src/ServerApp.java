import ServerSide.Server;
import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) throws IOException {

        Server serverForChat = new Server();

        while (true) {                                      // Waiting for connections from clients
            System.out.println("Waiting for Client...");
            serverForChat.makeConnection();
            System.out.println("Connected!\n\n");
        }



    }
}
