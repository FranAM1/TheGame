package Game;

import Game.VisualObjects.Balls;
import Game.VisualObjects.BorderWall;
import Game.VisualObjects.VisualObject;

public class TheGameRules {

    public void applyCollisionRules(VisualObject v1, VisualObject v2) {
        // CASO DOS BOLAS
        if (v1 instanceof Balls && v2 instanceof Balls) {
            v1.bounce("both");
            v2.bounce("both");
        }
        // CASO BOLA Y PARED
        else if (v1 instanceof Balls && v2 instanceof BorderWall) {
            // Posicion 0 es width, posicion 1 es height
            int[] wallDimensions = v2.getDimensions();

            if (wallDimensions[0] > wallDimensions[1]) {
                v1.bounce("vertical");
            } else {
                v1.bounce("horizontal");
            }
        }
    }
}
