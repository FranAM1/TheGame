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
                for (int i = 0; i < this.sc.getCc().getDownChannels().size(); i++) {
                    this.sc.getCc().addChannel(socket, i);
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
