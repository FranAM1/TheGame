// ServerConnector.java
package Communication;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnector implements Runnable {
    private ServerSocket serverSocket;

    private PeerIdentificator pi;

    public ServerConnector(int port) {
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
            this.serverSocket.accept();
            System.out.println("Connection accepted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}