import ServerSide.Server;

import java.io.IOException;
import java.util.Scanner;

public class ServerApp {
    
    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Server serverForChat = new Server();

        while (true) {
            System.out.println("Accept a client?");
            
            String choice = reader.nextLine();
            if (choice.toLowerCase().equals("y") || choice.toLowerCase().equals("yes")) {
                System.out.println("Waiting..");
                serverForChat.makeConnection();
                System.out.println("Conneceted!\n\n");
            } else {
                break;
            }
        }



    }
}
