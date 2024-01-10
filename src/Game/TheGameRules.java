package Game;

import Game.VisualObjects.Balls;
import Game.VisualObjects.BorderWall;
import Game.VisualObjects.VisualObject;

public class TheGameRules {

    public String applyCollisionRules(VisualObject v1, VisualObject v2) {
        String collisionType = "";
        if (v1 instanceof Balls && v2 instanceof Balls) {
            collisionType="balls";
        } else if (v1 instanceof Balls && v2 instanceof BorderWall) {
            // Posicion 0 es width, posicion 1 es height
            int[] wallDimensions = v2.getDimensions();

            if (wallDimensions[0] > wallDimensions[1]) {
                collisionType="borderWallY";
            } else {
                collisionType="borderWallX";
            }
        }
        return collisionType;
    }
}
