// CommunicationController.java
package Communication;

import Communication.Channels.Channel;
import Communication.Interlocutors.Interlocutor;
import DTO.DataFrame;
import Enums.PeerLocation;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class CommunicationController {
    HashMap<String, Channel> channels = new HashMap<>();


    public CommunicationController() {

    }

    public void setSocketToChannel(Socket socket){
        String ip = socket.getInetAddress().getHostAddress();
        Channel channel = channels.get(ip);
        channel.setSocket(socket);
        Thread thread = new Thread(channel);
        thread.start();
    }

    public void sendObject(Object object, Interlocutor interlocutor) {
        try{
            channels.get(interlocutor.getIp()).sendObject(object);
        }catch (Exception e){
            System.out.println("Error sending object: ");
            e.printStackTrace();
        }
    }

    public void addChannel(Interlocutor interlocutor){
        Channel channel = new Channel(interlocutor);
        channels.put(interlocutor.getIp(), channel);
    }
}