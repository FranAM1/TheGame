package Game;

import java.awt.*;

public class Balls implements VisualObject, Runnable{
    private TheGameModel model;
    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private int radius;

    public Balls(TheGameModel model,int positionX, int positionY) {
        this.model = model;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = 5;
        this.velocityY = 5;
        this.radius = 50;
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(positionX, positionY, radius, radius);
    }

    @Override
    public void move() {
        positionX += velocityX;
        positionY += velocityY;
    }

    @Override
    public void kill() {

    }

    @Override
    public void explode() {

    }

    @Override
    public void bounce() {

    }

    @Override
    public void run() {
        while (true) {
            move();
            model.checkBallOutOfBorders(this);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
