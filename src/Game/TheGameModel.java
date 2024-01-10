package Game;

import Game.VisualObjects.VisualObject;

import java.util.ArrayList;

public class TheGameModel {
    TheGameController controller;
    private ArrayList<VisualObject> visualObjects;
    private int dimensionX;
    private int dimensionY;

    public TheGameModel(int dimensionX, int dimensionY, TheGameController controller) {
        this.controller = controller;
        visualObjects = new ArrayList<>();
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    public void collideDetection(VisualObject visualObject, int[] newPosition){
        boolean collision = false;

        for (VisualObject otherVisualObject : visualObjects) {
            if (otherVisualObject != visualObject) {
                if (collisionBetweenTwoObjects(visualObject, otherVisualObject)) {
                    collision = true;
                    this.controller.collideManagment(visualObject, otherVisualObject);
                    break;
                }
            }
        }

        if (!collision) {
            visualObject.move(newPosition);
        }
    }

    private boolean collisionBetweenTwoObjects(VisualObject v1, VisualObject v2){
        boolean collision = false;

        int[] v1Position = v1.getPosition();
        int[] v1Dimensions = v1.getDimensions();

        int[] v2Position = v2.getPosition();
        int[] v2Dimensions = v2.getDimensions();

        if (v1Position[0] < v2Position[0] + v2Dimensions[0] &&
                v1Position[0] + v1Dimensions[0] > v2Position[0] &&
                v1Position[1] < v2Position[1] + v2Dimensions[1] &&
                v1Position[1] + v1Dimensions[1] > v2Position[1]) {
            collision = true;
        }

        return collision;
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
