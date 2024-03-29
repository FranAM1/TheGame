// ClientConnector.java
package Communication;

import java.net.Socket;

public class ClientConnector implements Runnable {
    private Socket socket;
    private CommunicationController cc;
    private int PORT;

    public ClientConnector(CommunicationController cc, int PORT) {
        this.cc = cc;
        this.PORT = PORT;
    }

    @Override
    public void run() {
        while(true) {
            this.socket = new Socket();
            createConnection();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {

                throw new RuntimeException(ex);
            }
        }
    }

    public void createConnection() {
        if (this.cc.getDownChannels() != null) {
            for (int i = 0; i < this.cc.getDownChannels().size(); i++) {
                try {

                    System.out.println("Conectando como cliente al canal: " + i+1);
                    this.socket = new Socket(this.cc.getDownChannels().get(i).getInterlocutor().getIp(), this.PORT);
                    this.cc.addChannel(socket, i);
                    System.out.println("Conexion como cliente establecida");
                } catch (Exception e) {

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {

                        throw new RuntimeException(ex);
                    }
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
}