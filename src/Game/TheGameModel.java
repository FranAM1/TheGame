package Game;

import DTO.CoordinatesDTO;
import DTO.VectorDTO;
import Enums.GateState;
import Enums.PeerLocation;
import Enums.WallLocation;
import Game.VisualObjects.Gate;
import Game.VisualObjects.VO;
import Game.VisualObjectsDynamic.VODynamic;

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

    public void collideDetection(VO visualObject, CoordinatesDTO newPosition){
        VO otherVisualObject = checkForCollision(visualObject);
        if (otherVisualObject != null){
            controller.collideManagement(visualObject, otherVisualObject);
        } else {
            ((VODynamic)visualObject).setPosition(newPosition);
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
    public void killVisualObjectDynamic(VODynamic visualObjectDynamic){
        visualObjectDynamic.kill();
        visualObjects.remove(visualObjectDynamic);
    }

    public void openGate(WallLocation wallLocation){
        for (VO visualObject : visualObjects) {
            if (visualObject instanceof Gate) {
                if (((Gate) visualObject).getLocation() == wallLocation) {
                    ((Gate) visualObject).setGateState(GateState.OPEN);
                }
            }
        }
    }

    public void closeGate(WallLocation wallLocation){
        for (VO visualObject : visualObjects) {
            if (visualObject instanceof Gate) {
                if (((Gate) visualObject).getLocation() == wallLocation) {
                    ((Gate) visualObject).setGateState(GateState.CLOSED);
                }
            }
        }
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
