package Game;

import Communication.Interlocutors.Peer;
import DTO.AppFrame;
import DTO.CoordinatesDTO;
import Enums.AppFrameType;
import Enums.GateState;
import Enums.PeerLocation;
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
                this.controller.killVisualObjectDynamic(ball);
                openGate(ball, wall);
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

    public VO handleAppFrame(AppFrame appFrame, PeerLocation peerLocation){
        if (appFrame.getType() == AppFrameType.BALL){
            Ball ball = (Ball) appFrame.getObject();
            ball.setModel(this.controller.getModel());
            ball.revive();
            Thread thread = new Thread(ball);
            thread.start();
            return changePosition(ball, peerLocation, ball.getRadius(), ball.getRadius());
        }
        return null;
    }

    private VO changePosition(VODynamic vo, PeerLocation peerLocation, int objectWidth, int objectHeight){
        int wallSpace = this.controller.getWallSpace();
        switch (peerLocation){
            case NORTH:
                CoordinatesDTO newPosition = new CoordinatesDTO(vo.getPosition().getX(), 0+wallSpace+objectHeight);
                vo.setPosition(newPosition);
                break;
            case SOUTH:
                newPosition = new CoordinatesDTO(
                        vo.getPosition().getX(),
                        (int) (this.controller.getHeight() - vo.getHitbox().getHeight()) - wallSpace - objectHeight);
                vo.setPosition(newPosition);
                break;
            case EAST:
                newPosition = new CoordinatesDTO(
                        (int) (this.controller.getWidth() - vo.getHitbox().getWidth()) - wallSpace - objectWidth,
                        vo.getPosition().getY());
                vo.setPosition(newPosition);
                break;
            case WEST:
                newPosition = new CoordinatesDTO(0+wallSpace+objectWidth, vo.getPosition().getY());
                vo.setPosition(newPosition);
                break;
        }
        return vo;
    }

    private void openGate(VO vo, Gate gate){
        switch (gate.getLocation()) {
            case NORTH:
                this.controller.sendVisualObject(vo, PeerLocation.NORTH);
                break;
            case SOUTH:
                this.controller.sendVisualObject(vo, PeerLocation.SOUTH);
                break;
            case EAST:
                this.controller.sendVisualObject(vo, PeerLocation.EAST);
                break;
            case WEST:
                this.controller.sendVisualObject(vo, PeerLocation.WEST);
                break;
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

    public WallLocation peerLocationToWallLocation(PeerLocation peerLocation) {
        switch (peerLocation) {
            case NORTH:
                return WallLocation.NORTH;
            case SOUTH:
                return WallLocation.SOUTH;
            case EAST:
                return WallLocation.EAST;
            case WEST:
                return WallLocation.WEST;
        }
        return null;
    }
}
