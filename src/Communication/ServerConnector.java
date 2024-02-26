// ServerConnector.java
package Communication;

import Communication.Channels.Channel;
import Game.TheGameController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private ServerSocket serverSocket;

    private Socket clientSocket;

    private CommunicationController cc;


    public ServerConnector(int port, CommunicationController cc) {
        this.cc = cc;
        try {
            this.serverSocket = new ServerSocket(port);
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
            cc.setSocketToChannel(clientSocket);
            new Thread(new PeerIdentificator(this.clientSocket, this)).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CommunicationController getCc() {
        return cc;
    }
}