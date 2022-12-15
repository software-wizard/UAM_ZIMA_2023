package pl.psi.specialfields;

import pl.psi.fields.TerrainTypes;
import pl.psi.specialfields.terrain.CloverField;
import pl.psi.specialfields.terrain.CursedGround;
import pl.psi.specialfields.terrain.EvilFog;
import pl.psi.specialfields.terrain.HolyGround;

import static pl.psi.fields.TerrainTypes.*;

public class FieldFactory {
    public Field getType(String fieldType){
        switch (fieldType) {
            case CLOVER_FIELD:
                return new CloverField();
            case CURSED_GROUND:
                return new CursedGround();
            case EVIL_FOG:
                return new EvilFog();
            case HOLY_GROUND:
                return new HolyGround();
        }
        return null;
    }
}
