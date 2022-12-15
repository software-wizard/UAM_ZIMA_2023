package pl.psi.specialfields.obstacles;

import pl.psi.fields.ObstacleTypes;
import pl.psi.specialfields.Obstacle;

public class Quicksand extends Obstacle {
    public final String type = ObstacleTypes.QUICK_SAND;

    @Override
    public String getType() {
        return type;
    }
}
