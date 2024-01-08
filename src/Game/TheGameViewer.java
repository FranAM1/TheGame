package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TheGameViewer extends JFrame implements MouseListener {
    private TheGameModel model;
    private Viewer viewer;
    private ControlPanel controlPanel;

    public TheGameViewer(TheGameModel model) {
        this.model = model;
        configureJFrame();
        setVisible(true);
    }

    private void configureJFrame() {
        this.setTitle("The Game");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
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

        viewer = new Viewer(model, this);
        viewer.addMouseListener(this);
        pane.add(viewer, c);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Balls ball = new Balls(e.getX(), e.getY());
        model.addVisualObject(ball);
        viewer.paintBall(ball);
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
}
