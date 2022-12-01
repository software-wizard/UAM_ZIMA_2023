package pl.psi.specialfields.obstacles;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.creatures.DamageCalculatorIf;
import pl.psi.specialfields.Field;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FirewallTest {

    // Hero będzie posiadać podstawowe umiejętności (Attack, Defense, Power, Knowledge)?
    // Czy dodajemy secondary skills?



    @Test
    void ShouldBlockThreeFields(){
        Field field1 = new Field(1, 1);
        Field field2 = new Field(2, 1);
        Field field3 = new Field(3, 1);

        Hero hero = new Hero(aCreatures1);

        Point point = new Point(2, 1);
        Firewall obstacle = new Firewall();
        obstacle.setOnField(0, 1, point);

        assertThat(field1.isBlocked()).isEqualTo(True);
        assertThat(field2.isBlocked()).isEqualTo(True);
        assertThat(field3.isBlocked()).isEqualTo(True);
    }


    @Test
    void ShouldDamageIfCreatureWalksOnIt(){

        Creature attacker = new Creature(CreatureStats.builder().maxHp(100).build(), calc);
        Creature defender = new Creature(CreatureStats.builder().maxHp(100).build(), calc);

        List<Creature> aCreatures1 = new ArrayList<>();
        List<Creature> aCreatures2 = new ArrayList<>();

        aCreatures1.add(attacker);
        aCreatures2.add(defender);

        Hero hero1 = new Hero(aCreatures1); // power = 1
        Hero hero2 = new Hero(aCreatures2);

        GameEngine ge = new GameEngine(hero1,hero2);

        Point point = new Point(3, 3);

        Firewall firewall = new Firewall();
        firewall.setOnField(point);

        ge.move(point);

        assertThat(attacker.getCurrentHp()).isEqualTo(80); // dmg = 10 + (1 * 10)
    }

    @Test
    void ShouldBeRemovedAfterTwoTurns(){
        Field field1 = new Field(1, 1);
        Field field2 = new Field(2, 1);

        Firewall obstacle = new Firewall();
        firewall.setOnField(1, 1, point);

        assertThat(field1.isBlocked).isEqualTo(True);
        assertThat(field2.isBlocked).isEqualTo(True);

        PropertyChangeEvent event = GameEngine.END_OF_TURN;

        obstacle.propertyChange(event);
        obstacle.propertyChange(event);

        assertThat(field1.isBlocked).isEqualTo(False);
        assertThat(field2.isBlocked).isEqualTo(False);
    }

}