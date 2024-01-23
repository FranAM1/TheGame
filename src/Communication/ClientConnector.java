// ClientConnector.java
package Communication;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnector implements Runnable {
    private Socket socket;
    private int port;
    private String id;

    private Scanner sc;

    public ClientConnector(String id, int port) {
        sc = new Scanner(System.in);
        try {
            this.socket = new Socket(id, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String message = sc.nextLine();

        try {
            this.socket.getOutputStream().write(message.getBytes());
            this.socket.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
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