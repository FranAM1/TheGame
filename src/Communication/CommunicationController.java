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
    HashMap<Interlocutor, Channel> channels = new HashMap<>();


    public CommunicationController() {

    }

    public void setSocketToChannel(Socket socket){
        Interlocutor interlocutorSocket = new Interlocutor(socket.getInetAddress().toString(), socket.getPort());
        Channel channel = channels.get(interlocutorSocket);
        channel.setSocket(socket);
    }

    public void sendObject(Object object, Interlocutor interlocutor) {
        try{
            channels.get(interlocutor).sendObject(object);
        }catch (Exception e){
            System.out.println("Error sending object: ");
            e.printStackTrace();
        }
    }

    public void addChannel(Interlocutor interlocutor){
        Channel channel = new Channel(interlocutor);
        channels.put(interlocutor, channel);
    }
}