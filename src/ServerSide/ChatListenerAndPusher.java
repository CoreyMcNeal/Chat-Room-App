package ServerSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatListenerAndPusher implements Runnable {

    private Socket clientChatSocket;
    private Server server;

    public ChatListenerAndPusher(Socket clientChatSocket,Server server) {  
        this.clientChatSocket = clientChatSocket;
        this.server = server;
    }


    @Override
    public void run() {                             // Listens for client output, and sends the message to all the clients connected.
        BufferedReader inFromClient;
        try {
            while (true) {
                inFromClient = new BufferedReader(new InputStreamReader(this.clientChatSocket.getInputStream()));
                String message = inFromClient.readLine();
                if (!(message == null)) {
                    this.server.sendMessage(message);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Client connection closed.");
        }
        
    }
    
}
