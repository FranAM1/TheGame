// CommunicationController.java
package Communication;

import Communication.Channels.Channel;
import Communication.Interlocutors.Interlocutor;
import DTO.AppFrame;
import DTO.DataFrame;
import Enums.PeerLocation;
import MainController.TheGamePeerController;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class CommunicationController {
    private HashMap<String, Channel> channels = new HashMap<>();
    private ArrayList<Channel> downChannels = new ArrayList<>();
    private TheGamePeerController tgpc;

    public CommunicationController(TheGamePeerController tgpc, int myPort, int otherPort) {
        this.tgpc = tgpc;
        createChannels();

        ClientConnector clientConnector = new ClientConnector(this, otherPort);
        Thread clientConnectorThread = new Thread(clientConnector);
        clientConnectorThread.setName("ClientConnector");
        clientConnectorThread.start();

        ServerConnector serverConnector = new ServerConnector(this, myPort);
        Thread serverConnectorThread = new Thread(serverConnector);
        serverConnectorThread.setName("ServerConnector");
        serverConnectorThread.start();
    }

    public void sendObject(Object object, Interlocutor interlocutor) {
        try{
            channels.get(interlocutor.getIp()).sendObject(object);
        }catch (Exception e){
            System.out.println("Error sending object: ");
            e.printStackTrace();
        }
    }

    private synchronized void createChannels(){
        for(Interlocutor interlocutor : tgpc.getPeers()){
            downChannels.add(new Channel(interlocutor, this));
        }
    }

    public void addChannel(Socket socket, int index){
        this.downChannels.get(index).setSocket(socket);
        new Thread(this.downChannels.get(index)).start();
        this.channels.put(this.downChannels.get(index).getInterlocutor().getIp(),this.downChannels.get(index));
        this.tgpc.openGate(this.downChannels.get(index).getInterlocutor());
        this.downChannels.remove(index);
    }

    public synchronized void moveToDownChannel(Channel channel) {
        this.downChannels.add(channel);
        this.channels.remove(channel.getInterlocutor().getIp());
        this.tgpc.closeGate(channel.getInterlocutor());
    }

    public void handleAppFrame(AppFrame appFrame, Interlocutor interlocutor) {
        System.out.println("Handling app frame");
        this.tgpc.manageAppFrame(appFrame, interlocutor);
    }

    public HashMap<String, Channel> getChannels() {
        return channels;
    }

    public ArrayList<Channel> getDownChannels() {
        return downChannels;
    }
}