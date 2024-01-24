// CommunicationController.java
package Communication;

import Communication.Channels.Channel;

import java.net.Socket;
import java.util.ArrayList;

public class CommunicationController {
    ArrayList<Channel> channels = new ArrayList<>();

    public CommunicationController(String ip, int port) {
        createConnection(ip, port);
    }

    private void createConnection(String id, int port) {
        try{
            ServerConnector sc = new ServerConnector(port, this);
            new Thread(sc).start();

            ClientConnector cc = new ClientConnector(id, port);
            new Thread(cc).start();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }
}