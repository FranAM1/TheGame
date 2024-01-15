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
                if (visualObject.getHitbox().intersects(otherVisualObject.getHitbox())) {
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
