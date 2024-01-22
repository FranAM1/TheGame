package Game.VisualObjectsDynamic;

import DTO.CoordinatesDTO;
import DTO.VectorDTO;
import Enums.VODState;
import Game.VisualObjects.VO;

public abstract class VODynamic extends VO implements Runnable{
    private CoordinatesDTO position;
    private VectorDTO velocity;

    private VectorDTO acceleration;
    private VODState state;

    public VODynamic(CoordinatesDTO position, VectorDTO velocity, VectorDTO acceleration, VODState state) {
        super();
        this.setPosition(position);
        this.setVelocity(velocity);
        this.setAcceleration(acceleration);
        this.setState(state);
    }

    public abstract void nextMove();


    public CoordinatesDTO getPosition() {
        return position;
    }

    public void setPosition(CoordinatesDTO position) {
        this.position = position;
    }

    public VectorDTO getVelocity() {
        return velocity;
    }

    public void setVelocity(VectorDTO velocity) {
        this.velocity = velocity;
    }

    public VectorDTO getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(VectorDTO acceleration) {
        this.acceleration = acceleration;
    }

    public VODState getState() {
        return state;
    }

    public void setState(VODState state) {
        this.state = state;
    }
}
