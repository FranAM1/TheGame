package Game;

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

    public String nextMove(int x, int y, int radius){
        return controller.nextMove(x, y, radius);
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
