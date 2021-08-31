package ServerSide;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    
    private ServerSocket chatSocket;
    private List<Socket> chatClientConnections = new ArrayList<>();

    public Server() throws IOException {
        this.chatSocket = new ServerSocket(9999);
    }

    public void sendMessage(String message) throws IOException {          // Writes the given message to all clients chatboxes
        for (Socket clientSocket: chatClientConnections) {                
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(message);
        }
    }

    public void makeConnection() throws IOException {                     // Establishes connection with the client, and starts
        Socket clientChatSocket = this.chatSocket.accept();               // a thread to handle the listening for client output
        chatClientConnections.add(clientChatSocket);

        ChatListenerAndPusher handler = new ChatListenerAndPusher(clientChatSocket, this);
        ExecutorService thread = Executors.newFixedThreadPool(1);
        thread.execute(handler);
    }
}
