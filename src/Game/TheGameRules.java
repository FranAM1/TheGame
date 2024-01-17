package Game;

import Game.VisualObjectsDynamic.Ball;
import Game.VisualObjects.Wall;
import Game.VisualObjects.VO;

public class TheGameRules {

    public void applyCollisionRules(VO v1, VO v2) {
        // CASO DOS BOLAS
        if (v1 instanceof Ball && v2 instanceof Ball) {
            v1.bounce("both");
            v2.bounce("both");
        }
        // CASO BOLA Y PARED
        else if (v1 instanceof Ball && v2 instanceof Wall) {
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
