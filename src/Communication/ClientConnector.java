// ClientConnector.java
package Communication;

import Communication.Interlocutors.Interlocutor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientConnector implements Runnable {
    private Socket socket;
    private CommunicationController cc;
    private int port;
    private String id;

    public ClientConnector(String id, int port, CommunicationController cc) {
        this.port = port;
        this.id = id;
        this.cc = cc;
    }

    @Override
    public void run() {
        while(socket == null) {
            try {
                socket = new Socket(id, port);
                cc.setSocketToChannel(socket);
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