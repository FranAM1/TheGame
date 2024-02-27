// ServerConnector.java
package Communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private ServerSocket serverSocket;

    private Socket clientSocket;

    private CommunicationController cc;


    public ServerConnector(CommunicationController cc) {
        this.cc = cc;
        try {
            this.serverSocket = new ServerSocket(10000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        while (true) {
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
    }

    public CommunicationController getCc() {
        return cc;
    }
}