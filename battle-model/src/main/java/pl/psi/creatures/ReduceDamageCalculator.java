package pl.psi.creatures;

import java.util.Random;

public class ReduceDamageCalculator extends AbstractCalculateDamageStrategy {
    private final double factor;

    public ReduceDamageCalculator(final double aFactor)
    {
        super( new Random() );
        factor = aFactor;
    }

    @Override
    public int calculateDamage(final Creature aAttacker, final Creature aDefender){
        return (int) (super.calculateDamage(aAttacker, aDefender)*factor);
    }
}
