package Game;

import Game.VisualObjects.Balls;
import Game.VisualObjects.VisualObject;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class TheGameViewer extends Canvas implements Runnable{
    private TheGameModel model;
    private BufferStrategy bs;

    public TheGameViewer(TheGameModel model, int width, int height) {
        this.model = model;
        this.setPreferredSize(new Dimension(width, height));
        this.bs = null;
    }

    private void repaintCanvas() {
        checkBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        g.clearRect(0, 0, getWidth(), getHeight());

        for (VisualObject visualObject : model.getVisualObjects()) {
            visualObject.paint(g);
        }

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
            repaintCanvas();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
