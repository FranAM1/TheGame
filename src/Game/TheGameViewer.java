package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class TheGameViewer extends Canvas implements Runnable{
    private TheGameModel model;
    private BufferStrategy bs;

    public TheGameViewer(TheGameModel model, JFrame frame) {
        this.model = model;
        this.setSize(frame.getWidth(), frame.getHeight());
        this.bs = null;
    }

    public void repaintCanvas() {
        checkBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.clearRect(0, 0, getWidth(), getHeight());

        for (VisualObject visualObject : model.getVisualObjects()) {
            visualObject.print(g);
        }

        bs.show();
        g.dispose();
    }

    public void paintBall(Balls ball) {
        checkBufferStrategy();

        Graphics g = bs.getDrawGraphics();

        ball.print(g);
        bs.show();
        g.dispose();
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("Painting");
    }

    private void checkBufferStrategy(){
        if (this.bs == null) {
            this.createBufferStrategy(2);
            this.bs = this.getBufferStrategy();
        }
    }

    @Override
    public void run() {
        while (true) {
            checkBufferStrategy();

            Graphics g = bs.getDrawGraphics();

            for (VisualObject visualObject : model.getVisualObjects()) {
                visualObject.print(g);
            }

            bs.show();
            g.dispose();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
