package Game;

import java.util.ArrayList;

public class TheGameModel {
    private ArrayList<VisualObject> visualObjects;
    private int dimensionX;
    private int dimensionY;

    public TheGameModel(int dimensionX, int dimensionY) {
        visualObjects = new ArrayList<>();
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    public void checkBallOutOfBorders(Balls ball) {
        if (ball.getPositionX() < 0 || ball.getPositionX() > dimensionX - ball.getRadius()) {
            ball.setVelocityX(-ball.getVelocityX());
        }
        if (ball.getPositionY() < 0 || ball.getPositionY() > dimensionY - ball.getRadius()) {
            ball.setVelocityY(-ball.getVelocityY());
        }
    }

    public void addVisualObject(VisualObject visualObject) {
        visualObjects.add(visualObject);
    }

    public ArrayList<VisualObject> getVisualObjects() {
        return visualObjects;
    }

    public void setVisualObjects(ArrayList<VisualObject> visualObjects) {
        this.visualObjects = visualObjects;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public void setDimensionX(int dimensionX) {
        this.dimensionX = dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public void setDimensionY(int dimensionY) {
        this.dimensionY = dimensionY;
    }
}
