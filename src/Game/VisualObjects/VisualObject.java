package Game.VisualObjects;

import java.awt.*;

public interface VisualObject {
    void paint(Graphics g);
    void move(int[] newPosition);
    void kill();
    void explode();
    void bounce(String bounceType);
    int[] getDimensions();
    int[] getPosition();
    Hitbox getHitbox();
}