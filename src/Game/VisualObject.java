package Game;

import java.awt.*;

public interface VisualObject {
    void paint(Graphics g);
    void move();
    void kill();
    void explode();
    void bounce();
}