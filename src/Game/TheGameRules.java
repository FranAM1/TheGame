package Game;

import DTO.AppFrame;
import Enums.AppFrameType;
import Enums.GateState;
import Enums.WallLocation;
import Game.VisualObjects.Gate;
import Game.VisualObjectsDynamic.Ball;
import Game.VisualObjects.Wall;
import Game.VisualObjects.VO;
import Game.VisualObjectsDynamic.VODynamic;

public class TheGameRules {
    private TheGameController controller;

    public TheGameRules(TheGameController controller) {
        this.controller = controller;
    }

    public void applyCollisionRules(VO v1, VO v2) {
        if (v1 instanceof Ball && v2 instanceof Gate) {
            Ball ball = (Ball) v1;
            Gate wall = (Gate) v2;

            if (wall.getGateState() == GateState.OPEN){
                AppFrame appFrame = new AppFrame(AppFrameType.BALL, ball);
                this.controller.killVisualObjectDynamic(ball);
                this.controller.sendDataFrame(appFrame);
            }else{
                bounceWall(wall, ball);
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

    private void bounceWall(Gate wall, Ball ball){
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
}
