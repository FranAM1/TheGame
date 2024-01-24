// ServerConnector.java
package Communication;

import Communication.Channels.Channel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private ServerSocket serverSocket;

    private Socket clientSocket;

    private CommunicationController cc;

    private PeerIdentificator pi;

    public ServerConnector(int port, CommunicationController cc) {
        this.cc = cc;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void killSocket() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try{
            System.out.println("Waiting for connection");
            this.clientSocket = this.serverSocket.accept();
            System.out.println("Connection accepted");

            Channel channel = new Channel(clientSocket, 1000);
            this.cc.getChannels().add(channel);
            new Thread(channel).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}