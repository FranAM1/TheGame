package Game;

public class TheGameController {
    private TheGameModel model;
    private TheGameViewer viewer;

    public TheGameController(TheGameModel model, TheGameViewer viewer) {
        this.model = model;
        this.viewer = viewer;
    }

    public static void main(String[] args) {
        TheGameModel model = new TheGameModel();
        TheGameViewer viewer = new TheGameViewer(model);
        TheGameController controller = new TheGameController(model, viewer);

        controller.run();
    }

    public void run() {
        while (true) {
            this.viewer.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}