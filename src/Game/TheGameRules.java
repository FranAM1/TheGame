package Game;

import Game.VisualObjectsDynamic.Ball;
import Game.VisualObjects.Wall;
import Game.VisualObjects.VO;

public class TheGameRules {

    public void applyCollisionRules(VO v1, VO v2) {
        if (v1 instanceof Ball && v2 instanceof Wall) {
            Ball ball = (Ball) v1;
            Wall wall = (Wall) v2;

            switch (wall.getLocation()) {
                case NORTH:
                case SOUTH:
                    ball.reboundY();
                    break;
                case EAST:
                case WEST:
                    ball.reboundX();
                    break;
            }
        }
        else if (v1 instanceof Ball && v2 instanceof Ball){
            Ball ball1 = (Ball) v1;
            Ball ball2 = (Ball) v2;

            ball1.reboundX();
            ball1.reboundY();
            ball2.reboundX();
            ball2.reboundY();
        }
    }
}
