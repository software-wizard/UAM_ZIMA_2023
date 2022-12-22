package pl.psi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.creatures.CreatureStatisticIf;

import java.util.*;

@Getter
public class EffectHolder {
    private final Set<EffectAbstract> activeEffects = new HashSet<>();
    private final Set<EffectTimePair> effectsTimeTable = new HashSet<>();

    private final CreatureStatisticIf baseStats;
    private CreatureStatisticIf stats;

    public EffectHolder(CreatureStatisticIf baseStats) {
        this.baseStats = baseStats;
        this.stats = baseStats;
    }

    public void addEffect(EffectAbstract effectToAdd)
    {
        if(!activeEffects.contains(effectToAdd))
        {
            var results = effectToAdd.applyEffect(stats, activeEffects,baseStats);

            stats = results.getStatsToSwitch();
            activeEffects.clear();
            activeEffects.addAll(results.getActiveEffects());
            synchronizeEffectsTime();

            if (effectToAdd.getDuration()!=0)
            {
                activeEffects.add(effectToAdd);
                effectsTimeTable.add(new EffectTimePair(effectToAdd, effectToAdd.getDuration()));
            }

        }
    }

    private void synchronizeEffectsTime(){
        effectsTimeTable.removeIf(effectTimePair -> !activeEffects.contains(effectTimePair.activeEffect));


    }

    public void updateEffectsTime(){
        for ( var effectTimePair : effectsTimeTable)
        {
            effectTimePair.timeToEnd--;
            if (effectTimePair.timeToEnd == 0)
            {
                var result = effectTimePair.activeEffect.unapplyEffect(stats, activeEffects);
                stats = result.getStatsToSwitch();
                effectsTimeTable.remove(effectTimePair);
                activeEffects.remove(effectTimePair.activeEffect);
            }
        }
    }

    @AllArgsConstructor
    private final static class EffectTimePair implements Comparable<EffectTimePair>{
        private final EffectAbstract activeEffect;
        private int timeToEnd;


        @Override
        public int compareTo(EffectTimePair o) {
            return this.timeToEnd-o.timeToEnd;
        }
    }
}
