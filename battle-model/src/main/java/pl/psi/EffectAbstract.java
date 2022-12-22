package pl.psi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.psi.creatures.CreatureStatisticIf;

import java.util.Collection;
import java.util.Objects;

/**

 **/

@Getter
@AllArgsConstructor
public abstract class EffectAbstract {
    private final String name;
    private final int duration;

    private final String modifiedStat;

    public abstract Result applyEffect(CreatureStatisticIf creatureStatsCurrent, Collection<EffectAbstract> activeEffects, CreatureStatisticIf baseStats);
    public abstract Result unapplyEffect(CreatureStatisticIf creatureStatsCurrent, Collection<EffectAbstract> activeEffects);

    public abstract boolean isNegative();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffectAbstract that = (EffectAbstract) o;
        return duration == that.duration && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }

    @Getter
    @AllArgsConstructor
    public final static class Result{
        private final CreatureStatisticIf statsToSwitch;
        private final Collection<EffectAbstract> activeEffects;

    }


}
