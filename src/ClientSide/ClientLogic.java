package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.io.InputStreamReader;

public class ClientLogic implements Runnable {
    
    private String name;
    private ClientGUI gui;

    private Socket chatConnection;
    private BufferedReader inFromServerChat;
    private PrintWriter outToServerChat;

    public ClientLogic(String name, ClientGUI gui, String hostIP) throws IOException {      // establishes connection to server
        this.chatConnection = new Socket(hostIP, 9999);                                    
        this.name = name;
        this.gui = gui;

        this.inFromServerChat = new BufferedReader(new InputStreamReader(this.chatConnection.getInputStream()));
        this.outToServerChat = new PrintWriter(this.chatConnection.getOutputStream(), true);
    }

    public void sendMessage(String message) throws IOException {            // sends the username and message to the server
        this.outToServerChat.println(this.name + " says: " + message);
    }

    @Override
    public void run() {                                                     // Grabbing messages from the server
        try {                                                               // continuous, using threading
            while (true) {
                String receivedMessage = this.inFromServerChat.readLine();
                if (receivedMessage == null) {
                    continue;
                }

                this.gui.addToChat(receivedMessage);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem reading messages from server");
        }
    }
}
