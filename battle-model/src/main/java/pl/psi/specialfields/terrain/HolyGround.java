package pl.psi.specialfields.terrain;

import pl.psi.creatures.Creature;
import pl.psi.fields.TerrainTypes;
import pl.psi.specialfields.BuffInterface;
import pl.psi.specialfields.Field;
import pl.psi.specialfields.FieldInterface;

public class HolyGround extends Field{
    // Stworzenia z dobrych frakcji zyskują 1 punkt morale, a złe tracą go.

    private final String type = TerrainTypes.HOLY_GROUND;
    public HolyGround() {}

    @Override
    public void buffCreature(Creature creature) {

    }
    @Override
    public String getType() {
        return type;
    }
}
