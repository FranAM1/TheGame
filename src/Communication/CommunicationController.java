// CommunicationController.java
package Communication;

import Communication.Channels.Channel;
import Communication.Interlocutors.Interlocutor;
import DTO.DataFrame;

import java.net.Socket;
import java.util.ArrayList;

public class CommunicationController {
    ArrayList<Channel> channels = new ArrayList<>();


    public CommunicationController() {

    }

    public void setSocketToChannel(Socket socket){
        Interlocutor interlocutorSocket = new Interlocutor(socket.getInetAddress().toString(), socket.getPort());

        for (Channel channel : channels) {
            if (channel.getInterlocutor().equals(interlocutorSocket)) {
                channel.setSocket(socket);
            }
        }
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void sendDataFrame(DataFrame dataFrame) {
        for (Channel channel : channels) {
            channel.sendDataFrame(dataFrame);
        }
    }

    public void addChannel(Interlocutor interlocutor){
        Channel channel = new Channel(interlocutor);
        channels.add(channel);
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }
}