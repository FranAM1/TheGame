package Game;

public class TheGameController {
    private TheGameModel model;
    private TheGameViewer viewer;
    private static final int dimensionX = 800;
    private static final int dimensionY = 600;

    public TheGameController(TheGameModel model, TheGameViewer viewer) {
        this.model = model;
        this.viewer = viewer;
    }

    public static void main(String[] args) {
        TheGameModel model = new TheGameModel(dimensionX, dimensionY);
        TheGameViewer viewer = new TheGameViewer(model, dimensionX, dimensionY);
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