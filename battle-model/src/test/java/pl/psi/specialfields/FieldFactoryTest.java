package pl.psi.specialfields;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static pl.psi.fields.TerrainTypes.*;

class FieldFactoryTest {
    @Test
    void isCreatingFieldsProperly(){
        String[] fieldTypes = {CLOVER_FIELD, CURSED_GROUND, EVIL_FOG, HOLY_GROUND};
        FieldFactory fieldFactory = new FieldFactory();
        Field cloverField = fieldFactory.getType(fieldTypes[0]);
        Field cursedGround = fieldFactory.getType(fieldTypes[1]);
        Field evilFog = fieldFactory.getType(fieldTypes[2]);
        Field holyGround = fieldFactory.getType(fieldTypes[3]);

        Assertions.assertEquals(CLOVER_FIELD, cloverField.getType());
        Assertions.assertEquals(CURSED_GROUND, cursedGround.getType());
        Assertions.assertEquals(EVIL_FOG, evilFog.getType());
        Assertions.assertEquals(HOLY_GROUND, holyGround.getType());
    }

}