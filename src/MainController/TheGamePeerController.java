package MainController;

import Communication.Channels.Channel;
import Communication.CommunicationController;
import Communication.Interlocutors.Interlocutor;
import Communication.Interlocutors.Peer;
import DTO.AppFrame;
import DTO.DataFrame;
import Enums.AppFrameType;
import Enums.DataFrameType;
import Enums.PeerLocation;
import Game.TheGameController;
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
        Peer peer = new Peer("172.16.131.188", 10001, PeerLocation.EAST);
        peers.add(peer);

        createChannels();
    }

    public void createChannels(){
        for(Peer peer : peers){
            Interlocutor interlocutor = new Interlocutor(peer.getIp(), peer.getPort());
            this.commsController.addChannel(interlocutor);
        }
    }

    public void manageAppFrame(AppFrame appFrame){

    }

    public static void main(String[] args) {
        TheGamePeerController mainController = new TheGamePeerController();

        mainController.run();
    }



    public void sendDataFrame(DataFrame dataFrame) {
        System.out.println("Sending data frame");
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
