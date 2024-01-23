// CommunicationController.java
package Communication;

import Communication.Channels.Channel;

import java.util.ArrayList;

public class CommunicationController {
    ArrayList<Channel> channels = new ArrayList<>();
    private ServerConnector serverConnector;
    private ClientConnector clientConnector;

    public CommunicationController(String ip, int port) {
        this.serverConnector = new ServerConnector(port);
        this.clientConnector = new ClientConnector(ip, port);
    }
}