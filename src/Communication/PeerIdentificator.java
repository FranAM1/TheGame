package Communication;

import java.net.Socket;

public class PeerIdentificator implements Runnable{
    private Socket socket;
    private ServerConnector sc;

    public PeerIdentificator(Socket socket, ServerConnector sc){
        this.socket = socket;
        this.sc = sc;
    }

    @Override
    public void run() {
        if(socket != null){
            System.out.println("IP conexion: " + socket.getInetAddress().getHostAddress());
            try {
                sc.getCc().setSocketToChannel(socket);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
