package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.InputStreamReader;

public class ClientLogic implements Runnable {
    
    private String name;

    private Socket chatConnection;
    private BufferedReader inFromServerChat;
    private PrintWriter outToServerChat;

    private BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

    public ClientLogic(String name) throws IOException {
        this.chatConnection = new Socket("localhost", 9999);
        this.name = name;

        this.inFromServerChat = new BufferedReader(new InputStreamReader(this.chatConnection.getInputStream()));
        this.outToServerChat = new PrintWriter(this.chatConnection.getOutputStream(), true);
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
}
