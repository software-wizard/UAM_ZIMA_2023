package pl.psi.creatures;

import lombok.Getter;
import pl.psi.EffectAbstract;

import java.util.Collection;

@Getter
public class PermanentStatsBuffEffect extends EffectAbstract {
    private final String statisticToRevert;
    private final CreatureStatisticIf statsToAdd;
    private final boolean isNegative;

    public PermanentStatsBuffEffect(String aName, String aStat,
                                    CreatureStatisticIf aStatsToAdd, boolean aIsNegative) {
        super(aName, 0, aStat);
        statisticToRevert = aStat;
        statsToAdd = aStatsToAdd;
        isNegative = aIsNegative;
    }

    public Result applyEffect(CreatureStatisticIf currentStats, Collection<EffectAbstract> activeEffects,
                                CreatureStatisticIf baseStats){

        CreatureStatisticIf newStats = new CreatureStats(
                currentStats.getName(),
                currentStats.getAttack() + statsToAdd.getAttack(),
                currentStats.getArmor() + statsToAdd.getArmor(),
                currentStats.getMaxHp() + statsToAdd.getMaxHp(),
                currentStats.getMoveRange(),
                currentStats.getDamage(),
                currentStats.getTier(),
                currentStats.getDescription(),
                currentStats.getSpecialAttributes(),
                currentStats.isUpgraded());

        return new Result(newStats, activeEffects);

    }

    public Result unapplyEffect(CreatureStatisticIf currentStats, Collection<EffectAbstract> activeEffects){
        return new Result(currentStats, activeEffects);
    }
    
    public boolean isNegative(){
        return this.isNegative;
    }



}
