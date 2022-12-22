package pl.psi.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.psi.Hero;
import pl.psi.HeroStats;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.EconomyArtifactFactory;
import pl.psi.creatures.Creature;
import pl.psi.gui.MainBattleController;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.hero.EconomyHero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EcoBattleConverter
{

    public static void startBattle( final EconomyHero aPlayer1, final EconomyHero aPlayer2 )
    {
        Scene scene = null;
        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( EcoBattleConverter.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( convert( aPlayer1 ), convert( aPlayer2 ) ) );
            scene = new Scene( loader.load() );
            final Stage aStage = new Stage();
            aStage.setScene( scene );
            aStage.setX( 5 );
            aStage.setY( 5 );
            aStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    public static Hero convert( final EconomyHero aPlayer1 )
    {
        final List< Creature > creatures = new ArrayList<>();
        final NecropolisFactory factory = new NecropolisFactory();
        final List< EconomyArtifact > artifacts = new ArrayList<>();
        final EconomyArtifactFactory artifactFactory = new EconomyArtifactFactory();
        artifacts.add(artifactFactory.create("right hand","Centaur's Axe"));
        int allAttack = 0, allArmor = 0, allSpellPower = 0, allKnowledge = 0;
        for(EconomyArtifact i : artifacts) {
            allAttack += i.getAttack();
            allArmor += i.getArmor();
            allSpellPower += i.getSpellPower();
            allKnowledge += i.getSpellPower();
        }
       final HeroStats heroStats = HeroStats.builder()
               .attack(allAttack)
               .armor(allArmor)
               .spellPower(allSpellPower)
               .knowledge(allKnowledge)
               .build();

        aPlayer1.getCreatures()
            .forEach( ecoCreature -> creatures.add( factory.create( ecoCreature.isUpgraded(),
                ecoCreature.getTier(), ecoCreature.getAmount() ) ) );
        return new Hero( creatures, heroStats);
    }
}
