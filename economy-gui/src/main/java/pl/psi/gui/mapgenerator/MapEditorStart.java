package pl.psi.gui.mapgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MapEditorStart extends Application {


    public MapEditorStart() {
    }

    public static void main(String[] args) {
        launch( args );
    }

    @Override
    public void start( final Stage aStage ) throws Exception
    {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getClassLoader()
                .getResource( "fxml/map_generator.fxml" ) );
        loader.setController( new MapEditorController() );
        final Scene scene = new Scene( loader.load() );
        aStage.setScene( scene );
        aStage.setX( 5 );
        aStage.setY( 5 );
        aStage.show();
    }
}
