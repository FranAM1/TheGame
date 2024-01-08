package Game;

import java.awt.*;

public class Balls implements VisualObject, Runnable{
    private int positionX;
    private int positionY;
    private int velocityX;
    private int velocityY;
    private int radius;

    public Balls(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = 5;
        this.velocityY = 5;
        this.radius = 50;
    }

    public void bounce() {
        this.velocityX *= -1;
        this.velocityY *= -1;
    }

    @Override
    public void print(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(positionX, positionY, radius, radius);
    }

    @Override
    public void move() {
        positionX += velocityX;
        positionY += velocityY;
    }

    @Override
    public void run() {
        while (true) {
            move();
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
