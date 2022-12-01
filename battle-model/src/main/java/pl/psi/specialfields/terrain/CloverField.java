package pl.psi.specialfields.terrain;

import pl.psi.creatures.Creature;
import pl.psi.specialfields.BuffInterface;
import pl.psi.specialfields.Field;

import java.util.List;

public class CloverField extends Field implements BuffInterface {

    // Wszystkie jednostki z neutralnych frakcji zyskują 2 punkty szczęścia.
    // Szczęśie może mieć wartość (-3; 3)

    public CloverField(){
        super();
    }

    @Override
    public void buffCreature(Creature creature) {

    }

}
