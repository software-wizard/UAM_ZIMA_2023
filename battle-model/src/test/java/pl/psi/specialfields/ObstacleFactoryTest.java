package pl.psi.specialfields;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static pl.psi.fields.ObstacleTypes.*;


class ObstacleFactoryTest {

    @Test
    void isCreatingObstaclesProperly(){
        String[] obstacleTypes = {FIRE_WALL, FORCE_FIELD, LAND_MINE, QUICK_SAND};
        ObstacleFactory obstacleFactory = new ObstacleFactory();
        Obstacle fireWall = obstacleFactory.getType(obstacleTypes[0]);
        Obstacle forceField = obstacleFactory.getType(obstacleTypes[1]);
        Obstacle landMine = obstacleFactory.getType(obstacleTypes[2]);
        Obstacle quickSand = obstacleFactory.getType(obstacleTypes[3]);

        Assertions.assertEquals(FIRE_WALL, fireWall.getType());
        Assertions.assertEquals(FORCE_FIELD, forceField.getType());
        Assertions.assertEquals(LAND_MINE, landMine.getType());
        Assertions.assertEquals(QUICK_SAND, quickSand.getType());
    }

}