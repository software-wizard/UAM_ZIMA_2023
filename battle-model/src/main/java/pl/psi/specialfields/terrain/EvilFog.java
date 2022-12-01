package pl.psi.specialfields.terrain;

import pl.psi.creatures.Creature;
import pl.psi.specialfields.BuffInterface;
import pl.psi.specialfields.Field;

import java.util.List;

public class EvilFog extends Field implements BuffInterface {
    // Stworzenia ze złych frakcji zyskują 1 punkt morale, a dobre tracą go.

    @Override
    public void handleEffect(List<Creature> creatures) {
        super.handleEffect(creatures);
    }

    @Override
    public void buffCreature(Creature creature) {

    }
}
