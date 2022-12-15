package pl.psi.gui.mapgenerator;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.psi.Board;
import pl.psi.Point;
import pl.psi.fields.ObstacleTypes;
import pl.psi.fields.TerrainTypes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapEditorController {

    @FXML
    HBox tileOptionsBox;

    @FXML
    VBox terrainList;

    @FXML
    VBox obstacleList;

    @FXML
    HBox mapGridBox;

    @FXML
    private GridPane gridMap;
    @FXML
    private Button readyButton;

    @FXML
    private ImageView selectedFieldSprite;

    @FXML
    private ImageView selectedObstacleSprite;

    // FIELD RADIO BUTTONS
    @FXML
    RadioButton rb_clover_field;
    @FXML
    RadioButton rb_cursed_ground_field;
    @FXML
    RadioButton rb_evil_fog_field;
    @FXML
    RadioButton rb_holy_ground_field;

    // OBSTACLE RADIO BUTTONS
    @FXML
    RadioButton obstacleRbFireWall;
    @FXML
    RadioButton obstacleRbForceField;
    @FXML
    RadioButton obstacleRbLandMine;
    @FXML
    RadioButton obstacleRbQuickSand;

    private String fieldType;

    private boolean isTerrainSelected;

    private Board board;
    final ToggleGroup fieldGroup = new ToggleGroup();

    HashMap<Point, MapField> fieldHashMap = new HashMap<>();

    public MapEditorController() {
    }

    @FXML
    private void initialize() throws IOException {
        fieldType = TerrainTypes.CLOVER_FIELD;
        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> hashMapToList(fieldHashMap));
        prepareTerrainRadioButtons();
        isTerrainSelected = true;
        refreshGui();
        setTerrainImage(TerrainTypes.CLOVER_FIELD);
        setObstacleImage(ObstacleTypes.FIRE_WALL);
    }

    private void refreshGui() {
        radioButtonController();

        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                convertMapToJson(fieldHashMap);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage stage = (Stage) readyButton.getScene().getWindow();
            stage.close();
        });
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                final int x1 = x;
                final int y1 = y;
                MapField mapTile = new MapField("");
                mapTile.setTerrainType(fieldType);

                mapTile.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                    if (isTerrainSelected) {
                        mapTile.setTerrainType(fieldType);
                    } else {
                        mapTile.setObstacleType(fieldType);
                    }
                });
                gridMap.add(mapTile, x, y);
                fieldHashMap.put(new Point(x1, y1), mapTile);
            }
        }
    }

    private void prepareTerrainRadioButtons() {
        // Terrain
        rb_clover_field.setUserData(TerrainTypes.CLOVER_FIELD);
        rb_clover_field.setToggleGroup(fieldGroup);
        rb_clover_field.setSelected(true);

        rb_cursed_ground_field.setUserData(TerrainTypes.CURSED_GROUND);
        rb_cursed_ground_field.setToggleGroup(fieldGroup);

        rb_evil_fog_field.setUserData(TerrainTypes.EVIL_FOG);
        rb_evil_fog_field.setToggleGroup(fieldGroup);

        rb_holy_ground_field.setUserData(TerrainTypes.HOLY_GROUND);
        rb_holy_ground_field.setToggleGroup(fieldGroup);

        // Obstacles
        obstacleRbFireWall.setUserData(ObstacleTypes.FIRE_WALL);
        obstacleRbFireWall.setToggleGroup(fieldGroup);

        obstacleRbForceField.setUserData(ObstacleTypes.FORCE_FIELD);
        obstacleRbForceField.setToggleGroup(fieldGroup);

        obstacleRbLandMine.setUserData(ObstacleTypes.LAND_MINE);
        obstacleRbLandMine.setToggleGroup(fieldGroup);

        obstacleRbQuickSand.setUserData(ObstacleTypes.QUICK_SAND);
        obstacleRbQuickSand.setToggleGroup(fieldGroup);

        fieldGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (fieldGroup.getSelectedToggle() != null) {
                    fieldType = fieldGroup.getSelectedToggle().getUserData().toString();
                }
            }
        });
    }


    void radioButtonController() {
        rb_clover_field.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setTerrainImage(String.valueOf(rb_clover_field.getUserData()));
            isTerrainSelected = true;
        });
        rb_cursed_ground_field.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setTerrainImage(String.valueOf(rb_cursed_ground_field.getUserData()));
            isTerrainSelected = true;
        });
        rb_evil_fog_field.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setTerrainImage(String.valueOf(rb_evil_fog_field.getUserData()));
            isTerrainSelected = true;
        });
        rb_holy_ground_field.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setTerrainImage(String.valueOf(rb_holy_ground_field.getUserData()));
            isTerrainSelected = true;
        });

        obstacleRbFireWall.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setObstacleImage(String.valueOf(obstacleRbFireWall.getUserData()));
            isTerrainSelected = false;
        });
        obstacleRbForceField.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setObstacleImage(String.valueOf(obstacleRbForceField.getUserData()));
            isTerrainSelected = false;
        });
        obstacleRbLandMine.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setObstacleImage(String.valueOf(obstacleRbLandMine.getUserData()));
            isTerrainSelected = false;
        });
        obstacleRbQuickSand.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            setObstacleImage(String.valueOf(obstacleRbQuickSand.getUserData()));
            isTerrainSelected = false;
        });
    }


    private void setTerrainImage(final String img_path) {
        String path = TerrainTypes.TERRAIN_SPRITES_PATH;
        path += img_path;
        path += ".png";
        try {
            selectedFieldSprite.setImage(new Image(new File(path).toURI().toString()));
        } catch (IllegalArgumentException exception) {
            System.out.println("Illegal Argument");
        }
    }

    private void setObstacleImage(final String img_path) {
        String path = ObstacleTypes.OBSTACLE_SPRITES_PATH;
        path += img_path;
        path += ".png";
        try {
            selectedObstacleSprite.setImage(new Image(new File(path).toURI().toString()));
        } catch (IllegalArgumentException exception) {
            System.out.println("Illegal Argument");
        }
    }

    private void convertMapToJson(HashMap<Point, MapField> fieldMap) throws IOException {
        ObjectMapper mapper = new JsonMapper();
        List<BattleMapField> battleField = hashMapToList(fieldMap);
        FileWriter file = new FileWriter("economy-gui/src/main/java/pl/psi/gui/mapgenerator/maps_json/test_converter.json");
        mapper.writeValue(file, battleField);
        file.close();
    }

    private List<BattleMapField> hashMapToList(HashMap<Point, MapField> fieldHashMap) {
        List<BattleMapField> battleMapFieldList = new ArrayList<>();

        for (Point key : fieldHashMap.keySet()) {
            battleMapFieldList.add(new BattleMapField(
                    key.getX(),
                    key.getY(),
                    fieldHashMap.get(key).getTerrainType(),
                    (fieldHashMap.containsKey(key)) ? fieldHashMap.get(key).getObstacleType() : ""
            ));
        }

        return battleMapFieldList;
    }
}
