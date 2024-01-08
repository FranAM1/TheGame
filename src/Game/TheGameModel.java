package Game;

import java.util.ArrayList;

public class TheGameModel {
    private ArrayList<VisualObject> visualObjects;

    public TheGameModel() {
        visualObjects = new ArrayList<>();
    }

    public void addVisualObject(VisualObject visualObject) {
        visualObjects.add(visualObject);
    }

    public void colideDetection(Balls ball){

    }

    public ArrayList<VisualObject> getVisualObjects() {
        return visualObjects;
    }

    public void setVisualObjects(ArrayList<VisualObject> visualObjects) {
        this.visualObjects = visualObjects;
    }
}
