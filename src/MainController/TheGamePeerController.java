package MainController;

import Communication.CommunicationController;
import Communication.Interlocutors.Peer;
import DTO.AppFrame;
import DTO.DataFrame;
import Enums.AppFrameType;
import Enums.DataFrameType;
import Game.TheGameController;
import Game.VisualObjectsDynamic.VODynamic;

import java.util.ArrayList;

public class TheGamePeerController {
    private TheGameController gameController;

    private CommunicationController commsController;

    private String fileName;

    private ArrayList<Peer> peers;

    private String ip;
   private int port;

    public TheGamePeerController() {
        this.ip = "localhost";
        this.port = 12345;
        this.fileName = "config.ini";
        this.gameController = new TheGameController(this);
        this.commsController = new CommunicationController(ip, port);
        this.peers = new ArrayList<>();

    }

    public static void main(String[] args) {
        TheGamePeerController mainController = new TheGamePeerController();

        mainController.run();
    }

    public void manageAppFrame(AppFrame appFrame){

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
