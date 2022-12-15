package pl.psi.specialfields.obstacles;

import pl.psi.fields.ObstacleTypes;
import pl.psi.specialfields.Obstacle;

public class LandMine extends Obstacle {
    public final String type = ObstacleTypes.LAND_MINE;

    @Override
    public String getType() {
        return type;
    }
}
