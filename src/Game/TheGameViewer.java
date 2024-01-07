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
        initializeUI();
        addMouseListener(this);
    }

    private void initializeUI() {
        setTitle("The Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
    }

    public void repaint() {
        super.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getX() + ", " + e.getY());
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
