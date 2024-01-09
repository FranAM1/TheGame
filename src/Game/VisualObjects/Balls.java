package Game.VisualObjects;

import Game.TheGameModel;

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
    }

    public void nextMove() {
        int[] newPosition = calcNextMove();

        this.model.collideDetection(this, newPosition);
    }

    private int[] calcNextMove(){
        int[] newPosition = new int[2];
        newPosition[0] = positionX + velocityX;
        newPosition[1] = positionY + velocityY;

        return newPosition;
    }

    @Override
    public int[] getDimensions() {
        int[] dimensions = new int[2];
        dimensions[0] = radius;
        dimensions[1] = radius;
        return dimensions;
    }

    @Override
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = positionX;
        position[1] = positionY;
        return position;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(positionX, positionY, radius, radius);
    }

    @Override
    public void move(int[] newPosition) {
        this.positionX = newPosition[0];
        this.positionY = newPosition[1];
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
            this.nextMove();
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
