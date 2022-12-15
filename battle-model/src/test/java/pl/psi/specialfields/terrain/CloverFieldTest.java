package pl.psi.specialfields.terrain;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.specialfields.Field;
import pl.psi.specialfields.terrain.CloverField;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CloverFieldTest {

    @Test
    void shouldGiveTwoLuckToNeutrallyAlignedCreature() {
        CloverField cloverField = new CloverField();
        Creature creature1 = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(0).build();

        cloverField.buffCreature(creature1);

        assertThat(creature1.getLuck()).isEqualTo(2);
    }

    @Test
    void shouldNotGiveTwoLuckToEvilAlignedCreature() {
        CloverField cloverField = new CloverField();
        Creature creature = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(-1).alignment(EVIL).build();

        cloverField.buffCreature(creature);

        assertThat(creature.getLuck()).isEqualTo(-1);
    }

    @Test
    void shouldNotGiveTwoLuckToGoodAlignedCreature() {
        CloverField cloverField = new CloverField();
        Creature creature = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(0).alignment(GOOD).build();

        cloverField.buffCreature(creature);

        assertThat(creature.getLuck()).isEqualTo(0);
    }

    @Test
    void shouldNotGiveLuckGreaterThan3() {
        CloverField cloverField = new CloverField();
        Creature creature = new Creature.Builder().statistic(CreatureStats.builder().build()).luck(2).alignment(NEUTRAL).build();

        cloverField.buffCreature(creature);

        assertThat(creature.getLuck()).isEqualTo(3);
    }


}