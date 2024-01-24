// ClientConnector.java
package Communication;

import java.io.IOException;
import java.net.Socket;

public class ClientConnector implements Runnable {
    private Socket socket;
    private int port;
    private String id;

    public ClientConnector(String id, int port) {
        this.port = port;
        this.id = id;
    }

    @Override
    public void run() {
        while(socket == null) {
            try {
                socket = new Socket("localhost", port);
                System.out.println("Client: connected to server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}