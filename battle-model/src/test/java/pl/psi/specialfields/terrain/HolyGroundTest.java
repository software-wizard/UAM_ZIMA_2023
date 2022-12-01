package pl.psi.specialfields.terrain;

import org.junit.jupiter.api.Test;
import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureStats;
import pl.psi.specialfields.terrain.EvilFog;

class HolyGroundTest {

    @Test
    void shouldGiveOneMoraleToGoodCreature() {
        EvilFog evilFog = new EvilFog();
        Creature goodCreature = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(1).alignment(GOOD).build();

        evilFog.buffCreature(goodCreature);

        assertEquals(2, goodCreature.getMorale());
    }

    @Test
    void shouldNotGiveOneMoraleToEvilCreature() {
        EvilFog evilFog = new EvilFog();
        Creature evilCreature = new Creature.Builder().statistic(CreatureStats.builder().build()).morale(1).alignment(EVIL).build();

        evilFog.buffCreature(evilCreature);

        assertEquals(0, evilCreature.getMorale());
    }

}