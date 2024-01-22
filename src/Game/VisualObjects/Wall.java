package Game.VisualObjects;

import DTO.CoordinatesDTO;
import Enums.WallLocation;

import java.awt.*;

public class Wall extends VO {
    private WallLocation location;
    private Dimension dimensions;

    private CoordinatesDTO position;

    public Wall(WallLocation location, Dimension dimensions, CoordinatesDTO position) {
        this.setLocation(location);
        this.setDimensions(dimensions);
        this.setPosition(position);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.position.getX(),
                   this.position.getY(),
                   this.dimensions.width,
                   this.dimensions.height);
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    public WallLocation getLocation() {
        return location;
    }

    public void setLocation(WallLocation location) {
        this.location = location;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimension) {
        this.dimensions = dimension;
    }

    public CoordinatesDTO getPosition() {
        return position;
    }

    public void setPosition(CoordinatesDTO position) {
        this.position = position;
    }
}
