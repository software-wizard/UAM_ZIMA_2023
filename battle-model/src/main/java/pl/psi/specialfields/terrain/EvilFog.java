package pl.psi.specialfields.terrain;

import pl.psi.creatures.Creature;
import pl.psi.fields.TerrainTypes;
import pl.psi.specialfields.Field;
import pl.psi.specialfields.FieldInterface;

public class EvilFog extends Field implements FieldInterface {
    // Stworzenia ze złych frakcji zyskują 1 punkt morale, a dobre tracą go.

    private final String type = TerrainTypes.EVIL_FOG;
    public EvilFog(){
    }

    @Override
    public void buffCreature(Creature aCreature) {

    }

    @Override
    public String getType() {
        return type;
    }
}
