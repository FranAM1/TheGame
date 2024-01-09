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

    public String nextMove(int x, int y, int radius){
        if (x < 0 || x >= gameController.getWidth() - radius){
            return "bounceX";
        }else if (y < 0 || y >= gameController.getHeight() - radius){
            return "bounceY";
        }
        return "move";
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
