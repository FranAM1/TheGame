package MainController;

import Communication.Channels.Channel;
import Communication.ClientConnector;
import Communication.CommunicationController;
import Communication.Interlocutors.Interlocutor;
import Communication.Interlocutors.Peer;
import Communication.ServerConnector;
import DTO.AppFrame;
import DTO.DataFrame;
import Enums.AppFrameType;
import Enums.DataFrameType;
import Enums.PeerLocation;
import Game.TheGameController;
import Game.VisualObjects.VO;
import Game.VisualObjectsDynamic.VODynamic;

import java.util.ArrayList;

public class TheGamePeerController {
    private TheGameController gameController;

    private CommunicationController commsController;

    private String fileName;

    private ArrayList<Peer> peers;

    private String[] args;

    public TheGamePeerController(String[] args) {
        this.args = args;
        this.fileName = "config.ini";
        this.gameController = new TheGameController(this);
        this.commsController = new CommunicationController();
        this.peers = new ArrayList<>();
        createInterlocutors();
    }

    public void createInterlocutors(){
        Peer peer = new Peer("127.0.0.1", Integer.parseInt(args[0]), PeerLocation.EAST);
        peers.add(peer);

        createChannels();
    }

    private Peer findPeerByLocation(PeerLocation peerLocation){
        for(Peer peer : peers){
            if(peer.getLocation() == peerLocation){
                return peer;
            }
        }
        return null;
    }

    public void sendAppFrame(VO visualObject, PeerLocation peerLocation) {
        Interlocutor interlocutorToSend = findPeerByLocation(peerLocation);

        AppFrame appFrame = new AppFrame(AppFrameType.BALL, visualObject);

        commsController.sendObject(appFrame, interlocutorToSend);
    }

    public void createChannels(){
        for(Peer peer : peers){
            Interlocutor interlocutor = new Interlocutor(peer.getIp(), peer.getPort());
            this.commsController.addChannel(interlocutor);
        }

        ClientConnector clientConnector = new ClientConnector("127.0.0.1", Integer.parseInt(args[1]), this.commsController);
        Thread clientConnectorThread = new Thread(clientConnector);
        clientConnectorThread.setName("ClientConnector");
        clientConnectorThread.start();

        ServerConnector serverConnector = new ServerConnector(Integer.parseInt(args[0]), this.commsController);
        Thread serverConnectorThread = new Thread(serverConnector);
        serverConnectorThread.setName("ServerConnector");
        serverConnectorThread.start();
    }

    public void manageAppFrame(AppFrame appFrame){

    }

    public static void main(String[] args) {
        TheGamePeerController mainController = new TheGamePeerController(args);

        mainController.run();
    }



    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
