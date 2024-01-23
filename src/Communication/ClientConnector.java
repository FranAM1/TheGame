// ClientConnector.java
package Communication;

import java.net.Socket;

public class ClientConnector implements Runnable {
    private Socket socket;
    private int port;
    private String address;

    public ClientConnector(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            System.out.println("Connecting to " + this.address + " on port " + this.port);
            this.socket = new Socket(this.address, this.port);
            System.out.println("Connected to " + this.address + " on port " + this.port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}