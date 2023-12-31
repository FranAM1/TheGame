package Game.VisualObjects;

import java.awt.*;

public class BorderWall implements VisualObject{
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private String position;

    public BorderWall(int positionX, int positionY, int width, int height, String position) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.position = position;
    }

    @Override
    public int[] getDimensions() {
        int[] dimensions = new int[2];
        dimensions[0] = width;
        dimensions[1] = height;
        return dimensions;
    }

    @Override
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = positionX;
        position[1] = positionY;
        return position;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(positionX, positionY, width, height);
    }

    @Override
    public void move(int[] newPosition) {

    }

    @Override
    public void kill() {

    }

    @Override
    public void explode() {

    }

    @Override
    public void bounce() {

    }
}
