package pl.psi.specialfields.terrain;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.specialfields.terrain.CloverField;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CloverFieldTest {

    @Test
    void shouldGiveTwoLuckToAllNeutrallyAlignedCreatures() {
        // zakładam że kreatury ani dobre ani złe są tworzone defaultowo jako neutral
        CloverField cloverField = new CloverField();
        Creature c1 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(0).build();
        Creature c2 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(-1).build();
        Creature c3 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(1).build();
        Creature c4 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(2).build();
        List<Creature> creatures = List.of(c1, c2, c3, c4);

        cloverField.handleEffect(creatures);

        assertThat(c1.getLuck()).isEqualTo(2);
        assertThat(c2.getLuck()).isEqualTo(1);
        assertThat(c3.getLuck()).isEqualTo(3);
        assertThat(c4.getLuck()).isEqualTo(3);
    }

    @Test
    void shouldOnlyGiveTwoLuckToNeutrallyAllignedCreatures() {
        CloverField cloverField = new CloverField();
        Creature c1 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(0).alignment(NEUTRAL).build();
        Creature c2 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(-1).alignment(NEUTRAL).build();
        Creature c3 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(3).alignment(EVIL).build();
        Creature c4 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(2).alignment(GOOD).build();
        List<Creature> creatures = List.of(c1, c2, c3, c4);

        cloverField.handleEffect(creatures);

        assertThat(c1.getLuck()).isEqualTo(2);
        assertThat(c2.getLuck()).isEqualTo(1);
        assertThat(c3.getLuck()).isEqualTo(3);
        assertThat(c4.getLuck()).isEqualTo(2);
    }

    @Test
    void shouldGiveTwoLuckToNeutrallyAlignedCreature() {
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(0).build();

        cloverField.handleEffect(List.of(creature1));

        assertThat(creature1.getLuck()).isEqualTo(2);
    }

    @Test
    void shouldNotGiveTwoLuckToEvilAlignedCreature() {
        CloverField cloverField = new CloverField();
        Creature creature = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(-1).alignment(EVIL).build();

        cloverField.handleEffect(List.of(creature));

        assertThat(creature.getLuck()).isEqualTo(-1);
    }

    @Test
    void shouldNotGiveTwoLuckToGoodAlignedCreature() {
        CloverField cloverField = new CloverField();
        Creature creature = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(0).alignment(GOOD).build();

        cloverField.handleEffect(List.of(creature));

        assertThat(creature.getLuck()).isEqualTo(0);
    }

    @Test
    void shouldNotGiveLuckGreaterThan3() {
        CloverField cloverField = new CloverField();
        Creature creature = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(2).alignment(NEUTRAL).build();

        cloverField.handleEffect(List.of(creature));

        assertThat(creature.getLuck()).isEqualTo(3);
    }


}