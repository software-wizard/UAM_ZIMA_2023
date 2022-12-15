package pl.psi.specialfields;

import pl.psi.fields.ObstacleTypes;
import pl.psi.specialfields.obstacles.Firewall;
import pl.psi.specialfields.obstacles.ForceField;
import pl.psi.specialfields.obstacles.LandMine;
import pl.psi.specialfields.obstacles.Quicksand;

import static pl.psi.fields.ObstacleTypes.*;

public class ObstacleFactory {
    public Obstacle getType(String obstacleType){
        switch (obstacleType) {
            case FIRE_WALL:
                return new Firewall(100);
            case FORCE_FIELD:
                return new ForceField();
            case LAND_MINE:
                return new LandMine();
            case QUICK_SAND:
                return new Quicksand();
        }
        return null;
    }
}
