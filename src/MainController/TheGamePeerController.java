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

    public TheGamePeerController() {
        this.fileName = "config.ini";
        this.gameController = new TheGameController(this);
        this.commsController = new CommunicationController();
        this.peers = new ArrayList<>();
        createInterlocutors();
    }

    public void createInterlocutors(){
        Peer peer = new Peer("127.0.0.1", 10000, PeerLocation.EAST);
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

        ClientConnector clientConnector = new ClientConnector("192.168.0.10", 10001, this.commsController);
        Thread clientConnectorThread = new Thread(clientConnector);
        clientConnectorThread.start();

        ServerConnector serverConnector = new ServerConnector(10000, this.commsController);
        Thread serverConnectorThread = new Thread(serverConnector);
        serverConnectorThread.start();
    }

    public void manageAppFrame(AppFrame appFrame){

    }

    public static void main(String[] args) {
        TheGamePeerController mainController = new TheGamePeerController();

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
