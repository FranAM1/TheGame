package Game;

import javax.swing.*;

public class TheGameController extends JFrame {
    private TheGameModel model;
    private TheGameViewer viewer;
    private static final int width = 800;
    private static final int height = 600;

    public TheGameController(TheGameModel model, TheGameViewer viewer) {
        this.model = model;
        this.viewer = viewer;
    }

    public static void main(String[] args) {
        TheGameModel model = new TheGameModel(width, height);
        TheGameViewer viewer = new TheGameViewer(model, width, height);
        TheGameController controller = new TheGameController(model, viewer);

        controller.run();
    }

    public void run() {
        while (true) {
            viewer.getCanvas().repaintCanvas();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}