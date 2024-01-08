package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Viewer extends Canvas implements Runnable{
    private TheGameModel model;
    private BufferStrategy bs;

    public Viewer(TheGameModel model, JFrame frame) {
        this.model = model;
        this.setSize(frame.getWidth(), frame.getHeight());
        this.bs = null;
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
            this.createBufferStrategy(1);
            this.bs = this.getBufferStrategy();
        }
    }

    @Override
    public void run() {

    }
}
