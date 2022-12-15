package pl.psi.specialfields.obstacles;

import org.junit.jupiter.api.Test;
import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.creatures.DamageCalculatorIf;
import pl.psi.specialfields.Field;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QuicksandTest {

    /*@Test
    void CreatureShouldNotBeAbleToMoveAfterWalkingOnIt(){

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

        Quicksand quicksand = new Quicksand();
        quicksand.setOnField(point);

        ge.move(point);

        assertThat(attacker.getMoveRange()).isEqualTo(0);
    }

    @Test
    void ShouldBecomeVisibleAfterCreatureWalksOnIt(){

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

        Quicksand quicksand = new Quicksand();
        quicksand.setOnField(point);

        ge.move(point);

        assertThat(quicksand.isVisible()).isEqualTo(True);
    }*/

}