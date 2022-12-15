package pl.psi.gui.mapgenerator;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import pl.psi.fields.ObstacleTypes;
import pl.psi.fields.TerrainTypes;

import java.io.File;

public class MapField extends StackPane {
    private final Rectangle terrain;
    private final Rectangle obstacles;

    private final Label label;

    private String terrainType;
    private String obstacleType;

    MapField(final String aName) {
        terrain = new Rectangle(60, 60);
        terrain.setFill(Color.WHITE);
        terrain.setStroke(Color.BLACK);
        getChildren().add(terrain);

        obstacles = new Rectangle(60, 60);
        obstacles.setFill(Color.TRANSPARENT);
        obstacles.setStroke(Color.BLACK);
        getChildren().add(obstacles);

        label = new Label("");
        getChildren().add(label);
    }

    void setName(final String aName) {
        label.setText(aName);
    }

    void setTerrainBackground(final String img_path) {
        String path = TerrainTypes.TERRAIN_SPRITES_PATH;
        path += img_path;
        path += ".png";
        Image img = new Image(new File(path).toURI().toString());
        terrain.setFill(new ImagePattern(img));
    }

    void setObstacleBackground(final String img_path){
        String obstacleSpritesPath = ObstacleTypes.OBSTACLE_SPRITES_PATH;
        obstacleSpritesPath += img_path;
        obstacleSpritesPath += ".png";
        Image obstacleImg = new Image(new File(obstacleSpritesPath).toURI().toString());
        obstacles.setFill(new ImagePattern(obstacleImg));
    }

    void setTerrainType(final String aType){
        this.terrainType = aType;
        this.setTerrainBackground(aType);
    }

    void setObstacleType(final String aType){
        this.obstacleType = aType;
        this.setObstacleBackground(obstacleType);
    }

    String getTerrainType(){
        return this.terrainType;
    }
    String getObstacleType() { return  this.obstacleType; }
}
