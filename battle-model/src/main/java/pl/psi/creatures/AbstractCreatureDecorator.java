package pl.psi.creatures;

import com.google.common.collect.Range;

import java.beans.PropertyChangeEvent;

public abstract class AbstractCreatureDecorator extends Creature {
    private final Creature decorated;

    protected AbstractCreatureDecorator(Creature aCreature) {
        decorated = aCreature;
    }

    @Override
    public CreatureStatisticIf getStats(){
        return decorated.getStats();
    }

    @Override
    public int getAmount(){
        return decorated.getAmount();
    }

    @Override
    public void setAmount(int aAmount){
        decorated.setAmount(aAmount);
    }

    @Override
    public int getCurrentHp(){
        return decorated.getCurrentHp();
    }

    @Override
    public void setCurrentHp(final int hp){
        decorated.setCurrentHp(hp);
    }

    @Override
    public void attack(final Creature aDefender){
        decorated.attack(aDefender);
    }

    @Override
    public boolean isAlive(){
        return decorated.isAlive();
    }

    @Override
    protected void applyDamage(final Creature aDefender, final int aDamage){
        decorated.applyDamage(aDefender, aDamage);
    }

    @Override
    protected boolean canCounterAttack(final Creature aDefender){
        return decorated.canCounterAttack(aDefender);
    }

    @Override
    protected void counterAttack(final Creature aAttacker){
        decorated.counterAttack(aAttacker);
    }

    @Override
    Range<Integer> getDamage() {
        return decorated.getDamage();
    }

    @Override
    int getAttack() {
        return decorated.getAttack();
    }

    @Override
    int getArmor() {
        return decorated.getArmor();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        decorated.propertyChange(evt);
    }

    @Override
    protected void restoreCurrentHpToMax() {
        decorated.restoreCurrentHpToMax();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    @Override
    public int getMoveRange() {
        return decorated.getMoveRange();
    }

}
