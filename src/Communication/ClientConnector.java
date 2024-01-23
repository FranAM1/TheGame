// ClientConnector.java
package Communication;

import java.net.Socket;

public class ClientConnector implements Runnable {
    private Socket socket;
    private int PORT;
    private String address;

    public ClientConnector(String address, int PORT) {
        this.address = address;
        this.PORT = PORT;
    }

    @Override
    public void run() {

    }
}