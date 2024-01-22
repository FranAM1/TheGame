package Game.VisualObjectsDynamic;

import DTO.CoordinatesDTO;
import DTO.VectorDTO;
import Enums.VODState;
import Game.TheGameModel;
import Game.VisualObjects.Hitbox;
import Game.VisualObjects.VO;

import java.awt.*;
import java.util.Random;

public class Ball extends VODynamic {
    private int radius;
    private Color color;
    private float mass;

    public Ball(CoordinatesDTO position, VectorDTO velocity, VectorDTO acceleration, VODState state, int radius, Color color, float mass) {
        super(position, velocity, acceleration, state);
        this.setRadius(radius);
        this.setColor(color);
        this.setMass(mass);
    }


    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void nextMove() {

    }

    @Override
    public void run() {

    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
