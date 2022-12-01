package pl.psi.specialfields.terrain;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.specialfields.terrain.EvilFog;

class EvilFogTest {

    @Test
    void shouldGiveOneMoraleToEvilCreature() {
        EvilFog evilFog = new EvilFog();
        Creature evilCreature = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(1).alignment(EVIL).build();

        evilFog.buffCreature(evilCreature);

        assertEquals(2, evilCreature.getMorale());
    }

    @Test
    void shouldNotGiveOneMoraleToGoodCreature() {
        EvilFog evilFog = new EvilFog();
        Creature goodCreature = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(1).alignment(GOOD).build();

        evilFog.buffCreature(goodCreature);

        assertEquals(0, goodCreature.getMorale());
    }

    @Test
    void shouldNotGiveOneMoraleToGoodCreature() {
        EvilFog evilFog = new EvilFog();
        Creature goodCreature = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(0).alignment(GOOD).build();
        Creature goodCreature2 = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(2).alignment(GOOD).build();
        Creature evilCreature = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(0).alignment(EVIL).build();
        Creature evilCreature2 = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(2).alignment(EVIL).build();

        evilFog.handleEffect(goodCreature);

        assertThat(goodCreature.getLuck()).isEqualTo(2);
        assertThat(goodCreature2.getLuck()).isEqualTo(1);
        assertThat(evilCreature.getLuck()).isEqualTo(3);
        assertThat(evilCreature2.getLuck()).isEqualTo(2);
    }

}