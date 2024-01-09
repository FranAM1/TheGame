package MainController;

import Game.TheGameController;

public class TheGamePeerController {
    private TheGameController gameController;

    public TheGamePeerController() {
        gameController = new TheGameController(this);
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
