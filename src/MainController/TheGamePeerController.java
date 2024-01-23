package MainController;

import Communication.CommunicationController;
import Game.TheGameController;

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
        // this.gameController = new TheGameController(this);
        this.commsController = new CommunicationController(ip, port);
        this.peers = new ArrayList<>();

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
