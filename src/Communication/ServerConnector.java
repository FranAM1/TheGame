// ServerConnector.java
package Communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private ServerSocket serverSocket;

    private Socket clientSocket;

    private CommunicationController cc;

    private int PORT;


    public ServerConnector(CommunicationController cc, int PORT) {
        this.cc = cc;
        this.PORT = PORT;
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        while (true) {
            try{
                System.out.println("Servidor escuchando en: " + this.PORT);
                createConnection();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createConnection() {
        try {

            System.out.println("Conectando como servidor...");
            this.clientSocket = serverSocket.accept();
            System.out.println("Conexion como servidor establecida");
            new Thread(new PeerIdentificator(this.clientSocket, this)).start();
        } catch (Exception e) {

            System.out.println("ServerConnector error: " + e);
        }
    }

    public CommunicationController getCc() {
        return cc;
    }
}