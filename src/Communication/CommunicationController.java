// CommunicationController.java
package Communication;

import Communication.Channels.Channel;

import java.net.Socket;
import java.util.ArrayList;

public class CommunicationController {
    ArrayList<Channel> channels = new ArrayList<>();

    public CommunicationController(String ip, int port) {
        channels.add(new Channel());

        createConnection(ip, port);
    }

    private void createConnection(String id, int port) {
        try{
            ServerConnector sc = new ServerConnector(port+1, this);
            new Thread(sc).start();

            ClientConnector cc = new ClientConnector(id, port, this);
            new Thread(cc).start();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setSocketToChannel(Socket socket){
        for(Channel channel : channels){
            if(channel.getSocket() == null){
                channel.loadOutputInput(socket);
                new Thread(channel).start();
            }
        }
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }
}