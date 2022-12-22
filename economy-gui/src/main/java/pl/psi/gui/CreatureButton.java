package pl.psi.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.psi.creatures.CreatureFactory;

public class CreatureButton extends Button
{

    private final String creatureName;
    private Stage dialog;

    public CreatureButton( final EcoController aEcoController, final CreatureFactory aFactory,
        final boolean aUpgraded, final int aTier )
    {
        super( aFactory.create( aUpgraded, aTier, 1 )
            .getName() );
        creatureName = aFactory.create( aUpgraded, aTier, 1 )
            .getName();
        getStyleClass().add( "creatureButton" );
        final Image creatureImage = new Image((getClass().getResource("/creatures/"+creatureName+".png").toExternalForm()));

        int goldCost =aFactory.create(aUpgraded, aTier, 1).getGoldCost();
        addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            final int amount = startDialogAndGetCreatureAmount(goldCost,creatureImage);
            if( amount != 0 )
            {
                aEcoController.buy( aFactory.create( aUpgraded, aTier, amount ) );
            }
            aEcoController.refreshGui();
        } );
    }

    private int startDialogAndGetCreatureAmount(int goldCost, Image creatureImage)
    {
        final ImageView creature = new ImageView(creatureImage);
        final VBox centerPane = new VBox();
        final HBox bottomPane = new HBox();
        final HBox topPane = new HBox();
        final Stage dialog = prepareWindow( centerPane, bottomPane, topPane );
        final Slider slider = createSlider();
        prepareConfirmAndCancelButton( bottomPane, slider );
        prepareTop( topPane, slider,goldCost);
        centerPane.getChildren().add(creature);
        centerPane.getChildren()
            .add( slider );
        centerPane.setAlignment(Pos.CENTER);
        dialog.showAndWait();

        return (int)slider.getValue();
    }

    private void prepareTop( final HBox aTopPane, final Slider aSlider, int goldCost)
    {
        // TODO creature cops should be visible here

        aTopPane.getChildren()
            .add( new Label( "Single Cost: " + goldCost ) );
        final Label slideValueLabel = new Label( "0");
        final Label amountValueLabel= new Label("0");
        aSlider.valueProperty()
            .addListener(
                ( slider, aOld, aNew ) ->{
                    slideValueLabel.setText( String.valueOf( aNew.intValue() ) );
                    amountValueLabel.setText(String.valueOf(goldCost*aNew.intValue()));
                } );
        aTopPane.getChildren()
            .add( slideValueLabel );
        aTopPane.getChildren()
            .add(new Label("Purchase Cost: "));
        aTopPane.getChildren().add(amountValueLabel);
    }

    private Stage prepareWindow( final Pane aCenter, final Pane aBottom, final Pane aTop )
    {
        dialog = new Stage();
        final BorderPane pane = new BorderPane();
        final Scene scene = new Scene( pane, 500, 300 );
        scene.getStylesheets()
            .add( "fxml/main.css" );
        dialog.setScene( scene );
        dialog.initOwner( this.getScene()
            .getWindow() );
        dialog.initModality( Modality.APPLICATION_MODAL );
        dialog.setTitle( "Buying " + creatureName );

        pane.setTop( aTop );
        pane.setCenter( aCenter );
        pane.setBottom( aBottom );
        return dialog;
    }

    private void prepareConfirmAndCancelButton( final HBox aBottomPane, final Slider aSlider )
    {
        final Button okButton = new Button( "OK" );
        aBottomPane.setAlignment( Pos.CENTER );
        okButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> dialog.close() );
        okButton.setPrefWidth( 200 );
        final Button cancelButton = new Button( "CLOSE" );
        cancelButton.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
            aSlider.setValue( 0 );
            dialog.close();
        } );
        cancelButton.setPrefWidth( 200 );
        HBox.setHgrow( okButton, Priority.ALWAYS );
        HBox.setHgrow( cancelButton, Priority.ALWAYS );
        aBottomPane.getChildren()
            .add( okButton );
        aBottomPane.getChildren()
            .add( cancelButton );
    }

    private Slider createSlider()
    {
        final Slider slider = new Slider();
        slider.setMin( 0 );
        slider.setMax( 100 );
        slider.setValue( 0 );
        slider.setShowTickLabels( true );
        slider.setShowTickMarks( true );
        slider.setMajorTickUnit( 10 );
        slider.setMinorTickCount( 5 );
        slider.setBlockIncrement( 10 );
        return slider;
    }
}
