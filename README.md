## Setup
*Requires user to use port forwarding in their router settings (common IP Addressses to access settings in the browser are 192.168.0.1 and 192.168.1.1. Look up your own respective router's IP address if those don't work). IP must be owned by the device running the server, and the port is 9999*

Have at least one device in the local network run the ServerApp.
  - javac ServerApp.java
  - java ServerApp 

For each client, run the ClientApp which is located in the same directory. 
  - javac ClientApp.java
  - java ClientApp

The Client program will ask for two things:
  - IP Address (The Host device IP)
  - Username (Which identifies the user in chat)

Once the IP and Username are submitted under the correct parameters, the user will enter a chatroom with other clients connected to the same IP under the same Local Area Network.

When done, close the program by using the Exit button, or clicking the X of the window.

## What I Used, and What I Learned
  - Threading in Java
  - Java Swing Framework
  - Java Sockets and ServerSockets
  - Reading Java Inputs and Outputs over a socket connection

## Future
  - Improve from connecting over LAN, to connecting clients over the internet
  - Add ability to send files over chat room connection
