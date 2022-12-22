package pl.psi.creatures;

import com.google.common.collect.Range;

import java.util.List;

public interface CreatureStatisticIf {
    String getName();
    int getAttack();
    int getArmor();
    int getMaxHp();
    int getMoveRange();
    Range< Integer > getDamage();
    int getTier();
    String getDescription();
    boolean isUpgraded();
    List<CreatureSpecialAttributeTypes> getSpecialAttributes();
}
