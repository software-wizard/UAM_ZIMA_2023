package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import pl.psi.fields.ObstacleTypes;
import pl.psi.fields.TerrainTypes;

import java.io.File;

class MapTile extends StackPane
{

    private final Rectangle rect;
    private final Rectangle obstacle;
    private final Rectangle moveRange;
    private final Label label;

    MapTile( final String aName )
    {
        rect = new Rectangle( 60, 60 );
        rect.setFill( Color.WHITE );
        rect.setStroke( Color.BLACK );
        getChildren().add( rect );

        moveRange = new Rectangle( 60, 60 );
        moveRange.setFill( Color.TRANSPARENT );
        moveRange.setStroke( Color.BLACK );
        getChildren().add( moveRange );

        obstacle = new Rectangle( 60, 60 );
        obstacle.setFill( Color.TRANSPARENT );
        obstacle.setStroke( Color.BLACK );
        getChildren().add( obstacle );

        label = new Label( aName );
        getChildren().add( label );
    }

    void setName( final String aName )
    {
        label.setText( aName );
    }

    void setBackgroundSprite(final Color aColor )
    {
        rect.setFill( aColor );
    }

    void setBackgroundSprite(String img_path) {
        if (img_path == null){
            obstacle.setFill(Color.TRANSPARENT);
        } else {
            String path = TerrainTypes.TERRAIN_SPRITES_PATH;
            path += img_path;
            path += ".png";
            Image img = new Image(new File(path).toURI().toString());
            rect.setFill(new ImagePattern(img));
        }
    }

    void setRectStroke(Color aColor){
        this.rect.setStroke(aColor);
    }

    void setMoveRange() {
        this.moveRange.setFill(Color.rgb(0,0,0,0.35));
    }

    public void setObstacleSprite(String terrainType) {
        if (terrainType == null){
            obstacle.setFill(Color.TRANSPARENT);
        } else {
            String path = ObstacleTypes.OBSTACLE_SPRITES_PATH;
            path += terrainType;
            path += ".png";
            Image img = new Image(new File(path).toURI().toString());
            obstacle.setFill(new ImagePattern(img));
        }
    }
}
