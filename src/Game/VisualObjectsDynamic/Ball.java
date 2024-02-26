package Game.VisualObjectsDynamic;

import DTO.CoordinatesDTO;
import DTO.VectorDTO;
import Enums.VODState;
import Game.TheGameModel;
import Game.VisualObjects.Hitbox;
import Game.VisualObjects.VO;

import java.awt.*;
import java.io.Serializable;

public class Ball extends VODynamic{
    private transient TheGameModel model;
    private int radius;
    private Color color;
    private float mass;

    public Ball(CoordinatesDTO position, VectorDTO velocity, VectorDTO acceleration, VODState state,
                TheGameModel model, int radius, Color color, float mass) {
        super(position, velocity, acceleration, state);
        this.setRadius(radius);
        this.setColor(color);
        this.setMass(mass);
        this.setModel(model);
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getPosition().getX(), this.getPosition().getY(), this.getRadius()*2, this.getRadius()*2);
    }

    @Override
    public void nextMove() {
        CoordinatesDTO nextMove = calcNextMove();
        this.model.collideDetection(this, nextMove);
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox(this.getPosition().getX(), this.getPosition().getY(), this.getRadius()*2, this.getRadius()*2);
    }

    private CoordinatesDTO calcNextMove(){
        int x = this.getPosition().getX() + this.getVelocity().getX();
        int y = this.getPosition().getY() + this.getVelocity().getY();

        return new CoordinatesDTO(x, y);
    }

    public void reboundX(){
        this.getVelocity().setX(this.getVelocity().getX() * -1);
        this.setPosition(this.calcNextMove());
    }

    public void reboundY(){
        this.getVelocity().setY(this.getVelocity().getY() * -1);
        this.setPosition(this.calcNextMove());
    }

    @Override
    public void run() {
        while (this.getState() == VODState.ALIVE){
            this.nextMove();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    public TheGameModel getModel() {
        return model;
    }

    public void setModel(TheGameModel model) {
        this.model = model;
    }
}
