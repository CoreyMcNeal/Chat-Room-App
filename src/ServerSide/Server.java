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

    public void sendMessage(String message) throws IOException {
        for (Socket clientSocket: chatClientConnections) {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(message);
        }
    }

    public void makeConnection() throws IOException {
        Socket clientChatSocket = this.chatSocket.accept();
        chatClientConnections.add(clientChatSocket);

        ChatListenerAndPusher handler = new ChatListenerAndPusher(clientChatSocket, this);
        ExecutorService thread = Executors.newFixedThreadPool(1);
        thread.execute(handler);
    }

    public void connectingLoop(int numberOfThreads) throws IOException {
        int i = 0;
        while (i < numberOfThreads) {
            makeConnection();
            i++;
        }
    }
    
}
