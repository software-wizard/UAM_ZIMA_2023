package pl.psi.specialfields.terrain;

import pl.psi.creatures.Creature;
import pl.psi.creatures.CreatureAlignmentTypes;
import pl.psi.creatures.CreatureBuffValue;
import pl.psi.fields.TerrainTypes;
import pl.psi.specialfields.Field;

public class CloverField extends Field{

    // Wszystkie jednostki z neutralnych frakcji zyskują 2 punkty szczęścia.
    // Szczęśie może mieć wartość (-3; 3)

    private final String type = TerrainTypes.CLOVER_FIELD;

    private final CreatureBuffValue buffValue = new CreatureBuffValue("luck", 2, CreatureAlignmentTypes.NEUTRAL);

    public CloverField() {
    }


    @Override
    public String getType() {
        return type;
    }
}
