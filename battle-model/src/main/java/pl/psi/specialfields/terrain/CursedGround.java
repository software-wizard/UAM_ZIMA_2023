package pl.psi.specialfields.terrain;

import pl.psi.creatures.Creature;
import pl.psi.fields.TerrainTypes;
import pl.psi.specialfields.Field;
import pl.psi.specialfields.FieldInterface;

public class CursedGround extends Field{

    // Na tej ziemi można rzucać tylko zaklęcia 1. poziomu. Wszelkie premie oraz kary do morale i szczęścia nie działają

    private final String type = TerrainTypes.CURSED_GROUND;

    public CursedGround() {
    }

    @Override
    public void buffCreature(Creature aCreature) {
        //aCreature.reset(morale, luck);
    }

    public void debuffSpellMasteries(){

    }

    @Override
    public String getType() {
        return type;
    }
}
