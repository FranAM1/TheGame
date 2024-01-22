package Game.VisualObjects;

import DTO.CoordinatesDTO;
import Enums.GateState;
import Enums.WallLocation;

import java.awt.*;

public class Gate extends Wall{
    private GateState gateState;

    public Gate(GateState gateState, WallLocation location, Dimension dimensions, CoordinatesDTO position) {
        super(location, dimensions, position);
        this.setGateState(gateState);
    }

    public GateState getGateState() {
        return gateState;
    }

    public void setGateState(GateState gateState) {
        this.gateState = gateState;
    }
}
