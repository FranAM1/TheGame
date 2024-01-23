// ServerConnector.java
package Communication;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerConnector implements Runnable {
    private ServerSocket serverSocket;

    public ServerConnector(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

    }
}