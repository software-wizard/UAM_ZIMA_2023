package pl.psi.specialfields.obstacles;

import pl.psi.fields.ObstacleTypes;
import pl.psi.specialfields.Obstacle;

public class ForceField extends Obstacle {
    public final String type = ObstacleTypes.FORCE_FIELD;

    @Override
    public String getType() {
        return type;
    }
}
