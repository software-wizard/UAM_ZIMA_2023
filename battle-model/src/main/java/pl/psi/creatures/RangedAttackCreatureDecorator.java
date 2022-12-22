package pl.psi.creatures;

import lombok.Getter;

@Getter
public class RangedAttackCreatureDecorator extends AbstractCreatureDecorator{

    private final Creature decorated;
    private final int maxShots;
    private int actualShotsAmount;
    private boolean inMelee = false;
    private final int effectiveAttackRange = 10;
    private final double MELEE_PENALTY = 0.5;
    private final double FAR_RANGE_PENALTY = 0.5;
    private double range_bonus;
    private final DamageCalculatorIf normalRangeDamageCalculator;
    private final DamageCalculatorIf farRangeDamageCalculator = new ReduceDamageCalculator(FAR_RANGE_PENALTY);
    private final DamageCalculatorIf meleeDamageCalculator = new ReduceDamageCalculator(MELEE_PENALTY);

    RangedAttackCreatureDecorator(Creature aDecorated, final int aShots){
        super(aDecorated);
        decorated = aDecorated;
        maxShots = aShots;
        actualShotsAmount = maxShots;
        normalRangeDamageCalculator = decorated.getCalculator();
    }

    public boolean shotsAvailable(){
        return actualShotsAmount > 0;
    }

    public boolean canShoot(){
        return !inMelee && shotsAvailable();
    }

    @Override
    public void attack(Creature aDefender){
        if(canShoot() && !inMelee){
            doRangeAttack(aDefender);
        }
        else{
            decorated.setCalculator(meleeDamageCalculator);
            decorated.attack(aDefender);
        }
    }

    public void doRangeAttack(Creature aDefender){
//        if(distanceToDefender > 10){
//            decorated.setCalculator(farRangeDamageCalculator);
//        }
//        else{
//            decorated.setCalculator(normalRangeDamageCalculator);
//        }
        final int damage = decorated.getCalculator().calculateDamage(decorated, aDefender);
        decorated.applyDamage(aDefender, damage);
        actualShotsAmount -= 1;
    }

    @Override
    public void approachedInMelee(){
        inMelee = true;
    }

    public void escapedFromMelee(){
        inMelee = false;
        decorated.setCalculator(normalRangeDamageCalculator);
    }

    public void addArcheryBonus(double bonus){
        range_bonus = bonus;
    }
}
