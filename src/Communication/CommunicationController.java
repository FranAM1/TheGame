// CommunicationController.java
package Communication;

import Communication.Channels.Channel;

import java.net.Socket;
import java.util.ArrayList;

public class CommunicationController {
    ArrayList<Channel> channels = new ArrayList<>();

    public CommunicationController(String ip, int port) {
        this.loadChannels(ip, port);
    }

    private void loadChannels(String ip, int port) {
        Socket socket = this.createConnection(ip, port);

        Channel channel = new Channel(socket, 1000);
        this.channels.add(channel);
        new Thread(channel).start();
    }

    private Socket createConnection(String id, int port) {
        Socket socket = null;
        try{
            ServerConnector sc = new ServerConnector(port);
            new Thread(sc).start();

            ClientConnector cc = new ClientConnector(id, port);
            new Thread(cc).start();

            socket = cc.getSocket();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return socket;
    }
}