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

    public TheGamePeerController() {
        this.fileName = "config.ini";
        this.gameController = new TheGameController(this);
        this.commsController = new CommunicationController(this);
        this.peers = new ArrayList<>();
        createInterlocutors();
    }

    public void createInterlocutors(){
        Peer peer = new Peer("127.0.0.1", 8000, PeerLocation.EAST);
        peers.add(peer);

        createClientServer();
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

    public void createClientServer(){
        ClientConnector clientConnector = new ClientConnector(this.commsController);
        Thread clientConnectorThread = new Thread(clientConnector);
        clientConnectorThread.setName("ClientConnector");
        clientConnectorThread.start();

        ServerConnector serverConnector = new ServerConnector(this.commsController);
        Thread serverConnectorThread = new Thread(serverConnector);
        serverConnectorThread.setName("ServerConnector");
        serverConnectorThread.start();
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

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
