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
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FirewallTest {
    @Test
    void ShouldDamageIfCreatureWalksOnIt(){

        Creature attacker = new Creature(CreatureStats.builder().maxHp(100).build(), calc);
        Firewall firewall = new Firewall();
        firewall.;

        ge.move(point);

        assertThat(attacker.getCurrentHp()).isEqualTo(80); // dmg = 10 + (1 * 10)
    }

    @Test
    void ShouldBeRemovedAfterTwoTurns(){
        Firewall obstacle = new Firewall(100);
        PropertyChangeSupport support = new PropertyChangeSupport(this);

        support.firePropertyChange(GameEngine.END_OF_TURN, 0, 1);

        obstacle.propertyChange();
        obstacle.propertyChange();

        assertThat(field1.isBlocked).isEqualTo(False);
        assertThat(field2.isBlocked).isEqualTo(False);
    }

}