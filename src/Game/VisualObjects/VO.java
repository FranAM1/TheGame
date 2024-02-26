package Game.VisualObjects;

import Game.VisualObjects.Hitbox;

import java.awt.*;
import java.io.Serializable;

public abstract class VO implements Serializable {
    public abstract void paint(Graphics g);
    public abstract Hitbox getHitbox();
}