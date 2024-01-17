package Game;

import Game.VisualObjects.VO;

import java.util.ArrayList;

public class TheGameModel {
    TheGameController controller;
    private ArrayList<VO> visualObjects;
    private int dimensionX;
    private int dimensionY;

    public TheGameModel(int dimensionX, int dimensionY, TheGameController controller) {
        this.controller = controller;
        visualObjects = new ArrayList<>();
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
    }

    public void collideDetection(VO visualObject, int[] newPosition){
        VO collisionObject = checkForCollision(visualObject);

        if(collisionObject != null){
            controller.collideManagement(visualObject, collisionObject);
        } else {
            visualObject.move(newPosition);
        }
    }

    public VO checkForCollision(VO visualObject){
        for (VO otherVisualObject : visualObjects) {
            if (otherVisualObject != visualObject) {
                if (visualObject.getHitbox().intersects(otherVisualObject.getHitbox())) {
                    return otherVisualObject;
                }
            }
        }
        return null;
    }

    public void addVisualObject(VO visualObject) {
        visualObjects.add(visualObject);
    }

    public ArrayList<VO> getVisualObjects() {
        return visualObjects;
    }

    public void setVisualObjects(ArrayList<VO> visualObjects) {
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
