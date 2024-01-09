package Game;

import MainController.TheGamePeerController;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TheGameController extends JFrame implements MouseListener {
    private TheGamePeerController peerController;
    private TheGameModel model;
    private TheGameViewer viewer;
    private static final int width = 800;
    private static final int height = 600;
    private static final int space = 50;

    public TheGameController(TheGamePeerController peerController) {
        this.peerController = peerController;
        this.model = new TheGameModel(width, height, this);
        this.viewer = new TheGameViewer(model, width, height);
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

    public String nextMove(int x, int y, int radius){
        return peerController.nextMove(x, y, radius);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() < space || e.getX() + space >= width || e.getY() < space || e.getY() + space >= height){
            return;
        }
        Balls ball = new Balls(model, e.getX(), e.getY());
        Thread thread = new Thread(ball);
        thread.start();
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

    public TheGamePeerController getPeerController() {
        return peerController;
    }

    public void setPeerController(TheGamePeerController peerController) {
        this.peerController = peerController;
    }

    public TheGameModel getModel() {
        return model;
    }

    public void setModel(TheGameModel model) {
        this.model = model;
    }

    public TheGameViewer getViewer() {
        return viewer;
    }

    public void setViewer(TheGameViewer viewer) {
        this.viewer = viewer;
    }


}