package Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TheGameController extends JFrame implements MouseListener {
    private TheGameModel model;
    private TheGameViewer viewer;
    private static final int width = 800;
    private static final int height = 600;

    public TheGameController(TheGameModel model, TheGameViewer viewer) {
        this.model = model;
        this.viewer = viewer;
        configureJFrame();
        configureCanvas();
        setVisible(true);
    }

    private void configureCanvas(){
        this.add(viewer);
        this.viewer.addMouseListener(this);
        Thread thread = new Thread(viewer);
        thread.start();
    }

    private void configureJFrame() {
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TheGameModel model = new TheGameModel(width, height);
        TheGameViewer viewer = new TheGameViewer(model, width, height);
        TheGameController controller = new TheGameController(model, viewer);

        controller.run();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        Balls ball = new Balls(model, e.getX(), e.getY());
        model.addVisualObject(ball);
        viewer.paintBall(ball);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}