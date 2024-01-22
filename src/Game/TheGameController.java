package Game;

import DTO.CoordinatesDTO;
import Enums.GateState;
import Enums.WallLocation;
import Game.VisualObjects.Gate;
import Game.VisualObjectsDynamic.Ball;
import Game.VisualObjects.Wall;
import Game.VisualObjects.VO;
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
    private static final int wallSpace = 5;

    public TheGameController(TheGamePeerController peerController) {
        this.rules = new TheGameRules();
        this.peerController = peerController;
        this.model = new TheGameModel(width, height, this);
        this.viewer = new TheGameViewer(model, width, height);
        configureJFrame();
        configureCanvas();
        createBorderGates();
        setVisible(true);
        this.pack();
    }

    private void configureCanvas(){
        this.add(viewer);
        this.viewer.addMouseListener(this);
        Thread thread = new Thread(viewer);
        thread.start();
    }

    public void collideManagement(VO v1, VO v2){
        rules.applyCollisionRules(v1, v2);
    }

    private void createBorderGates(){
        Dimension fullWidth = new Dimension(width, wallSpace);
        Dimension fullHeight = new Dimension(wallSpace, height);

        CoordinatesDTO northPosition = new CoordinatesDTO(0, 0);
        CoordinatesDTO eastPosition = new CoordinatesDTO(width - wallSpace, 0);
        CoordinatesDTO southPosition = new CoordinatesDTO(0, height - wallSpace);
        CoordinatesDTO westPosition = new CoordinatesDTO(0, 0);

        Gate northGate = new Gate(GateState.CLOSED, WallLocation.NORTH, fullWidth, northPosition);
        Gate eastGate = new Gate(GateState.CLOSED, WallLocation.EAST, fullHeight, eastPosition);
        Gate southGate = new Gate(GateState.CLOSED, WallLocation.SOUTH, fullWidth, southPosition);
        Gate westGate = new Gate(GateState.CLOSED, WallLocation.WEST, fullHeight, westPosition);

        model.addVisualObject(northGate);
        model.addVisualObject(eastGate);
        model.addVisualObject(southGate);
        model.addVisualObject(westGate);
    }

    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // Ball ball = new Ball(model, e.getX(), e.getY());
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