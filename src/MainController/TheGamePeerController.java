package MainController;

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

import java.util.ArrayList;

public class TheGamePeerController {
    private TheGameController gameController;

    private CommunicationController commsController;

    private String fileName;

    private ArrayList<Peer> peers;

    private static int MyPort = 10000;
    private static int OtherPort = 10001;

    public TheGamePeerController() {
        this.fileName = "config.ini";
        this.peers = new ArrayList<>();
        createInterlocutors();
        this.gameController = new TheGameController(this);
        this.commsController = new CommunicationController(this, MyPort, OtherPort);

    }

    public void createInterlocutors(){
        Peer peer = new Peer("localhost", OtherPort, PeerLocation.WEST);
        peers.add(peer);
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
        DataFrame dataFrame = new DataFrame(DataFrameType.APPLICATION_FRAME, appFrame);

        commsController.sendObject(dataFrame, interlocutorToSend);
    }

    public void manageAppFrame(AppFrame appFrame, Interlocutor interlocutor) {
        Peer peer = findPeerByIp(interlocutor.getIp());
        gameController.manageAppFrame(appFrame, peer.getLocation());
    }

    private Peer findPeerByIp(String ip){
        for(Peer peer : peers){
            if(peer.getIp().equals(ip)){
                return peer;
            }
        }
        return null;
    }

    public void openGate(Interlocutor interlocutor){
        PeerLocation peerLocation = findPeerByIp(interlocutor.getIp()).getLocation();
        gameController.openGate(peerLocation);
    }

    public void closeGate(Interlocutor interlocutor){
        PeerLocation peerLocation = findPeerByIp(interlocutor.getIp()).getLocation();
        gameController.closeGate(peerLocation);
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

    public TheGameController getGameController() {
        return gameController;
    }

    public void setGameController(TheGameController gameController) {
        this.gameController = gameController;
    }

    public CommunicationController getCommsController() {
        return commsController;
    }

    public void setCommsController(CommunicationController commsController) {
        this.commsController = commsController;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Peer> getPeers() {
        return peers;
    }

    public void setPeers(ArrayList<Peer> peers) {
        this.peers = peers;
    }
}
