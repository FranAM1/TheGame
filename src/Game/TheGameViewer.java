package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TheGameViewer extends JFrame implements MouseListener {
    private TheGameModel model;
    private Viewer canvas;
    private ControlPanel controlPanel;

    public TheGameViewer(TheGameModel model, int dimensionX, int dimensionY) {
        this.model = model;
        configureJFrame(dimensionX, dimensionY);
        setVisible(true);
    }

    private void configureJFrame(int dimensionX, int dimensionY) {
        this.setTitle("The Game");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(dimensionX, dimensionY);
        this.addComponentsToPane(this.getContentPane());
    }

    private void addComponentsToPane(Container pane) {
        this.addViewerToPane(pane);
    }

    private void addViewerToPane(Container pane) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;

        canvas = new Viewer(model, this);
        canvas.addMouseListener(this);
        pane.add(canvas, c);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Balls ball = new Balls(this.model, e.getX(), e.getY());
        model.addVisualObject(ball);
        canvas.paintBall(ball);
        System.out.println("Clicked at: " + e.getX() + ", " + e.getY());
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

    public TheGameModel getModel() {
        return model;
    }

    public void setModel(TheGameModel model) {
        this.model = model;
    }

    public Viewer getCanvas() {
        return canvas;
    }

    public void setCanvas(Viewer canvas) {
        this.canvas = canvas;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
}
