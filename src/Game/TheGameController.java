package Game;

import Game.VisualObjects.Balls;
import Game.VisualObjects.BorderWall;
import Game.VisualObjects.VisualObject;
import MainController.TheGamePeerController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TheGameController extends JFrame implements MouseListener {
    private TheGamePeerController peerController;
    private TheGameRules rules;
    private TheGameModel model;
    private TheGameViewer viewer;
    private static final int width = 800;
    private static final int height = 600;
    private static final int wallSpace = 10;

    public TheGameController(TheGamePeerController peerController) {
        this.peerController = peerController;
        this.model = new TheGameModel(width, height, this);
        this.viewer = new TheGameViewer(model, width, height);
        configureJFrame();
        configureCanvas();
        createBorderWalls();
        setVisible(true);
        this.pack();
    }

    private void configureCanvas(){
        this.add(viewer);
        this.viewer.addMouseListener(this);
        Thread thread = new Thread(viewer);
        thread.start();
    }

    public void collideManagment(VisualObject v1, VisualObject v2){

    }

    private void createBorderWalls(){
        model.addVisualObject(new BorderWall(0, 0, width, wallSpace, "top"));
        model.addVisualObject(new BorderWall(0, 0, wallSpace, height, "left"));
        model.addVisualObject(new BorderWall(0, height - wallSpace, width, wallSpace, "bottom"));
        model.addVisualObject(new BorderWall(width - wallSpace, 0, wallSpace, height, "right"));
    }

    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Balls ball = new Balls(model, e.getX(), e.getY());
        Thread thread = new Thread(ball);
        thread.start();
        model.addVisualObject(ball);
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