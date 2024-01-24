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
                System.out.println("Client: waiting for server");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
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