package pl.psi.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.psi.EconomyEngine;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.EconomyCreature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.hero.EconomyHero;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;

public class EcoController implements PropertyChangeListener {
    private final EconomyEngine economyEngine;
    @FXML
    HBox heroStateHBox;
    @FXML
    HBox shopsBox;
    @FXML
    Button readyButton;
    @FXML
    Label playerLabel;
    @FXML
    Label currentGoldLabel;
    @FXML
    Label roundNumberLabel;

    @FXML
    ComboBox<String> chooseMapComboBox;

    public EcoController(final EconomyHero aHero1, final EconomyHero aHero2) {
        economyEngine = new EconomyEngine(aHero1, aHero2);
    }

    @FXML
    void initialize() {
        refreshGui();
        economyEngine.addObserver(EconomyEngine.ACTIVE_HERO_CHANGED, this);
        economyEngine.addObserver(EconomyEngine.HERO_BOUGHT_CREATURE, this);
        economyEngine.addObserver(EconomyEngine.NEXT_ROUND, this);


        readyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (economyEngine.getRoundNumber() < 4) {
                economyEngine.pass();
            } else {
                goToBattle();
            }
        });
    }

    private void goToBattle() {
        EcoBattleConverter.startBattle(economyEngine.getPlayer1(), economyEngine.getPlayer2());
    }

    void refreshGui() {
        playerLabel.setText(economyEngine.getActiveHero()
                .toString());
        currentGoldLabel.setText(String.valueOf(economyEngine.getActiveHero()
                .getGold()));
        roundNumberLabel.setText(String.valueOf(economyEngine.getRoundNumber()));
        shopsBox.getChildren()
                .clear();
        heroStateHBox.getChildren()
                .clear();
        chooseMapComboBox.getItems().clear();

        final CreatureFactory factory = CreatureFactory.fractionEconomyFactory(economyEngine.getPlayer1().getFraction());
        final VBox creatureShop = new VBox();
        for (int i = 1; i < 8; i++) {
            creatureShop.getChildren()
                    .add(new CreatureButton(this, factory, false, i));
            creatureShop.getChildren()
                    .add(new CreatureButton(this, factory, true, i));
        }
        shopsBox.getChildren()
                .add(creatureShop);

        final VBox creaturesBox = new VBox();
        economyEngine.getActiveHero()
                .getCreatures()
                .forEach(c -> {
                    final HBox tempHbox = new HBox();
                    tempHbox.getChildren()
                            .add(new Label(String.valueOf(c.getAmount())));
                    tempHbox.getChildren()
                            .add(new Label(c.getName()));
                    creaturesBox.getChildren()
                            .add(tempHbox);
                });
        heroStateHBox.getChildren()
                .add(creaturesBox);

        chooseMapComboBox.setItems(generateMapSelection());
        chooseMapComboBox.getSelectionModel().selectPrevious();
        chooseMapComboBox.getItems();
    }

    void buy(final EconomyCreature aCreature) {
        economyEngine.buy(aCreature);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent aPropertyChangeEvent) {
        refreshGui();
    };

    /**
     * Metoda iteruje przez pliki z mapami zawarte w folderze po czym dodaje je do listy
     * @return zwraca listę z nazwami map
     */
    private ObservableList<String> generateMapSelection() {
        ObservableList<String> maps = FXCollections.observableArrayList();

        String folderPath = "economy-gui/src/main/java/pl/psi/gui/mapgenerator/maps_json";
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null){
            for (File file : listOfFiles){
                String name = file.getName();
                maps.add(name.substring(0, name.length() - 5));
            }
        }

        return maps;
    }
}
